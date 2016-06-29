package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MainController implements Initializable
{
	@FXML Label messagesLabel;
	@FXML Button connectButton;
	TestSchedule testSchedule = new TestSchedule();
	@FXML Button disconnectButton;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		connectButton.disableProperty().bind(testSchedule.runningProperty());
		
		connectButton.disabledProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				System.out.println("newValue : " + newValue);
				System.out.println("isDisable : " + connectButton.isDisable());
				System.out.println("isDisabled : " + connectButton.isDisabled());
			}});
	}

	@FXML public void connect()
	{
		testSchedule.setDelay(Duration.seconds(1));
		testSchedule.setPeriod(Duration.seconds(5));
		testSchedule.restart();
	}

	@FXML public void disconnect()
	{
		testSchedule.cancel();
	}
}

class TestSchedule extends ScheduledService<Void>
//class TestSchedule extends Service<Void>
{
	@Override
	protected Task<Void> createTask()
	{
		SampleTask sampleTask = new SampleTask();
		return sampleTask;
	}
}

class SampleTask extends Task<Void>
{
	@Override
	protected Void call() throws Exception
	{
		System.out.println("working...");
		Thread.sleep(2000);
		return null;
	}
}