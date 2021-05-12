package pkgView;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	 * String representing the type of plant selected for filtering by the user (woody or herbaceous)
	 */
	String type;
	/**
	 * A specific soil type selected by the uesr for filtering
	 */
	String soil;
	
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
		List<String> soils = scc.getSoil();
		this.type = "";
		this.soil = "";
		
		images = scc.getImagesFromController();
		
		String buttonStyle = "-fx-font-size:" + Double.valueOf(18).toString() +";"
				+ " -fx-font-weight: bold;"
				+ "-fx-background-color: linear-gradient(#fafafa , #afd9f5 );"
				+ "-font-family: Helvetica";
		
		ImageView turn_left_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_left_img.setFitHeight(80);
		turn_left_img.setPreserveRatio(true);
		turn_left_img.setRotationAxis(Rotate.Y_AXIS);
		turn_left_img.setRotate(180);
		Button left = new Button();
		left.setPrefSize(80,  60);
		left.setGraphic(turn_left_img);
		left.setStyle(buttonStyle);
		
		ImageView turn_right_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_right_img.setFitHeight(80);
		turn_right_img.setPreserveRatio(true);
		Button right = new Button();
		right.setPrefSize(80,  60);
		right.setGraphic(turn_right_img);
		right.setStyle(buttonStyle);
		
		left.setOnAction(scc.getHandlerForClickedLeft());
		right.setOnAction(scc.getHandlerForClickedRight());
		

		
		this.getChildren().add(left);
		this.getChildren().add(right);
		this.filter(sun, moisture, soils);
		this.setAlignment(Pos.CENTER);
		

		
	}
	
	/**
	 * This method simply calls the filterCarousel method from the SelectCarouselController by passing along arguments.
	 * 
	 * @param sun The sun level selected by the user on the draw garden screen
	 * @param moisture The moisture level selected by the user on the draw garden screen
	 * @param soil The soil type selected by the user on the draw garden screen
	 */
	public void filter(String sun, String moisture, List<String> soils) {
		scc.filterCarousel(sun, moisture, soils);
	}

	
	/**
	 * method that prepares the user chosen values to be Strings that can be used for filtering, and calls the filterCarousel method 
	 * @param type Plant type selected by the user (woody or herbaceous)
	 * 
	 * @param soil Specific soil type selected by the user
	 * @param sun Sun level selected by the user
	 * @param moisture Moisture level selected by the user
	 * @param soils List of all soil types used by the user to draw the garden
	 */
	public void filter(String type, String soil, String sun, String moisture, List<String> soils) {
		if(soil == null) {
			soil = "";
		}
		if(moisture == null) {
			moisture = "";
		}
		if(type == null) {
			type = "";
		}
		if(sun == null) {
			sun = "";
		}
		this.type = type;
		this.soil = soil;
		scc.filterCarousel(type, soil, sun, moisture, soils);
	}
	
	/**
	 * Getter for the scc field
	 * 
	 * @return scc field - A SelectCarouselController that has handlers for clicking images in this carousel
	 */
	public SelectCarouselController getScc() {
		return scc;
	}

	/**
	 * Setter for the scc field
	 * 
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
	/**
	 * Getter for type field
	 * 
	 * @return String representing a plant type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Setter for type field
	 * 
	 * @param type String representing a plant type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Getter for soil field
	 * 
	 * @return String representing a soil type
	 */
	public String getSoil() {
		return soil;
	}
	/**
	 * Setter for soil field
	 * 
	 * @param soil String representing a soil type
	 */
	public void setSoil(String soil) {
		this.soil = soil;
	}
	
	
}
