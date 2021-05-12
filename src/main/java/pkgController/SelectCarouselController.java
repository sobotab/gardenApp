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
 * 
 * 
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
	List<String> soils;
		
	/**
	 * Constructor initializes view and carouselView, but also reads in gardenData from a .ser file in order to capture the user's 
	 * decisions for sun, soil, and moisture.
	 * 
	 * @param view The program's view that is only initialized once
	 * @param carouselView A carouselView that holds the images for the carousel
	 */
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
		scv = (SelectCarouselView)carouselView;

		ArrayList<Object> gardenData = new ArrayList<Object>();
		soils = new ArrayList<String>();
		//Read in data from .ser files
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
		
		//Determine which soil types were used in the drawGarden screen
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)gardenData.get(0);
		if(plots.get(Soil.CLAY).size() != 0) {
			soils.add("clay");
		}
		if(plots.get(Soil.LOAMY).size() != 0) {
			soils.add("loamy");
		}
		if(plots.get(Soil.SANDY).size() != 0) {
			soils.add("sandy");
		}
		//Get the moisture and sun levels that the user decided on
		Moisture moisture2 = (Moisture)gardenData.get(2);
		this.moisture = moisture2.getLevel();
		Sun sun2 = (Sun)gardenData.get(3);
		this.sun = sun2.getLevel();
	}

	/**
	 * Filters the images in the SelectCarouselView and the corresponding carouselModel plants based on the user's soil, moisture, and sun decisions from
	 * the drawGarden screen.
	 * 
	 * @param sun String representing the user's choice of sun level
	 * @param moisture String representing the user's choice of moisture level
	 * @param soils List of Strings representing all soils chosen by the user
	 */
	public void filterCarousel(String sun, String moisture, List<String> soils) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<VBox> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<VBox> filteredImages = new ArrayList<VBox>();
		Iterator<PlantModel> plantIter = plants.iterator();
		Iterator<VBox> imageIter = images.iterator();
		//Iterate over the full list of plants to see if they should be added to the filtered Lists
		while(plantIter.hasNext() && imageIter.hasNext()) {
			PlantInfoModel plant = (PlantInfoModel)plantIter.next();
			VBox imageBox = imageIter.next();
			if(checkPlantConditions(plant, sun, moisture, soils)) {
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
	 * Helper function for determining if a plant should be in the selectCarousel at the time when the screen is initially loaded.
	 * This is only based on the user's decisions from the drawGarden screen
	 * 
	 * @param plant The current plantModel that is being considered
	 * @param sun The user's selected sun level
	 * @param moisture The user's selected moisture level
	 * @param soils A list of all the soil types the user placed in the garden.
	 * @return boolean representing whether or not the conditions are met
	 */
	public boolean checkPlantConditions(PlantModel plant, String sun, String moisture, List<String> soils) {
		String sunLevel = plant.getSun();
		String moistureLevel = plant.getMoisture();
		String soilType = plant.getSoil();
		boolean correctSoil = false;
		//Plant can have any of the included soil types
		for(String type: soils) {
			if(soilType.contains(type)) {
				correctSoil = true;
			}
		}
		return (sunLevel.contains(sun) && moistureLevel.contains(moisture) && correctSoil);
	}
	
	/**
	 * Filters the images in the carousel and and the plants in the carouselModel based on the filters chosen on the selectPlant screen.
	 * @param type woody or herbaceous (selected in comboBox)
	 * 
	 * @param soil A specific soilTypse chosen in the comboBox
	 * @param sun The user selected sun level from the drawGarden screen
	 * @param moisture The user selected moisture level from the drawGarden screen
	 * @param soils A list of all the soils that were placed in the drawGarden screen
	 */
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
			//Only add to carousel if plant wasn't already selected previously
			if(!carouselModel.getSelectedPlants().containsKey(plant.getName())) {
				if(checkPlantConditions(plant, type, soil, sun, moisture, soils)) {
					filteredPlants.add(plant);
					filteredImages.add(imageBox);
				}
			}
		}
		scv.setFilteredImages(filteredImages);
		scv.setCenter(0);
		carouselModel.setFilteredPlants(filteredPlants);
		carouselModel.setHeldPlant(0);
		scv.update();
	}
	
	/**
	 * Helper function for filtering on the selectPlants screen that determines if a plant matches
	 * the combination of requirements selected by the user
	 * 
	 * @param plant The plantModel that is currently being considered
	 * @param type The type of plant selected by the user (woody or herbaceous)
	 * @param soil The specific soil type selected in the ComboBox
	 * @param sun The sun level selected by the user
	 * @param moisture The moisture level selected by the user
	 * @param soils All the soil types that were placed on the drawGarden screen
	 * @return boolean stating whether or not the conditions are met
	 */
	public boolean checkPlantConditions(PlantModel plant, String type, String soil, String sun, String moisture, List<String> soils) {
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
		return (soilType.contains(soil) && plantType.contains(type) && sunLevel.contains(sun) && moistureLevel.contains(moisture) && correctSoil);
	}
	
	/**
	 * Getter for scv field
	 * 
	 * @return scv field - A SelectCarouselView with the images for the carousel
	 */
	public SelectCarouselView getScv() {
		return scv;
	}
	
	/**
	 * Setter for scv field
	 * 
	 * @param scv A SelectCarouselView that will replace the current scv field
	 */
	public void setScv(SelectCarouselView scv) {
		this.scv = scv;
	}

	/**
	 * Getter for the moisture field
	 * 
	 * @return moisture field - A String representing the user's moisture level choice
	 */
	public String getMoisture() {
		return moisture;
	}

	/**
	 * Setter for the moisture field
	 * 
	 * @param moisture A String representing a moisture level
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	/**
	 * Getter for the sun field
	 * 
	 * @return sun field - A String representing the user's sun level choice
	 */
	public String getSun() {
		return sun;
	}

	/**
	 * Setter for the sun field
	 * 
	 * @param sun String representing a sun level
	 */
	public void setSun(String sun) {
		this.sun = sun;
	}

	/**
	 * Getter for the soil field
	 * 
	 * @return soil field - A List of Strings representing all soil types chosen by the user
	 */
	public List<String> getSoil() {
		return soils;
	}

	/**
	 * Setter for the soil field
	 * 
	 * @param soil List of Strings representing different soil types
	 */
	public void setSoil(List<String> soil) {
		this.soils = soil;
	}
	
	
}
