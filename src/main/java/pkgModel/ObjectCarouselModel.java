package pkgModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class ObjectCarouselModel {
	public List<PlantObjectModel> plants;
	int firstPlant;
	int lastPlant;
	int viewPlant;
	int viewHeight;
	int viewWidth;
	int rationDecrease;
	int focusedPlant;
	
	public ObjectCarouselModel(List<PlantModel> plantInput, int focusedPlant) {
		this.plants = new ArrayList<PlantObjectModel>();
		
		for (PlantModel plant : plantInput)
			this.plants.add(new PlantObjectModel(
					plant.name, plant.sciName, 
					plant.spreadDiameter, 
					plant.sun, plant.moisture, plant.soil, 
					0, 0, 10, 10));
		
		this.focusedPlant = focusedPlant;
	}
	
	public void replacePlant(int index) {
		PlantObjectModel duplicatePlant = new PlantObjectModel(
				plants.get(index).name, 
				plants.get(index).sciName,
				plants.get(index).spreadDiameter,
				plants.get(index).sun, 
				plants.get(index).moisture,
				plants.get(index).soil,
				plants.get(index).x,
				plants.get(index).y,
				plants.get(index).height,
				plants.get(index).width
				);
		plants.add(index, duplicatePlant);
	}
	
	public PlantObjectModel removePlant(int index) {
		return plants.remove(index);
	}
	
	public void rotateLeft() {
		focusedPlant -= 1;
		if(focusedPlant < 0) {
			focusedPlant = plants.size() - 1;
		}
		
	}
	
	public void rotateRight() {
		focusedPlant += 1;
		if(focusedPlant == plants.size()) {
			focusedPlant = 0;
		}
	}
	
	public int plantSelected(int x, int y) {
		return 0;
	}

	public List<PlantObjectModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
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

	public int getfocusedPlant() {
		return focusedPlant;
	}

	public void setfocusedPlant(int focusedPlant) {
		this.focusedPlant = focusedPlant;
	}
	
	public PlantModel getPlantByIndex(int index) {
		return plants.get(index);
	}
}
