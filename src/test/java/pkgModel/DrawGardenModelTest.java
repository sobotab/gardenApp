package pkgModel;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class DrawGardenModelTest {
	
	DrawGardenModel drawGarden1 = new DrawGardenModel();

	@Test
	public void testDrawGardenModel() {
		assertFalse(drawGarden1.preOutline==null);
	}

	@Test
	public void testAddPreOutline() {
		Point2D.Double point = new Point2D.Double(10,10);
		drawGarden1.addPreOutline(point);
		assertEquals(drawGarden1.endPoint, point);
	}
	
	@Test
	public void testSetEndPoint() {
		Point2D.Double point = new Point2D.Double(11,11);
		drawGarden1.setEndPoint(point);
		assertEquals(drawGarden1.getEndPoint(), point);
	}
	
	@Test
	public void testGetEndPoint() {
		Point2D.Double point = new Point2D.Double(11,11);
		drawGarden1.endPoint = point;
		assertEquals(drawGarden1.getEndPoint(), point);
	}
	
	@Test
	public void testUndo() {
		assertEquals(null, drawGarden1.undo());
		drawGarden1.addPreOutline(new Point2D.Double(0,0));
		drawGarden1.addPlot(false, Soil.CLAY);
		drawGarden1.undo();
		Stack emptyStack = new Stack();
		assertEquals(emptyStack, drawGarden1.undoStack);
	}
	
	@Test
	public void testAddPlot() {
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
		ArrayList<Point2D.Double> single_plot = new ArrayList<Point2D.Double>();
		single_plot.add(new Point2D.Double(0, 0));
		Stack stack = new Stack();
		stack.add(single_plot);
		plots.put(Soil.LOAMY, stack);
		drawGarden1.setPlots(plots);
		assertEquals(true, drawGarden1.plots.containsKey(Soil.LOAMY));
	}
	
	@Test
	public void testGetPlots() {
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
		ArrayList<Point2D.Double> single_plot = new ArrayList<Point2D.Double>();
		single_plot.add(new Point2D.Double(0, 0));
		Stack stack = new Stack();
		stack.add(single_plot);
		plots.put(Soil.CLAY, stack);
		drawGarden1.setPlots(plots);
		assertEquals(true, drawGarden1.plots.containsKey(Soil.CLAY));
	}
	
	@Test
	public void testSetMoisture() {
		drawGarden1.setMoisture(Moisture.DRY);
		assertEquals(Moisture.DRY, drawGarden1.moisture);
	}
	
	@Test
	public void testSetSun() {
		drawGarden1.setSun(Sun.FULLSUN);
		assertEquals(Sun.FULLSUN, drawGarden1.sun);
	}
	
	@Test
	public void testSetBudget() {
		drawGarden1.setBudget(100);
		assertEquals(100, drawGarden1.budget);
	}
	
	@Test
	public void testGetMoisture() {
		drawGarden1.moisture = Moisture.FLOODED;
		assertEquals(Moisture.FLOODED, drawGarden1.getMoisture());
	}
	
	@Test
	public void testGetSun() {
		drawGarden1.sun = Sun.SHADE;
		assertEquals(Sun.SHADE, drawGarden1.getSun());
	}
	
	@Test
	public void testGetBudget() {
		drawGarden1.budget = 25;
		assertEquals(25, drawGarden1.getBudget());
	}
	
	@Test
	public void testScale() {
		Point2D.Double point = new Point2D.Double(5, 5);
		Point2D.Double point2 = new Point2D.Double(5, 5);
		Point2D.Double point3 = new Point2D.Double(5, 5);
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points3 = new ArrayList<Point2D.Double>(); 
		points.add(point);
		points2.add(point2);
		points3.add(point3);
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack2 = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack3 = new Stack<ArrayList<Point2D.Double>>();
		stack.add(points);
		stack2.add(points2);
		stack3.add(points3);
		drawGarden1.plots.put(Soil.CLAY, stack);
		drawGarden1.plots.put(Soil.SANDY, stack2);
		drawGarden1.plots.put(Soil.LOAMY, stack3);
		assertEquals(false, drawGarden1.scale(5.0));
	}
	/*
	@Test
	public void testFinish() {
		Point2D.Double point = new Point2D.Double(1, 1);
		Point2D.Double point2 = new Point2D.Double(1, 1);
		Point2D.Double point3 = new Point2D.Double(20, 20);
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points3 = new ArrayList<Point2D.Double>(); 
		points.add(point);
		points2.add(point2);
		points3.add(point3);
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack2 = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack3 = new Stack<ArrayList<Point2D.Double>>();
		stack.add(points);
		stack2.add(points2);
		stack3.add(points3);
		drawGarden1.plots.put(Soil.CLAY, stack);
		drawGarden1.plots.put(Soil.SANDY, stack2);
		drawGarden1.plots.put(Soil.LOAMY, stack3);
		Double old_grid = drawGarden1.gridSize;
		drawGarden1.finish();
		assertFalse(drawGarden1.gridSize == old_grid);
	}
	*/
}
