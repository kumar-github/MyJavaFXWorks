package com.tc.app.exchangemonitor.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.tc.app.exchangemonitor.model.ExternalTrade;

public interface IMainApplicationMonitorTabController extends IGenericController
{
	/* A function which returns the ExternalTradeSourceName for the given ExternalTrade  when called. */
	public final Function<ExternalTrade, String> externalTradeSourceFunction = (ExternalTrade anExternalTrade) ->
	{
		return anExternalTrade.getExternalTradeSourceOid().getExternalTradeSrcName().toLowerCase();
	};

	/* A function which returns the ExternalTradeStateName for the given ExternalTrade  when called. */
	public final Function<ExternalTrade, String> externalTradeStateFunction = (ExternalTrade anExternalTrade) ->
	{
		return anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName().toLowerCase();
	};

	public final Function<ExternalTrade, String> externalTradeStatusFunction = (ExternalTrade anExternalTrade) ->
	{
		return anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().toLowerCase();
	};
	
	public final Function<ExternalTrade, String> externalTradeCommodityFunction = (ExternalTrade anExternalTrade) ->
	{
		return anExternalTrade.getExchToolsTrade().getCommodity().toLowerCase();
	};

	public final List<Function<ExternalTrade, String>> externalTradeSearchablePropertiesList = Arrays.asList(
			(ExternalTrade anExternalTrade) -> anExternalTrade.getOid().toString(),
			externalTradeSourceFunction,
			externalTradeStateFunction,
			externalTradeStatusFunction,
			externalTradeCommodityFunction
			);

	//This is not working since we are giving the same predicate reference every time and the FilteredList is thinking that the predicate is not changed not apply it.  
	public  Predicate<ExternalTrade> somePredicate = (ExternalTrade anExternalTrade) ->
	{
		//String filterText = externalTradeTableViewDataFilterTextField.getText().trim().toLowerCase();
		String filterText = null;

		if(filterText.isEmpty() || filterText == null || filterText.equals(""))
			return true;

		if(anExternalTrade.getOid().toString().contains(filterText))
			return true;
		else if(anExternalTrade.getExternalTradeSourceOid().getExternalTradeSrcName().toLowerCase().contains(filterText))
			return true;
		else if(anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().toLowerCase().contains(filterText))
			return true;
		else if(anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName().toLowerCase().contains(filterText))
			return true;

		return false;
	};

	//default public Predicate<ExternalTrade> externalTradesTableViewFilterPredicateTemp()
	default public Predicate<ExternalTrade> externalTradesTableViewFilterPredicateTemp(String filterText)
	{
		return (ExternalTrade anExternalTrade) ->
		{
			//String filterText = externalTradeTableViewDataFilterTextField.getText().trim().toLowerCase();

			if(filterText.isEmpty() || filterText == null || filterText.equals(""))
				return true;
			if(anExternalTrade.getOid().toString().contains(filterText))
				return true;
			else if(anExternalTrade.getExternalTradeSourceOid().getExternalTradeSrcName().toLowerCase().contains(filterText))
				return true;
			else if(anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().toLowerCase().contains(filterText))
				return true;
			else if(anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName().toLowerCase().contains(filterText))
				return true;

			return false;
		};
	}

	//default public Predicate<ExternalTrade> externalTradesTableViewFilterPredicate()
	default public Predicate<ExternalTrade> externalTradesTableViewFilterPredicate(String filterText)
	{
		return (ExternalTrade anExternalTrade) ->
		{
			//String filterText = externalTradeTableViewDataFilterTextField.getText().trim().toLowerCase();
			return filterText.isEmpty() || filterText == null || filterText.equals("") || externalTradeSearchablePropertiesList.stream().anyMatch((aFunction) -> aFunction.apply(anExternalTrade).contains(filterText));
		};
	}
}