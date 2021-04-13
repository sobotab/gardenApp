package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pkgController.SelectPlantsController;

public class SelectPlantsView extends BorderPane {
	SelectCarouselView selectionCarousel; 
	
	public SelectPlantsView(View view) {
		SelectPlantsController spc = new SelectPlantsController(view);
		
		Label title = new Label("Select Plants");
		Button back = new Button("Back to drawing garden.");
		back.setOnAction(spc.getHandlerForBack());
		Button finish = new Button("Finish");
		finish.setOnAction(spc.getHandlerForNext());
		
		this.setBottom(back);
		this.setTop(title);
		this.setCenter(finish);
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
