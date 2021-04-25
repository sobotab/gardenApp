package pkgView;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pkgController.SelectPlantsController;

public class SelectPlantsView extends BorderPane {
	SelectCarouselView selectionCarousel; 
	SelectPlantsController spc;
	List<ImageView> selectedPlants;
	FlowPane plants;
	
	public SelectPlantsView(View view) {
		selectionCarousel = new SelectCarouselView(view);
		selectedPlants = new ArrayList<ImageView>();
		spc = new SelectPlantsController(view, this, selectionCarousel.getScc());
		
		
		for(VBox image: selectionCarousel.getFilteredImages()) {
			image.setOnMousePressed(spc.getHandlerForPlantSelected());
		}
		
		Label title = new Label("Select Plants");
		Button back = new Button("Back to drawing garden.");
		back.setOnAction(spc.getHandlerForBack());
		Button finish = new Button("Finish");
		finish.setOnAction(spc.getHandlerForNext());
		Label flowLabel = new Label("Plants you have selected");
		
		plants = new FlowPane(Orientation.VERTICAL);
		plants.getChildren().add(flowLabel);
		
		HBox box = new HBox();
		box.getChildren().add(back);
		box.getChildren().add(finish);
		
		this.setBottom(box);
		this.setTop(title);
		this.setRight(plants);
		this.setCenter(selectionCarousel);
	}
	
	void updateLeps(int numLeps) {}
	
	void updateDollars(int dollars) {}
	
	
	// getters & setters
	public SelectCarouselView getSelectionCarousel() {
		return this.selectionCarousel;
	}
	
	public void setSelectionCarousel(SelectCarouselView carousel) {
		this.selectionCarousel = carousel;
	}
	
	public void selectPlant(VBox box) {
		ImageView imv = (ImageView)box.getChildren().get(1);
		box.setScaleX(.5);
		box.setScaleY(.5);
		selectedPlants.add(imv);
		plants.getChildren().add(box);
		box.setOnMousePressed(spc.getHandlerForPlantDeSelected());
	}
	
	public void deSelectPlant(VBox box) {
		ImageView imv = (ImageView)box.getChildren().get(1);
		selectedPlants.remove(imv);
		plants.getChildren().remove(box);
		box.setOnMousePressed(spc.getHandlerForPlantSelected());
	}
	
	
}
