package pkgModel;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ModelTest {
	
	Model model = new Model();
	
	@Test
	public void testModel() {
		assertEquals(0, model.x);
	}
	
	@Test
	public void testMakePlants() {
		assertEquals(false, model.makePlants() == null);
	}
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented"); //TODO
	}

	@Test
	public void testGetPlants() {
		assertEquals(false, model.getPlants() == null);
	}

	@Test
	public void testSetPlants() {
		Set<PlantModel> plants = new HashSet<>();
		PlantModel plant = new PlantObjectModel();
		plants.add(plant);
		model.setPlants(plants);
		assertEquals(true, model.plants.contains(plant));
	}

	@Test
	public void testGetPotentialPlants() {
		assertEquals(false, model.getPotentialPlants() == null);
	}

	@Test
	public void testSetPotentialPlants() {
		Set<PlantModel> plants = new HashSet<>();
		PlantModel plant = new PlantObjectModel();
		plants.add(plant);
		model.setPotentialPlants(plants);
		assertEquals(true, model.potentialPlants.contains(plant));
	}

	@Test
	public void testGetSelectedPlants() {
		assertEquals(false, model.getSelectedPlants() == null);
	}

	@Test
	public void testSetSelectedPlants() {
		Set<PlantModel> plants = new HashSet<>();
		PlantModel plant = new PlantObjectModel();
		plants.add(plant);
		model.setSelectedPlants(plants);
		assertEquals(true, model.selectedPlants.contains(plant));
	}

	@Test
	public void testGetX() {
		assertEquals(0,model.getX());
	}

	@Test
	public void testSetX() {
		model.setX(3);
		assertEquals(3,model.x);
	}

	@Test
	public void testGetY() {
		assertEquals(0,model.getY());
	}

	@Test
	public void setY() {
		model.setY(300);
		assertEquals(300,model.y);
	}

}
