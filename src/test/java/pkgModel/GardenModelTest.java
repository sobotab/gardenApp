package pkgModel;

import static org.junit.Assert.*;
import org.junit.Test;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import pkgController.Soil;

public class GardenModelTest {
	
	GardenModel garden = new DrawGardenModel();

	@Test
	public void testGardenModel() {
		assertFalse(garden.plots == null);
		
	}

	@Test
	public void testSetScale() {
		garden.setScale(10);
		assertEquals(10, garden.getScale(),1);
	}
	
	@Test
	public void testGetPlots() {
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> map = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
		map.put(Soil.LOAMY, stack);
		GardenModel garden2 = new EditGardenModel(new ObjectCarouselModel(), new ArrayList<PlantInfoModel>(), map,100, 3);
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = garden2.getPlots();
		assertTrue(plots.get(Soil.LOAMY) == stack);
	}

	@Test
	public void testSetPlots() {
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
		ArrayList<Point2D.Double> single_plot = new ArrayList<Point2D.Double>();
		single_plot.add(new Point2D.Double(0, 0));
		Stack stack = new Stack();
		stack.add(single_plot);
		plots.put(Soil.CLAY, stack);
		garden.setPlots(plots);
		assertEquals(true, garden.plots.containsKey(Soil.CLAY));
	}

	@Test
	public void testGetConditions() {
		Set<HashMap<String,Set<Point>>> set = new HashSet<>();
		HashMap<String,Set<Point>> map = new HashMap<>();
		set.add(map);
		garden.setConditions(set);
		assertFalse(garden.getConditions() == null);
	}

	@Test
	public void testSetConditions() {
		Set<HashMap<String,Set<Point>>> set = new HashSet<>();
		HashMap<String,Set<Point>> map = new HashMap<>();
		set.add(map);
		garden.setConditions(set);
		assertEquals(true, garden.conditions.contains(map));
	}

	@Test
	public void testGetScale() {
		assertEquals(0, garden.getScale(), 1);
	}
	


}
