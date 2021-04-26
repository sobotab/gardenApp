package pkgModel;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import pkgController.Soil;

public class DrawGardenModel extends GardenModel {
	ArrayList<Point2D.Double> preOutline;
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
	Stack<Soil> undoStack;
	
	Point2D.Double endPoint;
	boolean set;
	
	public DrawGardenModel() {
		plots = new HashMap<>();
		plots.put(Soil.CLAY, new Stack<>());
		plots.put(Soil.SANDY, new Stack<>());
		plots.put(Soil.SILTY, new Stack<>());
		plots.put(Soil.PEATY, new Stack<>());
		plots.put(Soil.CHALKY, new Stack<>());
		plots.put(Soil.LOAMY, new Stack<>());
		endPoint = null;
		preOutline = new ArrayList<>();
		set = true;
		undoStack = new Stack<>();
	}


	public void addPreOutline(Point2D.Double point) {
		preOutline.add(point);
		setEndPoint(point);
	}

	public void setEndPoint(Point2D.Double point) {
		if (set) {
			endPoint = point;
			this.set = false;
		}
	}
	
	public Point2D.Double getEndPoint() {
		return endPoint;
	}
	
	public ArrayList<Point2D.Double> undo(){
		if (undoStack.size() > 0) {
			return plots.get(undoStack.pop()).pop();
		}
		return null;
	}
	
	public void addPlot(boolean drawing, Soil soil) {
		if (!drawing) {
			plots.get(soil).add(preOutline);
			preOutline = new ArrayList<>();
			set = true;
			undoStack.add(soil);
		}
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return this.plots;
	}
}
