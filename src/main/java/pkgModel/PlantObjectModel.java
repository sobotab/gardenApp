package pkgModel;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantObjectModel extends PlantModel{
	
	int x;
	int y;
	int height;
	int width;
	private final double BOTTOM = 400;
	
	public PlantObjectModel(int x, int y, int height ,int width) {

	public PlantObjectModel(String name, String sciName, int spreadDiameter, Sun sun, Moisture moisture, Soil soil, int x, int y, int height ,int width) {
		super(name,sciName,spreadDiameter,sun,moisture,soil);

		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = (int)Math.min(y,  BOTTOM);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
