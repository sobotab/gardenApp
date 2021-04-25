package pkgModel;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class PlantGardenModel extends GardenModel{
	List<PlantObjectModel> plants;
	List<PlantObjectModel> compost;
	ObjectCarouselModel carousel;
	Polygon gardenOutline;
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
		
	public boolean checkInsideGarden(int index) {
	    PlantObjectModel plantCheck = plants.get(index);

	    return(gardenOutline.contains(plantCheck.x, plantCheck.y, 
	    		plantCheck.spreadDiameter*0.6, plantCheck.spreadDiameter*0.6));

	}
	
	
	public boolean checkSpread(int index) {
		PlantObjectModel plant1 = plants.get(index);
		double x1 = plant1.x + (plant1.getSpreadDiameter()/7);
		double y1 = plant1.y + (plant1.getSpreadDiameter()/7);
		
		for (PlantObjectModel plant2 : this.plants) {	
			double x2 = plant2.x + (plant2.getSpreadDiameter()/7);		// Model coordinates represent top-left corner of PlantView		
			double y2 = plant2.y + (plant2.getSpreadDiameter()/7);		// Add this value to coordinate vals to offset to center of PlantView
			
			if (plant1 != plant2) {
				double distance = ( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );
				if (distance <= ( Math.pow( (plant1.getSpreadDiameter()/2) + (plant2.getSpreadDiameter()/2), 2) ))
					return true;	
			}
		}
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
	
	public Polygon getGardenOutline() {
		return this.gardenOutline;
	}

	public void setGardenOutline(Polygon gardenOutlineModel) {
		this.gardenOutline = gardenOutlineModel;
	}

}
