package com.pritle.assingment.smartalerts.alert;

import com.pritle.assingment.smartalerts.bean.UserProfile;
import com.pritle.assingment.smartalerts.common.SmartAlertCommon;

public class EmailAlerts implements SmartAlerts {
	UserProfile user;
	
	public EmailAlerts(UserProfile user) {
		this.user = user;
	}

	@Override
	public void sendAlert() {
		String emailbodyTemplate = getTemplate();
		sendEmail(SmartAlertCommon.EMAIL_SUBJECT_TEMPLATE, emailbodyTemplate);
	}

	@Override
	public String call() throws Exception {
		sendAlert();
		return null;
	}

	@Override
	public String getTemplate() {
		return SmartAlertCommon.EMAIL_BODY_TEMPLATE;
	}

	public void sendEmail(String subject, String body) {
		System.out.println("Send Email Notification");
		//Send email to user.getEmail() with subject and body
		// Save notification in database
	}
}
