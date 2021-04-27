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

import pkgController.Soil;

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

}
