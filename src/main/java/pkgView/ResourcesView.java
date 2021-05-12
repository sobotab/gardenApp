package pkgView;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pkgController.ResourcesController;
import pkgController.WelcomeController;



public class ResourcesView extends BorderPane{
	WelcomeController welcomeController;
	ResourcesController resourcesController;
	Button video;
	public ResourcesView(View view) {
		//Controller for button
		resourcesController=new ResourcesController(view);
	
		
		Label title = new Label("Resources");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		
		Button back = new Button("Back");
		back.setOnAction(resourcesController.getHandlerForBack());
		Button open; 
		
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
		Label video_headline = new Label("Click button below to learn how to use the application:");
		paragraph_headline.setTextFill(Color.BLACK);
		paragraph_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 10));
		paragraph.setTextFill(Color.BLACK);
		paragraph.setFont(Font.font("Roboto", 10));
		links_headline.setTextFill(Color.BLACK);
		links_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 10));
		links.setTextFill(Color.BLACK);
		links.setFont(Font.font("Roboto", 10));
		links_headline.setTextFill(Color.BLACK);
		video_headline.setTextFill(Color.BLACK);
		video_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 10));
		
		
		
		text_box.getChildren().addAll(paragraph_headline, paragraph, links_headline, links, video_headline);
		text_box.setSpacing(10);
		ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")));
		img.setFitHeight(250);
		img.setPreserveRatio(true);
	
	      

		VBox vbox = new VBox();


		
		open = new Button("View Instructional Video");
		open.setMinWidth(100);
		
		//Set handlers to buttons
		open.setOnAction(resourcesController.getHandlerForOpen());

	
		this.setCenter(vbox);
		
    
		
		this.setBottom(back);
		this.setTop(title);
		vbox.getChildren().addAll(text_box,open);
	
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}
	
}
