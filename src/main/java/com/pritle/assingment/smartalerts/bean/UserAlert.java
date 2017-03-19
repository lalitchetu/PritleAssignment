package com.pritle.assingment.smartalerts.bean;

import java.util.List;

public class UserAlert {
	private int userID;
	private List<String> alert;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<String> getAlert() {
		return alert;
	}

	public void setAlert(List<String> alert) {
		this.alert = alert;
	}

}
