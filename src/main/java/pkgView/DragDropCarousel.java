package pkgView;

import java.util.List;

import javafx.scene.layout.TilePane;

public class DragDropCarousel extends CarouselView {
	List<PlantView> plants;
	PlantView heldPlant;
	
	public DragDropCarousel() {}
	
	void updatePlant() {}
	
	// getters & setters
	public List<PlantView> getPlants() {
		return this.plants;
	}
	public PlantView getHeldPlant() {
		return this.heldPlant;
	}
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	public void setHeldPlant(PlantView heldPlant) {
		this.heldPlant = heldPlant;
	}
}
