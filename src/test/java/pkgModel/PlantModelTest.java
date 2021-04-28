package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantModelTest {
	
	PlantModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, 150, 200, 100, 200);
	
	@Test
	public void testPlantModel() {
		assertEquals("example_name", plant.name);
	}

	@Test
	public void testGetName() {
		assertEquals("example_name",plant.getName());
	}

	@Test
	public void testSetName() {
		plant.setName("Plant");
		assertEquals("Plant", plant.name);
	}

	@Test
	public void testGetSciName() {
		assertEquals("example_name", plant.getSciName());
	}

	@Test
	public void testSetSciName() {
		plant.setSciName("Scientific Plant");
		assertEquals("Scientific Plant", plant.sciName);
	}

	@Test
	public void testGetSpreadDiameter() {
		assertEquals(0,plant.getSpreadDiameter());
	}

	@Test
	public void testSetSpreadDiameter() {
		plant.setSpreadDiameter(5);
		assertEquals(5, plant.spreadDiameter);
	}

	@Test
	public void testGetSun() {
		assertEquals(Sun.FULLSUN,plant.getSun());
	}

	@Test
	public void testSetSun() {
		plant.setSun("part sun");
		assertEquals(Sun.PARTSUN,plant.sun);
	}

	@Test
	public void testGetMoisture() {
		assertEquals(Moisture.MOIST,plant.getMoisture());
	}

	@Test
	public void testSetMoisture() {
		plant.setMoisture("dry");
		assertEquals(Moisture.DRY,plant.moisture);
	}

	@Test
	public void testGetSoil() {
		assertEquals(Soil.CHALKY, plant.getSoil());
	}

	@Test
	public void testSetSoil() {
		plant.setSoil("clay");
		assertEquals(Soil.CLAY,plant.soil);
	}

}
