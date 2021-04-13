package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.View;

public class InfoPopupController {
	View view;
	
	public InfoPopupController(View view) {
		this.view = view;
	}
	
	public void clickedBack(ActionEvent event) {
		
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
}
