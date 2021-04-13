package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.ResourcesView;
import pkgView.View;
import pkgView.WelcomeView;

public class ResourcesController {
	View view;
	
	public ResourcesController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
}
