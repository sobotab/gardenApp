package pkgModel;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
		Point2D.Double point1 = new Point2D.Double(12,12);
		drawGarden1.setEndPoint(point);
		assertEquals(drawGarden1.getEndPoint(), point);
		drawGarden1.setEndPoint(point1);
		assertEquals(drawGarden1.getEndPoint(), point);
		assertNotEquals(drawGarden1.getEndPoint(), point1);
	}
	
	@Test
	public void testScalePoint() {
		drawGarden1.setCanvasLength(10d);
		drawGarden1.setCanvasWidth(10d);
		Point2D.Double point = new Point2D.Double(1,1);
		assertEquals(.1, drawGarden1.scalePoint(point).getX(), .01);
		assertEquals(.1, drawGarden1.scalePoint(point).getY(), .01);		
	}
	
	@Test
	public void testUnScalePoint() {
		drawGarden1.setCanvasLength(10d);
		drawGarden1.setCanvasWidth(10d);
		Point2D.Double point = new Point2D.Double(.1,.1);
		drawGarden1.unScalePoint(point);
		assertEquals(1, point.getX(), .01);
		assertEquals(1, point.getY(), .01);
	}
	
	@Test
	public void testUnScalePlots() {
		ArrayList<Point2D.Double> plot = new ArrayList<>();
		for (double i=0; i < .4; i+=.1) {
			plot.add(new Point2D.Double(i, i));
		}
		drawGarden1.setCanvasLength(10);
		drawGarden1.preOutline = plot;
		drawGarden1.addPlot(false, Soil.SANDY);
		ArrayList<Point2D.Double> unscaledPlot = drawGarden1.unScalePlots().get(Soil.SANDY).get(0);
		for (int i=0; i<4; i++ ) {
			assertEquals(unscaledPlot.get(i).getX(), i, .1);
			assertEquals(unscaledPlot.get(i).getY(), i, .1);
		}
	}
	
	@Test
	public void testGetEndPoint() {
		Point2D.Double point = new Point2D.Double(11,11);
		drawGarden1.endPoint = point;
		assertEquals(drawGarden1.getEndPoint(), point);
	}
	
	@Test
	public void getPlots() {
		ArrayList<Point2D.Double> plot = new ArrayList<>();
		for (double i=0; i < .4; i+=.1) {
			plot.add(new Point2D.Double(i, i));
		}
		drawGarden1.setCanvasLength(10);
		drawGarden1.preOutline = plot;
		drawGarden1.addPlot(false, Soil.SANDY);
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots;
		plots = drawGarden1.getPlots();
		for (int i=0; i<4; i++ ) {
			assertEquals(plots.get(Soil.SANDY).get(0).get(i).getX(), i, .1);
			assertEquals(plots.get(Soil.SANDY).get(0).get(i).getY(), i, .1);
		}
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
	public void testSetCanvasLength() {
		drawGarden1.setCanvasLength(10);
		assertEquals(drawGarden1.canvasLength, 10, .1);
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
	public void testSetScale() {
		drawGarden1.setScale(25);
		assertEquals(drawGarden1.scale, 25, .1);
	}
	
	@Test
	public void testSetGridSize() {
		drawGarden1.setGridSize(10);
		assertEquals(drawGarden1.gridSize, 10, .1);
	}
	
	@Test
	public void testGetGridSize() {
		drawGarden1.setGridSize(10);
		assertEquals(drawGarden1.gridSize, drawGarden1.getGridSize(), .1);
	}
	
	@Test
	public void testGetCanvasHeight() {
		drawGarden1.canvasHeight = 25;
		assertEquals(drawGarden1.getCanvasHeight(), 25, .1);
	}
	
	@Test
	public void testSetCanvasHeigth() {
		drawGarden1.setCanvasHeight(10);
		assertEquals(drawGarden1.getCanvasHeight(), 10, .1);
	}
	
	@Test
	public void testGetCanvasWidth() {
		drawGarden1.canvasWidth = 25;
		assertEquals(drawGarden1.getCanvasWidth(), 25, .1);
	}
	
	@Test
	public void testSetCanvasWidth() {
		drawGarden1.setCanvasWidth(10);
		assertEquals(drawGarden1.getCanvasWidth(), 10, .1);
	}
	
	@Test
	public void testGetRows() {
		drawGarden1.rows = 15;
		assertEquals(drawGarden1.getRows(), 15, .1);
	}
	
	@Test
	public void testSetRows() {
		drawGarden1.setRows(15);
		assertEquals(drawGarden1.getRows(), drawGarden1.rows, .1);
	}
	
	@Test
	public void testSetColumns() {
		drawGarden1.setColumns(15);
		assertEquals(15, drawGarden1.columns, .1);
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

}
