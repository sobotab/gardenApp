package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkgModel.DrawGardenModel;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class DrawGardenController {

	View view;
	DrawGardenModel dgm;
	
	public DrawGardenController(View view) {
		this.view=view;
		dgm = new DrawGardenModel();
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
	}
	
	public void clickedNext(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));
	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickedNext((ActionEvent) event);
	}
	
	public void mousePressed() {
		
	}
}
