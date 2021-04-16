package pkgController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import pkgModel.DrawGardenModel;
import pkgView.DrawGardenView;

import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class DrawGardenController {
	View view;
	DrawGardenModel dgm;
	DrawGardenView dgv;
	
	public DrawGardenController(View view, DrawGardenView dgv) {

		this.view=view;
		dgm = new DrawGardenModel();
		this.dgv = dgv;
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
	
	public void draw() {
		dgm.addPreOutline(dgv.getCurrent());
		dgm.addPlot(dgv.getDone(), dgv.getSoil());
	}
}
