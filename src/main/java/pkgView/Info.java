package pkgView;

import javafx.scene.layout.BorderPane;

public class Info extends BorderPane {
	InfoCarousel infoCarousel;
	
	public Info() {}
	
	public void openInfoPopUp() {
		// new infoPopUp( infoCarousel.get(x) );
		// Is this how this'll work? Or it'll go through model? Left InfoPopUp as is for now as I'm not sure.
	}
	
	// getters & setters
	public InfoCarousel getInfoCarousel() {
		return this.infoCarousel;
	}
	public void setInfoCarousel(InfoCarousel carousel) {
		this.infoCarousel = carousel;
	}
}
