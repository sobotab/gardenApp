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
 * @author Zane Greenholt
 * InfoCarouselController extends the CarouselController class and is the class that holds the handlers for the carousel on the info page.
 * 
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
		 * 
		 * @param view The program's view that is only initialized once
		 * @param carouselView A carouselView that holds the images for the carousel
		 */
		public InfoCarouselController(View view, CarouselView carouselView) {
			super(view, carouselView);
			icv = (InfoCarouselView)carouselView;
		}
		
		
		/**
		 * Filters the images that are shown in the carousel and the corresponding plants by the sun, moisture, soil, and plant type chosen by the user.
		 * 
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
				if(checkPlantConditions(plant, sun, moisture, soil, type)) {
					filteredImages.add(imageBox);
					filteredPlants.add(plant);
				}
			}
			icv.setFilteredImages(filteredImages);
			carouselModel.setFilteredPlants(filteredPlants);
			icv.update();
		}
		
		/**
		 * Helper method for checking that a plant's conditions match the current filters
		 * 
		 * @param plant The plantModel that is currently being considered
		 * @param sun The user selected sun level
		 * @param moisture The user selected moisture level
		 * @param soil The user selected soil type
		 * @param type The user selected plant type (woody or herbaceous)
		 * @return boolean representing whether or not the plant matches the current conditions
		 */
		public boolean checkPlantConditions(PlantModel plant, String sun, String moisture, String soil, String type) {
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
			return (sunLevel.contains(sun) && moistureLevel.contains(moisture) && soilType.contains(soil) && plantType.contains(type));
		}
		
}
