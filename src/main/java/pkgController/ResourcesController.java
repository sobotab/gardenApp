package pkgController;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.ResourcesView;
import pkgView.View;
import pkgView.WelcomeView;
/**
 * 
 * @author Ryan Dean
 * Controller for Resources screen.
 * 
 */
public class ResourcesController {
	/**
	 * View class for the program. Initialized once.
	 */
	View view;
	
	/**
	 * Constructor sets view field.
	 * 
	 * @param view		View class for the program.
	 */
public ResourcesController(View view) {
	
	this.view = view;
	}
/**
 * Handler for the clickedBack method. Sets view's stage to Welcome screen.
 * 
 * @param event		ActionEvent caused by clicked the back button.
 */
public void clickedBack(ActionEvent event) {
	/**
	 * Getter for clickedBack handler.
	 * 
	 * @return			EventHandler for clickedBack.
	 */
	view.setCurrentScreen(new WelcomeView(view));
	}


public void clickedOpen(ActionEvent event) {
		if(Desktop.isDesktopSupported())
	    {
	        try {
	            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=k85mRPqvMbE&ab_channel=CrazyFrog"));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } catch (URISyntaxException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	

	
/**
 * Getter for clickedBack handler.
 * 
 * @return			EventHandler for clickedBack.
 */
public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
public EventHandler getHandlerForOpen() {
		return event -> clickedOpen((ActionEvent) event);
	}
}