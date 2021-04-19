package pkgController;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.View;

public class InfoCarouselController extends CarouselController {
		View view;
		InfoCarouselView icv;
		private final double CENTER_IMAGE_SCALING = 1.3;
		private final double CENTER_X = 400.0;

		public InfoCarouselController(View view, CarouselView carouselView) {
			super(view, carouselView);
			icv = (InfoCarouselView)carouselView;
		}
		
		public void clickedPopup(MouseEvent event) {
			ImageView img = (ImageView)event.getSource();
			int index = 0;
			if(img.getScaleX() == CENTER_IMAGE_SCALING) {
				index = carouselModel.getHeldPlant();
			}
			else if(event.getSceneX() < CENTER_X) {
				index = carouselModel.getHeldPlant() - 1;
				if(index < 0) {
					index = carouselModel.getFilteredPlants().size() - 1;
				}
			}
			else {
				index = carouselModel.getHeldPlant() + 1;
				if(index == carouselModel.getFilteredPlants().size()) {
					index = 0;
				}
			}
			PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
			icv.openInfoPopUp(view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription());
		}
		
		public EventHandler getHandlerForPopup() {
			return event -> clickedPopup((MouseEvent) event);
		}
		
		public void filterCarousel(String sun, String moisture, String soil) {
			List<PlantModel> plants = carouselModel.getPlants();
			List<ImageView> images = icv.getImages();
			List<PlantModel> filteredPlants = new ArrayList<>();
			List<ImageView> filteredImages = new ArrayList<ImageView>();
			for(int i = 0; i < plants.size(); i++) {
				PlantModel plant = plants.get(i);
				String sunLevel = plant.getSun().getLevel();
				String moistureLevel = plant.getMoisture().getLevel();
				String soilType = plant.getSoil().getLevel();
				if(sunLevel.startsWith(sun) && moistureLevel.startsWith(moisture) && soilType.startsWith(soil)) {//use startWith instead of equals so the empty string will reset the carousel
					filteredImages.add(images.get(i));
					filteredPlants.add(plants.get(i));
				}
			}
			icv.setFilteredImages(filteredImages);
			carouselModel.setFilteredPlants(filteredPlants);
			icv.update();
		}
		
}
