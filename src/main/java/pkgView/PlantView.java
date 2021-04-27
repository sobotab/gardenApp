package pkgView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlantView extends ImageView{
	Image plantImage;
	int spread;
	boolean selected;
	boolean correctZone;
	
	public PlantView(Image plantImage, int spread) {
		this.plantImage = plantImage;
		this.setImage(plantImage);
		this.spread = spread;
	}
	
	public void updateLocation(double x, double y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
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
	public int getSpread() {
		return this.spread;
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
	public void setSpread(int spread) {
		this.spread = spread;
	}
}
