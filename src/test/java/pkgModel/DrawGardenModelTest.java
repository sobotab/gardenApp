package pkgModel;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
		assertEquals(true, drawGarden1.preOutline==null);
	}

	@Test
	public void testAddPreOutline() {
		Point2D.Double point = new Point2D.Double(10,10);
		drawGarden1.addPreOutline(point);
		assertEquals(drawGarden1.endPoint, null);
	}
	
	@Test
	public void testSetEndPoint() {
		Point2D.Double point = new Point2D.Double(11,11);
		drawGarden1.setEndPoint(point);
		assertEquals(drawGarden1.set, true);
	}
	
	@Test
	public void testGetEndPoint() {
		Point2D.Double point = new Point2D.Double(11,11);
		assertEquals(drawGarden1.getEndPoint(), point);
	}
	
	@Test
	public void testUndo() {
		assertEquals(null, drawGarden1.undo());
		drawGarden1.undoStack.push(Soil.CLAY);
		System.out.println(drawGarden1.undoStack.size());
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		stack.push(new ArrayList<Point2D.Double>());
		drawGarden1.plots.put(Soil.CLAY, stack);
		List<Point2D.Double> list = new ArrayList<Point2D.Double>();
		assertEquals(list, drawGarden1.undo());
	}
	
	@Test
	public void testAddPlot() {
		ArrayList<Point2D.Double> preOutline = drawGarden1.preOutline;
		drawGarden1.addPlot(false, Soil.CLAY);
		assertEquals(preOutline, drawGarden1.preOutline);
		
	}
	
	@Test
	public void testGetPlots() {
		assertEquals(1, drawGarden1.getPlots().get(Soil.CLAY).size());
	}
	
	@Test
	public void testSetMoisture() {
		drawGarden1.setMoisture(Moisture.DRY);
		assertEquals(Moisture.MOIST, drawGarden1.moisture);
	}
	
	@Test
	public void testSetSun() {
		drawGarden1.setSun(Sun.FULLSUN);
		assertEquals(Sun.PARTSUN, drawGarden1.sun);
	}
	
	@Test
	public void testSetBudget() {
		drawGarden1.setBudget(100);
		assertEquals(50, drawGarden1.budget);
	}
	
	@Test
	public void testGetMoisture() {
		assertEquals(Moisture.FLOODED, drawGarden1.getMoisture());
	}
	
	@Test
	public void testGetSun() {
		assertEquals(Sun.SHADE, drawGarden1.getSun());
	}
	
	@Test
	public void testGetBudget() {
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
		//assertEquals(false, drawGarden1.scale(5.0));
	}
	
	@Test
	public void testFinish() {
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
		drawGarden1.finish();
		assertTrue(false);
	}

}
