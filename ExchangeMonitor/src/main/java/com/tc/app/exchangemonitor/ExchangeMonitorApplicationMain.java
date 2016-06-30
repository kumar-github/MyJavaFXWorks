package com.tc.app.exchangemonitor;

import com.sun.javafx.application.LauncherImpl;

@SuppressWarnings("restriction")
public class ExchangeMonitorApplicationMain
{
	public static void main(String[] args)
	{
		LauncherImpl.launchApplication(ExchangeMonitorApplication.class, ExchangeMonitionApplicationPreloader.class, args);
	}
}