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
/**
 * 
 * @author Zane Greenholt
 * Main Model class for the program that loads plant data.
 */
public class Model {
	
	/**
	 * Constructor just creates a Model objects
	 */
	public Model() {
	}
	
	/**
	 * Reads in all plant data from a csv file and creates corresponding plant model objects
	 * 
	 * @return A List of plantModels that is all the plants in the program
	 */
	public List<PlantModel> makePlants() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("/files/plantData.csv"));
		List<PlantModel> plants = new ArrayList<PlantModel>();
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine().strip());
		}
		for(String line: lines) {
			String[] latestLine = line.split(",", 9);
			List<String> attributes = new ArrayList<String>();
			for(String attribute: latestLine) {
				attributes.add(attribute);
			}
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
			List<String> leps = new ArrayList<String>();
			for(String lep: latestLine) {
				leps.add(lep);
			}
			leps.remove(0);
			for(PlantModel plant: plants) {
				if(plant.getSciName().startsWith(plant_genus)) {
					plant.setLeps(leps);
				}
			}
			
		}
		return plants;
		
	}
	
}
