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


public class PlantGardenModel extends GardenModel implements Serializable {
	final int DEFAULTSCALE = 25;
	List<PlantObjectModel> plants;
	List<PlantObjectModel> compost;
	ObjectCarouselModel carousel;
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
	int numLeps;
	int budget;
	int dollars;
	int heldPlant;
	double canvasXOffset;
	double canvasYOffset;
	boolean fullscreen;
	double scale_factor;

	public PlantGardenModel(ObjectCarouselModel carouselModel, List<PlantInfoModel> plantInput, HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots, int budget, double scale_factor) {
		this.plots = plots;
		this.budget = budget;
		this.numLeps = 0;
		//this.carousel = new ObjectCarouselModel(plantInput, 0);
		this.carousel = carouselModel;
		carousel.fillCarousel(plantInput, 0);
		this.plants = new ArrayList<PlantObjectModel>();
		//this.plants.addAll(carousel.plants);
		//this.compost = new Set<PlantObjectModel>();
		this.scale_factor = scale_factor;
		
	}
	
	public void addPlantFromCarousel(int index, double init_x, double init_y) {
		PlantObjectModel plant = (carousel.removePlant(index));
		plant.setX(init_x);
		plant.setY(init_y);
		plants.add(plant);
		
		this.dollars += plant.dollars;
		this.numLeps += plant.numLeps;
	}
	
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

	public boolean checkCanvas(int index, double canvas_x, double canvas_y) {
		if (this.canvasXOffset != canvas_x && canvas_x != 0) {
			this.canvasXOffset = canvas_x;
			this.canvasYOffset = canvas_y;
		}
		PlantObjectModel plantCheck = plants.get(index);
		//Added this bit to account for planCheck's soil type now being string
		Soil soil = null;
		if(plantCheck.getSoil().contains("clay")) {
			soil = Soil.CLAY;
		}
		else if(plantCheck.getSoil().contains("sandy")) {
			soil = Soil.SANDY;
		}
		else {
			soil = Soil.LOAMY;
		}
		//Added bit ends
		Iterator validPlotsIter = plots.get( soil ).iterator();
		
		while (validPlotsIter.hasNext()) {		
			Point2D.Double testPoint = new Point2D.Double(plantCheck.x - canvas_x, plantCheck.y - canvas_y);
			
			if (inPolygon(testPoint, (ArrayList<Point2D.Double>)validPlotsIter.next())) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean checkSpread(int index) {
		PlantObjectModel plant1 = plants.get(index);
		double x1 = plant1.x + (plant1.getSpreadDiameter()/7);
		double y1 = plant1.y + (plant1.getSpreadDiameter()/7);
		
		for (PlantObjectModel plant2 : this.plants) {	
			double x2 = plant2.x + (plant2.getSpreadDiameter()/7);		// Model coordinates represent top-left corner of PlantView		
			double y2 = plant2.y + (plant2.getSpreadDiameter()/7);		// Add this value to coordinate vals to offset to center of PlantView
			
			if (plant1 != plant2) {
				double distance = ( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );
				//if (distance <= ( Math.pow( (plant1.getSpreadDiameter()/2) + (plant2.getSpreadDiameter()/2), 2) ))
				if (distance <= ( Math.pow( computeScaleSize(plant1) + computeScaleSize(plant2), 2) ))
					return true;	
			}
		}
		return false;
	}
	
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
				return lep1.getValue().compareTo(lep2.getValue());
			}
		});
		return mostCommonLeps;
	}
	
	public double computeScaleSize(PlantObjectModel plant) {
		double default_radius = plant.getSpreadDiameter()/2;
		return default_radius * scale_factor;
		/*
		if (Math.abs(DEFAULTSCALE - this.scale) < 1) {
			return default_radius;
		}
		//double scaled_radius = default_radius + (default_radius / (DEFAULTSCALE - this.scale) );
		double scaled_radius = default_radius + 30*((DEFAULTSCALE - this.scale) / default_radius);
		System.out.println("default: " + default_radius + ", this scale: " + this.scale + ", " + "new radius: " + scaled_radius);
		return Math.max(1.0, scaled_radius);
		*/
	}
	
	
	public void addPlant(PlantObjectModel plant) {
		this.getPlants().add(plant);
	}
	
	public void removePlant(int x, int y) {
		
	}
	
	public void setPlantLocation(int index, double x, double y) {
		plants.get(index).setX(x);
		plants.get(index).setY(y);
	}
	
	public void dragPlant(int index, double x, double y, double x_max, double y_max) {
		PlantObjectModel dragPlant = plants.get(index);
		dragPlant.setXInBounds( dragPlant.getX() + x, x_max);	
		dragPlant.setYInBounds( dragPlant.getY() + y, y_max);
	}
	
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
		double shrink_factor_x = canvas_width / max_x;
		double shrink_factor_y = canvas_height / max_y;
		for (Stack<ArrayList<Point2D.Double>> soil_type : plots.values()) {
			for (ArrayList<Point2D.Double> plot : soil_type) {
				for (Point2D.Double point : plot) {
					point.x = point.x * shrink_factor_x;
					point.y = point.y * shrink_factor_y;
				}
			}
		}
	}
	
	
	public void compost(PlantObjectModel plant) {
		
	}
	
	public boolean checkConditions() {
		return false;
	}

	public List<PlantObjectModel> getPlants() {
		return this.plants;
	}

	public void setPlants(List<PlantObjectModel> plants) {
		this.plants = plants;
	}

	public List<PlantObjectModel> getCompost() {
		return this.compost;
	}

	public void setCompost(List<PlantObjectModel> compost) {
		this.compost = compost;
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

	public int getHeldPlant() {
		return this.heldPlant;
	}

	public void setHeldPlant(int heldPlant) {
		this.heldPlant = heldPlant;
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
