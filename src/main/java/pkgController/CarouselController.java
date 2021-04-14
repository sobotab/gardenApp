package pkgController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pkgModel.CarouselModel;
import pkgModel.Model;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.PlantView;
import pkgView.View;

public abstract class CarouselController {
	
	View view;
	Model model = new Model();
	CarouselModel carouselModel; 
	CarouselView carouselView;
	
	public CarouselController(View view, CarouselView carouselView) {
		this.view = view;
		this.carouselView = carouselView;
		carouselModel = new CarouselModel(model.makePlants(), 2);
	}
	
	public void clickedRight(ActionEvent event) {
		carouselView.rotateRight();
	}
	
	public void clickedLeft(ActionEvent event) {
		carouselView.rotateLeft();
	}
	
	public EventHandler getHandlerForClickedRight() {
		return event -> clickedRight((ActionEvent) event);
	}
	
	public EventHandler getHandlerForClickedLeft() {
		return event -> clickedLeft((ActionEvent) event);
	}
	
	public List<ImageView> getImagesFromList(){
		Set<PlantModel> plants = carouselModel.getPlants();
		List<ImageView> images = new ArrayList<>();
		for(PlantModel plant: plants) {
			String sciName = plant.getSciName();
			Image image = new Image(getClass().getResourceAsStream("/images/" + sciName + ".jpg"));
			System.out.println("created image");
			ImageView img = new ImageView(image);
			images.add(img);
		}
		return images;
	}
	
}
