package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.CarouselView;
import pkgView.View;

public abstract class CarouselController {
	
	View view;
	
	public CarouselController(View view) {
		this.view = view;
	}
	
	public void clickedRight(ActionEvent event) {
		
	}
	
	public void clickedLeft(ActionEvent event) {
		
	}
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
}
