package com.tc.app.exchangemonitor.controller;

import org.hibernate.Query;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class FetchExternalTradesScheduledService extends ScheduledService<ObservableList<ExternalTrade>>
{
	//private final SQLQuery sqlQuery;
	private Query sqlQuery;
	//private final Duration DELAY = Duration.seconds(5);
	//private final Duration PERIOD = Duration.seconds(30);
	
	public FetchExternalTradesScheduledService()
	{
		this(null, Duration.seconds(5.0), Duration.seconds(30.0));
	}
	
	public FetchExternalTradesScheduledService(Query sqlQuery, Duration delay, Duration period)
	{
		super();
		this.sqlQuery = sqlQuery;
		this.setDelay(delay);
		this.setPeriod(period);
	}
	
	public void setSQLQuery(Query sqlQuery)
	{
		this.sqlQuery = sqlQuery;
	}
	
	@Override
	protected Task<ObservableList<ExternalTrade>> createTask()
	{
		FetchExternalTradesTask fetchExternalTradesTask = new FetchExternalTradesTask(sqlQuery);
		return fetchExternalTradesTask;
	}
	
	@Override
	protected void failed()
	{
		super.failed();
		System.out.println("Inside Failed.");
	}
	
	@Override
	protected void cancelled()
	{
		super.cancelled();
		System.out.println("Inside Cancelled.");
	}
	
	@Override
	protected void succeeded()
	{
		super.succeeded();
		System.out.println("Inside Succeeded.");
	}
	
	@Override
	protected void scheduled()
	{
		super.scheduled();
		System.out.println("Inside Scheduled.");
	}
	
	@Override
	protected void ready()
	{
		super.ready();
		System.out.println("Inside Ready.");
	}
	
	@Override
	protected void running()
	{
		super.running();
		System.out.println("Inside Running.");
	}
}