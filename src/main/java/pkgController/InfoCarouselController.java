package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.PlantInfoModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.View;

public class InfoCarouselController extends CarouselController {
		View view;
		InfoCarouselView icv;

		public InfoCarouselController(View view, CarouselView carouselView) {
			super(view, carouselView);
			icv = (InfoCarouselView)carouselView;
		}
		
		public void clickedPopup(MouseEvent event) {
			ImageView img = (ImageView)event.getSource();
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
		
}
