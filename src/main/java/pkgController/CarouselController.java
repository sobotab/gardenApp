package pkgController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.CarouselModel;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.PlantView;
import pkgView.View;

public abstract class CarouselController {
	
	View view;
	Model model;
	CarouselModel carouselModel; 
	CarouselView carouselView;
	
	public CarouselController() {
		model = new Model();
		carouselModel = new CarouselModel(model.makePlants(), 2);
	}
	
	public CarouselController(View view, CarouselView carouselView) {
		this.view = view;
		this.carouselView = carouselView;
		model = new Model();
		carouselModel = new CarouselModel(model.makePlants(), 2);
	}
	
	
	public void clickedRight(ActionEvent event) {
		carouselView.rotateRight();
		carouselModel.rotateRight();
	}
	
	public void clickedLeft(ActionEvent event) {
		carouselView.rotateLeft();
		carouselModel.rotateLeft();
	}
	
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
	
	
	
	public List<ImageView> getImagesFromList(){
		List<PlantModel> plants = carouselModel.getPlants();
		List<ImageView> images = new ArrayList<>();
		for(PlantModel plant: plants) {
			String sciName = plant.getSciName();
			Image image = new Image(getClass().getResourceAsStream("/images/" + sciName + ".jpg"));
			ImageView img = new ImageView(image);
			images.add(img);
		}
		return images;
	}

	public CarouselModel getCarouselModel() {
		return carouselModel;
	}

	public void setCarouselModel(CarouselModel carouselModel) {
		this.carouselModel = carouselModel;
	}
	
}
