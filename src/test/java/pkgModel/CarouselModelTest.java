package pkgModel;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarouselModelTest {
	
	List<PlantModel> plants = new ArrayList<PlantModel>();
	CarouselModel carousel2 = new CarouselModel(plants, 0);

	
	@Test
	public void testCarouselModel() {
		assertEquals(0, carousel2.heldPlant);

	}
	
	@Test
	public void testRotateLeft() {
		PlantModel plant1 = new PlantInfoModel("","",0,"full sun","moist", "sandy",0,0,"");
		PlantModel plant2 = new PlantInfoModel("","",0,"full sun","moist", "sandy",0,0,"");
		carousel2.filteredPlants.add(plant1);
		carousel2.filteredPlants.add(plant2);
		carousel2.rotateLeft();
		assertEquals(1,carousel2.heldPlant);
	}
	
	@Test
	public void testRotateRight() {
		PlantModel plant1 = new PlantInfoModel("","",0,"full sun","moist", "sandy",0,0,"");
		PlantModel plant2 = new PlantInfoModel("","",0,"full sun","moist", "sandy",0,0,"");
		carousel2.filteredPlants.add(plant1);
		carousel2.filteredPlants.add(plant2);
		carousel2.heldPlant = 1;
		carousel2.rotateRight();
		assertEquals(0,carousel2.heldPlant);
	}
	
	@Test
	public void testMapNameToPlants() {
		PlantModel plant = new PlantInfoModel("","",10,"full sun", "dry", "sandy", 0,0,"");
		carousel2.plants.add(plant);
		assertEquals(10, carousel2.mapNameToPlants().get("").getSpreadDiameter());
	}
	
	@Test
	public void testGetPlantByIndex() {
		PlantModel plant = new PlantInfoModel("","",10,"full sun", "dry", "sandy", 0,0,"");
		carousel2.filteredPlants.add(plant);
		assertEquals(10, carousel2.getPlantByIndex(0).getSpreadDiameter());
	}
	
	@Test
	public void testGetFilteredPlants() {
		PlantModel plant = new PlantInfoModel("","",0,"full sun", "dry", "sandy", 0,0,"");
		carousel2.filteredPlants.add(plant);
		assertEquals(1, carousel2.getFilteredPlants().size());
	}
	
	@Test
	public void testSetFilteredPlants() {
		List<PlantModel> filteredPlants = new ArrayList<PlantModel>();
		carousel2.heldPlant = 5;
		carousel2.setFilteredPlants(filteredPlants);
		assertEquals(0, carousel2.heldPlant);
		PlantModel plant = new PlantInfoModel("","",0,"full sun", "dry", "sandy", 0,0,"");
		filteredPlants.add(plant);
		carousel2.heldPlant = 5;
		carousel2.setFilteredPlants(filteredPlants);
		assertEquals(filteredPlants, carousel2.filteredPlants);
	}
	
	@Test
	public void testSelectPlant() {
		PlantModel plant = new PlantInfoModel("","",0,"full sun", "dry", "sandy", 0,0,"");
		carousel2.selectPlant(plant);
		assertEquals(0, carousel2.selectedPlants.get("").getSpreadDiameter());
	}
	
	@Test
	public void testDecrementHeldPlant() {
		carousel2.heldPlant = 0;
		carousel2.decrementHeldPlant();
		assertEquals(0, carousel2.heldPlant);
	}
	
	@Test
	public void testDeSelectPlant() {
		PlantModel plant = new PlantInfoModel("","",0,"full sun", "dry", "sandy", 0,0,"");
		carousel2.selectedPlants.put(plant.getName(), plant);
		carousel2.deSelectPlant(plant);
		assertEquals(0, carousel2.selectedPlants.size());
	}
	
	@Test
	public void testGetSelectedPlants() {
		PlantModel plant = new PlantInfoModel("","",0,"full sun", "dry", "sandy", 0,0,"");
		carousel2.selectedPlants.put("",plant);
		assertEquals(1, carousel2.getSelectedPlants().size());
	}
	
	@Test
	public void testSetSelectedPlants() {
		HashMap<String, PlantModel> map = new HashMap<String, PlantModel>();
		PlantInfoModel plant = new PlantInfoModel("Name", "Plantius-leafius",1, "full sun", "dry",  "clay", 15, 6, "");
		map.put("Name", plant);
		carousel2.setSelectedPlants(map);
		assertEquals(1, carousel2.selectedPlants.get("Name").getSpreadDiameter());
	}

	@Test
	public void testGetPlants() {
		PlantInfoModel plant = new PlantInfoModel("Name", "Plantius-leafius",1, "full sun", "dry",  "clay", 15, 6, "");
		carousel2.plants.add(plant);
		int test;
		if(carousel2.getPlants().contains(plant)) {
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
		PlantInfoModel plant = new PlantInfoModel("Name", "Plantius-leafius",1, "full sun", "dry",  "clay", 15, 6, "");
		plants.add(plant);
		carousel2.setPlants(plants);
		int test;
		if(carousel2.plants.contains(plant)){
			test = 1;
		}
		else {
			test = 0;
		}
		assertEquals(1,test);
		
	}


	@Test
	public void testGetHeldPlant() {
		assertEquals(0,carousel2.getHeldPlant());
		
	}

	@Test
	public void testSetHeldPlant() {
		carousel2.setHeldPlant(2);
		assertEquals(2,carousel2.heldPlant);
	}

}
