package pkgController;

public enum Moisture {
	
	WET("wet"), 
	DAMP("damp"), 
	DRY("dry");

	private String level = null;
	
	private Moisture(String s){
		level = s;
	}

	public String getLevel() {
		return level;
	}
	
}
