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
	int heldPlant;
	
	public ObjectCarouselModel() {
		this.plants = new ArrayList<PlantObjectModel>();
		this.heldPlant = 0;
		Set<PlantModel> plants = new HashSet<PlantModel>();
		PlantObjectModel Agalinis_purpurea = new PlantObjectModel("purple false foxglove", "Agalinis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 0, 0, 10, 10);
		PlantObjectModel Quercus_stellata = new PlantObjectModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.DAMP, Soil.CLAY, 0, 0, 10, 10);
		PlantObjectModel Anemone_virginiana = new PlantObjectModel("thimbleweed","Anemone-virginiana",1, Sun.FULLSUN,Moisture.DAMP,Soil.CLAY, 0, 0, 10, 10);
		PlantObjectModel Aralia_racemosa = new PlantObjectModel("spikenard","Aralia-racemosa",1,Sun.PARTSUN,Moisture.DAMP,Soil.CLAY, 0, 0, 10, 10);
		PlantObjectModel Acer_rubrum = new PlantObjectModel("red maple","Acer-rubrum",75,Sun.FULLSUN,Moisture.DAMP,Soil.CLAY, 0, 0, 10, 10);
		plants.add(Acer_rubrum);
		plants.add(Aralia_racemosa);
		plants.add(Anemone_virginiana);
		plants.add(Agalinis_purpurea);
		plants.add(Quercus_stellata);
		return;
	}
	
	public ObjectCarouselModel(Set<PlantObjectModel> plants2, int heldPlant) {
		this.plants = new ArrayList<PlantObjectModel>();
		this.plants.addAll(plants2);
		this.heldPlant = heldPlant;
	}
	
	public ObjectCarouselModel(int firstPlant, int lastPlant) {
		
	}
	
	public ObjectCarouselModel(int firstPlant, int lastPlant, int viewPlant, int viewHeight, int viewWidth, int rationDecrease, int heldPlant) {
		
	}
	
	public void rotateLeft() {
		heldPlant -= 1;
		if(heldPlant < 0) {
			heldPlant = plants.size() - 1;
		}
		
	}
	
	public void rotateRight() {
		heldPlant += 1;
		if(heldPlant == plants.size()) {
			heldPlant = 0;
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
		return plants.get(index);
	}
}
