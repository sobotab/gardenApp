package pkgView;

import java.util.List;

import javafx.scene.layout.TilePane;

public class DragDropCarouselView extends CarouselView {
	List<PlantView> plants;
	PlantView heldPlant;
	
	public DragDropCarouselView() {}
	
	void updatePlant() {}
	
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
