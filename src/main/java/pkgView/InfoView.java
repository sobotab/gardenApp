package pkgView;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import pkgController.InfoController;
/**
 * 
 * @author Zane Greenholt
 * Class that contains and displays all necessary objects on the info screen of the program
 */
public class InfoView extends BorderPane {
	/**
	 * An infoCarouselView which holds plant images and names
	 */
	InfoCarouselView infoCarousel;
	/**
	 * The preferred width of the comboBoxes that are used for filtering the carousel images
	 */
	private final double FILTER_WIDTH = 125.0;
	
	/**
	 * Constructor that initializes an infoCarousel and all necessary buttons and comboBoxes for filtering, and displays everything on the screen.
	 * @param view The program's View that is only initialized once
	 */
	public InfoView(View view) {
		infoCarousel = new InfoCarouselView(view);
		InfoController ic = new InfoController(view, infoCarousel);
		
		Button back = new Button("Back");
		back.setOnAction(ic.getHandlerForBack());
		
		Label title = new Label("Glossary   ");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(title, back);

		ComboBox<String> type = new ComboBox();
		type.setPromptText("Plant Type");
		type.setItems(FXCollections.observableArrayList("","woody","herbaceous"));
		type.setPrefWidth(FILTER_WIDTH);
		
		ComboBox<String> sun = new ComboBox();
		sun.setPromptText("Sun Level");
		sun.setItems(FXCollections.observableArrayList("","full sun", "part sun", "shade"));
		sun.setPrefWidth(FILTER_WIDTH);
	
		ComboBox<String> moisture = new ComboBox();
		moisture.setPromptText("Moisture Level");
		moisture.setItems(FXCollections.observableArrayList("","flooded","wet", "moist", "dry"));
		moisture.setPrefWidth(FILTER_WIDTH);
		
		ComboBox<String> soil = new ComboBox();
		soil.setPromptText("Soil Type");
		soil.setItems(FXCollections.observableArrayList("","clay","sandy","loamy"));
		soil.setPrefWidth(FILTER_WIDTH);
		
		
		Button filter = new Button("Filter");
		filter.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				String sunLevel = sun.getValue();
				String moistureLevel = moisture.getValue();
				String soilType = soil.getValue();
				String plantType = type.getValue();
				infoCarousel.filter(sunLevel, moistureLevel, soilType, plantType);
				
			}
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(type, moisture, soil, sun, filter);
     	
		
		this.setRight(vbox);
		this.setTop(hBox);
		this.setCenter(infoCarousel);
		this.setBottom(back);
		this.setMargin(getCenter(), new Insets(25));
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-sunshine.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
	}
	
	// getters & setters
	/**
	 * Getter for the infoCarousel field
	 * @return infoCarousel field - an InfoCarouselView that displays plant images
	 */
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	/**
	 * Setter for the infoCarousel field
	 * @param carousel An InfoCarouselView that will replace the current infoCarousel field
	 */
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
