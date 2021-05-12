package pkgModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

/**
 * 
 * @author Ryan Dean
 * The model class for the carousel in the Edit Garden screen.
 */
public class ObjectCarouselModel implements Serializable {
	/**
	 * List of plant objects in this carousel.
	 */
	public List<PlantObjectModel> plants;
	
	/**
	 * Constructor for no plant input, simply initializes model class.
	 */
	public ObjectCarouselModel() {
		this.plants = new ArrayList<PlantObjectModel>();
	}
	
	/**
	 * Constructor for when plant input is present. Uses fillCarousel() method to populate plants list.
	 * 
	 * @param plantInput 	List of plant models to be placed in carousel.
	 */
	public ObjectCarouselModel(List<PlantInfoModel> plantInput) {
		this.plants = new ArrayList<PlantObjectModel>();
		fillCarousel(plantInput);
	}
	
	/**
	 * Helper method for populating the plants list from a list of PlantInfoModels as input.
	 * 
	 * @param plantInput 	List of plant models to be placed in carousel.
	 */
	public void fillCarousel(List<PlantInfoModel> plantInput) {
		for (PlantModel plant : plantInput)
			this.plants.add(new PlantObjectModel(
					plant.name, plant.sciName, 
					plant.spreadDiameter, 
					plant.sun, plant.moisture, plant.soil, 
					plant.numLeps, plant.dollars, plant.leps,
					0, 0));		
	}
	
	/**
	 * Method used when plant is dragged out of carousel and must be replaced. Makes duplicate plant and
	 * inserts it where original plant was.
	 * 
	 * @param index 	Index of the plant to be replaced in this class's plants list.
	 */
	public void replacePlant(int index) {
		PlantObjectModel duplicatePlant = new PlantObjectModel(
				plants.get(index).name, 
				plants.get(index).sciName,
				plants.get(index).spreadDiameter,
				plants.get(index).sun, 
				plants.get(index).moisture,
				plants.get(index).soil,
				plants.get(index).numLeps,
				plants.get(index).dollars,
				plants.get(index).leps,
				plants.get(index).x,
				plants.get(index).y
				);
		plants.add(index, duplicatePlant);
	}
	
	/**
	 * Method to remove a plant from this class's plants list.
	 * 
	 * @param index 	Index of the plant object to be removed.
	 * @return 			The plant model that was removed.
	 */
	public PlantObjectModel removePlant(int index) {
		return plants.remove(index);
	}
	
	/**
	 * Method that increments the index of all plants in this class's plants list by -1. Used when spinning carousel left.
	 */
	public void rotateLeft() {
		Collections.rotate(plants, -1);
	}
	
	/**
	 * Method that increments the index of all plants in this class's plants list by 1. Used when spinning carousel right.
	 */
	public void rotateRight() {
		Collections.rotate(plants, 1);
	}

	
	// Getters & Setters
	
	public List<PlantObjectModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
	}

	public PlantModel getPlantByIndex(int index) {
		return plants.get(index);
	}
}
