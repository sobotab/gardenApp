package pkgModel;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import pkgController.Soil;

public abstract class GardenModel implements Serializable {
	
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
	Set<HashMap<String, Set<Point>>> conditions;
	double scale;
	
	public GardenModel() {
		
	}

	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return plots;
	}

	public void setPlots(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		this.plots = plots;
	}

	public Set<HashMap<String, Set<Point>>> getConditions() {
		return conditions;
	}

	public void setConditions(Set<HashMap<String, Set<Point>>> conditions) {
		this.conditions = conditions;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
	
}
