package com.pritle.assingment.smartalerts.alert;

import com.pritle.assingment.smartalerts.bean.UserProfile;
import com.pritle.assingment.smartalerts.common.SmartAlertCommon;

public class SMSAlerts implements SmartAlerts{
	UserProfile user;
	
	public SMSAlerts(UserProfile user) {
		this.user = user;
	}

	@Override
	public void sendAlert() {
		String smsTemplate = getTemplate();
		sendSMSNotification(smsTemplate);
	}

	@Override
	public String call() throws Exception {
		sendAlert();
		return null;
	}

	@Override
	public String getTemplate() {
		return SmartAlertCommon.SMS_TEMPLATE;
	}

	public void sendSMSNotification(String body) {
		System.out.println("Send SMS Notification");
		// Send SMS message to user.getPhone() with text in body
		// Save notification in database
	}
}
