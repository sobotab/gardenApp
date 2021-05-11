package pkgController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	 * @param event An ActionEvent that is a mouse click
	 */ 
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view));
	}
	
	/**
	 * Handler for clicking to the next screen. It takes the user to the editGarden screen after serializing the plants that were selected 
	 * so they can be correctly added to the garden.
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
	 * @param event A MouseEvent that is the mouse being clicked
	 */
	public void plantSelected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		Button button = (Button)event.getSource();
		VBox img = (VBox)button.getParent();
		int centerIndex = carouselModel.getHeldPlant();
		VBox centerImage = carouselView.getFilteredImages().get(centerIndex);
		double centerX = centerImage.getLayoutX();
		int index = 0;
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
		carouselModel.decrementHeldPlant();
		carouselView.getFilteredImages().remove(index);
		carouselView.decrementCenter();
		carouselView.update();
		spv.selectPlant(img);
		carouselModel.selectPlant(plant);
	}
	
	/**
	 * Getter for the plantSelected handler
	 * @return EventHandler for the plantSelected method
	 */
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((MouseEvent) event);
	}
	
	/**
	 * Handler for when an image is clicked in the selected plants flowPane. When the image is clicked it is removed from selected plants
	 * lists and added back to the carousel
	 * @param event A MouseEvent that is the mouse being clicked
	 */
	public void plantDeselected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		Button button = (Button)event.getSource();
		VBox img = (VBox)button.getParent();		
		spv.deSelectPlant(img);
		Text text = (Text)img.getChildren().get(0);
		String[] plantNames = text.getText().split("\n");
		String name = plantNames[0];
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getSelectedPlants().get(name);
		carouselModel.getSelectedPlants().remove(name);
		ArrayList<PlantModel> filteredPlants = (ArrayList<PlantModel>)carouselModel.getFilteredPlants();
		Iterator<PlantModel> it = filteredPlants.iterator();
		int index = 0;
		boolean found = false;
		while(it.hasNext() && !found) {
			PlantModel currentPlant = it.next();
			if(plant.getNumLeps() < currentPlant.getNumLeps()) {
				index++;
			}
			else {
				found = true;
			}
		}
		carouselModel.getFilteredPlants().add(index, plant);
		carouselView.getFilteredImages().add(index, img);
		carouselView.update();
	}
	
	/**
	 * Getter for the plantDeselected handler
	 * @return EventHandler for the plantDeselected method
	 */
	public EventHandler getHandlerForPlantDeSelected() {
		return event -> plantDeselected((MouseEvent) event);
	}
	
	/**
	 * Getter for the clickedBack handler
	 * @return EventHandler for the clickedBack method
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedNext handler
	 * @return EventHandler for the clickedNext method
	 */
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
}
