package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import pkgController.EditGardenController;

public class EditGardenView extends BorderPane{
	int userX;
	int userY;
	boolean clicked;
	DragDropCarouselView PlantCarousel;
	FlowPane garden;
	List<PlantView> plants;
	
	public EditGardenView(View view) {
		EditGardenController egc = new EditGardenController(view);
		
		Label title = new Label("Edit Garden");
		Button back = new Button("Back to plant select");
		back.setOnAction(egc.getHandlerForBack());
		Button save = new Button("Save");
		Button exit = new Button("Exit");
		exit.setOnAction(egc.getHandlerForExit());
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(save);
		this.setRight(exit);
	}
	
	public List<Point> movePlant() {
		List<Point> points = null;
		return points;
	}
	
	public void updatePlant() {}
	
	
	// getters
	public int getUserX() {
		return this.userX;
	}
	
	public int getUserY() {
		return this.userY;
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public DragDropCarouselView getPlantCarousel() {
		return this.PlantCarousel;
	}
	
	public FlowPane getGarden() {
		return this.garden;
	}
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	
	// setters
	public void setUserX(int x) {
		this.userX = x;
	}
	
	public void setUserY(int y) {
		this.userY = y;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setPlantCarousel(DragDropCarouselView carousel) {
		this.PlantCarousel = carousel;
	}
	
	public void setGarden(FlowPane garden) {
		this.garden = garden;
	}
	
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
}
