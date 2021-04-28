package pkgModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class ObjectCarouselModelTest{
	ObjectCarouselModel ocm = new ObjectCarouselModel(new ArrayList<PlantModel>(), 0);
	
	@Test
	public void testReplacePlant() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, 150, 200, 100, 200);
		ocm.plants.add(plant);
		ocm.replacePlant(0);
		assertEquals(5, ocm.plants.size());
	}
	
	@Test
	public void testRotateLeft() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, 150, 200, 100, 200);
		ocm.plants.add(plant);
		ocm.focusedPlant = 0;
		ocm.rotateLeft();
		assertEquals(5, ocm.focusedPlant);
	}
	
	@Test
	public void testRotateRight() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, 150, 200, 100, 200);
		ocm.plants.add(plant);
		System.out.println(ocm.plants.size());
		ocm.focusedPlant = 0;
		ocm.rotateRight();
		assertEquals(5, ocm.focusedPlant);
	}
	
	@Test
	public void testGetPlants() {
		assertEquals(1, ocm.getPlants().size());
	}
}