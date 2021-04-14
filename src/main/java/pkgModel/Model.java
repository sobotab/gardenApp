package pkgModel;

import java.util.HashSet;
import java.util.Set;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class Model {
	Set<PlantModel> plants;
	Set<PlantModel> potentialPlants;
	Set<PlantModel> selectedPlants;
	int x;
	int y;
	
	public Model() {
		potentialPlants = makePlants();
		
	}
	
	public Set<PlantModel> makePlants() {
		Set<PlantModel> plants = new HashSet<PlantModel>();
		PlantModel Agalanis_purpurea = new PlantInfoModel("purple false foxglove", "Agalanis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 4, 6, "Example Description");
		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.DAMP, Soil.CLAY, 463, 20, "Example Description");
		plants.add(Agalanis_purpurea);
		plants.add(Quercus_stellata);
		return plants;
		
	}
	
	public void update() {
		
	}

	public Set<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(Set<PlantModel> plants) {
		this.plants = plants;
	}

	public Set<PlantModel> getPotentialPlants() {
		return potentialPlants;
	}

	public void setPotentialPlants(Set<PlantModel> potentialPlants) {
		this.potentialPlants = potentialPlants;
	}

	public Set<PlantModel> getSelectedPlants() {
		return selectedPlants;
	}

	public void setSelectedPlants(Set<PlantModel> selectedPlants) {
		this.selectedPlants = selectedPlants;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
