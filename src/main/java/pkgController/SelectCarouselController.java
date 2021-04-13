package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgView.View;

public class SelectCarouselController extends CarouselController {
		
	public SelectCarouselController(View view) {
		super(view);
	}
	
	public void plantSelected(ActionEvent event) {
		
	}
	
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((ActionEvent) event);
	}
}
