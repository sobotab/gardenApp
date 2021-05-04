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
		
		Label paragraph = new Label("Have you ever stopped to wonder how insects are valuable to the environment? \n"
				+ "Although many may be led to believe that insects provide no ecological benefit, this is far from the case. \n"
				+ "Insects create the biological foundation for all terrestrial ecosystems. They cycle nutrients, pollinate \n"
				+ "plants, disperse seeds and maintain soil structure/fertility. It is critical to human society that ecosystems \n"
				+ "are properly functioning, and play an important role. However, not all plants are able to support leps. \n"
				+ "Nonnative plants cannot support leps in the way that native plants can. Therefore, it is important to \n"
				+ "keep track of the native plants that are able to support multiple lep species so that the environment can \n"
				+ "sustain itself. "
				+ "");
		paragraph.setTextFill(Color.WHITE);
		paragraph.setFont(Font.font("Cambria", 30));
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		
		this.setBottom(back);
		this.setTop(title);
		this.setCenter(paragraph);
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}
	
}
