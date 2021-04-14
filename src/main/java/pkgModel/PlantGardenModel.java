package pkgModel;

import java.awt.Point;
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
	
	public boolean isInsideGarden(List<Point> gardenOutline, Point plantLoc) {
	      int i;
	      int j;
	      boolean result = false;
	      for (i = 0, j = gardenOutline.size() - 1; i < gardenOutline.size(); j = i++) {
	        if ((gardenOutline.get(i).y > plantLoc.y) != (gardenOutline.get(j).y > plantLoc.y) &&
	            (plantLoc.x < (gardenOutline.get(j).x - gardenOutline.get(i).x) * (plantLoc.y - gardenOutline.get(i).y) / (gardenOutline.get(j).y-gardenOutline.get(i).y) + gardenOutline.get(i).x)) {
	          result = !result;
	         }
	        System.out.println("y =  " + gardenOutline.get(i).y + " " + plantLoc.y + " x =  " + gardenOutline.get(i).x + " " + plantLoc.x);
	      } 
	      return result;
	    }
	
	public boolean checkSpread() {
		return false;
	}
	
	public void addPlant(PlantObjectModel plant) {
		this.getPlants().add(plant);
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
