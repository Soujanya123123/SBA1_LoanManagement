package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.iiht.evaluation.eloan.service.LoanService;
import com.iiht.evaluation.eloan.service.LoginService;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	private String username = "";

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

		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName = registernewuser(request, response);
				break;
			case "validate":
				viewName = validate(request, response);
				break;
			case "placeloan":
				viewName = placeloan(request, response);
				break;
			case "application1":
				viewName = application1(request, response);
				break;
			case "editLoanProcess":
				viewName = editLoanProcess(request, response);
				break;
			case "registeruser":
				viewName = registerUser(request, response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;
			case "displaystatus":
				viewName = displaystatus(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
	}

	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String userName = request.getParameter("loginid");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");

		System.out.println("Sowji in username and password:--" + userName + ":" + password);

		if (userName != null && usertype.equalsIgnoreCase("admin")) {
			if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
				return "adminhome1.jsp";
			return "index.jsp";
		}

		if (userName != null && password != null) {
			User user = new User();
			user.setUsername(userName);
			user.setPassword(password);
			user.setUserType(usertype);

			boolean loginSuccess = LoginService.isValidUser(user, connDao);

			System.out.println("loginSuccess " + loginSuccess);

//			if (loginSuccess) {
//				this.username = userName;
//				return "userhome.jsp";
//			} else {
//				return "errorPage.jsp";
//			}
			
			if (loginSuccess) {
				this.username = userName;
				request.setAttribute("username",user.getUsername());
				return "userhome.jsp";
			} else {
				request.setAttribute("userLoginFailed",true);
				return "errorPage.jsp";
			}
		}

		else {
			return "index.jsp";
		}
	}

	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to place the loan information */

		String purpose = request.getParameter("loantype");
		String amtrequest = request.getParameter("loanamtreq");
		String doa = request.getParameter("dateofapplication");
		String bstructure = request.getParameter("bizstructure");
		String bindicator = request.getParameter("billingindicator");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String mobile = request.getParameter("phonenum");

		LoanInfo loanInfo = new LoanInfo("", purpose, Integer.parseInt(amtrequest), doa, bstructure, bindicator,
				address, email, mobile, "");

		LoanInfo createdLoan = LoanService.createLoan(loanInfo, connDao, username);

		System.out.println("Loan created id:-" + createdLoan.getApplno());

		request.setAttribute("createdLoan", createdLoan);
		return "loanDetails.jsp";
	}

	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to display the loan application page */

		return "application.jsp";
	}

	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */

		String editLoanId = request.getParameter("editappid");
		System.out.println("editLoanProcess for editLoanId " + editLoanId);
		LoanInfo editLoan = null;

		if (editLoanId != null) {
			editLoan = LoanService.fetchLoan(editLoanId, connDao);
			System.out.println("fetched editLoan " + editLoan);
			request.setAttribute("editLoan", editLoan);
			return "editloan.jsp";
		} else {
			int loanamtint = 0;
			String loanId = request.getParameter("loanid");
			String purpose = request.getParameter("loantype");
			String amtrequest = request.getParameter("loanamtreq");
			String doa = request.getParameter("dateofapplication");
			String bstructure = request.getParameter("bizstructure");
			String bindicator = request.getParameter("billingindicator");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String mobile = request.getParameter("phonenum");

			if (purpose == null || purpose == "") {
				purpose = editLoan.getPurpose();
			}
			if (amtrequest == null || amtrequest == "") {
				loanamtint = editLoan.getAmtrequest();
			} else {
				loanamtint = Integer.parseInt(amtrequest);
			}

			if (doa == null || doa == "") {
				doa = editLoan.getDoa();
			}
			if (bstructure == null || bstructure == "") {
				bstructure = editLoan.getBstructure();
			}
			if (bindicator == null || bindicator == "") {
				bindicator = editLoan.getBindicator();
			}
			if (address == null || address == "") {
				address = editLoan.getAddress();
			}
			if (email == null || email == "") {
				email = editLoan.getEmail();
			}
			if (mobile == null || mobile == "") {
				mobile = editLoan.getMobile();
			}

			System.out.println("editing for user " + loanId);
			LoanInfo updateEditedLoan = new LoanInfo(loanId, purpose, loanamtint, doa, bstructure, bindicator, address,
					email, mobile, "");
			System.out.println("updating the user:" + updateEditedLoan);

			int createdLoan = LoanService.updateLoan(loanId, updateEditedLoan, connDao);

			return "userhome.jsp";
		}
	}

	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* write the code to redirect page to read the user details */

		// reading the details from user and calling store directly without
		// existing user check which is already handled by 'registernewuser' operation
		// not sure about requirement

		/*
		 * try { String userName = request.getParameter("loginid"); String password =
		 * request.getParameter("password");
		 * 
		 * if (userName != null && password != null) {
		 * 
		 * RegisterUser registerUser = new RegisterUser();
		 * registerUser.setUsername(userName); registerUser.setPassword(password);
		 * 
		 * RegisterUser registeredUser =
		 * RegisterUserService.checkIfExistingUserAndCreate(registerUser, connDao);
		 * 
		 * if (registeredUser==null) { request.setAttribute("userExists",true);
		 * request.getRequestDispatcher("newuserui.jsp").forward(request, response); }
		 * else { request.setAttribute("username",registeredUser.getUsername());
		 * request.getRequestDispatcher("userhome.jsp").forward(request, response); } }
		 * }
		 * 
		 * catch (Throwable theException) { response.sendRedirect("errorPage.jsp"); //
		 * error page } return "index.jsp;";
		 */

		return "newuserui.jsp";
	}

	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * write the code to create the new user account read from user and return to
		 * index page
		 */

		try {
			String userName = request.getParameter("loginid");
			String password = request.getParameter("password");

			if (userName != null && password != null) {

				User user = new User();
				user.setUsername(userName);
				user.setPassword(password);

				User registeredUser = LoginService.checkIfExistingUserAndCreate(user, connDao);

				if (registeredUser == null) {
					request.setAttribute("userExists", true);
					request.getRequestDispatcher("newuserui.jsp").forward(request, response);
				} else {
					request.setAttribute("username", registeredUser.getUsername());
					request.getRequestDispatcher("userhome.jsp").forward(request, response);
				}
			}
		}

		catch (Throwable theException) {
			// show error page when below func's failed
			// 1. to check whether user exists
			// 2. to insert the user into db when user doesn't exists

			response.sendRedirect("errorPage.jsp");
		}
		return "index.jsp";
	}

	private String register(HttpServletRequest request, HttpServletResponse response) {
		/* write the code to redirect to register page */
		return "register.jsp;";
	}

	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * write the code the display the loan status based on the given application
		 * number
		 */

		String loanId = request.getParameter("appid");
		System.out.println("displaystatus for loanid " + loanId);

		LoanInfo fetchedLoan = LoanService.fetchLoan(loanId, connDao);
		request.setAttribute("fetchedLoan", fetchedLoan);

		return "loanDetails.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to editloan page */

		return "editloan.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to trackloan page */

		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write a code to return to trackloan page */
		return "application.jsp";
	}
}