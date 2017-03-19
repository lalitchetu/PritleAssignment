package com.pritle.assingment.smartalerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pritle.assingment.smartalerts.bean.UserAlert;
import com.pritle.assingment.smartalerts.bean.UserProfile;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserProfile findUser(String userID) {
		List<UserProfile> users = jdbcTemplate.query("SELECT * FROM UserProfile WHERE userID = ?",
				(rs, column) -> new UserProfile(rs.getInt("userID"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("dateOfBirth"), rs.getString("profilePicPath"),
						rs.getString("email"), rs.getString("phone")),
				userID);
		return users.isEmpty() ? null : users.get(0);
	}

	public void update(UserProfile user) {
		jdbcTemplate.update(
				"UPDATE UserProfile SET firstName = ?, lastName = ?, dateOfBirth = ?, profilePicPath = ?, email = ?, phone = ?"
						+ "  WHERE userID = ?",
				user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getProfilePicPath(),
				user.getEmail(), user.getPhone(), user.getUserID());
	}

	public UserAlert getUserAlert(int userID) {
		List<String> alert = jdbcTemplate.query(
				"SELECT a.alert FROM UserAlert u, Alert a WHERE u.alertID=a.alertID and u.userID = ?",
				(rs, column) -> rs.getString("alert"), userID);

		UserAlert userAlert = new UserAlert();
		userAlert.setUserID(userID);
		userAlert.setAlert(alert);
		return userAlert;
	}
}
