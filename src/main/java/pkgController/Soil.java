package pkgController;

public enum Soil {
	
	CLAY("clay"), 
	SANDY("sandy"), 
	SILTY("silty"),
	PEATY("peaty"),
	CHALKY("chalky"),
	LOAMY("loamy");

	private String level = null;
	
	private Soil(String s){
		level = s;
	}

	public String getLevel() {
		return level;
	}
	
}
