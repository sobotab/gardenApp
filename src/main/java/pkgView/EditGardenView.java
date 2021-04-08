package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class EditGarden extends BorderPane{
	int userX;
	int userY;
	boolean clicked;
	DragDropCarousel PlantCarousel;
	FlowPane garden;
	List<PlantView> plants;
	
	public EditGarden() {}
	
	public List<Point> movePlant() {}
	
	public void updatePlant() {}
	
	//getters & setters
	public int getUserX() {
		return this.userX;
	}
	public int getUserY() {
		return this.userY;
	}
	public boolean isClicked() {
		return this.clicked;
	}
	public DragDropCarousel getPlantCarousel() {
		return this.PlantCarousel;
	}
	public FlowPane getGarden() {
		return this.garden;
	}
	public List<PlantView> getPlants() {
		return this.plants;
	}
	public void setUserX(int x) {
		this.userX = x;
	}
	public void setUserY(int y) {
		this.userY = y;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	public void setPlantCarousel(DragDropCarousel carousel) {
		this.PlantCarousel = carousel;
	}
	public void setGarden(FlowPane garden) {
		this.garden = garden;
	}
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
}
