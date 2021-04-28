package pkgModel;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GardenModelTest {
	
	GardenModel garden = new DrawGardenModel();

	@Test
	public void testGardenModel() {
		assertEquals(false, garden.outline == null);
		
	}

	@Test
	public void testSetScale() {
		garden.setScale(10);
		assertEquals(1, garden.getScale());
	}
	
	@Test
	public void testGetOutline() {
		assertEquals(false, garden.getOutline() == null);
	}

	@Test
	public void testSetOutline() {
		Set<Point> points = new HashSet<>();
		Point point = new Point(0,0);
		points.add(point);
		garden.setOutline(points);
		assertEquals(true, garden.outline.contains(point));
	}

	@Test
	public void testGetConditions() {
		assertEquals(false, garden.getConditions() == null);
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
	public void testGetOrientation() {
		assertEquals(0, garden.getOrientation());
	}

	@Test
	public void testSetOrientation() {
		garden.setOrientation(1);
		assertEquals(1, garden.orientation);
	}

	@Test
	public void testGetScale() {
		assertEquals(0, garden.getScale());
	}


}
