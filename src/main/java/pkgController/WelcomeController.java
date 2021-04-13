package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import pkgView.DrawGardenView;
import pkgView.InfoView;
import pkgView.OpenGardenView;
import pkgView.ResourcesView;
import pkgView.View;

public class WelcomeController {
	
	View view;
	
	public WelcomeController(View view) {
		this.view = view;
	}

	/*
	 * The next 4 methods take the view and call its setCurrentScreen method.
	 * This method uses the setScene method of theStage with a border pane
	 * as a parameter of the scene.
	*/
	public void clickedNew(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view));
	}
	
	public void clickedInfo(ActionEvent event) {
		view.setCurrentScreen(new InfoView(view));
	}
	
	public void clickedOpen(ActionEvent event) {
		view.setCurrentScreen(new OpenGardenView(view));
	}
	
	public void clickedResources(ActionEvent event) {
		view.setCurrentScreen(new ResourcesView(view));
	}
	
	/*
	 * The next 4 methods return the event handlers for the buttons.
	 */
	
	public EventHandler getHandlerForNew() {
		return event -> clickedNew((ActionEvent) event);
	}
	
	public EventHandler getHandlerForInfo() {
		return event -> clickedInfo((ActionEvent) event);
	}
	
	public EventHandler getHandlerForOpen() {
		return event -> clickedOpen((ActionEvent) event);
	}
	
	public EventHandler getHandlerForResources() {
		return event -> clickedResources((ActionEvent) event);
	}
}
