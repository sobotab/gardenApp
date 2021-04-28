package pkgModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import pkgView.PlantView;
/**
 * 
 * @author Zane Greenholt
 * CLass containing logical operations for carousels
 */
public class CarouselModel {
	/**
	 * List of all plants in the program
	 */
	public List<PlantModel> plants;
	/**
	 * List of plants in the carousel after filtering
	 */
	public List<PlantModel> filteredPlants;
	/**
	 * List of plants selected in the case of the select plants carousel
	 */
	public HashMap<String, PlantModel> selectedPlants;
	/**
	 * The index of the plant that is currently the focus of the carousel
	 */
	int heldPlant;
	
	/**
	 * Constructor fills the plants and filteredPlants lists and initializes other fields
	 * @param plants2 List of plants that will fill plants and filteredPlants
	 * @param heldPlant index of the plant that should start as the carousel's focus
	 */
	public CarouselModel(List<PlantModel> plants2, int heldPlant) {
		this.plants = plants2;
		//this.plants.addAll(plants2);
		this.filteredPlants = new ArrayList<PlantModel>();
		this.filteredPlants.addAll(plants2);
		this.heldPlant = heldPlant;
		this.selectedPlants = new HashMap<String, PlantModel>();
		
	}
	
	
	/**
	 * Returns a hashmap of all the plants with their scientific names as keys
	 * @return
	 */
	public HashMap<String, PlantModel> mapNameToPlants(){
		HashMap<String, PlantModel> map = new HashMap<>();
		for(PlantModel plant: plants) {
			map.put(plant.sciName, plant);
		}
		return map;
	}
	
	/**
	 * Simulates rotating the carousel to the left by decrementing the heldPlant value. The value loops if it goes below zero.
	 */
	public void rotateLeft() {
		if(filteredPlants.size() > 0) {
			heldPlant -= 1;
			if(heldPlant < 0) {
				heldPlant = filteredPlants.size() - 1;
			}
		}
	}
	/**
	 * Simulates rotating the carousel to the right by incrementing the heldPlant index. The value loops it if goes above the filteredPlants size
	 */
	public void rotateRight() {
		heldPlant += 1;
		if(heldPlant >= filteredPlants.size()) {
			heldPlant = 0;
		}
	}

	/**
	 * Getter for the plants field
	 * @return plants field - List of PLantModels
	 */
	public List<PlantModel> getPlants() {
		return plants;
	}
	
	/**
	 * Setter for plants field
	 * @param plants A List of plantModels that will replace the current plants field
	 */
	public void setPlants(List<PlantModel> plants) {
		this.plants = plants;
	}

	/**
	 * Getter for heldPlant field
	 * @return heldPlant field - int representing index of the plant in the carousel's focus
	 */
	public int getHeldPlant() {
		return heldPlant;
	}
	/**
	 * Setter for heldPlant field
	 * @param heldPlant - An int that will replace the current heldPlant field
	 */
	public void setHeldPlant(int heldPlant) {
		this.heldPlant = heldPlant;
	}
	
	/**
	 * Returns the plantModel that is at the passed in index in the plants list
	 * @param index int representing an index in the plants list
	 * @return A PlantModel at the passed in index
	 */
	public PlantModel getPlantByIndex(int index) {
		return filteredPlants.get(index);
	}
	
	/**
	 * Getter for filteredPlants field
	 * @return filteredPlants field - A List of PlantModels
	 */
	public List<PlantModel> getFilteredPlants() {
		return filteredPlants;
	}
	
	/**
	 * Setter for filteredPlants field, it also updates the value of heldPlant to avoid errors
	 * @param filteredPlants A list of PlantModels that will replace the current filteredPlants field
	 */
	public void setFilteredPlants(List<PlantModel> filteredPlants) {
		this.filteredPlants = filteredPlants;
		if(heldPlant >= filteredPlants.size()) {
			if(filteredPlants.size() == 0) {
				heldPlant = 0;
			}
			else {
				heldPlant = filteredPlants.size() - 1;
			}
		}
	}
	
	/**
	 * Add the passed in plant to the selectedPlants hashMap with its common name as the key
	 * @param plant A PlantModel
	 */
	public void selectPlant(PlantModel plant) {
		selectedPlants.put(plant.getName(), plant);
	}
	
	/**
	 * Decrements the heldPlant index except for when it is already zero
	 */
	public void decrementHeldPlant() {
		heldPlant--;
		if(heldPlant < 0) {
			heldPlant++;
		}
	}
	
	/**
	 * Remove the passed in plant from the selectedPlants hashMap
	 * @param plant A PlantModel
	 */
	public void deSelectPlant(PlantModel plant) {
		selectedPlants.remove(plant.getName());
	}
	
	/**
	 * Getter for the selectedPlants field
	 * @return selectedPlants field - A hashMap of plant common names mapped to PlantModels
	 */
	public HashMap<String, PlantModel> getSelectedPlants() {
		return selectedPlants;
	}
	/**
	 * Setter for the selectedPlants field
	 * @param selectedPlants A hashMap of plant common names mapped to PlantModels
	 */
	public void setSelectedPlants(HashMap<String, PlantModel> selectedPlants) {
		this.selectedPlants = selectedPlants;
	}
}
