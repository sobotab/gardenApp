package pkgModel;

import java.io.Serializable;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public abstract class PlantModel implements Serializable{
	
	String name;
	String sciName;
	int spreadDiameter;
	int numLeps;
	int dollars;
	String sun;
	String moisture;
	String soil;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSciName() {
		return sciName;
	}

	public void setSciName(String sciName) {
		this.sciName = sciName;
	}

	public int getSpreadDiameter() {
		return spreadDiameter;
	}

	public void setSpreadDiameter(int spreadDiameter) {
		this.spreadDiameter = spreadDiameter;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
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
	
	
}
