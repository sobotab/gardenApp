package pkgModel;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class DrawGardenModel extends GardenModel {
	ArrayList<Point2D.Double> preOutline;
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
	Stack<Soil> undoStack;
	Moisture moisture;
	Sun sun;
	int budget;
	int height;
	int width;
	int scale;
	
	Point2D.Double endPoint;
	boolean set;
	
	public DrawGardenModel() {
		plots = new HashMap<>();
		plots.put(Soil.CLAY, new Stack<>());
		plots.put(Soil.SANDY, new Stack<>());
		plots.put(Soil.LOAMY, new Stack<>());
		endPoint = null;
		preOutline = new ArrayList<>();
		set = true;
		undoStack = new Stack<>();
		height = 500;
		width = 500;
		scale = 25;
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
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return this.plots;
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> undo(){
		if (undoStack.size() > 0) {
			plots.get(undoStack.pop()).pop();
			return plots;
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
	
	public void setMoisture(Moisture moisture) {
		this.moisture = moisture;
	}
	
	public void setSun(Sun sun) {
		this.sun = sun;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Moisture getMoisture() {
		return moisture;
	}

	public Sun getSun() {
		return sun;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public boolean scale(double change) {
		boolean inRange = true;
		for (Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
			for (int i=0; i<soil.size(); i++) {
				for (Point2D.Double point: soil.get(i)) {
					double x = point.getX()/(scale/(scale+change));
					double y = point.getY()/(scale/(scale+change));
					if (x > 475 || y > 475) {
						inRange = false;
					}
					point.setLocation(x, y);
				}
			}
		}
		scale++;
		return inRange;
	}
	
	public void finish() {
		int i = 1;
		while(scale(i)) {
			System.out.println(i);
			i++;
		}
	}
}
