package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantObjectModelTest {
	
	PlantObjectModel plant = new PlantObjectModel("name","sciName",15,Sun.FULLSUN, Moisture.WET, Soil.CLAY, 15, 20, 150, 200);

	@Test
	public void testPlantObjectModel() {
		assertEquals(1,(int)plant.x);
	}

	@Test
	public void testGetX() {
		assertEquals(1,(int)plant.getX());
	}

	@Test
	public void testSetX() {
		plant.setX(11);
		assertEquals(11,(int)plant.x);
	}

	@Test
	public void testGetY() {
		assertEquals(2,(int)plant.getY());
	}

	@Test
	public void testSetY() {
		plant.setY(12);
		assertEquals(12,(int)plant.y);
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
