package pkgModel;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	double height, width, xScale, yScale, scale;
	
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
		System.out.println("how many times is this called");
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
	
	public void unScalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()*width, point.getY()*height);
		point.setLocation(scaledPoint.getX(), scaledPoint.getY());
	}
	
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> unScalePlots() {
		int j=0;
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> tmpHashMap = new HashMap<>();
		tmpHashMap.put(Soil.CLAY, new Stack<ArrayList<Point2D.Double>>());
		tmpHashMap.put(Soil.SANDY, new Stack<ArrayList<Point2D.Double>>());
		tmpHashMap.put(Soil.LOAMY, new Stack<ArrayList<Point2D.Double>>());
		for (ArrayList<Point2D.Double> plot: plots.get(Soil.CLAY)) {
			tmpHashMap.get(Soil.CLAY).push(new ArrayList());
			for (Point2D.Double point: plot) {
				tmpHashMap.get(Soil.CLAY).get(j).add((Point2D.Double)point.clone());
			}
			j++;
		}
		j=0;
		for (ArrayList<Point2D.Double> plot: plots.get(Soil.SANDY)) {
			tmpHashMap.get(Soil.SANDY).push(new ArrayList());
			for (Point2D.Double point: plot) {
				tmpHashMap.get(Soil.SANDY).get(j).add((Point2D.Double)point.clone());
			}
			j++;
		}
		j=0;
		for (ArrayList<Point2D.Double> plot: plots.get(Soil.LOAMY)) {
			tmpHashMap.get(Soil.LOAMY).push(new ArrayList());
			for (Point2D.Double point: plot) {
				tmpHashMap.get(Soil.LOAMY).get(j).add((Point2D.Double)point.clone());
			}
			j++;
		}
		for (Stack<ArrayList<Point2D.Double>> soil: tmpHashMap.values()) {
			for (ArrayList<Point2D.Double> plot: soil) {
				for (Point2D.Double point: plot) {
					unScalePoint(point);
				}
			}
		}
		System.out.println(tmpHashMap.equals(plots));
		System.out.println(plots);
		System.out.println(tmpHashMap);
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
			preOutline.add((Point2D.Double)preOutline.get(0).clone());
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
	
	public void scale(double xScale, double yScale) {
		for(Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
			for (ArrayList<Point2D.Double> plot: soil) {
				for (Point2D.Double point: plot) {
					Point2D.Double tmp = new Point2D.Double();
					//tmp.setLocation(point.getX())
				}
			}
		}
	}
	
	public void setXYScale(double xScale, double yScale) {
		this.xScale = xScale;
		this.yScale = yScale;
	}
}
