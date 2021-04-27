package pkgModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
			String[] latestLine = line.split(",");
			String name = latestLine[0];
			String sciName = latestLine[1];
			int spread = Integer.parseInt(latestLine[2]);
			String sun = latestLine[3];
			String moisture = latestLine[4];
			String soil = latestLine[5];
			int numLeps = Integer.parseInt(latestLine[6]);
			int price = Integer.parseInt(latestLine[7]);
			String description = latestLine[8];
			PlantModel plant = new PlantInfoModel(name, sciName, spread, sun, moisture, soil, numLeps, price, description);
			plants.add(plant);
		}
//		List<PlantModel> plants = new ArrayList<PlantModel>();
//		PlantModel Agalinis_purpurea = new PlantInfoModel("purple false foxglove", "Agalinis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 4, 6, "Example Description");
//		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.MOIST, Soil.CLAY, 463, 20, "Example Description");
//		PlantModel Anemone_virginiana = new PlantInfoModel("thimbleweed","Anemone-virginiana",1, Sun.FULLSUN,Moisture.MOIST,Soil.CLAY, 2, 6, "Example Description");
//		PlantModel Aralia_racemosa = new PlantInfoModel("spikenard","Aralia-racemosa",1,Sun.PARTSUN,Moisture.MOIST,Soil.CLAY,6, 6, "Example Description");
//		PlantModel Acer_rubrum = new PlantInfoModel("red maple","Acer-rubrum",75,Sun.FULLSUN,Moisture.MOIST,Soil.CLAY,256,20,"Example Description");
//		plants.add(Agalinis_purpurea);
//		plants.add(Aralia_racemosa);
//		plants.add(Anemone_virginiana);
//		plants.add(Acer_rubrum);
//		plants.add(Quercus_stellata);
		for(PlantModel plant: plants) {
			System.out.println(plant.getName());
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
