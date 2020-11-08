package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.service.ApprovedLoanService;
import com.iiht.evaluation.eloan.service.LoanService;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;

	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall":
				viewName = listall(request, response);
				break;
			case "process":
				viewName = process(request, response);
				break;
			case "callemi":
				viewName = calemi(request, response);
				break;
			case "updatestatus":
				viewName = updatestatus(request, response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */

		return null;
	}

	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to calculate emi for given applno and display the details */
		String context = request.getParameter("context");
		System.out.println("context equal to" + context);
		
		String appid = request.getParameter("appid");
		System.out.println("calemi for loanid " + appid);
		ApprovedLoan approvedLoan = null;

		if (appid != null) {
			approvedLoan = ApprovedLoanService.fetchApprovedLoanForId(appid, connDao);
			System.out.println("approvedLoan fetched loanId " + approvedLoan);
			approvedLoan.setApplno(appid);
			approvedLoan.setEmi(0);
			approvedLoan.setPsd("");
			request.setAttribute("approvedLoan", approvedLoan);
		} else {
			String loanIdRcvd = request.getParameter("loanid");
			String loan_amount_sanctioned = request.getParameter("loan_amount_sanctioned");
			String loan_duration = request.getParameter("loan_duration");
			String payment_start_date = request.getParameter("payment_start_date");
			
			System.out.println("loanIdRcvd" + loanIdRcvd);
			System.out.println("loan_amount_sanctioned" + loan_amount_sanctioned);
			System.out.println("loan_duration" + loan_duration);
			System.out.println("payment_start_date" + payment_start_date);

			int termPaymentAmount = (Integer.parseInt(loan_amount_sanctioned)) * (1 + 9 / 100)
					^ Integer.parseInt(loan_duration);
			int monthlyPayment = (termPaymentAmount) / Integer.parseInt((loan_duration));

			LocalDate date = LocalDate.parse("2018-11-13");
			LocalDate lcd = date.plusMonths(Long.parseLong(loan_duration));

			String lcdString = lcd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			System.out.println("lcdString " + lcdString);

			// save to db the calculated amount
			System.out.println("approvedLoan with calculated emi loanId " + approvedLoan);
			
			approvedLoan = new ApprovedLoan(loanIdRcvd, Integer.parseInt(loan_amount_sanctioned),
					Integer.parseInt(loan_duration), payment_start_date, lcdString, monthlyPayment);
			request.setAttribute("approvedLoan", approvedLoan);
			

			if (context.equalsIgnoreCase("Approve")) {
				approvedLoan.setStatus("Y");
				System.out.println("Inserting loan " + approvedLoan);
				int success = ApprovedLoanService.createLoan(approvedLoan, connDao);
				return "adminhome1.jsp";
			}else if (context.equalsIgnoreCase("Reject")){
				approvedLoan.setStatus("N");
				System.out.println("Inserting loan " + approvedLoan);
				int success = ApprovedLoanService.createLoan(approvedLoan, connDao);
				return "adminhome1.jsp";
			}

			System.out.println("calemi for termPaymentAmount " + termPaymentAmount);
			System.out.println("calemi for monthlyPayment " + monthlyPayment);
		}

		return "calemi.jsp";
	}

	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* return to process page */
		return "process.jsp";

	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		/* write code to return index page */
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to display all the loans */

		List<LoanInfo> listAllLoansService = LoanService.listAll(connDao);
		request.setAttribute("listAllLoansService", listAllLoansService);
		return "listall.jsp";
	}

}