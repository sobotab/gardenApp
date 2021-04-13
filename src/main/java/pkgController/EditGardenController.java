package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class EditGardenController {
	View view;
	
	public EditGardenController(View view) {
		this.view=view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));
		
	}
	
	public void clickNext(ActionEvent event) {
		
	}
	
	public void clickExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForSave() {
		return event -> clickNext((ActionEvent) event);
	}
	
	public EventHandler getHandlerForExit() {
		return event -> clickExit((ActionEvent) event);
	}
}
