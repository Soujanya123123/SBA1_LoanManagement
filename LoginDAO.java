package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iiht.evaluation.eloan.model.User;

public class LoginDAO {

	public static final String INS_QRY = "INSERT INTO login(username,password,role_id,role_desc) VALUES(?,?,?,?)";
	public static final String SELECT_BY_ID_QRY = "SELECT username,password,role_id FROM login WHERE username=?";

	public User checkIfExistingUserAndCreate(User user, ConnectionDao connDao) {
		// check if username already exists by fetching username from login table
		// if exists return boolean value that user already exists to show error onto
		// the jsp
		try (PreparedStatement selectPS = connDao.getJDBCConnection().prepareStatement(SELECT_BY_ID_QRY)) {

			selectPS.setString(1, user.getUsername());
			ResultSet rs = selectPS.executeQuery();

			if (rs.next()) {
				System.out.println("RegisterUserDao: Check if user exist true:-" + rs.getString("username"));
				if (user.getUsername().equals(rs.getString("username"))) {
					return null;
				}
			} else {
				// insert the user into database if username is new and registering
				try (PreparedStatement insertPS = connDao.getJDBCConnection().prepareStatement(INS_QRY)) {

					insertPS.setString(1, user.getUsername());
					insertPS.setString(2, user.getPassword());
					insertPS.setString(3, "User");
					insertPS.setString(4, "User");

					insertPS.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return user;
	}

	public User addUser(User user) {
		int returnValue = 0;
		if (user != null) {
			try (Connection con = ConnectionDao.getJDBCConnection();
					PreparedStatement pst = con.prepareStatement(INS_QRY)) {

				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getUserType());
				pst.setString(4, user.getUserType());

				returnValue = pst.executeUpdate();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}

		if (returnValue > 0) {
			return user;
		}

		return null;
	}

	public boolean loginUser(User user, ConnectionDao connDao) {
		System.out.println("LoginDao:- user:- " + user.getUsername() + ":" + user.getPassword());
		boolean isLoginSuccess = false;
		try (PreparedStatement pst = connDao.getJDBCConnection().prepareStatement(SELECT_BY_ID_QRY)) {
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				if (user.getPassword().equals(rs.getString("password"))) {
					System.out.println("LoginDao:- found user:- " + user.getUsername());
					isLoginSuccess = true;
				} else
					System.out.println("LoginDao:- DID NOT found user:- " + user.getUsername());
			} else
				System.out.println("LoginDao:- Result set is empty " + user.getUsername());

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return isLoginSuccess;
	}
}
