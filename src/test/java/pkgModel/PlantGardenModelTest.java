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
	PlantGardenModel plantGarden = new PlantGardenModel(ocm, new ArrayList<PlantInfoModel>(), plots, 100, 5);
	
	public void testGardenModel() {
		assertEquals(false, plantGarden.compost == null);
	}
	
	
	@Test
	public void testAddPlant() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "", 15, 20, null, 150, 200, 100, 200);
		plantGarden.addPlant(plant);
		assertEquals(true, plantGarden.plants.contains(plant));
	}
	
	@Test
	public void testCheckSpread() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200, 100, 200);
		plantGarden.addPlant(plant);
		PlantObjectModel plant2 = new PlantObjectModel("","",15,"full sun", "wet", "clay", 15, 20, null, 150, 200, 100, 200);
		plantGarden.addPlant(plant2);
		assertFalse(plantGarden.checkSpread(0));
		PlantObjectModel plant3 = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
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
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
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
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
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
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
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
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
		plantGarden.addPlant(plant);
		int initialX = (int)plantGarden.plants.get(0).x;
		plantGarden.setPlantLocation(0, 20, 25);
		int  finalX = (int)plantGarden.plants.get(0).x;
		assertEquals(initialX, finalX);
	}
	
	@Test
	public void testDragPlant() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
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

	
	@Test
	public void testAddPlantFromCarousel() {
		int initialSize = plantGarden.getPlants().size();
		PlantModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200, 100, 200);
		List<PlantModel> plants = new ArrayList<PlantModel>();
		plants.add(plant);
		plantGarden.setCarousel(new ObjectCarouselModel(plants, 0));
		plantGarden.addPlantFromCarousel(0, 15, 15);
		int finalSize = plantGarden.getPlants().size();
		assertEquals(initialSize, finalSize);
	}

	@Test
	public void testInPolygon() {
		Point2D.Double point = new Point2D.Double(1,1);
		Point2D.Double point2 = new Point2D.Double(100,100);
		Point2D.Double point3 = new Point2D.Double(3,50);
		Point2D.Double point4 = new Point2D.Double(3,3);
		Point2D.Double point5 = new Point2D.Double(5,5);
		Point2D.Double point6 = new Point2D.Double(25,25);
		Point2D.Double point7 = new Point2D.Double(15,15);
		Point2D.Double point8 = new Point2D.Double(1000,1000);
		
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		points.add(point);
		points.add(point7);
		points.add(point8);
		
		assertEquals(true, plantGarden.inPolygon(point6, points));
		
	}
	
	@Test
	public void testCheckCanvas() {
		Point2D.Double point = new Point2D.Double(1, 5);
		Point2D.Double point2 = new Point2D.Double(1, 2);
		Point2D.Double point3 = new Point2D.Double(5, 5);
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>(); 
		ArrayList<Point2D.Double> points3 = new ArrayList<Point2D.Double>(); 
		points.add(point);
		points.add(point2);
		points2.add(point2);
		points3.add(point3);
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack2 = new Stack<ArrayList<Point2D.Double>>();
		Stack<ArrayList<Point2D.Double>> stack3 = new Stack<ArrayList<Point2D.Double>>();
		stack.add(points);
		stack2.add(points2);
		stack3.add(points3);
		plantGarden.plots.put(Soil.CLAY, stack);
		plantGarden.plots.put(Soil.SANDY, stack2);
		plantGarden.plots.put(Soil.LOAMY, stack3);
		
		PlantObjectModel plant = new PlantObjectModel("","",0,"","","clay",3,3,null, 3,3,3,3);
		PlantObjectModel plant2 = new PlantObjectModel("","",0,"","","sandy",0,0,null, 0,0,0,0);
		PlantObjectModel plant3 = new PlantObjectModel("","",0,"","","loamy",0,0,null, 0,0,0,0);
		plantGarden.plants.add(plant);
		plantGarden.plants.add(plant2);
		plantGarden.plants.add(plant3);
		assertEquals(false, plantGarden.checkCanvas(0, 3, 3));
		assertEquals(false, plantGarden.checkCanvas(1, 6, 6));
		assertEquals(true, plantGarden.checkCanvas(2, 6, 6));
		
	}
	
	@Test
	public void testGetBudget() {
		assertEquals(100, plantGarden.getBudget());
	}
	
	@Test
	public void testSetBudget() {
		plantGarden.setBudget(50);
		assertEquals(0, plantGarden.budget);
	}
	
	@Test
	public void testPlots() {
		assertEquals(1, plantGarden.getPlots().get(Soil.CLAY).size());
	}
}
