package com.tc.app.exchangemonitor.controller;

import javafx.util.StringConverter;

public class AccountConverter extends StringConverter<Account>
{
	@Override
	public String toString(Account object)
	{
		if(object == null)
			return null;
		return object.getAcctShortName();
	}

	@Override
	public Account fromString(String string)
	{
		return null;
	}
}