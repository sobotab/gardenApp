package pkgView;

import javafx.scene.layout.BorderPane;

public class SelectPlants extends BorderPane {
	SelectCarousel selectionCarousel; 
	
	public SelectPlants() {}
	
	void updateLeps(int numLeps) {}
	
	void updateDollars(int dollars) {}
	
	// getters & setters
	public SelectCarousel getSelectionCarousel() {
		return this.selectionCarousel;
	}
	public void setSelectionCarousel(SelectCarousel carousel) {
		this.selectionCarousel = carousel;
	}
}
