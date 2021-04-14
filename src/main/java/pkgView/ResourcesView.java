package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pkgController.ResourcesController;

public class ResourcesView extends BorderPane{
	
	public ResourcesView(View view) {
		ResourcesController rc = new ResourcesController(view);
		
		Label title = new Label("Resources");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		
		this.setBottom(back);
		this.setTop(title);
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}
	
}
