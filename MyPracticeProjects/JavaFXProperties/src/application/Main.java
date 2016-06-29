package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			Bill eletricBill = new Bill();
			System.out.println(eletricBill.getAmountDue());
			System.out.println(eletricBill.amountDueProperty());
			
			eletricBill.setAmountDue(100.0);
			
			System.out.println(eletricBill.getAmountDue());
			System.out.println(eletricBill.amountDueProperty());
			
			/*eletricBill.amountDueProperty().addListener(new ChangeListener()
			{
				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue)
				{
					System.out.println("Electric Bill Changed: oldValue : " + oldValue + " newValue : " + newValue);
				}});*/
			
			eletricBill.amountDueProperty().addListener((a, b, c) -> {System.out.println("Electric Bill Changed: oldValue : " + b + " newValue : " + c);});
			
			eletricBill.setAmountDue(200.0);
			Platform.exit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}