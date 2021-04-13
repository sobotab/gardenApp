package pkgController;

import javafx.event.ActionEvent;
import pkgView.View;

public class InfoController {
	View view;
	
	public InfoController(View view) {
		this.view = view;
	}
	
	public void clickedBack() {
		
	}
	
	public ActionEvent getHandlerForBack(ActionEvent event) {
		return event -> clickedBack((ActionEvent) event);
	}
}
