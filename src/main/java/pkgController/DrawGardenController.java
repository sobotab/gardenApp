package pkgController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pkgView.EditGardenView;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class DrawGardenController {
	View view;
		
	public DrawGardenController(View view) {
		this.view=view;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
	}
	
	public void clickedNext(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));
	}
	
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickedNext((ActionEvent) event);
	}
	
	
}
