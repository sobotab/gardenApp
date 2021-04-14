package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.View;

public class SelectCarouselController extends CarouselController {
		
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
	}
	
	public void plantSelected(ActionEvent event) {
		
	}
	
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((ActionEvent) event);
	}
}
