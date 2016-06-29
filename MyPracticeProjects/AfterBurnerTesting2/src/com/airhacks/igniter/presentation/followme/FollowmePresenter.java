package com.airhacks.igniter.presentation.followme;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.airhacks.igniter.business.flightcontrol.boundary.Tower;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author adam-bien.com
 */
public class FollowmePresenter implements Initializable {

    @FXML
    Label a;
    @Inject
    Tower tower;

    @Inject
    String pilot;

    @FXML
    TextField b;

    @FXML
    TextField c;

    IntegerProperty bNumber = new SimpleIntegerProperty();
    IntegerProperty cNumber = new SimpleIntegerProperty();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bindings.bindBidirectional(b.textProperty(), bNumber, new NumberStringConverter());
        Bindings.bindBidirectional(c.textProperty(), cNumber, new NumberStringConverter());
        a.textProperty().bind(bNumber.add(cNumber).asString());
        
        //fetched from followme.propertiess
        //System.out.println(rb.getString(pilot));
        System.out.println(pilot);
    }

    public void launch() {
    	//a.setText(tower.toString());
    	//System.out.println(tower.readyToTakeoff(pilot));
    }

}
