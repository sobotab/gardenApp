package pkgModel;

public class PlantInfoModel extends PlantModel{
	
	int numLeps;
	int dollars;
	String description;

	public PlantInfoModel(int numLeps, int dollars, String description) {
		
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
