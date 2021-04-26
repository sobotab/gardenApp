package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarouselModelTest {
	
	CarouselModel carousel1 = new CarouselModel(1,10,5,150,200,1,3);
	Set<PlantModel> plants = new HashSet<PlantModel>();
	CarouselModel carousel2 = new CarouselModel(plants, 0);

	
	@Test
	public void testCarouselModel() {
		CarouselModel carousel2 = new CarouselModel(new HashSet<PlantModel>(), 0);
		CarouselModel carousel3 = new CarouselModel(0,0);
		assertEquals(carousel2.firstPlant,carousel1.firstPlant);
		assertEquals(carousel3.lastPlant,carousel1.lastPlant);
	}
	
	@Test
	public void testRotateLeft() {
		PlantModel plant = new PlantInfoModel("","",0,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 0,0,"");
		carousel2.filteredPlants.add(plant);
		carousel2.rotateLeft();
		assertEquals(6,carousel2.heldPlant);
	}
	
	@Test
	public void testRotateRight() {
		PlantModel plant = new PlantInfoModel("","",0,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 0,0,"");
		carousel2.filteredPlants.add(plant);
		carousel2.rotateRight();
		assertEquals(4,carousel2.heldPlant);
	}
	
//	@Test
//	public void testPlantSelected() {
//		carousel1.plants.add(new PlantInfoModel("Name", "Plantius-leafius",1, Sun.FULLSUN, Moisture.DRY,  Soil.CLAY, 15, 6, ""));
//		assertEquals(11,carousel1.plantSelected(0, 0));
//	}
	@Test
	public void testMapNameToPlants() {
		PlantModel plant = new PlantInfoModel("","",0,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 0,0,"");
		carousel2.plants.add(plant);
		assertEquals(10, carousel2.mapNameToPlants().get("").getSpreadDiameter());
	}
	
	@Test
	public void testGetPlantByIndex() {
		PlantModel plant = new PlantInfoModel("","",0,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 0,0,"");
		carousel2.filteredPlants.add(plant);
		assertEquals(10, carousel2.getPlantByIndex(0).getSpreadDiameter());
	}
	
	@Test
	public void testGetFilteredPlants() {
		assertEquals(5, carousel2.getFilteredPlants().size());
	}
	
	@Test
	public void testSetFilteredPlants() {
		List<PlantModel> filteredPlants = new ArrayList<PlantModel>();
		carousel2.heldPlant = 5;
		carousel2.setFilteredPlants(filteredPlants);
		assertEquals(0, carousel2.heldPlant);
		PlantModel plant = new PlantInfoModel("","",0,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 5,6,"");
		filteredPlants.add(plant);
		carousel2.heldPlant = 5;
		carousel2.setFilteredPlants(filteredPlants);
		assertEquals(3, carousel2.heldPlant);
	}
	
	@Test
	public void testSelectPlant() {
		PlantModel plant = new PlantInfoModel("","",10,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 5,6,"");
		carousel2.selectPlant(plant);
		assertEquals(0, carousel2.selectedPlants.get("").getSpreadDiameter());
	}
	
	@Test
	public void testDerementHeldPlant() {
		carousel2.heldPlant = 0;
		carousel2.decrementHeldPlant();
		assertEquals(-1, carousel2.heldPlant);
	}
	
	@Test
	public void testDeSelectPlant() {
		PlantModel plant = new PlantInfoModel("","",10,Sun.FULLSUN, Moisture.DRY, Soil.SANDY, 5,6,"");
		carousel2.selectedPlants.put(plant.getName(), plant);
		carousel2.deSelectPlant(plant);
		assertEquals(1, carousel2.selectedPlants.size());
	}
	
	@Test
	public void testGetSelectedPlants() {
		assertEquals(1, carousel2.getSelectedPlants().size());
	}
	
	@Test
	public void testSetSelectedPlants() {
		HashMap<String, PlantModel> map = new HashMap<String, PlantModel>();
		PlantInfoModel plant = new PlantInfoModel("Name", "Plantius-leafius",1, Sun.FULLSUN, Moisture.DRY,  Soil.CLAY, 15, 6, "");
		map.put("Name", plant);
		carousel2.setSelectedPlants(map);
		assertEquals(0, carousel2.selectedPlants.get("Name").getSpreadDiameter());
	}

	@Test
	public void testGetPlants() {
		PlantInfoModel plant = new PlantInfoModel("Name", "Plantius-leafius",1, Sun.FULLSUN, Moisture.DRY,  Soil.CLAY, 15, 6, "");
		//carousel1.plants.add(new PlantInfoModel("Name", "Plantius-leafius",1, Sun.FULLSUN, Moisture.DRY,  Soil.CLAY, 15, 6, ""));
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
		List<PlantModel> plants = new ArrayList<PlantModel>();
		PlantInfoModel plant = new PlantInfoModel("Plant","Sciname",1,Sun.PARTSUN, Moisture.MOIST, Soil.LOAMY, 12, 6, "");
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
		CarouselModel carousel2 = new CarouselModel(1,1);
		carousel2.setFirstPlant(0);
		assertEquals(carousel2.firstPlant,0);
	}

	@Test
	public void testGetLastPlant() {
		assertEquals(carousel1.getLastPlant(),1);
	}

	@Test
	public void testSetLastPlant() {
		CarouselModel carousel2 = new CarouselModel(0,0);
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
