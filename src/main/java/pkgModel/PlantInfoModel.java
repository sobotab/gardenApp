package pkgModel;

import java.io.Serializable;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;
/**
 * 
 * @author Zane Greenholt
 * Class representing plant objects that are only used for informational purposes rather than to put in the garden
 */
public class PlantInfoModel extends PlantModel implements Serializable{
	/**
	 * A short description of the plant
	 */
	String description;
	
	/**
	 * Constructor sets the values of all fields
	 * 
	 * @param name The plant's common name
	 * @param sciName The plant's scientific name
	 * @param spreadDiameter The plant's root spread
	 * @param sun The plant's ideal sun level
	 * @param moisture The plant's ideal moisture levels
	 * @param soil The plant's ideal soil types
	 * @param numLeps The number of lep species supported by the plant
	 * @param dollars The price of the plant
	 * @param description A short description of the plant
	 */
	public PlantInfoModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, int numLeps, int dollars, String description) {
		super(name,sciName,spreadDiameter,sun,moisture,soil,numLeps,dollars);
		this.description = description;
	}
	/**
	 * Getter for description field
	 * 
	 * @return description field - A short String description of the plant
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter for description field
	 * 
	 * @param description - A String that is a short description of the plant
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
