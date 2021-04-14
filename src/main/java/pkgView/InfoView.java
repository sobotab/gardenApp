package pkgView;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
		
		ChoiceBox sun = new ChoiceBox();
		sun.setItems(FXCollections.observableArrayList("full sun", "part sun", "full shade"));
		
		Button filter = new Button("Filter");
		filter.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				infoCarousel.filter((String)sun.getValue());
			}
		});
		HBox box = new HBox();
		Label title = new Label("Glossary");
		box.getChildren().addAll(sun,filter);
		
		
		this.setRight(box);
		this.setTop(title);
		this.setLeft(back);
		this.setBottom(infoCarousel);
		this.setMargin(getBottom(), new Insets(25));
		
	}
	
	// getters & setters
	public InfoCarouselView getInfoCarousel() {
		return this.infoCarousel;
	}
	
	public void setInfoCarousel(InfoCarouselView carousel) {
		this.infoCarousel = carousel;
	}
	
}
