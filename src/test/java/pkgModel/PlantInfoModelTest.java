package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantInfoModelTest {
	
	PlantInfoModel plantInfo = new PlantInfoModel("name", "sciName", 100, "full sun", "dry", "clay", 15, 20, "description");

	@Test
	public void testPlantInfoModel() {
		assertEquals("description",plantInfo.description);
	}

	@Test
	public void testGetNumLeps() {
		assertEquals(15,plantInfo.getNumLeps());
	}

	@Test
	public void testSetNumLeps() {
		plantInfo.setNumLeps(3);
		assertEquals(3,plantInfo.numLeps);
	}

	@Test
	public void testGetDollars() {
		assertEquals(20,plantInfo.getDollars());
	}

	@Test
	public void testSetDollars() {
		plantInfo.setDollars(16);
		assertEquals(16,plantInfo.getDollars());
	}

	@Test
	public void testGetDescription() {
		assertEquals("description",plantInfo.getDescription());
	}

	@Test
	public void testSetDescription() {
		plantInfo.setDescription("Plant");
		assertEquals("Plant",plantInfo.description);
	}

}
