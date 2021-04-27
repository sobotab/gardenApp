package pkgView;

import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pkgController.OpenGardenController;

public class OpenGardenView extends BorderPane{
	Set<String> gardenNames;
	ListView<String> gardenList;
	
	public OpenGardenView(View view) {
		gardenList = new ListView();
		OpenGardenController ogc = new OpenGardenController(view, this);
		
		Label title = new Label("Open Garden");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 50));
		
		Button back = new Button("Back");
		Button open = new Button("Open");
		
		back.setOnAction(ogc.getHandlerForBack());
		open.setOnAction(ogc.getHandlerForOpen());
		
		
		VBox vBox = new VBox(gardenList, open);
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(vBox);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getCenter(), new Insets(10, 10, 10, 10));
    	
    	BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-leaves.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
    	
	}
	
	public void prepareListView(Set<String> gardenNames) {
		for (String gardenName : gardenNames) {
			gardenList.getItems().add(gardenName);
		}
	}
	
	// getters & setters
	public ListView<String> getGardenList() {
		return this.gardenList;
	}
	public void setGardenList(ListView<String> list) {
		this.gardenList = list;
	}
	
	public Set<String> getGardenNames() {
		return this.gardenNames;
	}

	public void setGardenNames(Set<String> names) {
		this.gardenNames = names;
		
	}
}
