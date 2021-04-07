package pkgController;

public enum Page {
	
	WELCOME("welcome"), 
	DRAWGARDEN("drawgarden"), 
	SELECTPLANTS("selectplants"),
	EDITGARDEN("editgarden"),
	OPENGARDEN("opengarden"),
	INFO("info"),
	INFOPOPUP("infopopup"),
	RESOURCES("resources");

	private String name = null;
	
	private Page(String s){
		name = s;
	}

	public String getName() {
		return name;
	}
	
}
