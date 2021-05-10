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
	ObjectCarouselModel ocm2 = new ObjectCarouselModel();
	
	@Test
	public void testReplacePlant() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200, 0, 0);
		ocm.plants.add(plant);
		ocm.replacePlant(0);
		assertEquals(5, ocm.plants.size());
	}
	
	@Test
	public void testRotateLeft() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200, 100, 200);
		ocm.plants.add(plant);
		ocm.focusedPlant = 0;
		ocm.rotateLeft();
		assertEquals(5, ocm.focusedPlant);
	}
	
	@Test
	public void testRotateRight() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200, 100, 200);
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
	
	@Test
	public void testFillCarousel() {
		PlantInfoModel plant = new PlantInfoModel("","",0,"","","",0,0,"");
		List<PlantInfoModel> plantInput = new ArrayList<PlantInfoModel>();
		plantInput.add(plant);
		ocm.fillCarousel(plantInput, 0);
		assertEquals(null, ocm.plants);
	}
		
	@Test
	public void testSetPlants() {
		List<PlantObjectModel> plants = new ArrayList<PlantObjectModel>();
		ocm.setPlants(plants);
		assertEquals(1, ocm.plants.size());
	}
	
	@Test
	public void testGetFocusedPlant() {
		assertEquals(3, ocm.getfocusedPlant());
	}
	
	@Test
	public void testSetFocusedPlant() {
		ocm.setfocusedPlant(1);
		assertEquals(0, ocm.focusedPlant);
	}
	
	@Test
	public void testGetPlantByIndex() {
		PlantObjectModel plant = new PlantObjectModel("","",0,"","","",0,0,null, 0,0,0,0);
		ocm.plants.add(plant);
		PlantModel plant2 = ocm.getPlantByIndex(0);
		assertEquals(1, plant2.getDollars());
	}
	
}