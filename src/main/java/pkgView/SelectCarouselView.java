package pkgView;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pkgController.SelectCarouselController;
/**
 * 
 * @author Zane Greenholt
 * The SelectCarouselView class extends CarouselView and holds the carousel implementation for the select plants screen.
 * The images in this carousel can be clicked to be added to a collection of selected plants that will be remembered for the edit garden screen.
 */
public class SelectCarouselView extends CarouselView{	
	// I don't see what this carousel does that isn't inherited from CarouselView.
	/**
	 * The controller for the select carousel
	 */
	SelectCarouselController scc;
	
	/**
	 * Constructor for the SelectCarouselView which automatically filters the shown plants based on selections from the draw garden screen and 
	 * initializes the left and right button for this carousel.
	 * 
	 * @param view The program's View that is only initialized once
	 */
	public SelectCarouselView(View view) {
		scc = new SelectCarouselController(view, this);
		this.setHgap(10);
		String sun = scc.getSun();
		String moisture = scc.getMoisture();
		List<String> soil = scc.getSoil();
		
		images = scc.getImagesFromController();
		
		Button left = new Button("<<<");
		Button right = new Button(">>>");
		left.setOnAction(scc.getHandlerForClickedLeft());
		right.setOnAction(scc.getHandlerForClickedRight());
		
		this.getChildren().add(left);
		this.getChildren().add(right);
		this.filter(sun, moisture, soil);
		this.setAlignment(Pos.CENTER);
	}
	
	/**
	 * This method simply calls the filterCarousel method from the SelectCarouselController by passing along arguments.
	 * @param sun The sun level selected by the user on the draw garden screen
	 * @param moisture The moisture level selected by the user on the draw garden screen
	 * @param soil The soil type selected by the user on the draw garden screen
	 */
	public void filter(String sun, String moisture, List<String> soil) {
		scc.filterCarousel(sun, moisture, soil);
	}
	/**
	 * Getter for the scc field
	 * @return scc field - A SelectCarouselController that has handlers for clicking images in this carousel
	 */
	public SelectCarouselController getScc() {
		return scc;
	}

	/**
	 * Setter for the scc field
	 * @param scc A SelectCarouselController that will replace the current scc field
	 */
	public void setScc(SelectCarouselController scc) {
		this.scc = scc;
	}
	
	/**
	 * Method that simply decrements the center index of the carousel unless it goes below zero
	 */
	public void decrementCenter() {
		center--;
		if(center < 0) {
			center++;
		}
	}
	
	
}
