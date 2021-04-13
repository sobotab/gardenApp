package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.InfoView;
import pkgView.View;
import pkgView.WelcomeView;

public class InfoController {
	View view;
	
	public InfoController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
}
