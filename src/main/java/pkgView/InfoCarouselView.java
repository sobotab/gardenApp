package pkgView;

import java.util.ArrayList;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgController.InfoCarouselController;
import pkgController.InfoController;

/**
 * 
 * @author Zane Greenholt
 * The InfoCarouselView class extends CarouselView and is the implementation for the info screen of the program
 * This carousel's images can be clicked to bring up a popup screen with plant information.
 */
public class InfoCarouselView extends CarouselView{
	
	/**
	 * The program's view that is only initialized once
	 */
	View view;
	/**
	 * The controller for the info carousel
	 */
	InfoCarouselController icc;

	/**
	 * Constructor for the InfoCarouselView.
	 * This constructor created the necessary buttons for using a carousel and puts them on the screen.
	 * 
	 * @param view The View of the program that is only created once
	 */
	public InfoCarouselView(View view) {

		this.view = view;
		icc = new InfoCarouselController(view, this);
		this.setHgap(10);

		images = icc.getImagesFromController();
		filteredImages = new ArrayList<VBox>();
		for(VBox image: images) {
			filteredImages.add(image);
			image.setOnMousePressed(icc.getHandlerForPopup());
			if(image.getChildren().size() == 6) {
				image.getChildren().remove(5);
			}
		}

		center = 1;
		
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
		
		left.setOnAction(icc.getHandlerForClickedLeft());
		right.setOnAction(icc.getHandlerForClickedRight());

		this.getChildren().add(left);
		this.getChildren().add(right);
		this.update();
		this.setAlignment(Pos.CENTER);
		
	}

	  
	/**
	 * A method that is part of the process of filtering the plants in the carousel. This part sets the values from the drop downs for 
	 * filtering to the empty string if the user did not select a specific value.
	 * 
	 * @param sun The chosen value from the sun comboBox on the info screen
	 * @param moisture The chosen value from the moisture comboBox on the info screen
	 * @param soil The chosen value from the soil comboBox on the info screen
	 * @param type The chosen value from the plantType comboBox on the info screen
	 */
	public void filter(String sun, String moisture, String soil, String type) {
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
		icc.filterCarousel(sun, moisture, soil, type);
	}
	
}
