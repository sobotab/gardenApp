package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.ResourcesView;
import pkgView.View;
import pkgView.WelcomeView;

/**
 * 
 * @author Ryan Dean
 * Controller for Resources screen.
 */
public class ResourcesController {
	/**
	 * View class for the program. Initialized once.
	 */
	View view;
	
	/**
	 * Constructor sets view field.
	 * @param view		View class for the program.
	 */
	public ResourcesController(View view) {
		this.view = view;
	}
	
	/**
	 * Handler for the clickedBack method. Sets view's stage to Welcome screen.
	 * @param event		ActionEvent caused by clicked the back button.
	 */
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	/**
	 * Getter for clickedBack handler.
	 * @return			EventHandler for clickedBack.
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
}
