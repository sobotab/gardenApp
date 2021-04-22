package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pkgController.SelectPlantsController;

public class SelectPlantsView extends BorderPane {
	SelectCarouselView selectionCarousel; 
	SelectPlantsController spc;
	
	public SelectPlantsView(View view) {
		spc = new SelectPlantsController(view);
		selectionCarousel = new SelectCarouselView(view);
		
		Label title = new Label("Select Plants");
		Button back = new Button("Back to drawing garden.");
		back.setOnAction(spc.getHandlerForBack());
		Button finish = new Button("Finish");
		finish.setOnAction(spc.getHandlerForNext());
		
		this.setBottom(back);
		this.setTop(title);
		this.setRight(finish);
		this.setBottom(selectionCarousel);
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
	
	
}
