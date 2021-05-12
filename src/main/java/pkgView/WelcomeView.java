package pkgView;

//import java.awt.Font;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pkgController.WelcomeController;
/**
 * 
 * @author Zane Greenholt
 * Class that represents the first screen in the program. This screen has buttons that allow the user to go to the info, open, new, and resources screens.
 */
public class WelcomeView extends BorderPane {
	/**
	 * The backgroundImage for the welcome screen
	 */
	Image backgroundImage;
	/**
	 * The background size for the welcome screen
	 */
	BackgroundSize bSize;
	/**
	 * The controller for the welcome screen
	 */
	WelcomeController welcomeController;
	/**
	 * Buttons used for navigating between screens
	 */
	Button open, newGarden, info, resources;
	/**
	 * An Hbox for holding the buttons
	 */
	HBox hBox;
	
	/**
	 * Constructor for WelcomeView initializes all buttons and displays all necessary nodes, images, and text on the screen.
	 * 
	 * @param view The program's View that is only initialized once
	 */
	public WelcomeView(View view) {
		
		//Controller for buttons
		
		welcomeController = new WelcomeController(view);
		backgroundImage = new Image(getClass().getResourceAsStream("/images/welcome-screen-img.jpg"));
		bSize = new BackgroundSize(800.0, 1000.0, false, false, false, true);
		setBackgroundImage(backgroundImage);
		
		//Create nodes
		
		hBox = new HBox();
		
		ImageView open_img = new ImageView(new Image("/images/open-file-icon.png"));
		open_img.setFitHeight(90);
		open_img.setPreserveRatio(true);
		open = new Button("");
		open.setFont(Font.font("Trebuchet MS", FontWeight.SEMI_BOLD, 30));
		open.setPrefSize(300.0, 150.0);
		open.setStyle("-fx-background-color: #D9C0DD;"
				+ "-fx-border-color: #000000;");
		open.setGraphic(open_img);
		open.setGraphicTextGap(10);
		Tooltip open_tip = new Tooltip("Open an existing garden");
		Tooltip.install(open, open_tip);
		
		ImageView new_img = new ImageView(new Image("/images/new-icon.png"));
		new_img.setFitHeight(90);
		new_img.setPreserveRatio(true);
		newGarden = new Button("");
		newGarden.setFont(Font.font("Trebuchet MS", FontWeight.SEMI_BOLD, 30));
		newGarden.setPrefSize(300.0, 150.0);
		newGarden.setStyle("-fx-background-color: #D9C0DD;"
				+ "-fx-border-color: #000000;");
		newGarden.setGraphic(new_img);
		newGarden.setGraphicTextGap(10);
		Tooltip new_tip = new Tooltip("Make a new garden");
		Tooltip.install(newGarden, new_tip);
		
		ImageView info_img = new ImageView(new Image("/images/info-icon.png"));
		info_img.setFitHeight(90);
		info_img.setPreserveRatio(true);
		info = new Button("");
		info.setFont(Font.font("Trebuchet MS", FontWeight.SEMI_BOLD, 30));
		info.setPrefSize(300.0, 150.0);
		info.setStyle("-fx-background-color: #D9C0DD;"
				+ "-fx-border-color: #000000;");
		info.setGraphic(info_img);
		info.setGraphicTextGap(10);
		Tooltip info_tip = new Tooltip("Checkout the plant glossary");
		Tooltip.install(info, info_tip);
		
		ImageView resources_img = new ImageView(new Image("/images/resources-icon.png"));
		resources_img.setFitHeight(90);
		resources_img.setPreserveRatio(true);
		resources = new Button("");
		resources.setFont(Font.font("Trebuchet MS", FontWeight.MEDIUM, 30));
		resources.setPrefSize(300.0, 150.0);
		resources.setStyle("-fx-background-color: #D9C0DD;"
				+ "-fx-border-color: #000000;");
		resources.setGraphic(resources_img);
		resources.setGraphicTextGap(10);
		Tooltip resources_tip = new Tooltip("Learn more about leps and native plants");
		Tooltip.install(resources, resources_tip);
		
		Label title = new Label("Insta-Garden");
		//title.setPrefWidth(50);
		//title.setPrefHeight(50);
		title.setAlignment(Pos.BASELINE_RIGHT);
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Roboto", FontWeight.BOLD, 120));
		title.setPadding(new Insets(100,0,0,0));
		title.setStyle("-fx-text-fill: #F9D3FF;");
		
		HBox titleBox = new HBox();
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		
		//Set handlers to buttons
		open.setOnAction(welcomeController.getHandlerForOpen());
		newGarden.setOnAction(welcomeController.getHandlerForNew());
		info.setOnAction(welcomeController.getHandlerForInfo());
		resources.setOnAction(welcomeController.getHandlerForResources());
		
		//Builds hbox
		this.setCenter(hBox);
		this.setTop(titleBox);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(60.0);
		hBox.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		hBox.getChildren().addAll(open, newGarden, info, resources);
	}
	/**
	 * Getter for the backgroundImage field
	 * 
	 * @return backgroundImage field - An image that is the background image for this screen
	 */
	public Image getBackgroundImage() {
		return this.backgroundImage;
	}
	/**
	 * Setter for the backgroundImage field
	 * 
	 * @param image An image that will replace the current background image for the welcome screen
	 */
	public void setBackgroundImage(Image image) {
		this.setBackground(new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}

	
}
