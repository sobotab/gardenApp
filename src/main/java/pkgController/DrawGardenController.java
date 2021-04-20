package pkgController;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	public Point2D.Double draw() {
		dgm.addPreOutline(dgv.getCurrent());
		dgm.addPlot(dgv.getDrawing(), dgv.getSoil());
		return dgm.getEndPoint();
	}
	
	public ArrayList<Point2D.Double> undo() {
		return dgm.undo();
	}
}
