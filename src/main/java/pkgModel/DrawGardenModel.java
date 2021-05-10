package pkgModel;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
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
	double canvasLength, gridSize, canvasHeight, canvasWidth, rows, columns;
	
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
		scaledPoint.setLocation(point.getX()/canvasLength, point.getY()/canvasLength);
		return scaledPoint;
	}
	
	public void unScalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()*canvasLength, point.getY()*canvasLength);
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
	
	public void setCanvasLength(double length) {
		this.canvasLength = length;
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
	
	public void setGridSize(double grid) {
		this.gridSize = grid;
	}
	
	public double getGridSize() {
		return gridSize;
	}
	
	public double getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(double canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public double getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(double canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public double getRows() {
		return rows;
	}

	public void setRows(double rows) {
		this.rows = rows;
	}

	public double getColumns() {
		return columns;
	}

	public void setColumns(double columns) {
		this.columns = columns;
	}

	public double getCanvasLength() {
		return canvasLength;
	}

	public boolean scale(double minGrid) {
		boolean outOfBounds = false;
		for(Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
			for (ArrayList<Point2D.Double> plot: soil) {
				for (Point2D.Double point: plot) {
					double x = point.getX()/(minGrid/gridSize);
					double y = point.getY()/(minGrid/gridSize);
					point.setLocation(x, y);
					if (x > 1.0 || y > 1.0) {
						outOfBounds = true;
					}
				}
			}
		}
		gridSize = minGrid;
		return outOfBounds;
	}
	
	public void finish() {
		if (!(plots.get(Soil.CLAY).isEmpty() && plots.get(Soil.SANDY).isEmpty() && plots.get(Soil.LOAMY).isEmpty())) {
			double minX = 1.0;
			double minY = 1.0;
			for(Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
				for (ArrayList<Point2D.Double> plot: soil) {
					for (Point2D.Double point: plot) {
						if (point.getX() < minX) {
							minX = point.getX();
						}
						if (point.getY() < minY) {
							minY = point.getY();
						}
					}
				}
			}
			for(Stack<ArrayList<Point2D.Double>> soil: plots.values()) {
				for (ArrayList<Point2D.Double> plot: soil) {
					for (Point2D.Double point: plot) {
						point.setLocation(point.getX()-minX+.01, point.getY()-minY+.01);
					}
				}
			}
			while(scale(gridSize+1.0)) {}
			while(!scale(gridSize-1.0)) {}
			scale(gridSize+1.0);
		}
	}

}
