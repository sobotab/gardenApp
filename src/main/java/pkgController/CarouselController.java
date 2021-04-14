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
	Model model = new Model();
	CarouselModel carouselModel; 
	CarouselView carouselView;
	InfoCarouselView icv;
	
	public CarouselController(View view, InfoCarouselView icv) {
		this.view = view;
		//this.carouselView = carouselView;
		this.icv = icv;
		carouselModel = new CarouselModel(model.makePlants(), 2);
	}
	
	public void clickedRight(ActionEvent event) {
		icv.rotateRight();
		carouselModel.rotateRight();
	}
	
	public void clickedLeft(ActionEvent event) {
		icv.rotateLeft();
		carouselModel.rotateLeft();
	}
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
	
	public void clickedPopup(MouseEvent event) {
		ImageView img = (ImageView)event.getSource();
		System.out.println(event.getSceneX());
		int index = 0;
		if(img.getScaleX() == 1.5) {
			index = carouselModel.getHeldPlant();
		}
		else if(event.getSceneX() < 400.0) {
			index = carouselModel.getHeldPlant() - 1;
			if(index < 0) {
				index = carouselModel.getPlants().size() - 1;
			}
		}
		else {
			index = carouselModel.getHeldPlant() + 1;
			if(index == carouselModel.getPlants().size()) {
				index = 0;
			}
		}
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
		icv.openInfoPopUp(view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription());
	}
	
	public EventHandler getHandlerForPopup() {
		return event -> clickedPopup((MouseEvent) event);
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
	
}
