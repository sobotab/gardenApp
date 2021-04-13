package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.InfoCarouselView;
import pkgView.InfoView;
import pkgView.View;
import pkgView.WelcomeView;

public class InfoController {
	View view;
	InfoCarouselView icv;
	
	public InfoController(View view, InfoCarouselView icv) {
		this.view = view;
		this.icv = icv;
		
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	public void clickedPopup(ActionEvent event) {
		icv.openInfoPopUp(view);
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForPopup() {
		return event -> clickedPopup((ActionEvent) event);
	}
	
}
