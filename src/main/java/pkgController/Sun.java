package pkgController;

public enum Sun {
	
	FULLSUN("full sun"), 
	PARTSUN("part sun"), 
	FULLSHADE("full shade");

	private String level = null;
	
	private Sun(String s){
		level = s;
	}

	public String getLevel() {
		return level;
	}
	
}
