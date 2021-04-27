package pkgController;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import pkgModel.DrawGardenModel;
import pkgModel.Model;
import pkgModel.ObjectCarouselModel;
import pkgModel.PlantGardenModel;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgModel.PlantObjectModel;
import pkgView.EditGardenView;
import pkgView.InfoPopupView;
import pkgView.PlantView;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class EditGardenController {
	View view;
	EditGardenView gardenView;
	PlantGardenModel gardenModel;
	
	public EditGardenController(View view, EditGardenView gardenView) {	
		
		// Hard-coded max dimension
		//double max_height = 500;
		
		// Read in data from DrawGarden screen
		
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = null;
		int budget = 0;
		try {
			FileInputStream fis = new FileInputStream("gardenData.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ArrayList<Object> receiveData = (ArrayList<Object>)ois.readObject();
	        plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)receiveData.get(0);
			budget = (int)receiveData.get(1);
	        ois.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		// Read in data from SelectPlants screen
		
		ArrayList<PlantInfoModel> plants2 = null;
		try {
			FileInputStream fis = new FileInputStream("plantData.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        plants2 = (ArrayList<PlantInfoModel>)ois.readObject();
	        ois.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		// Initialize & Add plants to view
		this.view=view;
		this.gardenView = gardenView;

		for (PlantModel plant : plants2) {
			gardenView.getPlantInput().add(new Pair<>(plant.getSciName(), plant.getSpreadDiameter()));
		}
		gardenView.setBudget(budget);
		gardenView.makeCanvas(plots);
		
		// Initialize & Add plants to model
		ObjectCarouselModel carouselModel = gardenView.getPlantCarousel().getController().carouselModel;
		this.gardenModel = new PlantGardenModel(carouselModel, plants2, plots, budget);		
	}
	
	// Screen control
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));	
	}
	
	public void clickNext(ActionEvent event) {}
	
	public void clickExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	// Saving garden
	
	public void clickedSave(ActionEvent event) {
		
		TextInputDialog savePopup = new TextInputDialog("type name here!");
		savePopup.setHeaderText("Enter the name of your garden:");
		Optional<String> inputName = savePopup.showAndWait(); 
		
		if (inputName.isPresent()) {
			System.out.println(inputName);
		}
		else {
			inputName = Optional.ofNullable("New Garden");
		}
		
		HashMap<String, PlantGardenModel> gardenData = new HashMap<String, PlantGardenModel>();
		gardenData.put(inputName.get(), gardenModel);
		//HashMap<String, ArrayList<PlantModel>, Stack<ArrayList<Point2D.Double>>> gardenDataBundled = new HashSet<>();
		try {
			FileOutputStream fos = new FileOutputStream("finalGardenData.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(gardenData);
	        oos.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        }
		
	}
		
	// Handle drag
	
	public void drag(MouseEvent event) {
		Node n = (Node)event.getSource();
		
		int index = gardenView.getPlants().indexOf(n);
		
		gardenModel.dragPlant(index, event.getX(), event.getY(),
				gardenView.getGarden().getWidth() - gardenView.getPlants().get(index).getFitHeight(), 
				gardenView.getGarden().getHeight() - gardenView.getPlants().get(index).getFitHeight());
		
		double x_loc = gardenModel.getPlants().get(index).getX();
		double y_loc = gardenModel.getPlants().get(index).getY();
		gardenView.setX( index, x_loc );
		gardenView.setY( index, y_loc );
		
		gardenView.drawSpread(index, x_loc, y_loc);
				
		for (PlantView plant : gardenView.getPlants()) {
			int spreadIndex = gardenView.getPlants().indexOf(plant);
			gardenView.updateSpread(
					spreadIndex, 
					gardenModel.checkCanvas(spreadIndex, gardenView.getCanvas().getLayoutX(), gardenView.getCanvas().getLayoutY()),
					gardenModel.checkSpread(spreadIndex)
					);
		}
		return;
	}
	
	// Handle press, extract from carousel if necessary
	
	public void press(MouseEvent event) {
		
		Node n = (Node)event.getSource();
		
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			
			int indexCarousel = gardenView.getPlantCarousel().getPlants().indexOf(n);
			
			gardenModel.getCarousel().replacePlant(indexCarousel);									// Subtract 1 from model carousel index b/c it does not contain compost
			gardenModel.addPlantFromCarousel(indexCarousel, 0, 0);	
			
			gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps());
			gardenView.replacePlant(indexCarousel);
			gardenView.addPlantFromCarousel(indexCarousel, n, event);
		}
		return;
	}
	
	// Used to run startFullDrag(), which can only be run inside setOnDragDetected.
	// Makes JavaFX start delivering drag events WITHOUT interfering with mouse events!
	// This lets me do stuff to what's UNDER what I'm dragging
	
	public void dragDetect(MouseEvent event, PlantView pv) {
		//pv.startFullDrag();
		System.out.print("drag detected       ");
		return;
	}
	
	public void release(MouseEvent event) {
		
	}
	

	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForSave() {
		return event -> clickedSave((ActionEvent) event);
	}
	
	public EventHandler getHandlerForExit() {
		return event -> clickExit((ActionEvent) event);
	}
	
	
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	public EventHandler getHandlerForRelease() {
		return event -> release((MouseEvent) event);
	}
	
	public EventHandler getHandlerForDragDetect(PlantView pv) {
		return event -> dragDetect((MouseEvent) event, pv);
	}
	
	
	
}
