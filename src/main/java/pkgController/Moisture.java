package pkgController;

public enum Moisture {
	
	WET("wet"), 
	MOIST("moist"), 
	DRY("dry"),
	FLOODED("flooded");

	private String level = null;
	
	private Moisture(String s){
		level = s;
	}

	public String getLevel() {
		return level;
	}
	
}
