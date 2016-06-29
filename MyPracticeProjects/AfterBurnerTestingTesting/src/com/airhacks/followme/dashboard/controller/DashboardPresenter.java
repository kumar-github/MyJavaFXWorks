package com.airhacks.followme.dashboard.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.airhacks.followme.dashboard.light.view.java.LightView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardPresenter implements Initializable
{
	@FXML
    //Label message;

    //@FXML
    //Pane lightsBox;

    //@Inject
    //Tower tower;

    @Inject
    private String prefix;

    @Inject
    private String happyEnding;

    //@Inject
    //private LocalDate date;

    //private String theVeryEnd;
    
    @FXML
    private void initialize() 
    {
    	System.out.println("...........");
    }
    
    public DashboardPresenter()
    {
    	System.out.println("constructor");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //fetched from dashboard.properties
        /*if(rb != null)
        	this.theVeryEnd = rb.getString("theEnd");*/
    }

    public void createLights()
    {
        for (int i = 0; i < 255; i++)
        {
            final int red = i;
            LightView view = new LightView((f) -> red);
            //view.getViewAsync(lightsBox.getChildren()::add);
        }
    }

    public void launch()
    {
        //message.setText("Date: " + date + " -> " + prefix + tower.readyToTakeoff() + happyEnding + theVeryEnd);
        System.out.println(prefix + happyEnding);
    }
}