package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlantObjectModelTest {
	
	PlantObjectModel plant = new PlantObjectModel(1,2,3,4);

	@Test
	public void testPlantObjectModel() {
		assertEquals(1,plant.x);
	}

	@Test
	public void testGetX() {
		assertEquals(1,plant.getX());
	}

	@Test
	public void testSetX() {
		plant.setX(11);
		assertEquals(11,plant.x);
	}

	@Test
	public void testGetY() {
		assertEquals(2,plant.getY());
	}

	@Test
	public void testSetY() {
		plant.setY(12);
		assertEquals(12,plant.y);
	}

	@Test
	public void testGetHeight() {
		assertEquals(3,plant.getHeight());
	}

	@Test
	public void testSetHeight() {
		plant.setHeight(13);
		assertEquals(13,plant.height);
	}

	@Test
	public void testGetWidth() {
		assertEquals(13,plant.getWidth());
	}

	@Test
	public void testSetWidth() {
		plant.setWidth(14);
		assertEquals(14,plant.width);
	}

}
