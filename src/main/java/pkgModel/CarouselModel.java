package pkgModel;

import java.util.HashSet;
import java.util.Set;

public class CarouselModel {
	public Set<PlantModel> plants;
	int firstPlant;
	int lastPlant;
	int viewPlant;
	int viewHeight;
	int viewWidth;
	int rationDecrease;
	int heldPlant;
	
	public CarouselModel() {
		
	}
	
	public CarouselModel(int firstPlant, int lastPlant) {
		
	}
	
	public CarouselModel(int firstPlant, int lastPlant, int viewPlant, int viewHeight, int viewWidth, int rationDecrease, int heldPlant) {
		plants = new HashSet<>();
		
	}
	
	public void rotateLeft() {
		
	}
	
	public void rotateRight() {
		
	}
	
	public int plantSelected(int x, int y) {
		return 0;
	}

	public Set<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(Set<PlantModel> plants) {
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
}
