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
	ObjectCarouselModel ocm = new ObjectCarouselModel();
	ObjectCarouselModel ocm2 = new ObjectCarouselModel();
	
	@Test
	public void testReplacePlant() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		ocm.plants.add(plant);
		ocm.replacePlant(0);
		assertEquals(2, ocm.plants.size());
	}
	
	@Test
	public void testRotateLeft() {
		PlantObjectModel plant1 = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		PlantObjectModel plant2 = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);

		ocm.plants.add(plant1);
		ocm.plants.add(plant2);
		assertEquals(0, ocm.plants.indexOf(plant1));
		ocm.rotateLeft();
		assertFalse(0 == ocm.plants.indexOf(plant1));
	}
	
	@Test
	public void testRotateRight() {
		PlantObjectModel plant1 = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		PlantObjectModel plant2 = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);

		ocm.plants.add(plant1);
		ocm.plants.add(plant2);
		assertEquals(0, ocm.plants.indexOf(plant1));
		ocm.rotateRight();
		assertFalse(0 == ocm.plants.indexOf(plant1));
	}
	
	@Test
	public void testGetPlants() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		ocm.plants.add(plant);
		assertEquals(plant, ocm.getPlants().get(0));
	}
	
	@Test
	public void testFillCarousel() {
		PlantInfoModel plant = new PlantInfoModel("","",0,"","","",0,0,"");
		List<PlantInfoModel> plantInput = new ArrayList<PlantInfoModel>();
		plantInput.add(plant);
		ocm.fillCarousel(plantInput);
		assertEquals(1, ocm.plants.size());
	}
		
	@Test
	public void testSetPlants() {
		List<PlantObjectModel> plants = new ArrayList<PlantObjectModel>();
		plants.add(new PlantObjectModel("","",0,"", null, null, 0, 0, null, 0, 0));
		ocm.setPlants(plants);
		assertEquals(1, ocm.plants.size());
	}
	
	@Test
	public void testGetPlantByIndex() {
		PlantObjectModel plant = new PlantObjectModel("","",0,"","","",0,0,null, 0,0);
		ocm.plants.add(plant);
		PlantModel plant2 = ocm.getPlantByIndex(0);
		assertEquals(plant, plant2);
	}
	
}