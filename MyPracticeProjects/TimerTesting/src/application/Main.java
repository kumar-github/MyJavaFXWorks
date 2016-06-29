package application;
	
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;

import javax.swing.JSpinner.DateEditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application
{
	public static boolean isPollerLoggedIn = false;
	public static boolean isPollerLoggedOut = false;
	private LoginLogoutMonitorTask loginLogoutMonitorTask = null;
	private Timer timer = null;
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			//String startTime = "20:30:00 US/Eastern";
			//String endTime = "18:30:00 US/Eastern";
			
			//String[] tempStartTime = startTime.split(" ") ;
			
			//startTime = tempStartTime[0];
			//String timeZone = tempStartTime[1];
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			/*System.out.println(dateFormat.parse("20:30:00"));
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			System.out.println(dateFormat.parse("20:30:00"));
			Date d1 = dateFormat.parse("20:30:00");
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			System.out.println(dateFormat.format(d1));*/
			
			Date currentDate = new Date();
			System.out.println("currentDate : " + currentDate);
			System.out.println(dateFormat.format(currentDate));	
			dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
			System.out.println("currentDate : " + currentDate);
			System.out.println(dateFormat.format(currentDate));
			dateFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
			System.out.println("currentDate : " + currentDate);
			System.out.println(dateFormat.format(currentDate));
			
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			
			Date time1 = df.parse("20:30:00");
			System.out.println(time1);
			
			df.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
			Date time2 = df.parse("20:30:00");
			System.out.println(time2);

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
			//Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR,20);
			cal.set(Calendar.MINUTE, 30);
			cal.set(Calendar.SECOND, 00);
			Date startTime = cal.getTime();
			cal.set(Calendar.HOUR,18);
			cal.set(Calendar.MINUTE, 30);
			cal.set(Calendar.SECOND, 00);
			Date endTime = cal.getTime();
			
			System.out.println("startTime : " + startTime);
			System.out.println("endTime : " + endTime);
			
			System.out.println(startTime.getHours());
			System.out.println(startTime.getMinutes());
			System.out.println(startTime.getSeconds());
			
			System.out.println(endTime.getHours());
			System.out.println(endTime.getMinutes());
			System.out.println(endTime.getSeconds());
			
			System.out.println(new Date().getHours());
			System.out.println(Calendar.getInstance().get(Calendar.HOUR));
			System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			
			
			Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
			cal1.set(Calendar.HOUR,20);
			cal1.set(Calendar.MINUTE, 30);
			cal1.set(Calendar.SECOND, 00);
			
			Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
			cal2.set(Calendar.HOUR,18);
			cal2.set(Calendar.MINUTE, 30);
			cal2.set(Calendar.SECOND, 00);
			
			System.out.println("cal1 : " + cal1);
			System.out.println("cal2 : " + cal2);
			
			System.out.println("cal1.getTime : " + cal1.getTime());
			System.out.println("cal2.getTime : " + cal2.getTime());
			
			Calendar currentHour = Calendar.getInstance();
			
			System.out.println("currentHour : " + currentHour.get(Calendar.HOUR));
			
			System.out.println(currentHour.after(cal2));
			System.out.println(currentHour.before(cal2));
			System.out.println(currentHour.after(cal1));
			System.out.println(currentHour.before(cal1));
			
			
			//System.out.println(dateFormat.parse(startTime));
			
			System.out.println(TimeZone.getTimeZone("US/Eastern"));
			System.out.println(TimeZone.getTimeZone("US/Central"));
			System.out.println(TimeZone.getTimeZone("EDT"));
			
			FlowPane root = new FlowPane();
			
			Button loginButon = new Button("Login");
			Button logoutButon = new Button("Logout");
			
			root.getChildren().addAll(loginButon, logoutButon);
			
			loginButon.setOnAction(e->handleLoginButtonClick());
			logoutButon.setOnAction(e->handleLogoutButtonClick());
			
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void handleLoginButtonClick()
	{
		System.out.println(TimeZone.getTimeZone("US/Eastern"));
		isPollerLoggedIn = true;
		isPollerLoggedOut = false;
		System.out.println("Logged In at : " + giveTime(System.currentTimeMillis()));
		
		if(timer != null)
		{
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}
	
	private void handleLogoutButtonClick()
	{
		isPollerLoggedOut = true;
		isPollerLoggedIn = false;
		System.out.println("Logged out at : " + giveTime(System.currentTimeMillis()));
		
		if(timer == null)
		{
			timer = new Timer();
			//loginLogoutMonitorTask = new LoginLogoutMonitorTask();
			loginLogoutMonitorTask = new LoginLogoutMonitorTask(timer);
			loginLogoutMonitorTask.setLatestLoggedOutTimeInMilliSeconds(System.currentTimeMillis());
			timer.scheduleAtFixedRate(loginLogoutMonitorTask, 0, (1000 * 3));	//3 second
		}
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

	public static void main(String[] args) {
		launch(args);
	}
}