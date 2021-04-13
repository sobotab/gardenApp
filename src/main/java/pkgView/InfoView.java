package pkgView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import pkgController.InfoController;

public class InfoView extends BorderPane {
	InfoCarouselView infoCarousel;
	
	public InfoView(View view) {
		InfoController ic = new InfoController(view);
		
		Button back = new Button("Back");
		back.setOnAction(ic.getHandlerForBack());
		
		//Popup popup = new Popup();
		//popup.setX(300);
		//popup.setY(250);
		//popup.getContent().add(back);
		
		Button show = new Button("Popup Window");
		show.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				//popup.show(view.getTheStage());
				Stage popupWindow = new Stage();
				popupWindow.initModality(Modality.APPLICATION_MODAL);
				popupWindow.setScene(new Scene(new InfoPopupView(view),500,400));
				popupWindow.show();
			}
		});
		
		Label title = new Label("Glossary");
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(show);
		
	}
	
	public void openInfoPopUp() {
		// new infoPopUp( infoCarousel.get(x) );
		// Is this how this'll work? Or it'll go through model? Left InfoPopUp as is for now as I'm not sure.
	}
	
	
	// getters & setters
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
