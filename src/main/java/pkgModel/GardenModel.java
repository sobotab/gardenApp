package pkgModel;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public abstract class GardenModel implements Serializable {
	
	Set<Point> outline;
	Set<HashMap<String, Set<Point>>> conditions;
	int scale;
	int orientation;
	
	public GardenModel() {
		
	}

	public Set<Point> getOutline() {
		return outline;
	}

	public void setOutline(Set<Point> outline) {
		this.outline = outline;
	}

	public Set<HashMap<String, Set<Point>>> getConditions() {
		return conditions;
	}

	public void setConditions(Set<HashMap<String, Set<Point>>> conditions) {
		this.conditions = conditions;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}
	
	
}
