package pkgView;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class OpenGardenView extends BorderPane{
	ListView<String> gardenList; // JavaFX class that makes a list of options. Looks like a good choice for this screen!
	
	public OpenGardenView() {}
	
	// getters & setters
	public ListView<String> getGardenList() {
		return this.gardenList;
	}
	public void setGardenList(ListView<String> list) {
		this.gardenList = list;
	}
}
