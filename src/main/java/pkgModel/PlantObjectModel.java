package pkgModel;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantObjectModel extends PlantModel{
	
	double x;
	double y;
	int height;
	int width;
	//private final double Y_MAX = 400;
	//private final double X_MAX = 700;
	
	public PlantObjectModel(String name, String sciName, int spreadDiameter, String sun, String moisture, String soil, double x, double y, int height ,int width) {
		super(name,sciName,spreadDiameter,sun,moisture,soil);

		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public double getX() {
		return x;
	}

	public void setXInBounds(double x, double x_max) {
		this.x = Math.min(x,  x_max);
		this.x = Math.max(this.x, 0);
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setYInBounds(double y, double y_max) {
		this.y = Math.min(y,  y_max);
		this.y = Math.max(this.y, 0);
	}
	
	public void setY(double y) {
		this.y = y;
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
