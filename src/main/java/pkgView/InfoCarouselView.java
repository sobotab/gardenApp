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

	public InfoCarouselView(View view) {
		InfoCarouselController icc = new InfoCarouselController(view, this);
		//InfoController ic = new InfoController(view,this);
		this.setHgap(50);
		images = icc.getImagesFromList();
		for(Node image: images) {
			image.setOnMousePressed(icc.getHandlerForPopup());
		}
		center = 2;
//		list = new ArrayList<Button>();
		Button left = new Button("<<<");
		Button right = new Button(">>>");
		left.setOnAction(icc.getHandlerForClickedLeft());
		right.setOnAction(icc.getHandlerForClickedRight());
//		Button b1 = new Button("1");
//		b1.setOnAction(ic.getHandlerForPopup());
//		Button b2 = new Button("2");
//		b2.setOnAction(ic.getHandlerForPopup());
//		Button b3 = new Button("3");
//		b3.setOnAction(ic.getHandlerForPopup());
//		Button b4 = new Button("4");
//		b4.setOnAction(ic.getHandlerForPopup());
//		Button b5 = new Button("5");
//		b5.setOnAction(ic.getHandlerForPopup());
//		list.add(b1);
//		list.add(b2);
//		list.add(b3);
//		list.add(b4);
//		list.add(b5);
		
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
	 
	
}
