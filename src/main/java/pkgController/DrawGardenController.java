package pkgController;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		
		// Send plots info
		
		dgm.setMoisture(dgv.getMoisture());
		dgm.setSun(dgv.getSun());
		try {
			dgm.setBudget(dgv.getBudget());
		} catch (NumberFormatException e) {
			dgv.errorPopup("Set a budget before continuing!");
			return;
		}
		
		ArrayList<Object> sendData = new ArrayList<Object>();
		int budget = 60;
		sendData.add(dgm.getPlots());
		sendData.add(dgm.getBudget());
		
		try {
			FileOutputStream fos = new FileOutputStream("gardenData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(sendData);
            oos.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } 
		
		
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
