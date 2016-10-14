package com.tc.app.exchangemonitor.controller;

import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;

//public static class TotalLine extends DummyPosition
public class TotalLine extends DummyPosition
{
	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();

	public TotalLine(ObservableList<? extends DummyPosition> items)
	{
		super("Total", null, null, null);

		// Bind total to the sum of the totals of all the other line items:
		//total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
		total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
	}

	@Override
	public ReadOnlyObjectProperty<Double> totalProperty()
	{
		return total;
	}
}