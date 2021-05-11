package pkgController;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
/**
 * 
 * @author Zane Greenholt
 * SelectCarouselController extends the CarouselController class and holds the handlers for the carousel on the select plants screen.
 */
public class SelectCarouselController extends CarouselController {
	/**
	 * The program's view that is only initialized once
	 */
	View view;
	/**
	 * A SelectCarouselView that holds the images for the carousel
	 */
	SelectCarouselView scv;
	/**
	 * A String representing the moisture level selected by the user on the drawGarden screen.
	 */
	String moisture;
	/**
	 * A String representing the sun level selected by the user on the drawGarden screen.
	 */
	String sun;
	/**
	 * A list of Strings representing all soil types used for plots by the user on the drawGarden screen.
	 */
	List<String> soil;
		
	/**
	 * Constructor initializes view and carouselView, but also reads in gardenData from a .ser file in order to capture the user's 
	 * decisions for sun, soil, and moisture.
	 * @param view The program's view that is only initialized once
	 * @param carouselView A carouselView that holds the images for the carousel
	 */
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
		scv = (SelectCarouselView)carouselView;

		ArrayList<Object> gardenData = new ArrayList<Object>();
		soil = new ArrayList<String>();
		
		try {
			FileInputStream fis = new FileInputStream("gardenData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			gardenData = (ArrayList<Object>)ois.readObject();
			ois.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("Error initializing stream");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)gardenData.get(0);
		if(plots.get(Soil.CLAY).size() != 0) {
			soil.add("clay");
		}
		if(plots.get(Soil.LOAMY).size() != 0) {
			soil.add("loamy");
		}
		if(plots.get(Soil.SANDY).size() != 0) {
			soil.add("sandy");
		}
		Moisture moisture2 = (Moisture)gardenData.get(2);
		this.moisture = moisture2.getLevel();
		Sun sun2 = (Sun)gardenData.get(3);
		this.sun = sun2.getLevel();
	}

	/**
	 * Filters the images in the SelectCarouselView and the corresponding carouselModel plants based on the user's soil, moisture, and sun decisions from
	 * the drawGarden screen.
	 * @param sun String representing the user's choice of sun level
	 * @param moisture String representing the user's choice of moisture level
	 * @param soil List of Strings representing all soils chosen by the user
	 */
	public void filterCarousel(String sun, String moisture, List<String> soil) {
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
			boolean correctSoil = false;
			for(String type: soil) {
				if(soilType.contains(type)) {
					correctSoil = true;
				}
			}
			if(sunLevel.contains(sun) && moistureLevel.contains(moisture) && correctSoil) {
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
	
	public void filterCarousel(String type, String soil, String sun, String moisture, List<String> soils) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<VBox> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<VBox> filteredImages = new ArrayList<VBox>();
		Iterator<PlantModel> plantIter = plants.iterator();
		Iterator<VBox> imageIter = images.iterator();
		while(plantIter.hasNext() && imageIter.hasNext()) {
			PlantInfoModel plant = (PlantInfoModel)plantIter.next();
			VBox imageBox = imageIter.next();
			String soilType = plant.getSoil();
			String sunLevel = plant.getSun();
			String moistureLevel = plant.getMoisture();
			int dollars = plant.getDollars();
			String plantType = "";
			if(dollars == 6) {
				plantType = "herbaceous";
			}
			else {
				plantType = "woody";
			}
			boolean correctSoil = false;
			if(soil == "") {
				for(String gardenSoil: soils) {
					if(soilType.contains(gardenSoil)) {
						correctSoil = true;
					}
				}
			}
			else {
				correctSoil = true;
			}
			if(soilType.contains(soil) && plantType.contains(type) && sunLevel.contains(sun) && moistureLevel.contains(moisture) && correctSoil) {
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
	
	/**
	 * Getter for scv field
	 * @return scv field - A SelectCarouselView with the images for the carousel
	 */
	public SelectCarouselView getScv() {
		return scv;
	}
	
	/**
	 * Setter for scv field
	 * @param scv A SelectCarouselView that will replace the current scv field
	 */
	public void setScv(SelectCarouselView scv) {
		this.scv = scv;
	}

	/**
	 * Getter for the moisture field
	 * @return moisture field - A String representing the user's moisture level choice
	 */
	public String getMoisture() {
		return moisture;
	}

	/**
	 * Setter for the moisture field
	 * @param moisture A String representing a moisture level
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	/**
	 * Getter for the sun field
	 * @return sun field - A String representing the user's sun level choice
	 */
	public String getSun() {
		return sun;
	}

	/**
	 * Setter for the sun field
	 * @param sun String representing a sun level
	 */
	public void setSun(String sun) {
		this.sun = sun;
	}

	/**
	 * Getter for the soil field
	 * @return soil field - A List of Strings representing all soil types chosen by the user
	 */
	public List<String> getSoil() {
		return soil;
	}

	/**
	 * Setter for the soil field
	 * @param soil List of Strings representing different soil types
	 */
	public void setSoil(List<String> soil) {
		this.soil = soil;
	}
	
	
}
