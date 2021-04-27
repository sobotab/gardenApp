package pkgController;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgModel.PlantGardenModel;
import pkgView.EditGardenView;
import pkgView.OpenGardenView;
import pkgView.View;
import pkgView.WelcomeView;

public class OpenGardenController {
	View view;
	OpenGardenView ogv;
	HashMap<String, PlantGardenModel> gardenData;
	
	public OpenGardenController(View view, OpenGardenView ogv) {
		this.view = view;
		this.ogv = ogv;
		
		// Read in existing garden data, if any
		
		gardenData = null;
		try {
			FileInputStream fis = new FileInputStream("finalGardenData.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        gardenData = (HashMap<String, PlantGardenModel>)ois.readObject();
	        ois.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		ogv.setGardenNames(gardenData.keySet());
		ogv.prepareListView(gardenData.keySet());
		
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
	}
	
	public void clickNext(ActionEvent event) {
		view.setCurrentScreen(new EditGardenView(view, null));
	}
	
	public void clickedOpen(ActionEvent event) {
		int selectedIndex = ogv.getGardenList().getSelectionModel().getSelectedIndex();
		String name = ogv.getGardenList().getSelectionModel().getSelectedItem();
		
		view.setCurrentScreen(new EditGardenView(view, name));
	}
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
	
	public EventHandler getHandlerForOpen() {
		return event -> clickedOpen((ActionEvent) event);
	}
}
