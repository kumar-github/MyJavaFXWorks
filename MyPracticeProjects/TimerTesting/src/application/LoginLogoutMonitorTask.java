package application;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LoginLogoutMonitorTask extends TimerTask
{
	private Timer timer = null;
	private long latestLoggedOutTimeInMilliSeconds = 0L;
	private long timeDelayInMilliSecondsBeforeSendingAlerts = 0L;
	
	public LoginLogoutMonitorTask(Timer timer)
	{
		this.timer = timer;
	}
	
	public void setLatestLoggedOutTimeInMilliSeconds(long latestLoggedOutTimeInMilliSeconds)
	{
		this.latestLoggedOutTimeInMilliSeconds = latestLoggedOutTimeInMilliSeconds;
	}
	
	public long getLatestLoggedOutTimeInMilliSeconds()
	{
		return latestLoggedOutTimeInMilliSeconds;
	}
	
	public void setTimeDelayInMilliSecondsBeforeSendingAlerts(long timeDelayInMilliSecondsBeforeSendingAlerts)
	{
		this.timeDelayInMilliSecondsBeforeSendingAlerts = timeDelayInMilliSecondsBeforeSendingAlerts;
		//this.timeDelayInMilliSecondsBeforeSendingAlerts = 600000L;
	}
	
	public long getTimeDelayInMilliSecondsBeforeSendingAlerts()
	{
		this.timeDelayInMilliSecondsBeforeSendingAlerts = 30000L;
		return timeDelayInMilliSecondsBeforeSendingAlerts;
	}
	
	@Override
	public void run()
	{
		System.out.println(new Date() + " Monitoring Started...");
		
		monitorPollersAndSendDisconnectionAlertsIfNeeded();
		
		System.out.println(new Date() + " Monitoring Stopped...");
	}

	private void monitorPollersAndSendDisconnectionAlertsIfNeeded()
	{
		monitorCMEPollersAndSendDisconnectionAlertsIfNeeded();
		monitorICEPollersAndSendDisconnectionAlertsIfNeeded();
	}
	
	private void monitorCMEPollersAndSendDisconnectionAlertsIfNeeded()
	{
	}

	private void monitorICEPollersAndSendDisconnectionAlertsIfNeeded()
	{
		sendICEPollersDisconnectionAlertsIfNeeded();
	}

	private void sendICEPollersDisconnectionAlertsIfNeeded()
	{
		sendPollersDisconnectionAlertsIfNeeded();
	}

	private void sendPollersDisconnectionAlertsIfNeeded()
	{
		//System.out.println("Inside Timer Task at " + new Date() + " Logged In? : " + Main.isLoggedIn + " Logged Out? : " + Main.isLoggedout);
		if(Main.isPollerLoggedOut)
		{
			long latestLoggedOutTimeInMilliSeconds = getLatestLoggedOutTimeInMilliSeconds();
			System.out.println("latestLoggedOutTimeInMilliSeconds : " + giveTime(latestLoggedOutTimeInMilliSeconds));
			
			long timeDelayInMilliSecondsBeforeSendingAlerts = getTimeDelayInMilliSecondsBeforeSendingAlerts(); 
			//long timeToSendAlert = getLatestLoggedOutTimeInMilliSeconds() + 120000L;
			
			long timeInMilliSecondsToSendAlert = latestLoggedOutTimeInMilliSeconds + timeDelayInMilliSecondsBeforeSendingAlerts ;
			System.out.println("timeInMilliSecondsToSendAlert : " + giveTime(timeInMilliSecondsToSendAlert));
			
			long currentTimeInMilliSeconds = System.currentTimeMillis();
			
			if(Main.isPollerLoggedOut && currentTimeInMilliSeconds >= timeInMilliSecondsToSendAlert )
			{
				sendAlert();
				System.out.println("Alerted once, so stopping Timer.");
				resetTimeStatistics();
			}
		}
		else
		{
			/* may be logged in */
			System.out.println("Logged in... Stopping Timer.");
			resetTimeStatistics();
		}
	}
	
	private void resetTimeStatistics()
	{
		/* Can't think a better way than this. */
		
		if(timer != null)
		{
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}

	private boolean sendAlert()
	{
		boolean status = false;
		
		System.out.println("Sending Alerts.... " + new Date());
		
		return status;
	}
	
	public String giveTime(long timeInMilliSeconds )
	{
		String currentTime = "";
		long seconds = (timeInMilliSeconds / 1000) % 60;
		long minutes = (timeInMilliSeconds / (1000 * 60)) % 60;
		long hours = (timeInMilliSeconds / (1000 * 60 * 60)) % 24;
		long milliSeconds = (timeInMilliSeconds % 1000);
		
		currentTime = String.format("%02d:%02d:%02d:%d", hours, minutes, seconds, milliSeconds);
		
		return currentTime;
	}
}