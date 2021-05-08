package pkgView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;


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
				+ "\n http://www.nativeplantcenter.net/plants/page/2/?s=quercus");
		paragraph_headline.setTextFill(Color.BLACK);
		paragraph_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 26));
		paragraph.setTextFill(Color.BLACK);
		paragraph.setFont(Font.font("Roboto", 26));
		links_headline.setTextFill(Color.BLACK);
		links_headline.setFont(Font.font("Roboto", 26));
		links.setTextFill(Color.BLACK);
		links.setFont(Font.font("Roboto", 26));
		
		text_box.getChildren().addAll(paragraph_headline, paragraph, links_headline, links);
		ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")));
		img.setFitHeight(200);
		img.setPreserveRatio(true);
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		//this.setRight(fade_transition());
		
		//this.setCenter(resource);
		this.setLeft(text_box);
		this.setBottom(back);
		this.setTop(title);
		//this.setCenter(paragraph);
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		/*
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		*/
		/*
		ArrayList<String> image_paths = new ArrayList();
		image_paths.add("background-lep.jpg");
		image_paths.add("background-welcome.jpg");
		while (true) {
			for (String image_path : image_paths)
		}
		*/
	}
	
	public ImageView fade_transition() {
		URL path = getClass().getResource("/images/");
		File img_directory = new File(path.getFile());
		System.out.println(path);
		File[] image_list = img_directory.listFiles();
		ImageView img = null;
		for (File image : image_list) {
			img = new ImageView(new Image(getClass().getResourceAsStream("/images/" + image.getName())));
		}
		//ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")));
		img.setFitHeight(300);
		img.setPreserveRatio(true);
		
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(5000));
		fade.setFromValue(1.0);
		fade.setToValue(0.0);
		fade.setCycleCount(1000);
		fade.setNode(img);
		fade.play();
		
		return img;
	}
	
}
