package pkgController;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
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
	
	public EditGardenController(View view, EditGardenView gardenView, String loadName) {	
		
		HashMap<String, PlantGardenModel> gardenData = null;
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = null;
		int budget = 0;
		int scale = 25;
		
		if (loadName != null) {
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
			
			this.view=view;
			this.gardenView = gardenView;
			
			this.gardenModel = gardenData.get(loadName);
			for (PlantModel plant : gardenModel.getCarousel().getPlants()) {
				gardenView.getPlantInput().add(new Pair<>(plant.getSciName(), plant.getSpreadDiameter()));
			}
			gardenView.getPlantCarousel().getController().carouselModel = gardenModel.getCarousel();
			gardenView.setBudget(gardenModel.getBudget());
			gardenView.makeCanvas(gardenModel.getPlots());
			gardenView.setScale(gardenModel.getScale());
		} 
		
		else {
			
			// Read in data from DrawGarden screen

			try {
				FileInputStream fis = new FileInputStream("gardenData.ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        ArrayList<Object> receiveData = (ArrayList<Object>)ois.readObject();
		        plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)receiveData.get(0);
				budget = (int)receiveData.get(1);
				scale = (int)receiveData.get(4);
				System.out.println("whats in data4: " + scale);
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
			gardenView.setScale(scale);
			
			// Initialize & Add plants to model
			ObjectCarouselModel carouselModel = gardenView.getPlantCarousel().getController().carouselModel;
			this.gardenModel = new PlantGardenModel(carouselModel, plants2, plots, budget, scale);		
			gardenModel.setScale((int)scale);

		}
	}
	
	// Screen control
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));	
	}
	
	public void clickNext(ActionEvent event) {}
	
	public void clickExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	// Change coordinates when window size changes
	
	public void fitCoordinatesToWindowWidth(double oldWidth, double newWidth) {
		Iterator plantViewIter = gardenView.getPlants().iterator();
		for (PlantObjectModel plantModel : gardenModel.getPlants()) {
			plantModel.setXInBounds( 
					plantModel.getX() + (newWidth - oldWidth),  
					gardenView.getGarden().getWidth() - 30);
			
			PlantView plantView = (PlantView)plantViewIter.next();
			plantView.setTranslateX( plantModel.getX() );
			int index = gardenView.getPlants().indexOf(plantView);
			gardenView.drawSpread(
					index, 
					plantModel.getX(), 
					plantModel.getY());
			gardenView.updateSpread(
					index, 
					gardenModel.checkCanvas(index, gardenView.getCanvas().getLayoutX(), gardenView.getCanvas().getLayoutY()),					
					gardenModel.checkSpread(index));
		}
	}
	
	public void fitCoordinatesToWindowHeight(double oldHeight, double newHeight) {
		Iterator plantViewIter = gardenView.getPlants().iterator();
		for (PlantObjectModel plantModel : gardenModel.getPlants()) {
			plantModel.setYInBounds( 
					plantModel.getY() + (newHeight - oldHeight),
					gardenView.getGarden().getWidth() - 30);
			
			PlantView plantView = (PlantView)plantViewIter.next();
			plantView.setTranslateY( plantModel.getY() );
			int index = gardenView.getPlants().indexOf(plantView);
			gardenView.drawSpread(
					index, 
					plantModel.getX(), 
					plantModel.getY());
			gardenView.updateSpread(
					index, 
					gardenModel.checkCanvas(index, gardenView.getCanvas().getLayoutX(), gardenView.getCanvas().getLayoutY()),
					gardenModel.checkSpread(index));
		}
	}
	
	
	// Updating view after loading a saved garden
	
	public void fetchGardenInfo() {
		gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps());
		for (PlantObjectModel plantInModel : gardenModel.getPlants()) {
			PlantView plantInView = gardenView.makePlantView(plantInModel.getSciName(), plantInModel.getSpreadDiameter());
			plantInView.setFitHeight(plantInModel.getSpreadDiameter()/4 + 20);
			plantInView.setFitWidth(plantInModel.getSpreadDiameter()/4 + 20);	
			
			gardenView.getPlants().add(plantInView);
			gardenView.getGarden().getChildren().add(plantInView);
			gardenView.getGarden().setAlignment(plantInView, Pos.TOP_LEFT);
			plantInView.updateLocation(plantInModel.getX(), plantInModel.getY());
			
			int viewIndex = gardenView.getPlants().size()-1;
			gardenView.drawSpread(
					viewIndex, 
					plantInModel.getX(), 
					plantInModel.getY());
			gardenView.updateSpread(
					viewIndex, 
					gardenModel.checkCanvas(viewIndex, gardenModel.getCanvasXOffset(), gardenModel.getCanvasYOffset()), 
					gardenModel.checkSpread(viewIndex));
		}
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
		
		// Load existing data
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
		
		gardenData.put(inputName.get(), gardenModel);
		
		// Re-save with appended info
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
		System.out.println("plant in model: " + gardenModel.getPlants().get(index).getName());
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
	
	// Update dollars/leps when plant dropped into garden or compost
	
	public void release(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
		//System.out.println("index " + index);
		PlantObjectModel plantModel = gardenModel.getPlants().get(index);
		if (plantModel.getX() <= 60 && plantModel.getY() >= gardenView.getGarden().getHeight() - 60) {
			
			gardenModel.setDollars(gardenModel.getDollars() - plantModel.getDollars());
			gardenModel.setNumLeps(gardenModel.getNumLeps() - plantModel.getNumLeps());
			gardenModel.getPlants().remove(plantModel);
			
			PlantView trashPlant = gardenView.getPlants().remove(index);
			Circle trashSpread = gardenView.getSpreads().remove(index);
			
			gardenView.getGarden().getChildren().remove(trashPlant);
			gardenView.getGarden().getChildren().remove(trashSpread);
			gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps());
		}
		return;
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
	
	
}
