package pkgController;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import pkgView.View;

public class DrawGardenController {

	View view;
	
	public DrawGardenController(View view) {
		this.view=view;
	}
	
	public void clickedBack() {
		
	}
	
	public void clickNext() {
		
	}
	
	//Make more methods for organizing the gardens
	
	public ActionEvent getHandlerForBack(ActionEvent event) {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public ActionEvent getHandlerForNext(ActionEvent event) {
		return event -> clickedBack((ActionEvent) event);
	}
}
