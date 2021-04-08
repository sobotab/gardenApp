package pkgModel;

import java.util.Set;

public class Model {
	Set<PlantModel> plants;
	Set<PlantModel> potentialPlants;
	Set<PlantModel> selectedPlants;
	int x;
	int y;
	
	public Model() {
		
	}
	
	public Set<PlantModel> makePlants() {
		return null;
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
