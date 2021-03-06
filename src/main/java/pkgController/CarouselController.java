package pkgController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pkgModel.CarouselModel;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.PlantView;
import pkgView.View;

/**
 * 
 * @author Zane Greenholt
 * Abstract class that contains the basic functionality for a controller for a carousel. These functions include handlers for clicking the left and right buttons.
 * 
 */
public abstract class CarouselController {
	/**
	 * The program's View that is only initialized once
	 */
	View view;
	/**
	 * An instance of the Model 
	 */
	Model model;
	/**
	 * The model for carousels' data
	 */
	CarouselModel carouselModel; 
	/**
	 * The view for the carousels' images and Nodes
	 */
	CarouselView carouselView;
	/**
	 * The scaling for the image that is focused on by the carousel
	 */
	private final double CENTER_IMAGE_SCALING = 2.0;
	
	/**
	 * Constructor initializes all this class' fields
	 * 
	 * @param view The program's View that is only initialized once
	 * @param carouselView A CarouselView object 
	 */
	public CarouselController(View view, CarouselView carouselView) {
		this.view = view;
		this.carouselView = carouselView;
		model = new Model();
		carouselModel = new CarouselModel(view.getController().getPlants(), 1);
	}
	
	/**
	 * Handler that calls the rotateRight methods for both the model and view
	 * 
	 * @param event An ActionEvent of the mouse being clicked
	 */
	public void clickedRight(ActionEvent event) {
		carouselView.rotateRight();
		carouselModel.rotateRight();
	}
	/**
	 * Handler that calls the rotateLeft methods for both the model and view
	 * 
	 * @param event An ActionEvent of the mouse being clicked
	 */
	public void clickedLeft(ActionEvent event) {
		carouselView.rotateLeft();
		carouselModel.rotateLeft();
	}
	
	/**
	 * Getter for the clickedRight handler
	 * 
	 * @return EventHandler for clickedRight method
	 */
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedLeft handler
	 * 
	 * @return EventHandler for clickedLeft method
	 */
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
	
	/**
	 * Getter for the images that are only loaded once in the program.
	 * 
	 * @return includes images loaded in the program's controller
	 */
	public List<VBox> getImagesFromController(){
		return view.getController().getPlantImages();
	}
	
	/**
	 * Getter for the carouselModel field
	 * 
	 * @return carouselModel field - a CarouselModel object
	 */
	public CarouselModel getCarouselModel() {
		return carouselModel;
	}
	/**
	 * Setter for the carouselModel field
	 * 
	 * @param carouselModel A CarouselModel object that will replace the current carouselModel field
	 */
	public void setCarouselModel(CarouselModel carouselModel) {
		this.carouselModel = carouselModel;
	}
	
	/**
	 * Handler that is used for when an image is clicked in the info carousel. It opens a popup with information about the plant that
	 * matches the clicked image.
	 * 
	 * @param event A MouseEvent that is the mouse being pressed
	 */
	public void clickedPopup(MouseEvent event) {
		VBox box = (VBox)event.getSource();
		ImageView img = (ImageView)box.getChildren().get(1);
		int centerIndex = carouselModel.getHeldPlant();
		VBox centerImage = carouselView.getFilteredImages().get(centerIndex);
		double centerX = centerImage.getLayoutX();
		int index = 0;
		//Check if the center image was clicked
		if(box.getScaleX() == CENTER_IMAGE_SCALING) {
			index = centerIndex;
		}
		//Otherwise use the x position to determine whether the image is to the left of center..
		else if(event.getSceneX() < centerX) {
			index = centerIndex - 1;
			if(index < 0) {
				index = carouselModel.getFilteredPlants().size() - 1;
			}
		}
		//Or right of center
		else {
			index = centerIndex + 1;
			if(index >= carouselModel.getFilteredPlants().size()) {
				index = 0;
			}
		}
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
		carouselView.openInfoPopUp(this.view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription(), plant.getLeps());
	}
	
	/**
	 * Getter for the clickedPopup handler
	 * 
	 * @return EventHandler for the clickedPopup method
	 */
	public EventHandler getHandlerForPopup() {
		return event -> clickedPopup((MouseEvent) event);
	}
	
}
