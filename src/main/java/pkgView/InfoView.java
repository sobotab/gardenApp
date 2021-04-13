package pkgView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import pkgController.InfoController;

public class InfoView extends BorderPane {
	InfoCarouselView infoCarousel;
	
	public InfoView(View view) {
		InfoController ic = new InfoController(view, this);
		
		Button back = new Button("Back");
		back.setOnAction(ic.getHandlerForBack());
		
		Button show = new Button("Popup Window");
		Button show2 = new Button("Another Popup Window");

		show.setOnAction(ic.getHandlerForPopup());
		show2.setOnAction(ic.getHandlerForPopup());
		
		HBox box = new HBox();
		box.getChildren().addAll(show, show2);
		
		Label title = new Label("Glossary");
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(box);
		
	}
	
	
	public void openInfoPopUp(View view) {
		Stage popupWindow = new Stage();
		popupWindow.initModality(Modality.NONE);
		popupWindow.setScene(new Scene(new InfoPopupView(view),500,400));
		popupWindow.setAlwaysOnTop(true);
		popupWindow.show();
	}
	
	
	// getters & setters
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
