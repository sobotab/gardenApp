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
	/**
	 * An ArrayList of Point2D.Double that saves the current plot being
	 * drawn before adding it to plots
	 */
	ArrayList<Point2D.Double> preOutline;

	Stack<Soil> undoStack;
	/**
	 * The moisture level of the garden
	 */
	Moisture moisture;
	/**
	 * The sun level of the garden
	 */
	Sun sun;
	/**
	 * The budget set for the garden
	 */
	int budget;
	/**
	 * canvasLength: the length of the canvas in pixels
	 * gridSize: the size of the grid squares
	 * canvasHeight: the height of the canvas
	 * rows: the number of rows in the canvas
	 * columns: the number of columns in the canvas
	 */
	double canvasLength, gridSize, canvasHeight, canvasWidth, rows, columns;
	
	/**
	 * The first point clicked by the user when drawing a plot
	 */
	Point2D.Double endPoint;
	/**
	 * Boolean to only set the endPoint when this is true
	 */
	boolean set;
	
	/**
	 * Constructor for DrawGardenModel. Initializes all of the data structures needed.
	 * Initializes all values needed.
	 */
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


	/**
	 * @param point added to preOutline.
	 * Adds a point to preOutline and sets the endPoint if its the first point in the plot
	 */
	public void addPreOutline(Point2D.Double point) {
		preOutline.add(scalePoint(point));
		setEndPoint(point);
	}

	/**
	 * @param point to be set to endPoint
	 * endPoint is only set if set is true, then changes set to false so endPoint is not changed.
	 */
	public void setEndPoint(Point2D.Double point) {
		if (set) {
			endPoint = point;
			this.set = false;
		}
	}
	
	/**
	 * @param point to be scaled
	 * @return
	 * Scales all points relative to the size of the canvas size so that they may be scaled easily.
	 */
	public Point2D.Double scalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()/canvasLength, point.getY()/canvasLength);
		return scaledPoint;
	}
	
	/**
	 * @param point to be unscaled
	 * Unscales a point so that it may be put back onto the canvas
	 */
	public void unScalePoint(Point2D.Double point) {
		Point2D.Double scaledPoint = new Point2D.Double();
		scaledPoint.setLocation(point.getX()*canvasLength, point.getY()*canvasLength);
		point.setLocation(scaledPoint.getX(), scaledPoint.getY());
	}
	
	/**
	 * @return an unscaled data structure of plots so that it may be redrawn on the canvas
	 */
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
	
	/**
	 * @return the endPoint, i.e. the first point clicked when drawing a plot
	 */
	public Point2D.Double getEndPoint() {
		return endPoint;
	}
	
	/**
	 * @return the unscaled plots
	 */
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return unScalePlots();
	}
	
	/**
	 * @return plots with one less plot than before undo was called
	 * This pops one plot from the data structure
	 */
	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> undo(){
		if (undoStack.size() > 0) {
			plots.get(undoStack.pop()).pop();
			return unScalePlots();
		}
		return null;
	}
	
	/**
	 * @param drawing boolean to ensure that the user is still not currently drawing
	 * @param soil the soil type of the plot
	 * Adds a plot to the plots data structure
	 */
	public void addPlot(boolean drawing, Soil soil) {
		if (!drawing) {
			preOutline.add((Point2D.Double)preOutline.get(0).clone());
			plots.get(soil).add(preOutline);
			preOutline = new ArrayList<>();
			set = true;
			undoStack.add(soil);
		}
	}
	
	/**
	 * @param length of the canvas
	 */
	public void setCanvasLength(double length) {
		this.canvasLength = length;
	}
	
	/**
	 * @param moisture value of the plots
	 */
	public void setMoisture(Moisture moisture) {
		this.moisture = moisture;
	}
	
	/**
	 * @param sun value of the plots
	 */
	public void setSun(Sun sun) {
		this.sun = sun;
	}
	
	/**
	 * @param budget value of the garden
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * @return the moisture value
	 */
	public Moisture getMoisture() {
		return moisture;
	}

	/**
	 * @return the sun value
	 */
	public Sun getSun() {
		return sun;
	}
	
	/**
	 * @return the budget value
	 */
	public int getBudget() {
		return budget;
	}
	

	/**
	 * Sets the current minimum of canvasLenght or canvasWidth
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	/**
	 * @param grid size of squares
	 */

	public void setGridSize(double grid) {
		this.gridSize = grid;
	}
	
	/**
	 * @return the size of grids
	 */
	public double getGridSize() {
		return gridSize;
	}
	
	/**
	 * @return returns the height of the canvas
	 */
	public double getCanvasHeight() {
		return canvasHeight;
	}

	/**
	 * @param canvasHeight to be set to the model
	 */
	public void setCanvasHeight(double canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	/**
	 * @return the width of the canvas
	 */
	public double getCanvasWidth() {
		return canvasWidth;
	}

	/**
	 * @param canvasWidth to be set to the model
	 */
	public void setCanvasWidth(double canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	/**
	 * @return the number of rows on the canvas
	 */
	public double getRows() {
		return rows;
	}

	/**
	 * @param rows to be set to the model
	 */
	public void setRows(double rows) {
		this.rows = rows;
	}

	/**
	 * @return the number of columns
	 */
	public double getColumns() {
		return columns;
	}

	/**
	 * @param columns to be set to the model
	 */
	public void setColumns(double columns) {
		this.columns = columns;
	}

	/**
	 * @return the minimum of the canvasHeight and the canvasWidth
	 */
	public double getCanvasLength() {
		return canvasLength;
	}

	/**
	 * @param minGrid the size of the grid to scale to
	 * @return true if one of the plots has gone off the canvas, false if the plots are still on the canvas.
	 * Scales all of the plots based off the change in grid
	 */
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
	
	/**
	 * Scales all of the points to fill the canvas
	 */
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
