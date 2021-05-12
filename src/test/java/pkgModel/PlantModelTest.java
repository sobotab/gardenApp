package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantModelTest {
	
	PlantModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
	
	@Test
	public void testPlantModel() {
		assertEquals("name", plant.name);
	}

	@Test
	public void testGetName() {
		plant.name = "name1";
		assertEquals("name1",plant.getName());
	}

	@Test
	public void testSetName() {
		plant.setName("Plant");
		assertEquals("Plant", plant.name);
	}

	@Test
	public void testGetSciName() {
		assertEquals("sciName", plant.getSciName());
	}

	@Test
	public void testSetSciName() {
		plant.setSciName("Scientific Plant");
		assertEquals("Scientific Plant", plant.sciName);
	}

	@Test
	public void testGetSpreadDiameter() {
		plant.spreadDiameter = 6;
		assertEquals(6,plant.getSpreadDiameter());
	}

	@Test
	public void testSetSpreadDiameter() {
		plant.setSpreadDiameter(5);
		assertEquals(5, plant.spreadDiameter);
	}

	@Test
	public void testGetSun() {
		plant.sun = "fullsun";
		assertEquals("fullsun",plant.getSun());
	}

	@Test
	public void testSetSun() {
		plant.setSun("partsun");
		assertEquals("partsun",plant.sun);
	}

	@Test
	public void testGetMoisture() {
		plant.moisture = "wet";
		assertEquals("wet",plant.getMoisture());
	}

	@Test
	public void testSetMoisture() {
		plant.setMoisture("dry");
		assertEquals("dry",plant.moisture);
	}

	@Test
	public void testGetSoil() {
		plant.soil = "loamy";
		assertEquals("loamy", plant.getSoil());
	}

	@Test
	public void testSetSoil() {
		plant.setSoil("clay");
		assertEquals("clay",plant.soil);
	}

}
