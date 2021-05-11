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
/**
 * 
 * @author Zane Greenholt
 * InfoCarouselController extends the CarouselController class and is the class that holds the handlers for the carousel on the info page.
 */
public class InfoCarouselController extends CarouselController {
		/**
		 * An InfoCarouselView that has the images for the carousel
		 */
		InfoCarouselView icv;
		/**
		 * The scaling for the image that is focused on by the carousel
		 */
		private final double CENTER_IMAGE_SCALING = 2.0;

		/**
		 * Constructor just initializes the view and carouselView fields
		 * @param view The program's view that is only initialized once
		 * @param carouselView A carouselView that holds the images for the carousel
		 */
		public InfoCarouselController(View view, CarouselView carouselView) {
			super(view, carouselView);
			icv = (InfoCarouselView)carouselView;
		}
		
//		/**
//		 * Handler that is used for when an image is clicked in the info carousel. It opens a popup with information about the plant that
//		 * matches the clicked image.
//		 * @param event A MouseEvent that is the mouse being pressed
//		 */
//		public void clickedPopup(MouseEvent event) {
//			VBox box = (VBox)event.getSource();
//			ImageView img = (ImageView)box.getChildren().get(1);
//			int centerIndex = carouselModel.getHeldPlant();
//			VBox centerImage = icv.getFilteredImages().get(centerIndex);
//			double centerX = centerImage.getLayoutX();
//			int index = 0;
//			if(box.getScaleX() == CENTER_IMAGE_SCALING) {
//				index = centerIndex;
//			}
//			else if(event.getSceneX() < centerX) {
//				index = centerIndex - 1;
//				if(index < 0) {
//					index = carouselModel.getFilteredPlants().size() - 1;
//				}
//			}
//			else {
//				index = centerIndex + 1;
//				if(index >= carouselModel.getFilteredPlants().size()) {
//					index = 0;
//				}
//			}
//			PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
//			icv.openInfoPopUp(this.view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription(), plant.getLeps());
//		}
//		
//		/**
//		 * Getter for the clickedPopup handler
//		 * @return EventHandler for the clickedPopup method
//		 */
//		public EventHandler getHandlerForPopup() {
//			return event -> clickedPopup((MouseEvent) event);
//		}
		
		/**
		 * Filters the images that are shown in the carousel and the corresponding plants by the sun, moisture, soil, and plant type chosen by the user.
		 * @param sun A String that is the sun level chosen by the user
		 * @param moisture A String that is the moisture level chosen by the user
		 * @param soil A String that is the soil type chosen by the user
		 * @param type A String that is the plant type decided by its price
		 */
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
				String sunLevel = plant.getSun();
				String moistureLevel = plant.getMoisture();
				String soilType = plant.getSoil();
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
