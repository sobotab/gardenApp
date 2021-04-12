package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import pkgView.WelcomeView;

public class WelcomeController {
	
	WelcomeView view;
	
	public WelcomeController() {
		this.view = view;
	}
	
	public void clickedNew(ActionEvent event) {
		
	}
	
	public void clickedInfo(ActionEvent event) {
		
	}
	
	public void clickedOpen(ActionEvent event) {
		
	}
	
	public void clickedResources(ActionEvent event) {
		
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
