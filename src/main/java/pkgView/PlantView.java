package pkgView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlantView extends ImageView{
	Image plantImage;
	boolean selected;
	boolean correctZone;
	
	public PlantView(Image plantImage) {
		this.plantImage = plantImage;
	}
	
	public void updateLocation(int x, int y) {}
	
	public void updateSpread(int x, int y) {}
	
	public void updateSelected() {}
	
	public void updateZone() {}
	
	// getters & setters
	public Image getPlantImage() {
		return this.plantImage;
	}
	public boolean isSelected() {
		return this.selected;
	}
	public boolean isCorrectZone() {
		return this.correctZone;
	}
	public void setPlantImage(Image image) {
		this.plantImage = image;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void setCorrectZone(boolean correctZone) {
		this.correctZone = correctZone;
	}
}
