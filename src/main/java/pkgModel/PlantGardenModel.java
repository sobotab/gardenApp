package pkgModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlantGardenModel extends GardenModel{
	List<PlantObjectModel> plants;
	List<PlantObjectModel> compost;
	ObjectCarouselModel carousel;
	int numLeps;
	int dollars;
	int heldPlant;

	public PlantGardenModel(List<PlantModel> plantInput) {
		
		this.carousel = new ObjectCarouselModel(plantInput, 0);
		this.plants = new ArrayList<PlantObjectModel>();
		//this.plants.addAll(carousel.plants);
		//this.compost = new Set<PlantObjectModel>();
		
	}
	
	public void addPlantFromCarousel(int index, double init_x, double init_y) {
		PlantObjectModel plant = (carousel.removePlant(index));
		plant.setX(init_x);
		plant.setY(init_y);
		plants.add(plant);
	}
		
	public boolean checkInsideGarden(List<Point> gardenOutline, int index) {
		int x = (int)plants.get(index).x;
		int y = (int)plants.get(index).y;
		int count = 0;
		
		for (Point coord : gardenOutline) {
			if (coord.y == y && coord.x < x)
				count++;
		}
		if (count % 2 != 0)
			return true;
		return false;
	}
	
	public boolean checkSpread() {
		return false;
	}
	
	public void addPlant(PlantObjectModel plant) {
		this.getPlants().add(plant);
	}
	
	public void removePlant(int x, int y) {
		
	}
	
	public void setPlantLocation(int index, double x, double y) {
		plants.get(index).setX(x);
		plants.get(index).setY(y);
	}
	
	public void dragPlant(int index, double x, double y, double x_max, double y_max) {
		PlantObjectModel dragPlant = plants.get(index);
		dragPlant.setXInBounds( dragPlant.getX() + x, x_max);
		dragPlant.setYInBounds( dragPlant.getY() + y, y_max);
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

	public List<PlantObjectModel> getCompost() {
		return compost;
	}

	public void setCompost(List<PlantObjectModel> compost) {
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
	
	public ObjectCarouselModel getCarousel() {
		return this.carousel;
	}
	
	public void setCarousel(ObjectCarouselModel carousel) {
		this.carousel = carousel;
	}
	
}
