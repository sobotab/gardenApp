package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import pkgView.DrawGardenView;
import pkgView.InfoView;
import pkgView.OpenGardenView;
import pkgView.ResourcesView;
import pkgView.View;
/**
 * 
 * @author Zane Greenholt
 * Class that holds the handlers for the welcome screen
 */
public class WelcomeController {
	/**
	 * The program's View that is only initialized once
	 */
	View view;
	
	/**
	 * Constructor initializes the class' view
	 * @param view The program's View that is only initialized once
	 */
	public WelcomeController(View view) {
		this.view = view;
	}

	/*
	 * The next 4 methods take the view and call its setCurrentScreen method.
	 * This method uses the setScene method of theStage with a border pane
	 * as a parameter of the scene.
	*/
	/**
	 * Handler for clicking the new button. It takes the user to the dragGarden screen.
	 * @param event An ActionEvent that is a mouse being clicked
	 */
	public void clickedNew(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view, false));
	}
	
	/**
	 * Handler for clicking the info button. It takes the user to the glossary.
	 * @param event An ActionEvent that is a mouse being clicked
	 */
	public void clickedInfo(ActionEvent event) {
		view.setCurrentScreen(new InfoView(view));
	}
	/**
	 * Handler for clicking the open button. It takes the user to the open garden screen.
	 * @param event An ActionEvent that is a mouse being clicked
	 */
	public void clickedOpen(ActionEvent event) {
		view.setCurrentScreen(new OpenGardenView(view));
	}
	
	/**
	 * Handler for clicking the resources button. It takes the user to the resources screen.
	 * @param event An ActionEvent that is a mouse being clicked
	 */
	public void clickedResources(ActionEvent event) {
		view.setCurrentScreen(new ResourcesView(view));
	}
	
	/*
	 * The next 4 methods return the event handlers for the buttons.
	 */
	
	/**
	 * Getter for the clickedNew handler
	 * @return EventHandler for clickedNew method
	 */
	public EventHandler getHandlerForNew() {
		return event -> clickedNew((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedInfo handler
	 * @return EventHandler for clickedInfo method
	 */
	public EventHandler getHandlerForInfo() {
		return event -> clickedInfo((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedOpen handler
	 * @return EventHandler for the clickedOpen method
	 */ 
	public EventHandler getHandlerForOpen() {
		return event -> clickedOpen((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedResources handler
	 * @return EventHandler for the clickedResources method
	 */
	public EventHandler getHandlerForResources() {
		return event -> clickedResources((ActionEvent) event);
	}
}
