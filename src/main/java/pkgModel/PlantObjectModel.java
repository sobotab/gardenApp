package pkgModel;

import java.io.Serializable;
import java.util.List;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

/**
 * 
 * @author Ryan Dean
 * Model class representing the PlantView objects interacted with on the Edit Garden screen.
 */
public class PlantObjectModel extends PlantModel implements Serializable {
	/**
	 * X-location of the plant model.
	 */
	double x;
	/**
	 * Y-location of the plant model.
	 */
	double y;
	
	/**
	 * Constructor sets all fields of plant model.
	 * @param name 				The plant's common name
	 * @param sciName 			The plant's scientific name
	 * @param spreadDiameter 	The plant's root spread
	 * @param sun 				The plant's ideal sun level
	 * @param moisture 			The plant's ideal moisture levels
	 * @param soil 				The plant's ideal soil types
	 * @param numLeps 			The number of lep species supported by the plant
	 * @param dollars 			The price of the plant
	 * @param leps 				List of strings representing lep species supported by this plant
	 * @param x 				Double x-location of this plant model
	 * @param y 				Double y-location of this plant model
	 */
	public PlantObjectModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, 
			int numLeps, int dollars, List<String> leps, double x, double y) {
		super(name,sciName,spreadDiameter,sun,moisture,soil,numLeps,dollars);
		this.x = x;
		this.y = y;
		this.leps = leps;
	}
	
	/**
	 * Sets the x value of a plantModel within certain bounds.
	 * @param x 		New x location.
	 * @param x_max 	Maximum x value.
	 */
	public void setXInBounds(double x, double x_max) {
		this.x = Math.min(x,  x_max);
		this.x = Math.max(this.x, 0);
	}
	
	/**
	 * Sets the y value of a plantModel within certain bounds.
	 * @param y 		New y location.
	 * @param y_max 	Maximum y value.
	 */
	public void setYInBounds(double y, double y_max) {
		this.y = Math.min(y,  y_max);
		this.y = Math.max(this.y, 0);
	}
	
	
	// Getters & Setters
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
}
