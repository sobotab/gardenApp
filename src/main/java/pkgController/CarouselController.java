package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.CarouselView;
import pkgView.View;

public abstract class CarouselController {
	
	View view;
	CarouselView carouselView;
	
	public CarouselController(View view, CarouselView carouselView) {
		this.view = view;
		this.carouselView = carouselView;
	}
	
	public void clickedRight(ActionEvent event) {
		carouselView.rotateRight();
	}
	
	public void clickedLeft(ActionEvent event) {
		carouselView.rotateLeft();
	}
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
}
