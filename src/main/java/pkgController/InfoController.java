package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.InfoView;
import pkgView.View;
import pkgView.WelcomeView;

public class InfoController {
	View view;
	InfoView infoview;
	
	public InfoController(View view, InfoView infoview) {
		this.view = view;
		this.infoview = infoview;
		
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	public void clickedPopup(ActionEvent event) {
		infoview.openInfoPopUp(view);
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForPopup() {
		return event -> clickedPopup((ActionEvent) event);
	}
	
}
