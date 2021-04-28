package pkgController;

import java.util.HashMap;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.CarouselModel;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.InfoCarouselView;
import pkgView.InfoView;
import pkgView.View;
import pkgView.WelcomeView;
/**
 * Class that holds the handlers for the info screen
 */
public class InfoController {
	/**
	 * The program's view that is only initialized once
	 */
	View view;
	/**
	 * Constructor initializes the view
	 * @param view The program's view that is only initialized once
	 */
	public InfoController(View view) {
		this.view = view;
	}
	
	/**
	 * Handler for clicking the back button. It sets the screen to the welcome screen.
	 * @param event An ActionEvent that is the mouse being clicked
	 */
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	/**
	 * Getter for the clickedBack handler
	 * @return EventHandler for the clickedBack method
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
}
