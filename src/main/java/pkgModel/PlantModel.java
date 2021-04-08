package pkgModel;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public abstract class PlantModel {
	
	String name;
	String sciName;
	int spreadDiameter;
	Sun sun;
	Moisture moisture;
	Soil Soil;

	public PlantModel() {
		
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

	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public Moisture getMoisture() {
		return moisture;
	}

	public void setMoisture(Moisture moisture) {
		this.moisture = moisture;
	}

	public Soil getSoil() {
		return Soil;
	}

	public void setSoil(Soil soil) {
		Soil = soil;
	}
	
	
}
