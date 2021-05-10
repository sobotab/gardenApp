package pkgModel;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import pkgController.Soil;
import pkgView.PlantView;

/**
 * 
 * @author Ryan Dean
 * Model class for the Edit Garden screen. Tracks plant location and garden conditions, and stores all information needed for save/load.
 */
public class PlantGardenModel extends GardenModel implements Serializable {
	/**
	 * List of PlantObjectModels that represent plants in the garden (post-dragging from carousel).
	 */
	List<PlantObjectModel> plants;
	/**
	 * Model class for the carousel holding plant objects pre-dragging into garden.
	 */
	ObjectCarouselModel carousel;
	/**
	 * Hashmap storing all plot outlines organized by soil type.
	 */
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
	/**
	 * Integer representation of total leps supported by plant in garden.
	 */
	int numLeps;
	/**
	 * Maximum budget for the garden being edited, as input by user in Draw Garden.
	 */
	int budget;
	/**
	 * Current total cost of all plants in the garden.
	 */
	int dollars;
	/**
	 * X-Offset of the canvas relative to the scene, used to put model coordinates in terms of plot coordinates.
	 */
	double canvasXOffset;
	/**
	 * Y-Offset of the canvas relative to the scene, used to put model coordinates in terms of plot coordinates.
	 */
	double canvasYOffset;
	/**
	 * Scale factor for this garden, determined by ratio between scale selected in Draw Garden and the default scale.
	 */
	double scale_factor;
	
	/**
	 * Constructor for this model class. Initializes the carousel model and fills it with the plants chosen in Select Plants.
	 * @param carouselModel 	The model class for the carousel in this screen.
	 * @param plantInput 		A list of plant models to be placed in the carousel.
	 * @param plots 			Hashmap containing all plot outlines drawn in Draw Garden, organized by soil type.
	 * @param budget 			Integer representing maximum budget for this garden.
	 * @param scale_factor 		Double computed through view's DEFAULTSCALE / max_dimension of plot.
	 */
	public PlantGardenModel(ObjectCarouselModel carouselModel, List<PlantInfoModel> plantInput, HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots, int budget, double scale_factor) {
		this.plots = plots;
		this.budget = budget;
		this.numLeps = 0;
		this.carousel = carouselModel;
		carousel.fillCarousel(plantInput);
		System.out.println("carousel size post filling: " + carousel.getPlants().size());
		this.plants = new ArrayList<PlantObjectModel>();
		this.scale_factor = scale_factor;
	}
	
	/**
	 * Helper method used to place a plant object in the garden from the carousel, increments budget/lep trackers.
	 * @param index 	Index of plant to be added in carousel's plants list.
	 * @param init_x 	Initial x value to set the plant model to.
	 * @param init_y 	Initial y value to set the plant model to.
	 */
	public void addPlantFromCarousel(int index, double init_x, double init_y) {
		PlantObjectModel plant = (carousel.removePlant(index));
		plant.setX(init_x);
		plant.setY(init_y);
		plants.add(plant);
		
		this.dollars += plant.dollars;
		this.numLeps += plant.numLeps;
	}
	
	/**
	 * Method used for detecting if plant object is inside a plot. Loops through adjacent vertices in plot polygon and determines 
	 * if plant's point is between them, and whether its y-value (horizontal line) intersects the the polygon odd/even # of times.
	 * @param testPoint 	The xy coordinates of the plant object.
	 * @param polygon 		The list of points that make up the outline of the garden plot.
	 * @return 				Boolean representing if point is inside polygon.
	 */
	public boolean inPolygon (Point2D.Double testPoint, ArrayList<Point2D.Double> polygon) {
		int vertex, adjacentVertex;
		int count = 0;
		
		for (vertex = 0; vertex < polygon.size(); vertex++) {
			adjacentVertex = vertex + 1;
			if (vertex == polygon.size()-1) { 
				adjacentVertex = 0; 
			}
			
			Point2D.Double v1 = polygon.get(vertex);
			Point2D.Double v2 = polygon.get(adjacentVertex);
			
			if ((v1.y > testPoint.y) != (v2.y > testPoint.y)) {					// is testPoint between adjacent vertices?
				Double slope = (testPoint.y - v1.y) / (v2.y - v1.y);			// find x-coordinate of where line between adjacent vertices would intersect
				if ( slope * (v2.x - v1.x) + v1.x < testPoint.x)				// with horizontal line extending from testPoint
					count++;
			}
		}
		return (count % 2 != 0);					// if odd number of intersections to the left of testPoint, it is inside polygon
	}

	/**
	 * Method for detecting whether plant objects are in a plot of the correct soil type. Uses inPolygon().
	 * @param index 		Index of the plant object to be tested.
	 * @param canvas_x 		The x-offset of the canvas containing the plot drawings in the scene.
	 * @param canvas_y 		The y-offset of the canvas containing the plot drawings in the scene.
	 * @return 				Boolean representing whether plant object is in a valid plot.
	 */
	public boolean checkCanvas(int index, double canvas_x, double canvas_y) {
		if (this.canvasXOffset != canvas_x && canvas_x != 0) {
			this.canvasXOffset = canvas_x;
			this.canvasYOffset = canvas_y;
		}
		PlantObjectModel plantCheck = plants.get(index);
		//Added this bit to account for planCheck's soil type now being string
		ArrayList<Soil> soil = new ArrayList<Soil>();
		if(plantCheck.getSoil().contains("clay")) {
			soil.add(Soil.CLAY);
		}
		if(plantCheck.getSoil().contains("sandy")) {
			soil.add(Soil.SANDY);
		}
		if(plantCheck.getSoil().contains("loamy")) {
			soil.add(Soil.LOAMY);
		}
		//Added bit ends
		for (Soil soil_type : soil) {
			Iterator validPlotsIter = plots.get( soil_type ).iterator();
			
			while (validPlotsIter.hasNext()) {		
				Point2D.Double testPoint = new Point2D.Double(plantCheck.x - canvas_x, plantCheck.y - canvas_y);
				
				if (inPolygon(testPoint, (ArrayList<Point2D.Double>)validPlotsIter.next())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method for checking whether two spread circles overlap one another. 
	 * @param index 	Index of the plant spread to be tested against all other spreads.
	 * @return 			Boolean representing whether the spread experiences overlap.
	 */
	public boolean checkSpread(int index) {
		PlantObjectModel plant1 = plants.get(index);
		double x1 = plant1.x + (plant1.getSpreadDiameter()/7);
		double y1 = plant1.y + (plant1.getSpreadDiameter()/7);
		
		for (PlantObjectModel plant2 : this.plants) {	
			double x2 = plant2.x + (plant2.getSpreadDiameter()/7);		// Model coordinates represent top-left corner of PlantView		
			double y2 = plant2.y + (plant2.getSpreadDiameter()/7);		// Add fraction of spread (spread is tied to PlantImage size) to offset model coordinates to center of PlantView.
			
			if (plant1 != plant2) {
				double distance = ( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );
				if (distance <= ( Math.pow( computeScaleSize(plant1) + computeScaleSize(plant2), 2) ))
					return true;	
			}
		}
		return false;
	}
	
	/**
	 * Method for tracking and organizing all plant species supported by plants in the scene.
	 * @return 	List containing each plant species and an integer of the number of plants supporting it.
	 */
	public ArrayList<Map.Entry<String, Integer>> trackMostPopularLeps() {
		HashMap<String, Integer> lepTracker = new HashMap<String, Integer>();
		for (PlantObjectModel plant : this.plants) {
			if (plant.leps != null) {
				for (String lep : plant.leps) {
					if (lepTracker.containsKey(lep)) {
						int count = lepTracker.get(lep) + 1;
						lepTracker.put(lep, count);
					}
					else {
						lepTracker.put(lep, 1);
					}
				}
			}
		}
		
		ArrayList<Map.Entry<String, Integer>> mostCommonLeps;
		mostCommonLeps = new ArrayList<Map.Entry<String, Integer>> (lepTracker.entrySet());
		mostCommonLeps.sort(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> lep1, Map.Entry<String, Integer> lep2) {
				return lep2.getValue().compareTo(lep1.getValue());
			}
		});
		return mostCommonLeps;
	}
	
	/**
	 * Helper method for computing a plant object' scale given the scale factor of the garden.
	 * @param plant 	The plant object whose spread is to be altered.
	 * @return 			Double representing the scaled spread.
	 */
	public double computeScaleSize(PlantObjectModel plant) {
		double default_radius = plant.getSpreadDiameter()/2;
		return default_radius * scale_factor;
	}
	
	
	/**
	 * Method to immediately change plant model's location, NOT drag it for incremental change.
	 * @param index 	Index of the plant object to be moved in this class's plants list.
	 * @param x 		New x-coordinate.
	 * @param y 		New y-coordinate.
	 */
	public void setPlantLocation(int index, double x, double y) {
		plants.get(index).setX(x);
		plants.get(index).setY(y);
	}
	
	/**
	 * Method called when controller detects plant dragging. Changes plant model's xy coordinates.
	 * @param index 	Index of the plant object to be moved in this class's plants list.
	 * @param x 		X-Coordinate of the drag event.
	 * @param y 		Y-Coordinate of the drag event.
	 * @param x_max 	Maximum x-value of the stackpane in view that holds the garden canvas.
	 * @param y_max 	Maximum y-value of the stackpane in view that holds the garden canvas.
	 */
	public void dragPlant(int index, double x, double y, double x_max, double y_max) {
		PlantObjectModel dragPlant = plants.get(index);
		dragPlant.setXInBounds( dragPlant.getX() + x, x_max);	
		dragPlant.setYInBounds( dragPlant.getY() + y, y_max);
	}
	
	/**
	 * Method used to scale plot coordinates so that plot drawings fit on view's canvas, which is not necessarily
	 * the same size as Draw Garden's canvas.
	 * @param canvas_width 		Integer representing the view's canvas's width.
	 * @param canvas_height 	Integer representing the view's canvas's height.
	 */
	public void adaptPlots(int canvas_width, int canvas_height) {
		
		double max_x = 0;
		double max_y = 0;
		for (Stack<ArrayList<Point2D.Double>> soil_type : plots.values()) {
			for (ArrayList<Point2D.Double> plot : soil_type) {
				for (Point2D.Double point : plot) {
					max_x = Math.max(max_x, point.x);
					max_y = Math.max(max_y, point.y);
				}
			}
		}
		double shrink_factor = 1.0;
		if ((max_x - canvas_width) > (max_y - canvas_height)) {
			shrink_factor = canvas_width / max_x;
		}
		else {
			shrink_factor = canvas_height / max_y;
		}
		for (Stack<ArrayList<Point2D.Double>> soil_type : plots.values()) {
			for (ArrayList<Point2D.Double> plot : soil_type) {
				for (Point2D.Double point : plot) {
					point.x = point.x * shrink_factor;
					point.y = point.y * shrink_factor;
				}
			}
		}
		System.out.println("maxx" + max_x);
		System.out.println("maxy" + max_y);
		System.out.println("canvasx" + canvas_width);
		System.out.println("canvasy" + canvas_height);

		System.out.println("shrink" + shrink_factor);
		this.setScaleFactor(shrink_factor * scale_factor);
		
	}
	
	
	
	// Getters & Setters

	public List<PlantObjectModel> getPlants() {
		return this.plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
	}

	public int getNumLeps() {
		return this.numLeps;
	}

	public void setNumLeps(int numLeps) {
		this.numLeps = numLeps;
	}
	
	public int getBudget() {
		return this.budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getDollars() {
		return this.dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}
	
	public ObjectCarouselModel getCarousel() {
		return this.carousel;
	}
	
	public void setCarousel(ObjectCarouselModel carousel) {
		this.carousel = carousel;
	}

	public HashMap<Soil, Stack<ArrayList<Point2D.Double>>> getPlots() {
		return this.plots;
	}

	public double getScaleFactor() {
		return this.scale_factor;
	}
	
	public void setScaleFactor(double scale_factor) {
		this.scale_factor = scale_factor;
	}
	
	public double getCanvasXOffset() {
		return this.canvasXOffset;
	}
	
	public double getCanvasYOffset() {
		return this.canvasYOffset;
	}
}
