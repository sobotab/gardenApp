package pkgController;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
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
	
	public void plantSelected(ActionEvent event) {
		
	}
	
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((ActionEvent) event);
	}
	
	public void filterCarousel(String sun, String moisture, String soil) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<ImageView> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<ImageView> filteredImages = new ArrayList<ImageView>();
		for(int i = 0; i < plants.size(); i++) {
			PlantInfoModel plant = (PlantInfoModel)plants.get(i);
			String sunLevel = plant.getSun().getLevel();
			String moistureLevel = plant.getMoisture().getLevel();
			String soilType = plant.getSoil().getLevel();
			//use startWith instead of equals so the empty string will reset the carousel
			if(sunLevel.startsWith(sun) && moistureLevel.startsWith(moisture) && soilType.startsWith(soil)) {
				filteredImages.add(images.get(i));
				filteredPlants.add(plants.get(i));
			}
		}
		scv.setFilteredImages(filteredImages);
		carouselModel.setFilteredPlants(filteredPlants);
		scv.update();
	}
	
	
}
