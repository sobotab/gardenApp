package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkgModel.ObjectCarouselModel;
import pkgView.CarouselView;
import pkgView.DragDropCarouselView;
import pkgView.InfoCarouselView;
import pkgView.View;

public class DragDropCarouselController extends CarouselController {
	View view;
	DragDropCarouselView dcv;
	ObjectCarouselModel carouselModel;
	
	public DragDropCarouselController(View view, CarouselView carouselView) {
		this.view = view;
		this.carouselView = carouselView;
		dcv = (DragDropCarouselView)carouselView;
		carouselModel = new ObjectCarouselModel();
	}
	
	public void clickedRight(ActionEvent event) {
		dcv.rotateRight();
		carouselModel.rotateRight();
	}
	
	public void clickedLeft(ActionEvent event) {
		dcv.rotateLeft();
		carouselModel.rotateLeft();
	}
	
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
	
}
