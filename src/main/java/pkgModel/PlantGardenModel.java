package pkgModel;

import java.util.Set;

public class PlantGardenModel extends GardenModel{
	
	Set<PlantModel> plants;
	Set<PlantModel> compost;
	int numLeps;
	int dollars;
	int heldPlant;

	public PlantGardenModel() {
		
	}
	
	public boolean checkSpread() {
		return false;
	}
	
	public void addPlant(PlantModel plant) {
		
	}
	
	public void removePlant(int x, int y) {
		
	}
	
	public void compost(PlantModel plant) {
		
	}
	
	public boolean checkConditions() {
		return false;
	}

	public Set<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(Set<PlantModel> plants) {
		this.plants = plants;
	}

	public Set<PlantModel> getCompost() {
		return compost;
	}

	public void setCompost(Set<PlantModel> compost) {
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
