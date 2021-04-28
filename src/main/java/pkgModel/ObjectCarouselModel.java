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

public class ObjectCarouselModel implements Serializable {
	public List<PlantObjectModel> plants;
	int firstPlant;
	int lastPlant;
	int focusedPlant;
	
	public ObjectCarouselModel() {
		this.plants = new ArrayList<PlantObjectModel>();
	}
	
	public ObjectCarouselModel(List<PlantModel> plantInput, int focusedPlant) {
		this.plants = new ArrayList<PlantObjectModel>();
		
		for (PlantModel plant : plantInput)
			this.plants.add(new PlantObjectModel(
					plant.name, plant.sciName, 
					plant.spreadDiameter, 
					plant.sun, plant.moisture, plant.soil, 
					plant.numLeps, plant.dollars,
					0, 0, 10, 10));
		
		this.focusedPlant = focusedPlant;
	}
	
	public void fillCarousel(List<PlantInfoModel> plantInput, int focusedPlant) {
		for (PlantModel plant : plantInput)
			this.plants.add(new PlantObjectModel(
					plant.name, plant.sciName, 
					plant.spreadDiameter, 
					plant.sun, plant.moisture, plant.soil, 
					plant.numLeps, plant.dollars,
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
				plants.get(index).numLeps,
				plants.get(index).dollars,
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
		Collections.rotate(plants, -1);
	}
	
	public void rotateRight() {
		Collections.rotate(plants, 1);
	}

	public List<PlantObjectModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
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
