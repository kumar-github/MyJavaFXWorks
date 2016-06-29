package application;

import java.util.Calendar;
import java.util.TimeZone;

public class FinalTest
{
	public static void main(String[] args)
	{
		String startTime = "20:30:00 US/Eastern";
		String endTime = "18:30:00 US/Eastern";
		
		String[] x = startTime.substring(0, 8).split(":");
		System.out.println(x[0]);
		System.out.println(x[1]);
		System.out.println(x[2]);
		
		System.out.println("startTime : " + startTime);
		System.out.println("endTime : " + endTime);
		
		//TimeZone EDTTimeZone = TimeZone.getTimeZone("US/Eastern");
		TimeZone EDTTimeZone = TimeZone.getTimeZone(startTime.split(" ")[1]);
		Calendar EDTTimeZoneCalendarWithStartTime = Calendar.getInstance(EDTTimeZone);
		EDTTimeZoneCalendarWithStartTime.set(Calendar.HOUR_OF_DAY, 20);
		EDTTimeZoneCalendarWithStartTime.set(Calendar.MINUTE, 30);
		EDTTimeZoneCalendarWithStartTime.set(Calendar.SECOND, 00);
		
		Calendar EDTTimeZoneCalendarWithEndTime = Calendar.getInstance(EDTTimeZone);
		EDTTimeZoneCalendarWithEndTime.set(Calendar.HOUR_OF_DAY, 18);
		EDTTimeZoneCalendarWithEndTime.set(Calendar.MINUTE, 30);
		EDTTimeZoneCalendarWithEndTime.set(Calendar.SECOND, 00);
		
		System.out.println("Start Time in GMT : " + EDTTimeZoneCalendarWithStartTime.getTime());
		System.out.println("End Time in GMT : " + EDTTimeZoneCalendarWithEndTime.getTime());
		Calendar currentTimeCalendar = Calendar.getInstance();
		System.out.println("currentTimeCalendar : " + currentTimeCalendar.getTime());
		
		if(currentTimeCalendar.before(EDTTimeZoneCalendarWithEndTime) || currentTimeCalendar.after(EDTTimeZoneCalendarWithStartTime))
		{
			System.out.println("ICE Working.");
		}
		else
		{
			System.out.println("ICE Down Time");
		}
	}
}