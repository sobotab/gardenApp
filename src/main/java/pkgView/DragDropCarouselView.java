package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pkgController.DragDropCarouselController;

public class DragDropCarouselView extends CarouselView {
	List<PlantView> plants;
	PlantView heldPlant;
	DragDropCarouselController dcc;
	int maxViewSize = 3;
	final double DEFAULT_IMG_SIZE = 60;
	final double SHRINK_IMG_SCALE = 0.9;
	Button left;
	Button right;
	
	public DragDropCarouselView(View view) {
		dcc = new DragDropCarouselController(view, this);
		plants = new ArrayList<PlantView>();
		heldPlant = null;
		
		PlantView compost = new PlantView(new Image(getClass().getResourceAsStream("/images/compost.png")), 0);
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(80);
    	
    	//plants.add(compost);
    	this.getChildren().add(compost);
    	
    	left = new Button("<<<");
		right = new Button(">>>");
		left.setOnAction(dcc.getHandlerForClickedLeft());
		right.setOnAction(dcc.getHandlerForClickedRight());
		
		this.getChildren().add(left);
		//this.getChildren().add(right);
    	this.setAlignment(Pos.CENTER);
    	
		this.setHgap(5);
    	this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
                new CornerRadii(50), Insets.EMPTY)));

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
			
			if (plants.indexOf(plant) < maxViewSize)
				this.getChildren().add( plant );
			
			if (plants.size() >= maxViewSize) {
				if (plants.indexOf(plant) == 0 || plants.indexOf(plant) == maxViewSize - 1)
					plant.setFitHeight( plant.getFitHeight() * SHRINK_IMG_SCALE);
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
		this.getChildren().add(index+2, plant);
		if ((index == 1 || index == maxViewSize) && maxViewSize >= plants.size()-1)
			plant.setFitHeight( plant.getFitHeight() * SHRINK_IMG_SCALE);
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
