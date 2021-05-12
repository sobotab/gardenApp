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

public class EditGardenModelTest {
	ObjectCarouselModel ocm = new ObjectCarouselModel();
	HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
	EditGardenModel plantGarden = new EditGardenModel(ocm, new ArrayList<PlantInfoModel>(), plots, 100, 5);
	
	public void testGardenModel() {
		assertEquals(false, plantGarden == null);
	}
	
	
	@Test
	public void testCheckSpread() {
		PlantObjectModel plant = new PlantObjectModel("name","sciName",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		plantGarden.getPlants().add(plant);
		PlantObjectModel plant2 = new PlantObjectModel("","",15,"full sun", "wet", "clay", 15, 20, null, 150, 200);
		plantGarden.getPlants().add(plant2);
		assertTrue(plantGarden.checkSpread(0));
		PlantObjectModel plant3 = new PlantObjectModel("","",1,"full sun", "wet", "clay", 150, 200, null, 200, 200);
		plantGarden.getPlants().add(plant3);
		assertFalse(plantGarden.checkSpread(2));
	}

	@Test
	public void testGetPlants() {
		assertEquals(false, plantGarden.getPlants() == null);
	}

	@Test
	public void testSetPlants() {
		List<PlantObjectModel> plants = new ArrayList<>();
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200);
		plants.add(plant);
		plantGarden.setPlants(plants);
		assertEquals(true, plantGarden.plants.contains(plant));
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
	public void testSetPlantLocation() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200);
		plantGarden.getPlants().add(plant);
		int initialX = (int)plantGarden.plants.get(0).x;
		plantGarden.setPlantLocation(0, 20, 25);
		int  finalX = (int)plantGarden.plants.get(0).x;
		assertFalse(initialX == finalX);
	}
	
	@Test
	public void testDragPlant() {
		PlantObjectModel plant = new PlantObjectModel("","",15,"full sun", "wet", "clay", 150, 200, null, 150, 200);
		plantGarden.getPlants().add(plant);
		int initialX = (int)plantGarden.plants.get(0).x;
		plantGarden.dragPlant(0, 10, 10, 500, 500);
		int finalX = (int)plantGarden.plants.get(0).x;
		assertFalse(initialX == finalX);
	}
	
	@Test
	public void testGetCarousel() {
		ObjectCarouselModel carouselTest = new ObjectCarouselModel();
		plantGarden.carousel = carouselTest;
		assertEquals(carouselTest, plantGarden.getCarousel());
	}
	
	@Test
	public void testSetCarousel() {
		ObjectCarouselModel carousel = plantGarden.carousel;
		ObjectCarouselModel carousel2 = new ObjectCarouselModel();
		plantGarden.setCarousel(carousel2);
		assertEquals(carousel2, plantGarden.carousel);
	}

	
	@Test
	public void testAddPlantFromCarousel() {
		int initialSize = plantGarden.getPlants().size();
		PlantInfoModel plant = new PlantInfoModel("","",15,"full sun", "wet", "clay", 150, 200, null);
		List<PlantInfoModel> plants = new ArrayList<PlantInfoModel>();
		plants.add(plant);
		plantGarden.setCarousel(new ObjectCarouselModel(plants));
		plantGarden.addPlantFromCarousel(0, 15, 15);
		int finalSize = plantGarden.getPlants().size();
		assertFalse(initialSize == finalSize);
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
		Point2D.Double point = new Point2D.Double(1, 1);
		Point2D.Double point2 = new Point2D.Double(10, 1);
		Point2D.Double point3 = new Point2D.Double(5, 10);
		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>(); 
		//ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>(); 
		//ArrayList<Point2D.Double> points3 = new ArrayList<Point2D.Double>(); 
		points.add(point);
		points.add(point2);
		points.add(point3);
		Stack<ArrayList<Point2D.Double>> stack = new Stack<ArrayList<Point2D.Double>>();
		//Stack<ArrayList<Point2D.Double>> stack2 = new Stack<ArrayList<Point2D.Double>>();
		//Stack<ArrayList<Point2D.Double>> stack3 = new Stack<ArrayList<Point2D.Double>>();
		stack.add(points);
		//stack2.add(points2);
		//stack3.add(points3);
		plantGarden.plots.put(Soil.CLAY, stack);
		//plantGarden.plots.put(Soil.SANDY, stack2);
		//plantGarden.plots.put(Soil.LOAMY, stack3);
		
		PlantObjectModel plant = new PlantObjectModel("","",10,"","","clay",3,3,null, 5,5);
		PlantObjectModel plant2 = new PlantObjectModel("","",10,"","","clay",0,0,null, 20,5);
		//PlantObjectModel plant3 = new PlantObjectModel("","",10,"","","loamy",0,0,null, 0,0);
		plantGarden.plants.add(plant);
		plantGarden.plants.add(plant2);
		//plantGarden.plants.add(plant3);
		assertEquals(true, plantGarden.checkCanvas(0, 0, 0));
		assertEquals(false, plantGarden.checkCanvas(1, 0, 0));
		
	}
	
	@Test
	public void testGetBudget() {
		assertEquals(100, plantGarden.getBudget());
	}
	
	@Test
	public void testSetBudget() {
		plantGarden.setBudget(50);
		assertEquals(50, plantGarden.budget);
	}
	
	@Test
	public void testPlots() {
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = new HashMap<Soil, Stack<ArrayList<Point2D.Double>>>();
		ArrayList<Point2D.Double> single_plot = new ArrayList<Point2D.Double>();
		single_plot.add(new Point2D.Double(0, 0));
		Stack stack = new Stack();
		stack.add(single_plot);
		plots.put(Soil.CLAY, stack);
		plantGarden.setPlots(plots);
		assertEquals(true, plantGarden.plots.containsKey(Soil.CLAY));
	}
}
