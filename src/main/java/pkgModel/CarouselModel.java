package pkgModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import pkgView.PlantView;

public class CarouselModel {
	public List<PlantModel> plants;
	public List<PlantModel> filteredPlants;
	public HashMap<String, PlantModel> selectedPlants;
	int firstPlant;
	int lastPlant;
	int viewPlant;
	int viewHeight;
	int viewWidth;
	int rationDecrease;
	int heldPlant;
	
	public CarouselModel(List<PlantModel> plants2, int heldPlant) {
		this.plants = plants2;
		//this.plants.addAll(plants2);
		this.filteredPlants = new ArrayList<PlantModel>();
		this.filteredPlants.addAll(plants2);
		this.heldPlant = heldPlant;
		this.selectedPlants = new HashMap<String, PlantModel>();
		
	}
	
	public CarouselModel(int firstPlant, int lastPlant) {
		
	}
	
	public CarouselModel(int firstPlant, int lastPlant, int viewPlant, int viewHeight, int viewWidth, int rationDecrease, int heldPlant) {
		
	}
	
	public HashMap<String, PlantModel> mapNameToPlants(){
		HashMap<String, PlantModel> map = new HashMap<>();
		for(PlantModel plant: plants) {
			map.put(plant.sciName, plant);
		}
		return map;
	}
	
	public void rotateLeft() {
		if(filteredPlants.size() > 0) {
			heldPlant -= 1;
			if(heldPlant < 0) {
				heldPlant = filteredPlants.size() - 1;
			}
		}
	}
	
	public void rotateRight() {
		heldPlant += 1;
		if(heldPlant >= filteredPlants.size()) {
			heldPlant = 0;
		}
	}
	
//	public int plantSelected(int x, int y) {
//		return 0;
//	}

	public List<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantModel> plants) {
		this.plants = plants;
	}

	public int getFirstPlant() {
		return firstPlant;
	}

	public void setFirstPlant(int firstPlant) {
		this.firstPlant = firstPlant;
	}

	public int getLastPlant() {
		return lastPlant;
	}

	public void setLastPlant(int lastPlant) {
		this.lastPlant = lastPlant;
	}

	public int getViewPlant() {
		return viewPlant;
	}

	public void setViewPlant(int viewPlant) {
		this.viewPlant = viewPlant;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getHeldPlant() {
		return heldPlant;
	}

	public void setHeldPlant(int heldPlant) {
		this.heldPlant = heldPlant;
	}
	
	public PlantModel getPlantByIndex(int index) {
		return filteredPlants.get(index);
	}

	public List<PlantModel> getFilteredPlants() {
		return filteredPlants;
	}

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
	
	public void selectPlant(PlantModel plant) {
		selectedPlants.put(plant.getName(), plant);
	}
	
	public void decrementHeldPlant() {
		heldPlant--;
		if(heldPlant < 0) {
			heldPlant++;
		}
	}
	
	public void deSelectPlant(PlantModel plant) {
		selectedPlants.remove(plant.getName());
	}

	public HashMap<String, PlantModel> getSelectedPlants() {
		return selectedPlants;
	}

	public void setSelectedPlants(HashMap<String, PlantModel> selectedPlants) {
		this.selectedPlants = selectedPlants;
	}
}
