package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.dao.ApprovedLoansDAO;
import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;

public class ApprovedLoanService {
	
	private static ApprovedLoansDAO fetchApprovedLoanDao = new ApprovedLoansDAO();
	
	public static ApprovedLoan fetchApprovedLoanForId(String loanId, ConnectionDao connDao) {
		return fetchApprovedLoanDao.fetchApprovedLoanForId(loanId, connDao);
	}
	
	public static int createLoan(ApprovedLoan approvedLoan, ConnectionDao connDao) {
		ApprovedLoan returnApprovedLoan = fetchApprovedLoanDao.fetchApprovedLoanForId(approvedLoan.getApplno(), connDao);
		if(returnApprovedLoan == null) {
			return fetchApprovedLoanDao.createLoan(approvedLoan, connDao);
		}
		else 
		{
			return fetchApprovedLoanDao.updateLoan(approvedLoan, connDao);
		}
	}
}
