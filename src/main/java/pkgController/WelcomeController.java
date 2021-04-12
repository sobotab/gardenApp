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
	
	public void clickedNew(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView());
	}
	
	public void clickedInfo(ActionEvent event) {
		view.setCurrentScreen(new InfoView());
	}
	
	public void clickedOpen(ActionEvent event) {
		view.setCurrentScreen(new OpenGardenView());
	}
	
	public void clickedResources(ActionEvent event) {
		view.setCurrentScreen(new ResourcesView());
	}
	
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
