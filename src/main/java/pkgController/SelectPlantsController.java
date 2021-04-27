package pkgController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pkgModel.CarouselModel;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.DrawGardenView;
import pkgView.EditGardenView;
import pkgView.SelectCarouselView;
import pkgView.SelectPlantsView;
import pkgView.View;

public class SelectPlantsController {
	View view;
	SelectCarouselController scc;
	SelectPlantsView spv;
	private final double CENTER_IMAGE_SCALING = 1.3;
	
	public SelectPlantsController(View view, SelectPlantsView spv, SelectCarouselController scc) {
		this.view = view;
		this.spv = spv;
		this.scc = scc;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view));
	}
	
	public void clickNext(ActionEvent event) {
		
		// Send plants info
		/*
		ArrayList<PlantInfoModel> plantList = new ArrayList<PlantInfoModel>();
		Iterator iter = scc.carouselModel.selectedPlants.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry plantEntry = (Map.Entry)iter.next();
			plantList.add((PlantInfoModel)plantEntry.getValue());
		}
		ArrayList<Double> testList = new ArrayList<Double>();
		*/
		
		ArrayList<PlantInfoModel> plantList = new ArrayList<PlantInfoModel>();
		for (PlantModel plant : scc.carouselModel.selectedPlants.values()) {
			plantList.add((PlantInfoModel)plant);
		}
		
		
		try {
			FileOutputStream fos = new FileOutputStream("plantData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(plantList);
        	System.out.println(plantList.get(0));
            oos.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } 
		
		view.setCurrentScreen(new EditGardenView(view));
		
	}
	
	public void plantSelected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		VBox img = (VBox)event.getSource();
		int centerIndex = carouselModel.getHeldPlant();
		VBox centerImage = carouselView.getFilteredImages().get(centerIndex);
		double centerX = centerImage.getLayoutX();
		int index = 0;
		if(img.getScaleX() == CENTER_IMAGE_SCALING) {
			index = centerIndex;
		}
		else if(event.getSceneX() < centerX) {
			index = centerIndex - 1;
			if(index < 0) {
				index = carouselModel.getFilteredPlants().size() - 1;
			}
		}
		else {
			index = centerIndex + 1;
			if(index >= carouselModel.getFilteredPlants().size()) {
				index = 0;
			}
		}
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
		carouselModel.getFilteredPlants().remove(index);
		carouselModel.decrementHeldPlant();
		carouselView.getFilteredImages().remove(index);
		carouselView.decrementCenter();
		carouselView.update();
		spv.selectPlant(img);
		carouselModel.selectPlant(plant);
	}
	
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((MouseEvent) event);
	}
	
	public void plantDeselected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		VBox img = (VBox)event.getSource();
		spv.deSelectPlant(img);
		Text text = (Text)img.getChildren().get(0);
		String name = text.getText();
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getSelectedPlants().remove(name);
		carouselModel.getFilteredPlants().add(plant);
		carouselView.getFilteredImages().add(img);
		carouselView.update();
	}
	
	public EventHandler getHandlerForPlantDeSelected() {
		return event -> plantDeselected((MouseEvent) event);
	}
	
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
}
