package pkgController;

import javafx.event.ActionEvent;
import pkgView.View;

public class SelectCarouselController extends CarouselController {
		
	public SelectCarouselController(View view) {
		super(view);
	}
	
	public void plantSelected() {
		
	}
	
	public ActionEvent getHandlerForPlantSelected(ActionEvent event) {
		return event -> clickedBack((ActionEvent) event);
	}
}
