package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import pkgController.DragDropCarouselController;

/**
 * 
 * @author Ryan Dean
 * View class for the carousel in the Edit Garden screen.
 */
public class DragDropCarouselView extends CarouselView {
	/**
	 * List of plantView objects in this carousel.
	 */
	List<PlantView> plants;
	/**
	 * The controller for this class, helps communicate with carousel model.
	 */
	DragDropCarouselController dcc;
	/**
	 * Integer representing max number of plantView objects to show at one time in carousel.
	 */
	int maxViewSize = 5;
	/**
	 * Constant double for fit height/width to set every plantView in carousel to.
	 */
	final double DEFAULT_IMG_SIZE = 60;
	/**
	 * Constant double for how much to shrink plantViews on the sides of the carousels.
	 */
	final double SHRINK_IMG_SCALE = 0.9;
	/**
	 * Button for "spinning" the carousel to the left.
	 */
	Button left;
	/**
	 * Button for "spinning" the carousel to the right.
	 */
	Button right;
	
	/**
	 * Constructor for this carousel view. Makes left/right buttons and prepares for plants to be added.
	 * 
	 * @param view 		The view class for this program, initialized only once.
	 */
	public DragDropCarouselView(View view) {
		dcc = new DragDropCarouselController(view, this);
		plants = new ArrayList<PlantView>();
    	
		ImageView turn_right_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_right_img.setFitHeight(40);
		turn_right_img.setPreserveRatio(true);
		right = new Button();
		right.setPrefSize(30,  20);
		right.setGraphic(turn_right_img);

		ImageView turn_left_img = new ImageView(new Image("/images/carousel-turn-icon.png"));
		turn_left_img.setFitHeight(40);
		turn_left_img.setPreserveRatio(true);
		turn_left_img.setRotationAxis(Rotate.Y_AXIS);
		turn_left_img.setRotate(180);
		left = new Button();
		left.setPrefSize(30,  20);
		left.setGraphic(turn_left_img);
    	
		left.setOnAction(dcc.getHandlerForClickedLeft());
		right.setOnAction(dcc.getHandlerForClickedRight());
		
		this.getChildren().add(left);
    	this.setAlignment(Pos.CENTER);
    	
		this.setHgap(10);
    	//this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
        //        new CornerRadii(50), Insets.EMPTY)));
    	this.setStyle("-fx-background-color: "
    			+ "linear-gradient(#fcfcfc, #f3f3f3);"
    			+ "-fx-background-radius: 25;");

	}
	
	/**
	 * Increments the index of all plants in this class's plants list by -1.
	 */
	public void rotateLeft() {
		Collections.rotate(plants, -1);
		update();
	}
	
	/**
	 * Increments the index of all plants in this class's plants list by 1.
	 */
	public void rotateRight() {
		Collections.rotate(plants, 1);
		update();
	}
	
	/**
	 * Method to re-fill the carousel with plantViews after spinning, sets sizes as well.
	 */
	public void update() {
		this.getChildren().removeAll(plants);
		this.getChildren().remove(right);
		
		for (PlantView plant : plants) {
			
			plant.setFitHeight( DEFAULT_IMG_SIZE );
			Rectangle default_template = new Rectangle(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE);
			default_template.setArcHeight(15);
			default_template.setArcWidth(15);
			plant.setClip(default_template);
			
			if (plants.indexOf(plant) < maxViewSize) {
				this.getChildren().add( plant );
			}
			
			if (plants.size() >= maxViewSize) {
				if (plants.indexOf(plant) == 0 || plants.indexOf(plant) == maxViewSize - 1) {
					plant.setFitHeight( plant.getFitHeight() * SHRINK_IMG_SCALE);
					Rectangle img_template = new Rectangle(
							plant.getFitHeight(), 
							plant.getFitHeight());
					img_template.setArcHeight(15);
					img_template.setArcWidth(15);
					plant.setClip(img_template);
				}
				
			}	
		}		
		this.getChildren().add(right);
		System.out.println(this.getChildren().size());
		System.out.println(this.plants.size());
	}	
	
	/**
	 * Method to add a plant to the carousel for the first time, added to plants list and carousel child nodes.
	 * 
	 * @param plant 	The plantView to be added to the carousel.
	 */
	public void initializePlant(PlantView plant) {		
		this.plants.add(plant);
		if (plants.size() <= maxViewSize) {
			this.getChildren().add(plant);
		}
		
	}
	
	/**
	 * Method to add a plant at a specific index in the carousel's plants list.
	 * 
	 * @param plant 	PlantView to be added to carousel.
	 * @param index 	Index to place that plantView at.
	 */
	public void addPlantAtIndex(PlantView plant, int index) {
		this.plants.add(index, plant);
		this.getChildren().add(index+1, plant);
		if ((index == 1 || index == maxViewSize) && maxViewSize <= plants.size()-1) {
			
			plant.setFitHeight( plant.getFitHeight() * SHRINK_IMG_SCALE);
			Rectangle img_template = new Rectangle(
					plant.getFitHeight(), 
					plant.getFitHeight());
			img_template.setArcHeight(15);
			img_template.setArcWidth(15);
			plant.setClip(img_template);
		}
	}
	
	/**
	 * Method to remove a plant at a particular index from the carousel's plants list.
	 * 
	 * @param index 	Index of the plantView to be removed.
	 * @return 			The plantView object removed.
	 */
	public PlantView removePlant(int index) {
		return this.plants.remove(index);
	}


	
	// Getters & Setters
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	public DragDropCarouselController getController() {
		return this.dcc;
	}
	
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	
	public void setController(DragDropCarouselController dcc) {
		this.dcc = dcc;
	}
	
}
