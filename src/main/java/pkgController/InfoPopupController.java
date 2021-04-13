package pkgController;

import javafx.event.ActionEvent;
import pkgView.View;

public class InfoPopupController {
	View view;
	
	public InfoPopupController(View view) {
		this.view = view;
	}
	
	public void clickedBack() {
		
	}
	
	public ActionEvent getHandlerForBack(ActionEvent event) {
		return event -> clickedBack((ActionEvent) event);
	}
}
