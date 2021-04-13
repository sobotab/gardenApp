package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.DrawGardenView;
import pkgView.EditGardenView;
import pkgView.View;

public class SelectPlantsController {
View view;
	
	public SelectPlantsController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view));
	}
	
	public void clickNext(ActionEvent event) {
		view.setCurrentScreen(new EditGardenView(view));
		
	}
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
}
