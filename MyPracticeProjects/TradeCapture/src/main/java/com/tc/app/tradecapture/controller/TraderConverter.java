package com.tc.app.tradecapture.controller;

import javafx.util.StringConverter;

public class TraderConverter extends StringConverter<IctsUser>
{
	@Override
	public String toString(IctsUser object)
	{
		if(object == null)
			return null;
		return object.getUserInit();
	}

	@Override
	public IctsUser fromString(String string)
	{
		return null;
	}
}