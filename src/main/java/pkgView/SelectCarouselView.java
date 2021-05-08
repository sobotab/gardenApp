package pkgView;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import pkgController.SelectCarouselController;
/**
 * 
 * @author Zane Greenholt
 * The SelectCarouselView class extends CarouselView and holds the carousel implementation for the select plants screen.
 * The images in this carousel can be clicked to be added to a collection of selected plants that will be remembered for the edit garden screen.
 */
public class SelectCarouselView extends CarouselView{	
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
		
		ImageView turn_left_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_left_img.setFitHeight(80);
		turn_left_img.setPreserveRatio(true);
		turn_left_img.setRotationAxis(Rotate.Y_AXIS);
		turn_left_img.setRotate(180);
		Button left = new Button();
		left.setPrefSize(80,  60);
		left.setGraphic(turn_left_img);
		
		ImageView turn_right_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_right_img.setFitHeight(80);
		turn_right_img.setPreserveRatio(true);
		Button right = new Button();
		right.setPrefSize(80,  60);
		right.setGraphic(turn_right_img);
		
		left.setOnAction(scc.getHandlerForClickedLeft());
		right.setOnAction(scc.getHandlerForClickedRight());
		

		
		this.getChildren().add(left);
		this.getChildren().add(right);
		this.filter(sun, moisture, soil);
		this.setAlignment(Pos.CENTER);
//		
//		String hover_border_style = "-fx-border-color: #ffff00;"
//				+ "-fx-border-width: 100;";
//		
//		for(VBox image: filteredImages) {
//			ImageView imv = (ImageView)image.getChildren().get(1);
//			imv.setOnMouseEntered(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent e) {
//					imv.setStyle(hover_border_style);
//				}
//			});
//		}
		
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
