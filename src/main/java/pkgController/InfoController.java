package pkgController;

import java.util.HashMap;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.CarouselModel;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.InfoCarouselView;
import pkgView.InfoView;
import pkgView.View;
import pkgView.WelcomeView;

public class InfoController {
	View view;
	InfoCarouselView icv;
	Model model;
	CarouselModel carouselModel;
	
	public InfoController(View view, InfoCarouselView icv) {
		this.view = view;
		this.icv = icv;
		model = new Model();
		carouselModel = new CarouselModel(view.getController().getPlants(), 2);
		
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
}
