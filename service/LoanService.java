package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dao.LoanDAO;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class LoanService {
	
	private static LoanDAO loanDao = new LoanDAO();
	
	public static LoanInfo createLoan(LoanInfo loanInfo, ConnectionDao connDao, String username) {
		return loanDao.createLoan(loanInfo, connDao, username);
	}

	public static LoanInfo fetchLoan(String loanId, ConnectionDao connDao) {
		return loanDao.fetchLoan(loanId, connDao);
	}

	public static int updateLoan(String editLoanId, LoanInfo updateEditedLoan, ConnectionDao connDao) {
		return loanDao.updateLoan(editLoanId, updateEditedLoan, connDao);
	}
	
	public static List<LoanInfo> listAll(ConnectionDao connDao) {
		return loanDao.listAll(connDao);
	}
}
