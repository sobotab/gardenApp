package pkgModel;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantInfoModel extends PlantModel{
	
	int numLeps;
	int dollars;
	String description;

	public PlantInfoModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, int numLeps, int dollars, String description) {
		super(name,sciName,spreadDiameter,sun,moisture,soil);
		this.numLeps = numLeps;
		this.dollars = dollars;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
