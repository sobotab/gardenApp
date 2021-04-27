package pkgModel;

import java.io.Serializable;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantInfoModel extends PlantModel implements Serializable{
	
	int numLeps;
	int dollars;
	String description;

	public PlantInfoModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, int numLeps, int dollars, String description) {
		super(name,sciName,spreadDiameter,sun,moisture,soil,numLeps,dollars);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
