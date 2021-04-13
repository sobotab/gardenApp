package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkgView.View;

public class PlantController {
	View view;
	
	public PlantController(View view) {
		this.view = view;
	}
	
	public void drag(MouseEvent event) {
		
	}
	
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
}
