package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantObjectModelTest {
	
	PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);

	@Test
	public void testPlantObjectModel() {
		assertEquals(150,(int)plant.x);
	}

	@Test
	public void testGetX() {
		plant.x = 2;
		assertEquals(2,(int)plant.getX());
	}

	@Test
	public void testSetX() {
		plant.setX(11);
		assertEquals(11,(int)plant.x);
	}

	@Test
	public void testGetY() {
		plant.y = 2;
		assertEquals(2,(int)plant.getY());
	}

	@Test
	public void testSetY() {
		plant.setY(12);
		assertEquals(12,(int)plant.y);
	}

}
