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
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pkgController.ResourcesController;

public class ResourcesView extends BorderPane{
	
	public ResourcesView(View view) {
		ResourcesController rc = new ResourcesController(view);
		
		Label title = new Label("Resources");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		Label resource = new Label("Have you ever stopped to wonder how insects are valuable to the environment? "
				+ "\nAlthough many may be led to believe that insects provide no ecological benefit, this is far from the case. Insects create the biological foundation for all terrestrial ecosystems. "
				+ "\nThey cycle nutrients, pollinate plants, disperse seeds and maintain soil structure/fertility. It is critical to human society that ecosystems are properly functioning, and play an important role. "
				+ "\nHowever, not all plants are able to support leps. Nonnative plants cannot support leps in the way that native plants can. Therefore, it is important to keep track of the native plants that are able to "
				+ "\nsupport multiple lep species so that the environment can sustain itself."
				+ " \n \n To learn more about native plants visit: "
				+ "\n https://mtcubacenter.org/native-plant-finder "
				+ "\n https://bhwp.org/grow/garden-with-natives/native-plant-plant-profiles-a-to-z/"
				+ "\n http://www.nativeplantcenter.net/plants/page/2/?s=quercus");
		resource.setTextFill(Color.WHITE);
		resource.setFont(Font.font("Cambria", 14));
		
		//this.setCenter(resource);
		this.setLeft(resource);
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
