package pkgView;

import javafx.scene.control.Button;
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
	
	
	public WelcomeView() {
		welcomeController = new WelcomeController();
		hBox = new HBox();
		open = new Button();
		newGarden = new Button();
		info = new Button();
		resources = new Button();
		this.setCenter(hBox);
		hBox.setPrefSize(100,200);
		hBox.getChildren().addAll(open, newGarden, info, resources);
		
		//open.setOnAction(welcomeController.se);
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
