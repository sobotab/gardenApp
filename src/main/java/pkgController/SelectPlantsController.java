package pkgController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.CarouselModel;
import pkgModel.PlantInfoModel;
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
	private final double CENTER_X = 400.0;
	
	public SelectPlantsController(View view, SelectPlantsView spv, SelectCarouselController scc) {
		this.view = view;
		this.spv = spv;
		this.scc = scc;
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new DrawGardenView(view));
	}
	
	public void clickNext(ActionEvent event) {
		view.setCurrentScreen(new EditGardenView(view));
		
	}
	
	public void plantSelected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		ImageView img = (ImageView)event.getSource();
		int index = 0;
		if(img.getScaleX() == CENTER_IMAGE_SCALING) {
			index = carouselModel.getHeldPlant();
		}
		else if(event.getSceneX() < CENTER_X) {
			index = carouselModel.getHeldPlant() - 1;
			if(index < 0) {
				index = carouselModel.getFilteredPlants().size() - 1;
			}
		}
		else {
			index = carouselModel.getHeldPlant() + 1;
			if(index >= carouselModel.getFilteredPlants().size()) {
				index = 0;
			}
		}
		PlantInfoModel plant = (PlantInfoModel)carouselModel.getPlantByIndex(index);
		carouselModel.getFilteredPlants().remove(index);
		carouselModel.decrementHeldPlant();
		carouselView.getFilteredImages().remove(index);
		carouselView.decrementCenter();
		scc.getScv().update();
		spv.selectPlant(img);
		carouselModel.selectPlant(plant);
		//Maybe put deselected handler in here so the same plant would be remembered
	}
	
	public EventHandler getHandlerForPlantSelected() {
		return event -> plantSelected((MouseEvent) event);
	}
	
	public void plantDeselected(MouseEvent event) {
		SelectCarouselView carouselView = scc.getScv();
		CarouselModel carouselModel = scc.getCarouselModel();
		ImageView img = (ImageView)event.getSource();
		
	}
	
	public EventHandler getHandlerForPlantDeSelected() {
		return event -> plantDeselected((MouseEvent) event);
	}
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForNext() {
		return event -> clickNext((ActionEvent) event);
	}
}
