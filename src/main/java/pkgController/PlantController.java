package pkgController;

import javafx.event.ActionEvent;
import pkgView.View;

public class PlantController {
	View view;
	
	public PlantController(View view) {
		this.view = view;
	}
	
	public void drag(MouseEvent event) {
		
	}
	
	public MouseEvent getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
}
