package pkgController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.SelectCarouselView;
import pkgView.View;

public class SelectCarouselController extends CarouselController {
	View view;
	SelectCarouselView scv;
		
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
		scv = (SelectCarouselView)carouselView;
	}

	
	public void filterCarousel(String sun, String moisture, String soil) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<VBox> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<VBox> filteredImages = new ArrayList<VBox>();
		Iterator<PlantModel> plantIter = plants.iterator();
		Iterator<VBox> imageIter = images.iterator();
		while(plantIter.hasNext() && imageIter.hasNext()) {
			PlantInfoModel plant = (PlantInfoModel)plantIter.next();
			VBox imageBox = imageIter.next();
			String sunLevel = plant.getSun();
			String moistureLevel = plant.getMoisture();
			String soilType = plant.getSoil();
			if(sunLevel.contains(sun) && moistureLevel.contains(moisture) && soilType.contains(soil)) {
				filteredPlants.add(plant);
				filteredImages.add(imageBox);
			}
		}
		
		scv.setFilteredImages(filteredImages);
		scv.setCenter(0);
		carouselModel.setFilteredPlants(filteredPlants);
		carouselModel.setHeldPlant(0);
		scv.update();
	}

	public SelectCarouselView getScv() {
		return scv;
	}

	public void setScv(SelectCarouselView scv) {
		this.scv = scv;
	}
	
	
}
