package pkgView;

import java.util.ArrayList;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgController.InfoCarouselController;
import pkgController.InfoController;

public class InfoCarouselView extends CarouselView{
	
	InfoCarouselController icc;

	public InfoCarouselView(View view) {

		icc = new InfoCarouselController(view, this);
		this.setHgap(10);

		images = icc.getImagesFromList();
		filteredImages = new ArrayList<ImageView>();
		for(ImageView image: images) {
			filteredImages.add(image);
			image.setOnMousePressed(icc.getHandlerForPopup());
		}

		center = 2;
		Button left = new Button("<<<");
		Button right = new Button(">>>");
		left.setOnAction(icc.getHandlerForClickedLeft());
		right.setOnAction(icc.getHandlerForClickedRight());

		this.getChildren().add(left);
		this.getChildren().add(right);
		this.update();
		this.setAlignment(Pos.CENTER);
		
	}
	
	  public void openInfoPopUp(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description) {
		  Stage popupWindow = new Stage();
		  popupWindow.initModality(Modality.NONE);
		  popupWindow.setScene(new Scene(new InfoPopupView(view, img, name, sciName, numLeps, dollars, description),500,400));
		  popupWindow.setAlwaysOnTop(true);
		  popupWindow.show(); 
		  }
	  
	  public void filter(String sun, String moisture, String soil, String type) {
		  if(soil == null) {
			  soil = "";
		  }
		  if(moisture == null) {
			  moisture = "";
		  }
		  if(type == null) {
			  type = "";
		  }
		  if(sun == null) {
			  sun = "";
		  }
		  icc.filterCarousel(sun, moisture, soil, type);
	  }
	 
	
}
