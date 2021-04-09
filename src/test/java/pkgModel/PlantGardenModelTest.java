package pkgModel;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PlantGardenModelTest {
	
	PlantGardenModel plantGarden = new PlantGardenModel();
	
	public void testPlantGarden() {
		assertEquals(false, plantGarden.compost == null);
	}
	
	@Test
	public void testCheckSpread() {
		assertEquals(true, plantGarden.checkSpread());
	}
	
	@Test
	public void testAddPlant() {
		PlantModel plant = new PlantObjectModel();
		plantGarden.addPlant(plant);
		assertEquals(true, plantGarden.plants.contains(plant));
	}
	
	@Test
	public void testRemovePlant() {
		plantGarden.removePlant(0, 0);
		assertEquals(true, plantGarden.plants.isEmpty());
	}
	
	@Test
	public void testCompost() {
		PlantModel plant = new PlantObjectModel();
		plantGarden.compost(plant);
		assertEquals(true,plantGarden.compost.contains(plant));
	}
	
	@Test
	public void testCheckConditions() {
		assertEquals(true, plantGarden.checkConditions());
	}

	@Test
	public void testGetPlants() {
		assertEquals(false, plantGarden.getPlants() == null);
	}

	@Test
	public void testSetPlants() {
		Set<PlantModel> plants = new HashSet<>();
		PlantObjectModel plant = new PlantObjectModel();
		plants.add(plant);
		plantGarden.setPlants(plants);
		assertEquals(true, plantGarden.plants.contains(plant));
	}

	@Test
	public void testGetCompost() {
		assertEquals(false, plantGarden.getCompost() == null);
	}

	@Test
	public void testSetCompost() {
		Set<PlantModel> compost = new HashSet<>();
		PlantObjectModel plant = new PlantObjectModel();
		compost.add(plant);
		plantGarden.setCompost(compost);
		assertEquals(true, plantGarden.compost.contains(plant));
	}

	@Test
	public void testGetNumLeps() {
		assertEquals(0, plantGarden.getNumLeps());
	}

	@Test
	public void testSetNumLeps() {
		plantGarden.setNumLeps(2);
		assertEquals(2, plantGarden.numLeps);
	}

	@Test
	public void testGetDollars() {
		assertEquals(0, plantGarden.getDollars());
	}

	@Test
	public void testSetDollars() {
		plantGarden.setDollars(16);
		assertEquals(16, plantGarden.dollars);
	}

	@Test
	public void testGetHeldPlant() {
		assertEquals(0, plantGarden.getHeldPlant());
	}

	@Test
	public void testSetHeldPlant() {
		plantGarden.setHeldPlant(3);
		assertEquals(3, plantGarden.heldPlant);
	}

}
