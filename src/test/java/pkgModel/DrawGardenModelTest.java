package pkgModel;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class DrawGardenModelTest {
	
	DrawGardenModel drawGarden1 = new DrawGardenModel();

	@Test
	public void testDrawGardenModel() {
		assertEquals(false, drawGarden1.preCondition==null);
	}
	
	@Test
	public void testCheckOutline() {
		assertEquals(true,drawGarden1.checkOutline(true));
	}
	
	@Test
	public void testCheckConditions() {
		assertEquals(true,drawGarden1.checkConditions(true));
	}

	@Test
	public void testGetPreOutline() {
		assertEquals(false,drawGarden1.getPreOutline()==null);
	}

	@Test
	public void testSetPreOutline() {
		Set<Point> points = new HashSet<>();
		points.add(new Point(0,0));
		drawGarden1.setPreOutline(points);
		assertEquals(false,drawGarden1.preOutline.isEmpty());
	}

	@Test
	public void testGetPreCondition() {
		assertEquals(false,drawGarden1.getPreCondition()==null);
	}

	@Test
	public void testSetPreCondition() {
		Set<Point> points = new HashSet<>();
		points.add(new Point(0,0));
		drawGarden1.setPreCondition(points);
		assertEquals(false,drawGarden1.preCondition.isEmpty());
	}

}
