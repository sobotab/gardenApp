package pkgModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlantGardenModel extends GardenModel{
	
	List<PlantObjectModel> plants;
	Set<PlantObjectModel> compost;
	CarouselModel carousel;
	int numLeps;
	int dollars;
	int heldPlant;

	public PlantGardenModel() {
		this.carousel = new CarouselModel();
		this.plants = new ArrayList<PlantObjectModel>();
		//this.compost = new Set<PlantObjectModel>();
	}
	
	public boolean checkSpread() {
		return false;
	}
	
	public void addPlant(PlantObjectModel plant) {
		
	}
	
	public void removePlant(int x, int y) {
		
	}
	
	public void compost(PlantObjectModel plant) {
		
	}
	
	public boolean checkConditions() {
		return false;
	}

	public List<PlantObjectModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
	}

	public Set<PlantObjectModel> getCompost() {
		return compost;
	}

	public void setCompost(Set<PlantObjectModel> compost) {
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
	
	public CarouselModel getCarousel() {
		return this.carousel;
	}
	
	public void setCarousel(CarouselModel carousel) {
		this.carousel = carousel;
	}
	
}
