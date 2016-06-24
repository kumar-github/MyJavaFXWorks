package com.tc.app.exchangemonitor.controller;

import java.util.List;

import org.hibernate.SQLQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class FetchExternalTradesTask extends Task<ObservableList<DummyExternalTrade>>
{
	private final SQLQuery sqlQuery;
	
	public FetchExternalTradesTask()
	{
		updateMessage("");
		updateProgress(0.0, 0.0);
		sqlQuery = null;
	}
	
	public FetchExternalTradesTask(SQLQuery sqlQuery)
	{
		updateMessage("");
		updateProgress(0.0, 0.0);
		this.sqlQuery = sqlQuery;
	}
	
	@Override
	protected ObservableList<DummyExternalTrade> call() throws Exception
	{
		try
		{
			return FXCollections.observableArrayList(fetchExternalTradesForQuery(sqlQuery));
		}
		catch(Exception exception)
		{
			throw exception;
		}
	}

	private List<DummyExternalTrade> fetchExternalTradesForQuery(SQLQuery sqlQuery)
	{
		List<DummyExternalTrade> dummyExternalTrades = null;
		
		try
		{
			updateMessage("Task Started...");
			updateProgress(-1.0,  -1.0);
			
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				updateMessage(ex.toString());
			}
			
			long startTime = System.currentTimeMillis();
			dummyExternalTrades = sqlQuery.list();
			long endTime = System.currentTimeMillis();
			updateMessage("Task Completed. It took " + (endTime - startTime) + " milliseconds to fetch " + dummyExternalTrades.size() + " record(s).");
			updateProgress(1.0,  1.0);
			
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				updateMessage(ex.toString());
			}
		}
		catch(Exception exception)
		{
			throw exception;
		}
		return dummyExternalTrades;
	}
	
	@Override
	protected void failed()
	{
		super.failed();
		/*System.out.println("inside failed.");
		updateMessage("Failed");*/
	}
	
	@Override
	protected void cancelled()
	{
		super.cancelled();
		/*System.out.println("inside cancelled.");
		updateMessage("Task Cancelled.");*/
	}
	
	@Override
	protected void running()
	{
		super.running();
		/*System.out.println("inside running.");
		updateMessage("Task Running.");*/
	}
	
	@Override
	protected void succeeded()
	{
		super.succeeded();
		/*System.out.println("inside succeeded.");
		updateMessage("Task Succeeded.");*/
	}
	
	@Override
	protected void scheduled()
	{
		super.scheduled();
		/*System.out.println("inside scheduled.");
		updateMessage("Task Scheduled.");*/
	}
}