package com.iiht.evaluation.eloan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.model.LoanInfo;

public class LoanDAO {

	public static final String INS_QRY = "INSERT INTO Loan_Information(username,loan_purpose,loan_amt_requested,loan_applied_date,business_structure,tax_indicator,address_line1,address_line2,city,state,postal_code,phone_number,email_id) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_BY_ID_QRY = "SELECT * FROM Loan_Information WHERE application_number=?";
	public static final String UPD_QRY = "UPDATE Loan_Information SET loan_purpose=?,loan_amt_requested=?,loan_applied_date=?,business_structure=?,tax_indicator=?,address_line1=?,phone_number=?,email_id=? WHERE application_number=?";

	public LoanInfo createLoan(LoanInfo loanInfo, ConnectionDao connDao, String username) {
		int returnValue = 0;
		int idColVar = 0;
		if (loanInfo != null) {
			try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(INS_QRY,
					Statement.RETURN_GENERATED_KEYS)) {

				pst.setString(1, username);
				pst.setString(2, loanInfo.getPurpose());
				pst.setInt(3, loanInfo.getAmtrequest());
				pst.setString(4, loanInfo.getDoa());
				pst.setString(5, loanInfo.getBstructure());
				pst.setString(6, "Y");
				pst.setString(7, loanInfo.getAddress());
				pst.setString(8, loanInfo.getAddress());
				pst.setString(9, "Austin");
				pst.setString(10, "Texas");
				pst.setString(11, "70929");
				pst.setString(12, loanInfo.getMobile());
				pst.setString(13, loanInfo.getEmail());

				returnValue = pst.executeUpdate();

				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					idColVar = rs.getInt(1);
					System.out.println("automatically generated key value = " + idColVar);
					loanInfo.setApplno("" + idColVar);
				}
				rs.close();
				pst.close();

			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}

		if (returnValue > 0) {
			return loanInfo;
		}

		return null;
	}

	public LoanInfo fetchLoan(String loanid, ConnectionDao connDao) {
		LoanInfo loanInfo = new LoanInfo();
		try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(SELECT_BY_ID_QRY)) {

			pst.setString(1, loanid);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				loanInfo.setApplno(rs.getString("application_number"));
				loanInfo.setPurpose(rs.getString("loan_purpose"));
				loanInfo.setAmtrequest(rs.getInt("loan_amt_requested"));
				loanInfo.setDoa(rs.getString("loan_applied_date"));
				loanInfo.setBstructure(rs.getString("business_structure"));
				loanInfo.setAddress(rs.getString("address_line1"));
				loanInfo.setMobile(rs.getString("phone_number"));
				loanInfo.setEmail(rs.getString("email_id"));
			}

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return loanInfo;
	}

	public int updateLoan(String loanid, LoanInfo updateEditedLoan, ConnectionDao connDao) {
		int rs = 0;
		try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(UPD_QRY)) {

			System.out.println("Update from loanDAO :-" + loanid);

			pst.setString(1, updateEditedLoan.getPurpose());
			pst.setInt(2, updateEditedLoan.getAmtrequest());
			pst.setString(3, updateEditedLoan.getDoa());
			pst.setString(4, updateEditedLoan.getBstructure());
			pst.setString(5, "Y");
			pst.setString(6, updateEditedLoan.getAddress());
			pst.setString(7, updateEditedLoan.getMobile());
			pst.setString(8, updateEditedLoan.getEmail());
			pst.setString(9, loanid);

			rs = pst.executeUpdate();

			System.out.println("Update loan response " + rs);

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return rs;
	}

	public static final String SELECT_ALL_QRY = "SELECT application_number,loan_purpose,loan_amt_requested,loan_applied_date,business_structure,phone_number,email_id FROM Loan_Information";

	public List<LoanInfo> listAll(ConnectionDao connDao) {
		List<LoanInfo> allLoansInfo = new ArrayList<>();
		try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(SELECT_ALL_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				LoanInfo loanInfo = new LoanInfo();
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(2));
				loanInfo.setAmtrequest(rs.getInt(3));
				loanInfo.setDoa(rs.getString(4));
				loanInfo.setBstructure(rs.getString(5));
				loanInfo.setMobile(rs.getString(6));
				loanInfo.setEmail(rs.getString(7));

				allLoansInfo.add(loanInfo);
			}

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return allLoansInfo;
	}
}
