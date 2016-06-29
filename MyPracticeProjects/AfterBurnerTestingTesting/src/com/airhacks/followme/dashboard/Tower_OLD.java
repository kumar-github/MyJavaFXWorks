package com.airhacks.followme.dashboard;

import javax.annotation.PostConstruct;

public class Tower_OLD
{
	@PostConstruct
    public void init()
	{
        System.out.println("Tower.init()");
    }
	
	public String readyToTakeoff()
	{
        System.out.println("Ready to take-off");
        return "ok from tower";
    }
}