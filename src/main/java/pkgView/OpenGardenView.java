package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import pkgController.OpenGardenController;

public class OpenGardenView extends BorderPane{
	ListView<String> gardenList; // JavaFX class that makes a list of options. Looks like a good choice for this screen!
	
	public OpenGardenView(View view) {
		OpenGardenController ogc = new OpenGardenController(view);
		Button back = new Button("Back");
		Button open = new Button("Open");
		
		Label title = new Label("Open Garden");
		
		back.setOnAction(ogc.getHandlerForBack());
		open.setOnAction(ogc.getHandlerForNext());
		
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(open);
		
	}
	
	// getters & setters
	public ListView<String> getGardenList() {
		return this.gardenList;
	}
	public void setGardenList(ListView<String> list) {
		this.gardenList = list;
	}
}
