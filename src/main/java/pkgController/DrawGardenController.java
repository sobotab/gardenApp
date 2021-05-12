package pkgController;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgModel.DrawGardenModel;
import pkgView.DrawGardenView;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class DrawGardenController {
	/**
	 * The view
	 */
	View view;
	/**
	 * The model for drawGarden
	 */
	DrawGardenModel dgm;
	/**
	 * The view for drawGarden
	 */
	DrawGardenView dgv;
	
	/**
	 * @param view the top view
	 * @param dgv the view which initializes the controller
	 * @param createOnBack boolean for whether the view was made after pressing the back button
	 * 
	 * Initializes all of the important values and imports the drawGardenModel if the
	 * back button was pressed
	 */
	public DrawGardenController(View view, DrawGardenView dgv, boolean createOnBack) {

		this.view=view;
		this.dgv = dgv;
		boolean set = false;
		
		try {
			FileInputStream fis = new FileInputStream("drawGarden.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (createOnBack) {
				dgm = (DrawGardenModel)ois.readObject();
				set = true;
			}
			ois.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception");
		}
		
		if(!set) {
			dgm = new DrawGardenModel();
		}
	}
	
	/**
	 * @param event javafx event clicked
	 * initializes the welcome screen
	 */
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
	}
	
	/**
	 * @param event javafx event clicked
	 * Saves drawGarden data and initializes selectPlants
	 */
	public void clickedNext(ActionEvent event) {
		
		// Send plots info
		finish();
		dgm.setMoisture(dgv.getMoisture());
		dgm.setSun(dgv.getSun());
		if(dgv.getBudget() > 0) {
			dgm.setBudget(dgv.getBudget());
		} else {
			dgv.errorPopup("Set a budget before continuing!");
			return;
		}
		
		ArrayList<Object> sendData = new ArrayList<Object>();
		sendData.add(dgm.getPlots());
		sendData.add(dgm.getBudget());
		sendData.add(dgm.getMoisture());
		sendData.add(dgm.getSun());
		sendData.add((int)dgv.getScale());
		sendData.add(dgm.getRows() * 9);

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
		
		try {
			FileOutputStream fos = new FileOutputStream("drawGarden.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(dgm);
            oos.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        }
		
		view.setCurrentScreen(new SelectPlantsView(view));
	}
	
	/**
	 * @return the handler for the back button
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	/**
	 * @return the handler for the next button
	 */
	public EventHandler getHandlerForNext() {
		return event -> clickedNext((ActionEvent) event);
	}
	
	/**
	 * @return the first point the user clicked
	 * Adds a plot drawn to plots in the model
	 */
	public Point2D.Double draw() {
		dgm.setGridSize(dgv.getMinGrid());
		dgm.setCanvasLength(dgv.getMinLength());
		dgm.addPreOutline(dgv.getCurrent());
		dgm.addPlot(dgv.getDrawing(), dgv.getSoil());
		return dgm.getEndPoint();
	}
	
	/**
	 * @return plots with one fewer plot than before it was called
	 * Pops the last plot drawn from plots before redrawing
	 */
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> undo() {
		return dgm.undo();
	}
	
	/**
	 * @param minGrid the minimum of rows or columns
	 * @return the plots scaled
	 * Scales and redraws the plots
	 */
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> scale(double minGrid) {
		dgm.setCanvasLength(dgv.getMinLength());
		dgm.scale(dgv.getMinGrid());
		return dgm.getPlots();
	}
	
	/**
	 * Sets important scaling factors before saving when the next button is clicked
	 */
	public void finish() {
		dgm.setCanvasHeight(dgv.getCanvasHeight());
		dgm.setCanvasWidth(dgv.getCanvasWidth());
		dgm.setRows(dgv.getRows());
		dgm.setColumns(dgv.getColumns());
	}
}
