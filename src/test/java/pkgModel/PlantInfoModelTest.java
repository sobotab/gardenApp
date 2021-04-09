package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlantInfoModelTest {
	
	PlantInfoModel plantInfo = new PlantInfoModel(0,0,"");

	@Test
	public void testPlantInfoModel() {
		assertEquals("",plantInfo.description);
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
