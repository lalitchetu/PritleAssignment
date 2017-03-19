package com.pritle.assingment.smartalerts.service;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pritle.assingment.smartalerts.alert.Alert;
import com.pritle.assingment.smartalerts.alert.EmailAlerts;
import com.pritle.assingment.smartalerts.alert.PushAlerts;
import com.pritle.assingment.smartalerts.alert.SMSAlerts;
import com.pritle.assingment.smartalerts.alert.SmartAlerts;
import com.pritle.assingment.smartalerts.bean.UserAlert;
import com.pritle.assingment.smartalerts.bean.UserProfile;
import com.pritle.assingment.smartalerts.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public void sendAlert(UserProfile user) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		UserAlert userAlert = userDao.getUserAlert(user.getUserID());
		if (userAlert.getAlert() != null && userAlert.getAlert().size() > 0) {
			SmartAlerts alert = null;
			for (String alertType : userAlert.getAlert()) {
				switch (Alert.valueOf(alertType)) {
				case PUSH:
					alert = new PushAlerts(user);
					break;
				case SMS:
					alert = new SMSAlerts(user);
					break;
				case EMAIL:
					alert = new EmailAlerts(user);
					break;
				}
				if (alert != null)
					executor.submit(alert);
			}
		}
		executor.shutdown();
	}
}
