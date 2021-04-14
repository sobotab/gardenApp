package pkgView;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		infoCarousel = new InfoCarouselView(view);
		InfoController ic = new InfoController(view, infoCarousel);
		
		Button back = new Button("Back");
		back.setOnAction(ic.getHandlerForBack());
		
		
		Label title = new Label("Glossary");
		
		this.setTop(title);
		this.setLeft(back);
		this.setBottom(infoCarousel);
		this.setMargin(getBottom(), new Insets(25));
		
	}
	
	
//	public void openInfoPopUp(View view) {
//		Stage popupWindow = new Stage();
//		popupWindow.initModality(Modality.NONE);
//		popupWindow.setScene(new Scene(new InfoPopupView(view),500,400));
//		popupWindow.setAlwaysOnTop(true);
//		popupWindow.show();
//	}
	
	
	// getters & setters
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
