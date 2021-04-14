package pkgModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarouselModel {
	public List<PlantModel> plants;
	public List<PlantModel> filteredPlants;
	int firstPlant;
	int lastPlant;
	int viewPlant;
	int viewHeight;
	int viewWidth;
	int rationDecrease;
	int heldPlant;
	
	public CarouselModel(Set<PlantModel> plants, int heldPlant) {
		this.plants = new ArrayList<PlantModel>();
		this.plants.addAll(plants);
		this.filteredPlants = new ArrayList<PlantModel>();
		this.filteredPlants.addAll(plants);
		this.heldPlant = heldPlant;
	}
	
	public CarouselModel(int firstPlant, int lastPlant) {
		
	}
	
	public CarouselModel(int firstPlant, int lastPlant, int viewPlant, int viewHeight, int viewWidth, int rationDecrease, int heldPlant) {
		
	}
	
	public void rotateLeft() {
		heldPlant -= 1;
		if(heldPlant < 0) {
			heldPlant = filteredPlants.size() - 1;
		}
		
	}
	
	public void rotateRight() {
		heldPlant += 1;
		if(heldPlant == filteredPlants.size()) {
			heldPlant = 0;
		}
	}
	
	public int plantSelected(int x, int y) {
		return 0;
	}

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
	}
}
