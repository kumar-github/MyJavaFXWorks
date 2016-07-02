package com.tc.app.exchangemonitor.main;

import com.sun.javafx.application.LauncherImpl;
import com.tc.app.exchangemonitor.ExchangeMonitorApplication;
import com.tc.app.exchangemonitor.preloader.ExchangeMonitionApplicationPreloader;

@SuppressWarnings("restriction")
public class ExchangeMonitorApplicationMain
{
	public static void main(String[] args)
	{
		LauncherImpl.launchApplication(ExchangeMonitorApplication.class, ExchangeMonitionApplicationPreloader.class, args);
	}
}