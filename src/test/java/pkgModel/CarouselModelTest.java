package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class CarouselModelTest {
	
	CarouselModel carousel1 = new CarouselModel(1,10,5,150,200,1,3);

	
	@Test
	public void testCarouselModel() {
		CarouselModel carousel2 = new CarouselModel();
		CarouselModel carousel3 = new CarouselModel(0,0);
		assertEquals(carousel2.firstPlant,carousel3.firstPlant);
		assertEquals(carousel2.lastPlant,carousel3.lastPlant);
	}
	
	@Test
	public void testRotateLeft() {
		carousel1.rotateLeft();
		assertEquals(6,carousel1.viewPlant);
	}
	
	@Test
	public void testRotateRight() {
		carousel1.rotateRight();
		assertEquals(5,carousel1.viewPlant);
	}
	
	@Test
	public void testPlantSelected() {
		carousel1.plants.add(new PlantObjectModel());
		assertEquals(11,carousel1.plantSelected(0, 0));
	}

	@Test
	public void testGetPlants() {
		PlantObjectModel plant = new PlantObjectModel();
		carousel1.plants.add(new PlantObjectModel());
		int test;
		if(carousel1.getPlants().contains(plant)) {
			test = 1;
		}
		else {
			test = 0;
		}
		assertEquals(1,test);
	}

	@Test
	public void testSetPlants() {
		Set<PlantModel> plants = new HashSet<>();
		PlantObjectModel plant = new PlantObjectModel();
		plants.add(plant);
		carousel1.setPlants(plants);
		int test;
		if(carousel1.plants.contains(plant)){
			test = 1;
		}
		else {
			test = 0;
		}
		assertEquals(1,test);
		
	}

	@Test
	public void testGetFirstPlant() {
		assertEquals(carousel1.getFirstPlant(),0);
	}

	@Test
	public void testSetFirstPlant() {
		CarouselModel carousel2 = new CarouselModel();
		carousel2.setFirstPlant(0);
		assertEquals(carousel2.firstPlant,0);
	}

	@Test
	public void testGetLastPlant() {
		assertEquals(carousel1.getLastPlant(),1);
	}

	@Test
	public void testSetLastPlant() {
		CarouselModel carousel2 = new CarouselModel();
		carousel2.setLastPlant(1);
		assertEquals(carousel2.lastPlant,1);

	}

	@Test
	public void testGetViewPlant() {
		assertEquals(5, carousel1.getViewPlant());

	}

	@Test
	public void testSetViewPlant() {
		carousel1.setViewPlant(4);
		assertEquals(4, carousel1.viewPlant);
	}

	@Test
	public void testGetViewHeight() {
		assertEquals(150,carousel1.getViewHeight());
	}

	@Test
	public void testSetViewHeight() {
		carousel1.setViewHeight(200);
		assertEquals(200,carousel1.viewHeight);
	}

	@Test
	public void testGetViewWidth() {
		assertEquals(200,carousel1.getViewWidth());
	}

	@Test
	public void testSetViewWidth() {
		carousel1.setViewWidth(150);
		assertEquals(150,carousel1.getViewWidth());
	}

	@Test
	public void testGetHeldPlant() {
		assertEquals(3,carousel1.getHeldPlant());
		
	}

	@Test
	public void testSetHeldPlant() {
		carousel1.setHeldPlant(2);
		assertEquals(2,carousel1.heldPlant);
	}

}
