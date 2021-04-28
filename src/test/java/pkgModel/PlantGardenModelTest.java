package pkgModel;

import static org.junit.Assert.*;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class PlantGardenModelTest {
	ObjectCarouselModel ocm = new ObjectCarouselModel(new ArrayList<PlantModel>(), 0);
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
	PlantGardenModel plantGarden = new PlantGardenModel(ocm, new ArrayList<PlantInfoModel>(), plots);
	
	public void testGardenModel() {
		assertEquals(false, plantGarden.compost == null);
	}
	
	
	@Test
	public void testAddPlant() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "", 15, 20, 150, 200);
		plantGarden.addPlant(plant);
		assertEquals(true, plantGarden.plants.contains(plant));
	}
	
	@Test
	public void testCheckSpread() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, 150, 200);
		plantGarden.addPlant(plant);
		PlantObjectModel plant2 = new PlantObjectModel("","",15,"full sun", "wet", "clay", 15, 20, 150, 200);
		plantGarden.addPlant(plant2);
		assertFalse(plantGarden.checkSpread(0));
		PlantObjectModel plant3 = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
		plantGarden.addPlant(plant3);
		assertTrue(plantGarden.checkSpread(2));
	}
	
	@Test
	public void testRemovePlant() {
		plantGarden.removePlant(0, 0);
		assertEquals(true, plantGarden.plants.isEmpty());
	}
	
	@Test
	public void testCompost() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
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
		List<PlantObjectModel> plants = new ArrayList<>();
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
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
		List<PlantObjectModel> compost = new ArrayList<>();
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
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
	
	@Test
	public void testSetPlantLocation() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
		plantGarden.addPlant(plant);
		int initialX = (int)plantGarden.plants.get(0).x;
		plantGarden.setPlantLocation(0, 20, 25);
		int  finalX = (int)plantGarden.plants.get(0).x;
		assertEquals(initialX, finalX);
	}
	
	@Test
	public void testDragPlant() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
		plantGarden.addPlant(plant);
		int initialX = (int)plantGarden.plants.get(0).x;
		plantGarden.dragPlant(0, 10, 10, 500, 500);
		int finalX = (int)plantGarden.plants.get(0).x;
		assertEquals(initialX, finalX);
	}
	
	@Test
	public void testGetCarousel() {
		assertEquals(new ObjectCarouselModel(new ArrayList<PlantModel>(), 0), plantGarden.getCarousel());
	}
	
	@Test
	public void testSetCarousel() {
		ObjectCarouselModel carousel = plantGarden.carousel;
		ObjectCarouselModel carousel2 = new ObjectCarouselModel(new ArrayList<PlantModel>(), 0);
		plantGarden.setCarousel(carousel2);
		assertEquals(carousel, plantGarden.carousel);
	}
	
//	@Test
//	public void testGetGardenOutline() {
//		assertEquals(new Polygon(), plantGarden.getGardenOutline());
//	}
//	
//	@Test
//	public void testSetGardenOutline() {
//		java.awt.Polygon polygon = plantGarden.gardenOutline;
//		java.awt.Polygon polygon2 = new Polygon();
//		plantGarden.setGardenOutline(polygon2);
//		assertEquals(polygon, plantGarden.gardenOutline);
//	}
	
	@Test
	public void testAddPlantFromCarousel() {
		int initialSize = plantGarden.getPlants().size();
		PlantModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
		List<PlantModel> plants = new ArrayList<PlantModel>();
		plants.add(plant);
		plantGarden.setCarousel(new ObjectCarouselModel(plants, 0));
		plantGarden.addPlantFromCarousel(0, 15, 15);
		int finalSize = plantGarden.getPlants().size();
		assertEquals(initialSize, finalSize);
	}
	
//	@Test
//	public void testCheckInsideGarden() {
//		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, 150, 200);
//		plantGarden.addPlant(plant);
//		java.awt.Polygon polygon2 = new Polygon();
//		plantGarden.setGardenOutline(polygon2);
//		assertTrue(plantGarden.checkInsideGarden(0));
//	}

}
