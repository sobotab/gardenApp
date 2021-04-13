package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.View;

public class SelectPlantsController {
View view;
	
	public SelectPlantsController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		
	}
	
	public void clickNext(ActionEvent event) {
		
	}
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickedBack((ActionEvent) event);
	}
}
