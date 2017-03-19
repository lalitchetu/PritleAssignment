package com.pritle.assingment.smartalerts.alert;

import java.util.concurrent.Callable;

public interface SmartAlerts extends Callable<String>{
	public void sendAlert();
	public String getTemplate();
}
