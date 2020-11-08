package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dao.LoginDAO;
import com.iiht.evaluation.eloan.model.User;

public class LoginService {
	
	private static LoginDAO loginDao = new LoginDAO();
	
	public User adminLogin(User user) {
		return loginDao.addUser(user);
	}
	
	public static boolean isValidUser(User user, ConnectionDao connDao) {
		return loginDao.loginUser(user, connDao);
	}
	
	public static User checkIfExistingUserAndCreate(User user, ConnectionDao connDao) {
		return loginDao.checkIfExistingUserAndCreate(user, connDao);
	}

}
