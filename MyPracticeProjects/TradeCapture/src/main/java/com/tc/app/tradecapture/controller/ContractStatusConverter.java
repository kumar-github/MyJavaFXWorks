package com.tc.app.exchangemonitor.controller;

import javafx.util.StringConverter;

public class ContractStatusConverter extends StringConverter<ContractStatus>
{
	@Override
	public String toString(ContractStatus object)
	{
		if(object == null)
			return null;
		return object.getContrStatusCode();
	}

	@Override
	public ContractStatus fromString(String string)
	{
		return null;
	}
}