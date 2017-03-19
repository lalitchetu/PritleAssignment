package com.pritle.assingment.smartalerts.alert;

import com.pritle.assingment.smartalerts.bean.UserProfile;
import com.pritle.assingment.smartalerts.common.SmartAlertCommon;

public class PushAlerts implements SmartAlerts{
	UserProfile user;
	
	public PushAlerts(UserProfile user) {
		this.user = user;
	}

	@Override
	public void sendAlert() {
		String pushTemplate = getTemplate();
		sendPushNotification(pushTemplate);
	}

	@Override
	public String call() throws Exception {
		sendAlert();
		return null;
	}

	@Override
	public String getTemplate() {
		return SmartAlertCommon.PUSH_TEMPLATE;
	}

	public void sendPushNotification(String body) {
		System.out.println("Send Push Notification");
		// Send push message to user.getPhone() with text in body
		// Save notification in database
	}
}
