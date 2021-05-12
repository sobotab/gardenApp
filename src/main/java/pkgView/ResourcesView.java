package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
import javafx.scene.text.FontWeight;
import pkgController.ResourcesController;
import pkgController.WelcomeController;



public class ResourcesView extends BorderPane{
	WelcomeController welcomeController;
	ResourcesController resourcesController;
	Button video;
	ImageView imageview;
	
	public ResourcesView(View view) {
		//Controller for button
		resourcesController=new ResourcesController(view);
	
		
		Label title = new Label("Resources");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		
		Button back = new Button("Back");
		back.setOnAction(resourcesController.getHandlerForBack());
		back.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		back.setStyle("-fx-background-color: linear-gradient(#fafafa , #afd9f5 );");
		Button open; 
		
		
		
		VBox text_box = new VBox();
		Label paragraph_headline = new Label("Have you ever stopped to wonder how insects are valuable to the environment?");
		Label paragraph = new Label("Although many may be led to believe that insects provide no ecological benefit, this is far from the case. \n"
				+ "Insects (also known as leps) create the biological foundation for all terrestrial ecosystems. They cycle nutrients, pollinate \n"
				+ "plants, disperse seeds and maintain soil structure/fertility. It is critical to human society that ecosystems \n"
				+ "are properly functioning, and play an important role. However, not all plants are able to support leps. \n"
				+ "Nonnative plants cannot support leps in the way that native plants can. Therefore, it is important to \n"
				+ "keep track of the native plants that are able to support multiple lep species so that the environment can \n"
				+ "sustain itself.\n");
		
		Label links_headline = new Label("To learn more, visit:");
		Label links = new Label("https://mtcubacenter.org/native-plant-finder"
				+ "\n https://bhwp.org/grow/garden-with-natives/native-plant-plant-profiles-a-to-z/"
				+ "\n http://www.nativeplantcenter.net/plants/");
		
		Hyperlink link = new Hyperlink("https://mtcubacenter.org/native-plant-finder");
		Hyperlink link1 = new Hyperlink("https://bhwp.org/grow/garden-with-natives/native-plant-plant-profiles-a-to-z");
		Hyperlink link2 = new Hyperlink("http://www.nativeplantcenter.net/plants");
		
		
		
		ImageView back_img = new ImageView(new Image("/images/back-icon.png"));
		
		Label video = new Label("To learn how to operate the application, click the video below:");
		paragraph_headline.setTextFill(Color.BLACK);
		paragraph_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		paragraph.setTextFill(Color.WHITE);
		paragraph.setFont(Font.font("Roboto", 26));
		links_headline.setTextFill(Color.BLACK);
		links_headline.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		links.setTextFill(Color.WHITE);
		links.setFont(Font.font("Roboto", 26));
		video.setTextFill(Color.BLACK);
		video.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		link.setTextFill(Color.BLACK);
		link.setFont(Font.font("Roboto", 30));
		link1.setTextFill(Color.BLACK);
		link1.setFont(Font.font("Roboto", 30));
		link2.setTextFill(Color.BLACK);
		link2.setFont(Font.font("Roboto", 30));
		text_box.getChildren().addAll(paragraph_headline, paragraph, links_headline, link,link1,link2,video);
		text_box.setSpacing(10);
		ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/background-lep.jpg")));
		img.setFitHeight(250);
		img.setPreserveRatio(true);
		
	
	      

		VBox vbox = new VBox();


		
		open = new Button("View Instructional Video");
		open.setMinWidth(100);
		open.setOnAction(resourcesController.getHandlerForBack());
		open.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		open.setStyle("-fx-background-color: linear-gradient(#fafafa , #afd9f5 );");
		
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