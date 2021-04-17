package pkgView;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class DragDropCarouselView extends CarouselView {
	List<PlantView> plants;
	PlantView heldPlant;
	
	public DragDropCarouselView() {
		plants = new ArrayList<PlantView>();
		heldPlant = null;
		
		PlantView compost = new PlantView(new Image(getClass().getResourceAsStream("/images/compost.png")));
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(80);
    	
    	plants.add(compost);
    	this.getChildren().add(compost);
    	
    	this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
                CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public void addPlant(PlantView plant) {
		this.plants.add(plant);
		this.getChildren().add(plant);
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
	
	// setters
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	public void setHeldPlant(PlantView heldPlant) {
		this.heldPlant = heldPlant;
	}
	
}
