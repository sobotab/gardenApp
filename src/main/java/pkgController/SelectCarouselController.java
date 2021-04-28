package pkgController;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.CarouselView;
import pkgView.InfoCarouselView;
import pkgView.SelectCarouselView;
import pkgView.View;

public class SelectCarouselController extends CarouselController {
	View view;
	SelectCarouselView scv;
	String moisture;
	String sun;
	List<String> soil;
		
	public SelectCarouselController(View view, CarouselView carouselView) {
		super(view, carouselView);
		scv = (SelectCarouselView)carouselView;

		ArrayList<Object> gardenData = new ArrayList<Object>();
		soil = new ArrayList<String>();
		
		try {
			FileInputStream fis = new FileInputStream("gardenData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			gardenData = (ArrayList<Object>)ois.readObject();
			ois.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("Error initializing stream");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = (HashMap<Soil, Stack<ArrayList<Point2D.Double>>>)gardenData.get(0);
		if(plots.get(Soil.CLAY).size() != 0) {
			soil.add("clay");
		}
		if(plots.get(Soil.LOAMY).size() != 0) {
			soil.add("loamy");
		}
		if(plots.get(Soil.SANDY).size() != 0) {
			soil.add("sandy");
		}
		Moisture moisture = (Moisture)gardenData.get(2);
		this.moisture = moisture.getLevel();
		Sun sun = (Sun)gardenData.get(3);
		this.sun = sun.getLevel();
	}

	
	public void filterCarousel(String sun, String moisture, List<String> soil) {
		List<PlantModel> plants = carouselModel.getPlants();
		List<VBox> images = scv.getImages();
		List<PlantModel> filteredPlants = new ArrayList<>();
		List<VBox> filteredImages = new ArrayList<VBox>();
		Iterator<PlantModel> plantIter = plants.iterator();
		Iterator<VBox> imageIter = images.iterator();
		while(plantIter.hasNext() && imageIter.hasNext()) {
			PlantInfoModel plant = (PlantInfoModel)plantIter.next();
			VBox imageBox = imageIter.next();
			String sunLevel = plant.getSun();
			String moistureLevel = plant.getMoisture();
			String soilType = plant.getSoil();
			boolean correctSoil = false;
			for(String type: soil) {
				if(soilType.contains(type)) {
					correctSoil = true;
				}
			}
			System.out.println(sunLevel + " " + sun + " " + moistureLevel + " " + moisture + " " + correctSoil );
			if(sunLevel.contains(sun) && moistureLevel.contains(moisture) && correctSoil) {
				filteredPlants.add(plant);
				filteredImages.add(imageBox);
			}
		}
		
		scv.setFilteredImages(filteredImages);
		scv.setCenter(0);
		carouselModel.setFilteredPlants(filteredPlants);
		carouselModel.setHeldPlant(0);
		scv.update();
	}

	public SelectCarouselView getScv() {
		return scv;
	}

	public void setScv(SelectCarouselView scv) {
		this.scv = scv;
	}


	public String getMoisture() {
		return moisture;
	}


	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}


	public String getSun() {
		return sun;
	}


	public void setSun(String sun) {
		this.sun = sun;
	}


	public List<String> getSoil() {
		return soil;
	}


	public void setSoil(List<String> soil) {
		this.soil = soil;
	}
	
	
}
