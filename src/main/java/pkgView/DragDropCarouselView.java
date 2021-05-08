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

public class DragDropCarouselView extends CarouselView {
	List<PlantView> plants;
	PlantView heldPlant;
	DragDropCarouselController dcc;
	int maxViewSize = 5;
	final double DEFAULT_IMG_SIZE = 60;
	final double SHRINK_IMG_SCALE = 0.9;
	Button left;
	Button right;
	
	public DragDropCarouselView(View view) {
		dcc = new DragDropCarouselController(view, this);
		plants = new ArrayList<PlantView>();
		heldPlant = null;
		
		/*
		PlantView compost = new PlantView(new Image(getClass().getResourceAsStream("/images/compost.png")), 0);
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(80);
    	*/
		
    	//plants.add(compost);
    	//this.getChildren().add(compost);
    	
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
		//this.getChildren().add(right);
    	this.setAlignment(Pos.CENTER);
    	
		this.setHgap(5);
    	//this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
        //        new CornerRadii(50), Insets.EMPTY)));
    	this.setStyle("-fx-background-color: "
    			+ "linear-gradient(#fcfcfc, #f3f3f3);"
    			+ "-fx-background-radius: 25;");

	}
	
	public void rotateLeft() {
		Collections.rotate(plants, -1);
		update();
	}
	
	public void rotateRight() {
		Collections.rotate(plants, 1);
		update();
	}
	
	List<Point> movePlant() {
		List<Point> points = null;
		return points;
	}
	
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
			System.out.println("index: " + plants.indexOf(plant) + "total : " + plants.size());

			
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
	}	
	
	public void initializePlant(PlantView plant) {		
		this.plants.add(plant);
		if (plants.size() <= maxViewSize) {
			this.getChildren().add(plant);
		}
		
	}
	
	public void addPlantAtIndex(PlantView plant, int index) {
		//this.plants.add(plant);
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
	
	public PlantView removePlant(int index) {
		return this.plants.remove(index);
	}
	
	/*
	public void replacePlant(int index) {
		PlantView duplicatePlant = new PlantView(
				plants.get(index).plantImage
				);
		this.plants.add(duplicatePlant);
		this.getChildren().add(duplicatePlant);
	}
	*/
	
	public void updatePlant() {}
	
	// getters
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	public PlantView getHeldPlant() {
		return this.heldPlant;
	}
	
	public DragDropCarouselController getController() {
		return this.dcc;
	}
	
	// setters
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	public void setHeldPlant(PlantView heldPlant) {
		this.heldPlant = heldPlant;
	}
	
	public void setController(DragDropCarouselController dcc) {
		this.dcc = dcc;
	}
	
}
