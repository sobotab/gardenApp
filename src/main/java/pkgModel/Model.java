package pkgModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class Model {
	Set<PlantModel> plants;
	List<PlantModel> potentialPlants;
	Set<PlantModel> selectedPlants;
	int x;
	int y;
	
	public Model() {
		//potentialPlants = makePlants();
		
	}
	
	public List<PlantModel> makePlants() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("/files/plantData.csv"));
		List<PlantModel> plants = new ArrayList<PlantModel>();
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine().strip());
		}
		for(String line: lines) {
			String[] latestLine = line.split(",", 9);
			List<String> attributes = Arrays.asList(latestLine);
			String name = attributes.get(0);
			String sciName = attributes.get(1);
			int spread = Integer.parseInt(attributes.get(2));
			String sun = attributes.get(3);
			String moisture = attributes.get(4);
			String soil = attributes.get(5);
			int numLeps = Integer.parseInt(attributes.get(6));
			int price = Integer.parseInt(attributes.get(7));
			String description = attributes.get(8);
			PlantModel plant = new PlantInfoModel(name, sciName, spread, sun, moisture, soil, numLeps, price, description);
			plants.add(plant);
		}
		sc = new Scanner(getClass().getResourceAsStream("/files/common_leps.csv"));
		lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine().strip());
		}
		for(String line: lines) {
			String[] latestLine = line.split(",");
			String plant_genus = latestLine[0];
			List<String> leps = Arrays.asList(latestLine).subList(1, latestLine.length);
			System.out.println(leps);
			for(PlantModel plant: plants) {
				if(plant.getSciName().startsWith(plant_genus)) {
					plant.setLeps(leps);
					System.out.println(plant.getSciName());
				}
			}
			
		}
		return plants;
		
	}
	
//	public void update() {
//		
//	}

	public Set<PlantModel> getPlants() {
		return plants;
	}
	
//	public HashMap<String, PlantModel> mapNameToPlants(){
//		HashMap<String, PlantModel> map = new HashMap<>();
//		for(PlantModel plant: potentialPlants) {
//			map.put(plant.sciName, plant);
//		}
//		return map;
//	}

	public void setPlants(Set<PlantModel> plants) {
		this.plants = plants;
	}

	public List<PlantModel> getPotentialPlants() {
		return potentialPlants;
	}

	public void setPotentialPlants(List<PlantModel> potentialPlants) {
		this.potentialPlants = potentialPlants;
	}

	public Set<PlantModel> getSelectedPlants() {
		return selectedPlants;
	}

	public void setSelectedPlants(Set<PlantModel> selectedPlants) {
		this.selectedPlants = selectedPlants;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
