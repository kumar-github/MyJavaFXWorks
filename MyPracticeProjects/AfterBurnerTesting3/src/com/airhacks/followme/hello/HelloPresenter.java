package com.airhacks.followme.hello;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author airhacks.com
 */
public class HelloPresenter
{
	@Inject
	HelloService hello;

    @FXML
    TextField nameTextField;

    public void sayHello() {
        hello.sayHelloTo(nameTextField.getText());
    }

}
