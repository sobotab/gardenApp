package pkgModel;

import java.io.Serializable;
import java.util.List;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;
/**
 * 
 * @author Zane Greenholt
 * Class representing a plant and its important attributes
 */
public abstract class PlantModel implements Serializable{
	/**
	 * The plant's common name
	 */
	String name;
	/**
	 * The plant's scientific name
	 */
	String sciName;
	/**
	 * The plant's root spread
	 */
	int spreadDiameter;
	/**
	 * The number of lep species supported by the plant
	 */
	int numLeps;
	/**
	 * The price of the plant
	 */
	int dollars;
	/**
	 * The plant's ideal sun level
	 */
	String sun;
	/**
	 * The plant's ideal moisture levels
	 */
	String moisture;
	/**
	 * The plant's ideal soil types
	 */
	String soil;
	/**
	 * List of the names of the lep species the plant supports
	 */
	List<String> leps;

	/**
	 * Constructor sets fields to the argument values
	 * 
	 * @param name The plant's common name
	 * @param sciName The plant's scientific name
	 * @param spreadDiameter The plant's root spread
	 * @param sun The plant's ideal sun level
	 * @param moisture The plant's ideal moisture levels
	 * @param soil The plant's ideal soil types
	 * @param numLeps The number of lep species supported by the plant
	 * @param dollars The price of the plant
	 */
	public PlantModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, int numLeps, int dollars) {
		this.name = name;
		this.sciName = sciName;
		this.spreadDiameter = spreadDiameter;
		this.sun = sun;
		this.moisture = moisture;
		this.soil = soil;
		this.numLeps = numLeps;
		this.dollars = dollars;
	}
	/**
	 * 
	 * Getter for name field
	 * 
	 * @return name field - String representing the plant's common name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter for name field
	 * 
	 * @param name A String representing the plant's common name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter for sciName field
	 * 
	 * @return sciName field - String representing the plant's scientific name
	 */
	public String getSciName() {
		return sciName;
	}
	/**
	 * Setter for sciName field
	 * 
	 * @param sciName A String representing the plant's scientific name
	 */
	public void setSciName(String sciName) {
		this.sciName = sciName;
	}
	/**
	 * Getter for spreadDiameter field
	 * 
	 * @return spreadDiameter field - An int representing the plant's root spread
	 */
	public int getSpreadDiameter() {
		return spreadDiameter;
	}
	/**
	 * Setter for spreadDiameter field
	 * 
	 * @param spreadDiameter An int representing the plant's root spread
	 */
	public void setSpreadDiameter(int spreadDiameter) {
		this.spreadDiameter = spreadDiameter;
	}
	/**
	 * Getter for sun field
	 * 
	 * @return sun field - A String representing the plant's ideal sun level
	 */
	public String getSun() {
		return sun;
	}
	/**
	 * Setter for sun field
	 * 
	 * @param sun A string representing the plant's ideal sun level
	 */
	public void setSun(String sun) {
		this.sun = sun;
	}
	/**
	 * Getter for moisture field
	 * 
	 * @return moisture field - A String representing the plant's ideal moisture levels
	 */
	public String getMoisture() {
		return moisture;
	}
	/**
	 * Setter for moisture field
	 * 
	 * @param moisture A string representing the plant's ideal moisture levels
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
	/**
	 * Getter for soil field
	 * 
	 * @return soil field - A string representing the plant's ideal soil types
	 */
	public String getSoil() {
		return soil;
	}
	/**
	 * Setter for soil field
	 * 
	 * @param soil A String representing the plant's ideal soil types
	 */
	public void setSoil(String soil) {
		this.soil = soil;
	}
	/**
	 * Getter for numLeps field
	 * 
	 * @return numLeps field - An int representing the number of lep species supported by the plant
	 */
	public int getNumLeps() {
		return numLeps;
	}
	/**
	 * Setter for numLeps field
	 * 
	 * @param numLeps An int representing the number of lep species supported by the plant
	 */
	public void setNumLeps(int numLeps) {
		this.numLeps = numLeps;
	}
	/**
	 * Getter for dollars field
	 * 
	 * @return dollars field - An int representing the price of the plant
	 */
	public int getDollars() {
		return dollars;
	}
	/**
	 * Setter for dollars field
	 * 
	 * @param dollars - An int representing the price of the plant
	 */
	public void setDollars(int dollars) {
		this.dollars = dollars;
	}
	/**
	 * Getter for the leps field
	 * 
	 * @return List of scientific names of supported leps for this hostplant
	 */
	public List<String> getLeps() {
		return leps;
	}
	/**
	 * Setter for the leps field
	 * 
	 * @param leps List of Strings representing lep names
	 */
	public void setLeps(List<String> leps) {
		this.leps = leps;
	}
	
	
}
