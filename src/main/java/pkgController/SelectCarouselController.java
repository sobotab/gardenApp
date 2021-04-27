package pkgController;

import java.util.ArrayList;
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
	private final double CENTER_IMAGE_SCALING = 1.3;
	private final double CENTER_X = 400.0;
	
		
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
		scv = (SelectCarouselView)carouselView;
	}
	
//	public void plantSelected(MouseEvent event) {
//		ImageView img = (ImageView)event.getSource();
//		int index = 0;
//		if(img.getScaleX() == CENTER_IMAGE_SCALING) {
//			index = carouselModel.getHeldPlant();
//		}
//		else if(event.getSceneX() < CENTER_X) {
//			index = carouselModel.getHeldPlant() - 1;
//			if(index < 0) {
//				index = carouselModel.getFilteredPlants().size() - 1;
//			}
//		}
//		else {
//			index = carouselModel.getHeldPlant() + 1;
//			if(index >= carouselModel.getFilteredPlants().size()) {
//				index = 0;
//			}
//		}
//		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
//		carouselModel.getFilteredPlants().remove(index);
//		scv.getFilteredImages().remove(index);
//		
//	}
	
//	public EventHandler getHandlerForPlantSelected() {
//		return event -> plantSelected((MouseEvent) event);
//	}
	
	public void filterCarousel(String sun, String moisture, String soil) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<VBox> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<VBox> filteredImages = new ArrayList<VBox>();
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
