package com.airhacks.igniter.business.flightcontrol.boundary;

import javax.annotation.PostConstruct;

public class Tower
{
	@PostConstruct
	public void init()
	{
        System.out.println("Tower.init()");
    }
	
	public String readyToTakeoff(String pilot)
	{
        System.out.println(pilot + " is ready to take-off");
        return "ok from tower";
	}
}