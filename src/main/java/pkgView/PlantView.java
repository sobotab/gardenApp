package pkgView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Ryan Dean
 * View class for plant objects. Used by Edit Garden screen to click and drag plants onto garden.
 */
public class PlantView extends ImageView{
	/**
	 * Image of the plant this PlantView represents.
	 */
	Image plantImage;
	/**
	 * Integer spread (diameter) of the plant this plantView represents.
	 */
	int spread;
	
	/**
	 * Constructor sets all fields.
	 * @param plantImage		Image of plant this plantView represents.
	 * @param spread			Integer spread of the plant this plantView represents.
	 */
	public PlantView(Image plantImage, int spread) {
		this.plantImage = plantImage;
		//this.setImage(plantImage);
		this.spread = spread;
	}
	
	/**
	 * Method to change xy coordinates of plantView.
	 * @param x		Double new x location.
	 * @param y		Double new y location.
	 */
	public void updateLocation(double x, double y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	// Getters & Setters
	
	public Image getPlantImage() {
		return this.plantImage;
	}
	public int getSpread() {
		return this.spread;
	}
	public void setPlantImage(Image image) {
		this.plantImage = image;
	}
	public void setSpread(int spread) {
		this.spread = spread;
	}
}
