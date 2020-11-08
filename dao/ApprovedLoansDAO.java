package com.iiht.evaluation.eloan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class ApprovedLoansDAO {
	
	public static final String SELECT_QRY=
		"SELECT application_number,loan_amount_sanctioned,loan_duration,payment_start_date,loan_closure_date,monthly_payment FROM Admin_Data WHERE application_number=? ";
	public static final String INS_QRY=
			"Insert into Admin_Data (application_number,loan_amount_sanctioned,loan_duration,payment_start_date,loan_closure_date,monthly_payment,loan_status_indicator) values ( ?,?,?,?,?,?,?)";
	public static final String UPD_QRY = 
			"UPDATE Admin_Data SET loan_amount_sanctioned=?,loan_duration=?,payment_start_date=?,loan_closure_date=?,monthly_payment=?,loan_status_indicator=? WHERE application_number=?";
	
	public int createLoan(ApprovedLoan approvedLoan, ConnectionDao connDao) {
		int returnValue = 0;
		if (approvedLoan != null) {
			try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(INS_QRY)) {
				
				pst.setInt(1, Integer.parseInt(approvedLoan.getApplno()));
				pst.setInt(2, approvedLoan.getAmotsanctioned());
				pst.setInt(3, approvedLoan.getLoanterm());
				pst.setString(4, approvedLoan.getPsd());
				pst.setString(5, approvedLoan.getLcd());
				pst.setInt(6, approvedLoan.getEmi());
				pst.setString(7, approvedLoan.getStatus());
				
				returnValue = pst.executeUpdate();
				pst.close();

			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return returnValue;
	}

	public ApprovedLoan fetchApprovedLoanForId(String loanId, ConnectionDao connDao) {
		
		ApprovedLoan approvedLoan = null ;
		try(PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(SELECT_QRY)){
			
			pst.setInt(1, Integer.parseInt(loanId));
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				approvedLoan = new ApprovedLoan();
				approvedLoan.setApplno(""+rs.getInt(1));
				approvedLoan.setAmotsanctioned(rs.getInt(2));
				approvedLoan.setLoanterm(rs.getInt(3));
				approvedLoan.setPsd(rs.getString(4));
				approvedLoan.setLcd(rs.getString(5));
				approvedLoan.setEmi(rs.getInt(6));
				
				System.out.println("Fetch admin approved loan:- " + approvedLoan);
			}
						
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		return approvedLoan;
	}

	public int updateLoan(ApprovedLoan approvedLoan, ConnectionDao connDao) {
		int returnValue = 0;
		if (approvedLoan != null) {
			try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(UPD_QRY)) {
				
				System.out.println("Updating loan " + approvedLoan);
				
				pst.setInt(7, Integer.parseInt(approvedLoan.getApplno()));
				pst.setInt(1, approvedLoan.getAmotsanctioned());
				pst.setInt(2, approvedLoan.getLoanterm());
				pst.setString(3, approvedLoan.getPsd());
				pst.setString(4, approvedLoan.getLcd());
				pst.setInt(5, approvedLoan.getEmi());
				pst.setString(6, approvedLoan.getStatus());
				
				
				returnValue = pst.executeUpdate();
				pst.close();

			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return returnValue;
	}
}
