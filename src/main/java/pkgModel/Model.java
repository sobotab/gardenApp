package pkgModel;

import java.util.Set;

public class Model {
	Set<Plant> plants;
	Set<Plant> potentialPlants;
	Set<Plant> selectedPlants;
	int x;
	int y;
	
	public Set<Plant> makePlants() {
		return null;
	}
	
	public void update() {
		
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void setPlants(Set<Plant> plants) {
		this.plants = plants;
	}

	public Set<Plant> getPotentialPlants() {
		return potentialPlants;
	}

	public void setPotentialPlants(Set<Plant> potentialPlants) {
		this.potentialPlants = potentialPlants;
	}

	public Set<Plant> getSelectedPlants() {
		return selectedPlants;
	}

	public void setSelectedPlants(Set<Plant> selectedPlants) {
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
