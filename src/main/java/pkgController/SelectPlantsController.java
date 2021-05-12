package pkgController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pkgModel.CarouselModel;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.DrawGardenView;
import pkgView.EditGardenView;
import pkgView.SelectCarouselView;
import pkgView.SelectPlantsView;
import pkgView.View;
/**
 * 
 * @author Zane Greenholt
 * Controller that holds the handlers for the select plants screen
 */
public class SelectPlantsController {
	/**
	 * The program's View that is only initialized once
	 */
	View view;
	/**
	 * A SelectCarouselController that holds handlers for the select plants screen's carousel
	 */
	SelectCarouselController scc;
	/**
	 * A SelectPlantsView that holds all the Nodes for the select plants screen
	 */
	SelectPlantsView spv;
	/**
	 * double representing the scaling of the image that is in the carousel's focus
	 */
	private final double CENTER_IMAGE_SCALING = 2.0;
	
	/**
	 * Constructor simply initializes fields with the corresponding arguments
	 * 
	 * @param view The program's View that is only initialized once
	 * @param spv SelectPlantsView that holds all the Nodes for the select plants screen
	 * @param scc SelectCarouselController that holds handlers for the select plants screen's carousel
	 */
	public SelectPlantsController(View view, SelectPlantsView spv, SelectCarouselController scc) {
		this.view = view;
		this.spv = spv;
		this.scc = scc;
	}
	/**
	 * Handler for clicking back to the previous screen. It takes the user to the drawGarden screen.
	 * 
	 * @param event An ActionEvent that is a mouse click
	 */ 
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view, true));
	}
	
	/**
	 * Handler for clicking to the next screen. It takes the user to the editGarden screen after serializing the plants that were selected 
	 * so they can be correctly added to the garden.
	 * 
	 * @param event an ActionEvent that is a mouse click
	 */
	public void clickNext(ActionEvent event) {
		
		// Send plants info
		
		ArrayList<PlantInfoModel> plantList = new ArrayList<PlantInfoModel>();
		for (PlantModel plant : scc.carouselModel.selectedPlants.values()) {
			plantList.add((PlantInfoModel)plant);
		}
		
		
		try {
			FileOutputStream fos = new FileOutputStream("plantData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(plantList);
            oos.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        	e.printStackTrace();
        } 
		view.setCurrentScreen(new EditGardenView(view, null));
	}
	
	/**
	 * Handler for when a plant in the carousel is clicked. It removes the plant from the carousel and adds it to the lists of selectedPlants/images
	 * in the corresponding model and view
	 * 
	 * @param event A MouseEvent that is the mouse being clicked
	 */
	public void plantSelected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		Button button = (Button)event.getSource();
		//Get the VBox that is the parent of the clicked button
		VBox img = (VBox)button.getParent();
		//Make sure the correct popup Handler is set once the plant moves to selectedPlants area
		img.setOnMousePressed(getHandlerForSelectedPlantPopup());
		//Keep track of attributes of the plant in the middle of the carousel
		int centerIndex = carouselModel.getHeldPlant();
		VBox centerImage = carouselView.getFilteredImages().get(centerIndex);
		double centerX = centerImage.getLayoutX();
		int index = 0;
		//Determine if the plant is the left, middle, or right from the carousel
		if(img.getScaleX() == CENTER_IMAGE_SCALING) {
			index = centerIndex;
		}
		else if(event.getSceneX() < centerX) {
			index = centerIndex - 1;
			if(index < 0) {
				index = carouselModel.getFilteredPlants().size() - 1;
			}
		}
		else {
			index = centerIndex + 1;
			if(index >= carouselModel.getFilteredPlants().size()) {
				index = 0;
			}
		}
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
		carouselModel.getFilteredPlants().remove(index);
		//Decrement indexes in case the removed plant was the last to avoid indexOutOfBoundsException
		carouselModel.decrementHeldPlant();
		//Decrement indexes in case the removed plant was the last to avoid indexOutOfBoundsException
		carouselView.getFilteredImages().remove(index);
		carouselView.decrementCenter();
		carouselView.update();
		//Make sure both the plant and image list are updated
		spv.selectPlant(img);
		carouselModel.selectPlant(plant);
		spv.updateNumPlants();
	}
	
	/**
	 * Getter for the plantSelected handler
	 * 
	 * @return EventHandler for the plantSelected method
	 */
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((MouseEvent) event);
	}
	
	/**
	 * Handler for when an image is clicked in the selected plants flowPane. When the image is clicked it is removed from selected plants
	 * lists and added back to the carousel
	 * 
	 * @param event A MouseEvent that is the mouse being clicked
	 */
	public void plantDeselected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		Button button = (Button)event.getSource();
		//VBox that is the parent of the clicked button
		VBox img = (VBox)button.getParent();
		//When the VBox goes back to the carousel, the popup handler returns to the carousel one
		img.setOnMousePressed(scc.getHandlerForPopup());
		spv.deSelectPlant(img);
		//Get the common name of the plant from the VBox text
		Text text = (Text)img.getChildren().get(0);
		String[] plantNames = text.getText().split("\n");
		String name = plantNames[0];
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getSelectedPlants().get(name);
		carouselModel.getSelectedPlants().remove(name);
		//Get user selected attributes to check if the plant should go back in the carousel
		String sun = scc.getSun();
		String moisture = scc.getMoisture();
		List<String> soils = scc.getSoil();
		String type = carouselView.getType();
		String soil = carouselView.getSoil();
		//Only add the plant back to the carousel immediately if the current filter types match
		if(checkPlantConditions(plant,type,soil,sun,moisture,soils)) {
			ArrayList<PlantModel> filteredPlants = (ArrayList<PlantModel>)carouselModel.getFilteredPlants();
			Iterator<PlantModel> it = filteredPlants.iterator();
			int index = 0;
			boolean found = false;
			//Find the correct spot to place the plant so that the sorting by lep count is maintained
			while(it.hasNext() && !found) {
				PlantModel currentPlant = it.next();
				if(plant.getNumLeps() < currentPlant.getNumLeps()) {
					index++;
				}
				else {
					found = true;
				}
			}
			//Add to both the image and plantModel lists
			carouselModel.getFilteredPlants().add(index, plant);
			carouselView.getFilteredImages().add(index, img);
		}
		carouselView.update();
		spv.updateNumPlants();
	}
	/**
	 * Helper method that checks if a plant matches current user selected conditions on the selectPlants screen
	 * 
	 * @param plant The plantModel that is currently being considered
	 * @param type The plant type chosen by the user
	 * @param soil A specific soil type chosen by the user
	 * @param sun  The sun level chosen by the user
	 * @param moisture The moisture level chosen by the user
	 * @param soils A list of all soils that were placed on the drawGarden screen
	 * @return boolean representing whether or not the plant fits the current conditions
	 */
	public boolean checkPlantConditions(PlantInfoModel plant, String type, String soil, String sun, String moisture, List<String> soils) {
		boolean correctSoil = false;
		//If there is a specific soil type chosen, do not check the list of all soils in the garden
		//Otherwise, see if the plant belongs in any soil type the garden has
		if(soil == "") {
			for(String soilType: soils) {
				if(plant.getSoil().contains(soilType)) {
					correctSoil = true;
				}
			}
		}
		else {
			correctSoil = true;
		}
		//Determine if the plant is woody or herbaceous based on the price
		String plantType = "";
		if(plant.getDollars() == 6) {
			plantType = "herbaceous";
		}
		else {
			plantType = "woody";
		}
		//Get the plants attributes
		String plantSoil = plant.getSoil();
		String plantSun = plant.getSun();
		String plantMoisture = plant.getMoisture();
		//Check the conditions
		return(plantSoil.contains(soil) && plantSun.contains(sun) && plantMoisture.contains(moisture) && plantType.contains(type) && correctSoil);
	}
	
	/**
	 * Handler for infoPopups when plant VBoxes are clicked in the selectedPlants listView
	 * 
	 * @param event A MouseEvent representing a click
	 */
	public void selectedPlantPopup(MouseEvent event) {
		VBox box = (VBox)event.getSource();
		ImageView imv = (ImageView)box.getChildren().get(1);
		//Get the plant's common name from the VBox text
		Text nameText = (Text)box.getChildren().get(0);
		String commonName = nameText.getText().split("\n")[0];
		HashMap<String, PlantModel> selectedPlants = scc.getCarouselModel().getSelectedPlants();
		//Determine the plant that was clicked using the HashMap of names to plantModels
		PlantInfoModel plant = (PlantInfoModel)selectedPlants.get(commonName);
		CarouselView carouselView = spv.getSelectionCarousel();
		carouselView.openInfoPopUp(view, imv, commonName, plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription(), plant.getLeps());
	}
	
	/**
	 * Getter for the selectedPlantPopup handler
	 * 
	 * @return An EventHandler for selectedPlantPopup
	 */
	public EventHandler getHandlerForSelectedPlantPopup() {
		return event -> selectedPlantPopup((MouseEvent) event);
	}
	
	/**
	 * Getter for the plantDeselected handler
	 * 
	 * @return EventHandler for the plantDeselected method
	 */
	public EventHandler getHandlerForPlantDeSelected() {
		return event -> plantDeselected((MouseEvent) event);
	}
	
	/**
	 * Getter for the clickedBack handler
	 * 
	 * @return EventHandler for the clickedBack method
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedNext handler
	 * 
	 * @return EventHandler for the clickedNext method
	 */
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
}
