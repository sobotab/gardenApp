package pkgModel;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public abstract class PlantModel {
	
	String name;
	String sciName;
	int spreadDiameter;
	String sun;
	String moisture;
	String soil;

	public PlantModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil) {
		this.name = name;
		this.sciName = sciName;
		this.spreadDiameter = spreadDiameter;
		this.sun = sun;
		this.moisture = moisture;
		this.soil = soil;
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
		soil = soil;
	}
	
	
}
