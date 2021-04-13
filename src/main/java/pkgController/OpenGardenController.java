package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.View;
import pkgView.WelcomeView;

public class OpenGardenController {
	View view;
	
	public OpenGardenController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
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
