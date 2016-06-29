package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ComboBoxAutoCompleteTest extends Application {

	private static final String[] LISTA = { "Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",
			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",
			"Figo", "Figo da", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Kino (Kiwano)", "Kiwi", "Laranja Bahia", 
			"Laranja para suco", "Laranja seleta", "Laranjinha kinkan", "Lichia", "Manga espada", "Manga Haden",
			"Manga Palmer", "Manga Tommy", "Mangostim", "elancia sem semente", "Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", 
			"Mirtilo", "Morango", "Nectarina", "Physalis", "Pinha", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",
			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",
			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",
			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",
			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Massa de alho", "Maxixe", "Milho"};

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button tempButton = new Button("Just for focus");
		ComboBox<String> cmb = new ComboBox<>();
		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(LISTA);
		stage.setScene(new Scene(new FlowPane(tempButton, cmb)));
		stage.show();
		stage.setTitle("Filter ComboBox");
		stage.setWidth(300);
		stage.setHeight(300);
		new ComboBoxAutoComplete<String>(cmb);
		
	}

}
