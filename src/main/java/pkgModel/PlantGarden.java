package pkgModel;

import java.util.Set;

public class PlantGarden {
	
	Set<Plant> plants;
	Set<Plant> compost;
	int numLeps;
	int dollars;
	int heldPlant;

	public PlantGarden() {
		
	}
	
	public boolean checkSpread() {
		return false;
	}
	
	public void addPlant(Plant plant) {
		
	}
	
	public void removePlant(int x, int y) {
		
	}
	
	public void compost(Plant plant) {
		
	}
	
	private boolean checkConditions() {
		return false;
	}

	public Set<Plant> getPlants() {
		return plants;
	}

	public void setPlants(Set<Plant> plants) {
		this.plants = plants;
	}

	public Set<Plant> getCompost() {
		return compost;
	}

	public void setCompost(Set<Plant> compost) {
		this.compost = compost;
	}

	public int getNumLeps() {
		return numLeps;
	}

	public void setNumLeps(int numLeps) {
		this.numLeps = numLeps;
	}

	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}

	public int getHeldPlant() {
		return heldPlant;
	}

	public void setHeldPlant(int heldPlant) {
		this.heldPlant = heldPlant;
	}
	
	
}
