package pkgView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pkgController.ResourcesController;

public class ResourcesView extends BorderPane{
	
	public ResourcesView(View view) {
		ResourcesController rc = new ResourcesController(view);
		
		Label title = new Label("Resources");
		title.setTextFill(Color.BLACK);
		title.setFont(Font.font("Cambria", 80));
		
		VBox text_box = new VBox();
		Label paragraph_headline = new Label("Have you ever stopped to wonder how insects are valuable to the environment?");
		Label paragraph = new Label("Although many may be led to believe that insects provide no ecological benefit, this is far from the case. \n"
				+ "Insects create the biological foundation for all terrestrial ecosystems. They cycle nutrients, pollinate \n"
				+ "plants, disperse seeds and maintain soil structure/fertility. It is critical to human society that ecosystems \n"
				+ "are properly functioning, and play an important role. However, not all plants are able to support leps. \n"
				+ "Nonnative plants cannot support leps in the way that native plants can. Therefore, it is important to \n"
				+ "keep track of the native plants that are able to support multiple lep species so that the environment can \n"
				+ "sustain itself.\n");
		Label links_headline = new Label("To learn more, visit:");
		Label links = new Label("https://mtcubacenter.org/native-plant-finder"
				+ "\n https://bhwp.org/grow/garden-with-natives/native-plant-plant-profiles-a-to-z/"
				+ "\n http://www.nativeplantcenter.net/plants/");
		paragraph_headline.setTextFill(Color.BLACK);
		paragraph_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		paragraph.setTextFill(Color.BLACK);
		paragraph.setFont(Font.font("Roboto", 26));
		links_headline.setTextFill(Color.BLACK);
		links_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		links.setTextFill(Color.BLACK);
		links.setFont(Font.font("Roboto", 26));
		
		text_box.getChildren().addAll(paragraph_headline, paragraph, links_headline, links);
		text_box.setSpacing(10);
		ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")));
		img.setFitHeight(250);
		img.setPreserveRatio(true);
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		
		this.setLeft(text_box);
		this.setBottom(back);
		this.setTop(title);
		this.setRight(img);
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		/*
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		*/

	}
}
