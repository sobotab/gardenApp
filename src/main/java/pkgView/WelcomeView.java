package pkgView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pkgController.WelcomeController;

public class WelcomeView extends BorderPane {
	Image backgroundImage;
	BackgroundSize bSize;
	WelcomeController welcomeController;
	Button open, newGarden, info, resources;
	HBox hBox;
	
	
	public WelcomeView(View view) {
		//Controller for buttons
		welcomeController = new WelcomeController(view);
		
		//Create nodes
		hBox = new HBox();
		open = new Button("Open");
		newGarden = new Button("New");
		info = new Button("Info");
		resources = new Button("Resources");
		Label title = new Label("Welcome");
		
		//Set handlers to buttons
		open.setOnAction(welcomeController.getHandlerForOpen());
		newGarden.setOnAction(welcomeController.getHandlerForNew());
		info.setOnAction(welcomeController.getHandlerForInfo());
		resources.setOnAction(welcomeController.getHandlerForResources());
		
		//Builds hbox
		this.setCenter(hBox);
		this.setTop(title);
		hBox.setPrefSize(100,200);
		hBox.getChildren().addAll(open, newGarden, info, resources);
	}
	
	public Image getBackgroundImage() {
		return this.backgroundImage;
	}
	public void setBackgroundImage(Image image) {
		this.setBackground(new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}

	
}
