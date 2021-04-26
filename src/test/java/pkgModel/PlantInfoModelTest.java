package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantInfoModelTest {
	
	PlantInfoModel plantInfo = new PlantInfoModel("name", "sciName", 100, Sun.FULLSUN, Moisture.DRY, Soil.CLAY, 15, 20, "");

	@Test
	public void testPlantInfoModel() {
		assertEquals("plant",plantInfo.description);
	}

	@Test
	public void testGetNumLeps() {
		assertEquals(0,plantInfo.getNumLeps());
	}

	@Test
	public void testSetNumLeps() {
		plantInfo.setNumLeps(3);
		assertEquals(3,plantInfo.numLeps);
	}

	@Test
	public void testGetDollars() {
		assertEquals(0,plantInfo.getDollars());
	}

	@Test
	public void testSetDollars() {
		plantInfo.setDollars(16);
		assertEquals(16,plantInfo.getDollars());
	}

	@Test
	public void testGetDescription() {
		assertEquals("",plantInfo.getDescription());
	}

	@Test
	public void testSetDescription() {
		plantInfo.setDescription("Plant");
		assertEquals("Plant",plantInfo.description);
	}

}
