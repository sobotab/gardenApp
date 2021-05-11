package pkgController;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;

import javax.imageio.ImageIO;

import javafx.embed.swing.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
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
import pkgView.PlantView;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;
/**
 * 
 * @author Ryan Dean
 * Controller for Edit Garden screen. Holds handlers and facilitates communication between EditGardenView and EditGardenModel.
 */
public class EditGardenController {
	/**
	 * The program's view. Initialized only once.
	 */
	View view;
	/**
	 * View class for this screen. Holds all nodes necessary for the screen.
	 */
	EditGardenView gardenView;
	/**
	 * Model class for this screen. Holds all plant and garden data, and has methods for calculating plant interactions.
	 */
	PlantGardenModel gardenModel;
	
	/**
	 * Constructor loads a serialized gardenModel and disperses that data to model and view if loadName present. If not, 
	 * reads serialized information from Select Plants and Draw Garden screens, and disperses that data to model and view instead.
	 * @param view 			EditGardenView that is the view class for this screen. Information necessary for display is sent there.
	 * @param gardenView 	EditGardenModel, the model class for this screen. All garden and plant information is sent there.
	 * @param loadName 		String representing user-input name for the garden to load. Null if not loading a garden.
	 */
	public EditGardenController(View view, EditGardenView gardenView, String loadName) {	
		
		HashMap<String, PlantGardenModel> gardenData = null;
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = null;
		int budget = 0;
		double max_dimension = 45.0;	// default in case scale cannot be read
		
		// Loading a serialized gardenModel
		
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
				ArrayList<String> string_info = new ArrayList<String>();
				string_info.add(plant.getSciName());
				string_info.add(plant.getName());
				string_info.add(plant.getSoil());
				Pair<ArrayList<String>, Integer> plant_info = new Pair<>(string_info, plant.getSpreadDiameter());
				gardenView.getPlantInput().add(plant_info);
			}
			
			gardenView.getPlantCarousel().getController().carouselModel = gardenModel.getCarousel();
			gardenView.setBudget(gardenModel.getBudget());
			gardenView.makeCanvas(gardenModel.getPlots());
			gardenView.setScaleFactor(gardenModel.getScaleFactor());
		} 
		
		// Making a new garden, instead read data from previous screens.
		
		else {
			
			// Read in data from DrawGarden screen

			try {
				FileInputStream fis = new FileInputStream("gardenData.ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        ArrayList<Object> receiveData = (ArrayList<Object>)ois.readObject();
		        plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)receiveData.get(0);
				budget = (int)receiveData.get(1);
				max_dimension = (double)receiveData.get(5);
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
		        System.out.println("size: " + plants2.size());
		        ois.close();
			} catch (FileNotFoundException e) {
	        	System.out.println("File not found");
	        } catch (IOException e) {
	        	System.out.println("Error initializing stream");
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	        }
			double scale_factor = gardenView.DEFAULTSCALE / max_dimension;
					
			// Initialize & Add plants to view
			this.view=view;
			this.gardenView = gardenView;
	
			for (PlantInfoModel plant : plants2) {
				ArrayList<String> string_info = new ArrayList<String>();
				string_info.add(plant.getSciName());
				string_info.add(plant.getName());
				string_info.add(plant.getSoil());
				Pair<ArrayList<String>, Integer> plant_info = new Pair<>(string_info, plant.getSpreadDiameter());
				gardenView.getPlantInput().add(plant_info);
			}
			
			// Initialize & Add plants to model
			ObjectCarouselModel carouselModel = gardenView.getPlantCarousel().getController().carouselModel;
			this.gardenModel = new PlantGardenModel(carouselModel, plants2, plots, budget, scale_factor);		
			gardenModel.adaptPlots(gardenView.CANVASWIDTH - 10, gardenView.CANVASHEIGHT - 10);
			
			System.out.println("pre scaling: " + scale_factor);
			System.out.println("post scaling: " + gardenModel.getScaleFactor());
			gardenView.setBudget(budget);
			gardenView.makeCanvas(plots);
			gardenView.setScaleFactor(gardenModel.getScaleFactor());
		}
	}
	
		
	/**
	 * Handler for clicking back button. Returns user to previous screen: Select Plants.
	 * @param event 	The ActionEvent caused by clicking the back button.
	 */
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));	
	}
	
	/**
	 * Handler for clicking exit button. Returns user to Welcome screen.	
	 * @param event 	The ActionEvent caused by clicking the exit button.
	 */
	public void clickedExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	/**
	 * Handler for clicking the "See more" button. Opens pop-up showing all supported lep species.
	 * @param event 	The ActionEvent caused by clicking the "See more" button.
	 */
	public void clickedMoreLeps(ActionEvent event) {
		gardenView.openLepPopup(gardenModel.trackMostPopularLeps());
	}
	
	/**
	 * Handler for clicking the save button. Opens prompt for garden name, then serializes gardenModel.
	 * @param event 	The ActionEvent caused by clicking the save button.
	 */
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
	
	/**
	 * Handler for clicking print button. Saves a snapshot of the garden stackpane in EditGardenView.
	 * @param event 	The ActionEvent caused by clicking the print button.
	 */
	public void clickedPrint(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG", "*.png");
		fileChooser.getExtensionFilters().add(extensionFilter);
		File imgFile = fileChooser.showSaveDialog(view.getTheStage());
		if (imgFile != null) {
			try {
				WritableImage canvasImg = new WritableImage((int)(gardenView.CANVASWIDTH * 1.4), (int)(gardenView.CANVASHEIGHT * 1.05));
				gardenView.getGarden().snapshot(null, canvasImg);
				BufferedImage bufferedImage = SwingFXUtils.fromFXImage(canvasImg, null);
				ImageIO.write(bufferedImage, "png", imgFile);
			}
			catch (IOException e) {
				System.out.println("Error initializing stream");
			}
		}
	}
	
	
	/**
	 * Helper method used when loading a saved garden. Updates view according to all data stored in the loaded gardenModel.
	 */
	public void fetchGardenInfo() {
		gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps(), gardenModel.trackMostPopularLeps());
		for (PlantObjectModel plantInModel : gardenModel.getPlants()) {
			ArrayList<String> plant_info = new ArrayList<String>();
			plant_info.add(plantInModel.getSciName());
			plant_info.add(plantInModel.getName());
			plant_info.add(plantInModel.getSoil());
			PlantView plantInView = gardenView.makePlantView(
					plant_info,
					plantInModel.getSpreadDiameter());
			plantInView.setFitHeight(plantInModel.getSpreadDiameter()/4 + 30);
			plantInView.setFitWidth(plantInModel.getSpreadDiameter()/4 + 30);	
			Rectangle plant_template = new Rectangle(plantInView.getFitWidth(), plantInView.getFitWidth());
			plant_template.setArcWidth(15);
			plant_template.setArcHeight(15);
			plantInView.setClip(plant_template);
			
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
	
		
	/**
	 * Handler for dragging a PlantView node. Updates position in model, then updates view accordingly. Runs checks for outside bounds/overlapping spreads.
	 * @param event 	The MouseEvent caused by dragging a PlantView node.
	 */
	public void drag(MouseEvent event) {
		Node n = (Node)event.getSource();
		
		int index = gardenView.getPlants().indexOf(n);
		if (index > -1) {
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
		}
		return;
	}
	
	/**
	 * Handler for clicking a PlantView node in the view's DragDropCarousel. Prompts model and view to transport plant from carousel to garden.
	 * @param event 	MouseEvent caused by clicking on a PlantView node.
	 */
	public void press(MouseEvent event) {
		
		Node n = (Node)event.getSource();
		
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			
			int indexCarousel = gardenView.getPlantCarousel().getPlants().indexOf(n);
			
			PlantView testPlant = gardenView.getPlantCarousel().getPlants().get(indexCarousel);
			if (gardenView.computeScaleSize(testPlant) >= gardenView.CANVASHEIGHT/2) {
				Stage errorPopup = new Stage();
				errorPopup.setTitle("Oops!");
				Label errorLabel = new Label("This plant is too big for your garden.\n"
						+ "Try returning to Draw Garden and changing your scale!");
				errorLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
				Scene scene = new Scene(errorLabel);
				errorPopup.setScene(scene);
				errorPopup.show();
				return;
			}
			
			gardenModel.getCarousel().replacePlant(indexCarousel);
			gardenModel.addPlantFromCarousel(indexCarousel, 0, 0);	
			
			gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps(), gardenModel.trackMostPopularLeps());
			gardenView.replacePlant(indexCarousel);
			gardenView.addPlantFromCarousel(indexCarousel, n);
			
		}
		return;
	}
	
	/**
	 * Handler for releasing mouse button after dragging a PlantView node. Prompts model to update budget/lep info and send that to view.
	 * @param event 	MouseEvent caused by releasing mouse button on PlantView node.
	 */
	public void release(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
		if (index != -1) {
			PlantObjectModel plantModel = gardenModel.getPlants().get(index);
			if (plantModel.getX() <= 80 && plantModel.getY() >= gardenView.getGarden().getHeight() - 80) {
				
				gardenModel.setDollars(gardenModel.getDollars() - plantModel.getDollars());
				gardenModel.setNumLeps(gardenModel.getNumLeps() - plantModel.getNumLeps());
				gardenModel.getPlants().remove(plantModel);
				
				PlantView trashPlant = gardenView.getPlants().remove(index);
				Shape trashSpread = gardenView.getSpreads().remove(index);
				
				gardenView.getGarden().getChildren().remove(trashPlant);
				gardenView.getGarden().getChildren().remove(trashSpread);
				gardenView.updateInfoPanel(gardenModel.getDollars(), gardenModel.getNumLeps(), gardenModel.trackMostPopularLeps());
			}
		}
		return;
	}
	
	/**
	 * Getter for the clickedBack handler.
	 * @return 		EventHandler for the clickedBack method.
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedSave handler.
	 * @return 		EventHandler for the clickedSave method.
	 */
	public EventHandler getHandlerForSave() {
		return event -> clickedSave((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedExit handler.
	 * @return 		EventHandler for the clickedExit method.
	 */
	public EventHandler getHandlerForExit() {
		return event -> clickedExit((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedPrint handler.
	 * @return 		EventHandler for the clickedPrint method.
	 */
	public EventHandler getHandlerForPrint() {
		return event -> clickedPrint((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedMoreLeps handler.
	 * @return 		EventHandler for the clickedMoreLeps method.
	 */
	public EventHandler getHandlerForMoreLeps() {
		return event -> clickedMoreLeps((ActionEvent) event);
	}
	
	/**
	 * Getter for the drag handler.
	 * @return 		EventHandler for the drag method.
	 */
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	/**
	 * Getter for the press handler.
	 * @return 		EventHandler for the press method.
	 */
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	/**
	 * Getter for the release handler.
	 * @return 		EventHandler for the release method.
	 */
	public EventHandler getHandlerForRelease() {
		return event -> release((MouseEvent) event);
	}

	
	
}
