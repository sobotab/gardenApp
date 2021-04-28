package pkgModel;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
	double height, width, scale;
	
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
		height = 500.0;
		width = 500.0;
		scale = 25;
	}


	public void addPreOutline(Point2D.Double point) {
		preOutline.add(scalePoint(point));
		setEndPoint(point);
	}

	public void setEndPoint(Point2D.Double point) {
		if (set) {
			endPoint = point;
			this.set = false;
		}
	}
	
	public Point2D.Double scalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()/width, point.getY()/height);
		return scaledPoint;
	}
	
	public Point2D.Double unScalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()*width, point.getY()*height);
		return scaledPoint;
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> unScalePlots() {
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> tmpHashMap = new HashMap<>();
		tmpHashMap = (HashMap<Soil, Stack<ArrayList<Double>>>) plots.clone();
		for (Stack<ArrayList<Point2D.Double>> soil: tmpHashMap.values()) {
			for (ArrayList<Point2D.Double> plot: soil) {
				for (Point2D.Double point: plot) {
					point=unScalePoint(point);
				}
			}
		}
		return tmpHashMap;
	}
	
	public Point2D.Double getEndPoint() {
		return endPoint;
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return unScalePlots();
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> undo(){
		if (undoStack.size() > 0) {
			plots.get(undoStack.pop()).pop();
			return unScalePlots();
		}
		return null;
	}
	
	public void addPlot(boolean drawing, Soil soil) {
		if (!drawing) {
			preOutline.add(scalePoint(preOutline.get(0)));
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
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public boolean scale(double change) {
		boolean inRange = true;
		for (Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
			for (int i=0; i<soil.size(); i++) {
				for (Point2D.Double point: soil.get(i)) {
					double x = (point.getX()+250.0)/(scale/(scale+change))-250.0;
					double y = (point.getY()+250.0)/(scale/(scale+change))-250.0;
					if (x > 450 || y > 450) {
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
			i++;
		}
	}
}
