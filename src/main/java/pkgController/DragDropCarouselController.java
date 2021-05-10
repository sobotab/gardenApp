package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkgModel.ObjectCarouselModel;
import pkgView.CarouselView;
import pkgView.DragDropCarouselView;
import pkgView.InfoCarouselView;
import pkgView.View;

/**
 * 
 * @author Ryan Dean
 * The controller class for the carousel in the Edit Garden screen.
 */
public class DragDropCarouselController extends CarouselController {
	/**
	 * The view class for the program. Initialized once.
	 */
	View view;
	/**
	 * The view class for Edit Garden's carousel.
	 */
	DragDropCarouselView dcv;
	/**
	 * The model class for Edit Garden's carousel.
	 */
	ObjectCarouselModel carouselModel;
	
	/**
	 * Constructor for this controller class, sets model and view classes.
	 * @param view 			The view class for the program.
	 * @param carouselView 	The view class for this screen's carousel.
	 */
	public DragDropCarouselController(View view, CarouselView carouselView) {
		super(view,carouselView);
		dcv = (DragDropCarouselView)carouselView;
		carouselModel = new ObjectCarouselModel();
	}
	
	/**
	 * Handler for clicking right button on carousel. Tells model and view to rotate collections.
	 */
	public void clickedRight(ActionEvent event) {
		dcv.rotateRight();
		carouselModel.rotateRight();
	}
	
	/**
	 * Handler for clicking left button on carousel. Tells model and view to rotate collections.
	 */
	public void clickedLeft(ActionEvent event) {
		dcv.rotateLeft();
		carouselModel.rotateLeft();
	}	
}
