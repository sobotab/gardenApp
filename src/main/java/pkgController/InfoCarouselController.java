package pkgController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
			VBox box = (VBox)event.getSource();
			ImageView img = (ImageView)box.getChildren().get(1);
			int index = 0;
			if(box.getScaleX() == CENTER_IMAGE_SCALING) {
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
				if(index >= carouselModel.getFilteredPlants().size()) {
					index = 0;
				}
			}
			PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
			icv.openInfoPopUp(view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription());
		}
		
		public EventHandler getHandlerForPopup() {
			return event -> clickedPopup((MouseEvent) event);
		}
		
		public void filterCarousel(String sun, String moisture, String soil, String type) {
			List<PlantModel> plants = carouselModel.getPlants();
			List<VBox> images = icv.getImages();
			List<PlantModel> filteredPlants = new ArrayList<>();
			List<VBox> filteredImages = new ArrayList<VBox>();
			Iterator<PlantModel> plantIter = plants.iterator();
			Iterator<VBox> imageIter = images.iterator();
			while(plantIter.hasNext() && imageIter.hasNext()) {
				PlantInfoModel plant = (PlantInfoModel)plantIter.next();
				VBox imageBox = imageIter.next();
				String sunLevel = plant.getSun().getLevel();
				String moistureLevel = plant.getMoisture().getLevel();
				String soilType = plant.getSoil().getLevel();
				int price = plant.getDollars();
				String plantType;
				if(price == 6) {
					plantType = "herbaceous";
				}
				else {
					plantType = "woody";
				}
				//use contains instead of equals so the empty string will reset the carousel, also one plant can have multiple soil or moisture levels
				if(sunLevel.contains(sun) && moistureLevel.contains(moisture) && soilType.contains(soil) && plantType.contains(type)) {
					filteredImages.add(imageBox);
					filteredPlants.add(plant);
				}
			}
			icv.setFilteredImages(filteredImages);
			carouselModel.setFilteredPlants(filteredPlants);
			icv.update();
		}
		
}
