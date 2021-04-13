package pkgView;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import pkgController.InfoController;

public class InfoView extends BorderPane {
	InfoCarouselView infoCarousel;
	
	public InfoView(View view) {
		InfoController ic = new InfoController(view);
		
		Button back = new Button("Back");
		back.setOnAction(ic.getHandlerForBack());
		
		this.setBottom(back);
		
	}
	
	public void openInfoPopUp() {
		// new infoPopUp( infoCarousel.get(x) );
		// Is this how this'll work? Or it'll go through model? Left InfoPopUp as is for now as I'm not sure.
	}
	
	
	// getters & setters
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
