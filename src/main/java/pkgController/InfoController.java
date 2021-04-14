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
		carouselModel = new CarouselModel(model.getPotentialPlants(), 2);
		
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
//	public void clickedPopup(MouseEvent event) {
//		ImageView img = (ImageView)event.getSource();
////		String image_string = img.getImage().getUrl();
////		String sciName = "";
////		for(int i = 8; i < image_string.length(); i++) {
////			if(image_string.charAt(i) == '.') {
////				i = image_string.length();
////			}
////			else {
////				sciName += image_string.charAt(i);
////			}
////		}
////		System.out.println(sciName);
//		PlantInfoModel plant = (PlantInfoModel)carouselModel.getCenterPlant();
//		icv.openInfoPopUp(view, img, plant.getName(), plant.getSciName(), plant.getNumLeps(), plant.getDollars(), plant.getDescription());
//	}
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
//	public EventHandler getHandlerForPopup() {
//		return event -> clickedPopup((MouseEvent) event);
//	}
//	
}
