package pkgView;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import pkgController.Controller;

/**
 * 
 * @author Zane Greenholt
 * Main View class of the program that controls the screen that is showing and holds hover based handlers
 */
public class View {
	/**
	 * Initial Scene width before going full screen
	 */
	final int SCENEWIDTH = 800;
	/**
	 * Initial Scene height before going full screen
	 */
	final int SCENEHEIGHT = 600;
	/**
	 * The programs main controller object
	 */
	public Controller controller;
	/**
	 * The current screen that is showing
	 */
	BorderPane currentScreen; // replaced screen for borderpane, effectively the same
	//All individual screens now inherit borderpane: gives us children, height/width, background, padding, margins (everything that was in screen)
	/**
	 * The stage of the main screen of the program
	 */
	Stage theStage;
	
	/**
	 * Constructor for the view sets the first screen and shows it
	 * 
	 * @param theStage The stage of the main screen of the program
	 * @param controller The program's main controller that is only initialized once
	 */
	public View(Stage theStage, Controller controller) {
		this.theStage = theStage;
		
		//this.theStage.setFullScreen(true);
		this.theStage.setMaximized(true);
		
		this.controller = controller;
		theStage.setTitle("Garden Software");
		
		//First screen is the WelcomeView
		theStage.setScene(new Scene(new WelcomeView(this), SCENEWIDTH, SCENEHEIGHT));
        theStage.show();
	}
	


	/**
	 * Sets the handlers for the VBox passed in that allows it to be highlighted on hovering
	 * 
	 * @param box A VBox that is a plant image and text about its data
	 */
	public void setHoverHandlers(VBox box) {
		BorderWidths bw = new BorderWidths(3,3,3,3);
		BorderStroke bs = new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, new CornerRadii(10), bw, Insets.EMPTY);
		Border border = new Border(bs);
		
		BackgroundFill bf = new BackgroundFill(Color.YELLOW, new CornerRadii(10), Insets.EMPTY);
		Background imageBackground = new Background(bf);
		box.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				box.setBorder(border);
				box.setBackground(imageBackground);
			}
		});
		
		box.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				box.setBorder(Border.EMPTY);
				box.setBackground(Background.EMPTY);
			}
		});
	}
	
	/**
	 * Getter for the controller field
	 * 
	 * @return The program's controller
	 */
	public Controller getController() {
		return controller;
	}
	
	/**
	 * Getter for theStage field
	 * 
	 * @return The stage of the main program screen
	 */
	public Stage getTheStage() {
		return this.theStage;
	}
	
	/**
	 * Getter for the current screen's pane
	 * 
	 * @return A BorderPane representing the current screen showing in the program
	 */
	public BorderPane getCurrentScreen() {
		return this.currentScreen;
	}
	
	/**
	 * Sets the current showing screen to a scene with the pane that is passed in
	 * 
	 * @param pane A BorderPane that is one screen of the program
	 */
	public void setCurrentScreen(BorderPane pane) {
		//Changes the current pane in the scene. This method is attached
		//to the event handlers of the previous/next buttons
		Scene theScene = theStage.getScene();
		theStage.setScene(new Scene(pane, theScene.getWidth(), theScene.getHeight()));
	}
	
	
}
