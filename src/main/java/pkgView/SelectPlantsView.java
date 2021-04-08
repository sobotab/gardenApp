package pkgView;

import javafx.scene.layout.BorderPane;

public class SelectPlantsView extends BorderPane {
	SelectCarouselView selectionCarousel; 
	
	public SelectPlantsView() {}
	
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
