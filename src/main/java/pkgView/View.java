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

public class View {
	final int SCENEWIDTH = 800;
	final int SCENEHEIGHT = 600;
	List<Node> widgets;
	List<Image> plantImages;
	GraphicsContext gc;
	public Controller controller;
	Window window;
	BorderPane currentScreen; // replaced screen for borderpane, effectively the same
	//All individual screens now inherit borderpane: gives us children, height/width, background, padding, margins (everything that was in screen)
	Stage theStage;
	Scene theScene;
	
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
	
	public void ImportImages() {}
	
	Image createImage(String image_file) {
		Image img = null;
		return img;
	}
	
	boolean clearPane() {
		return false;
	}
	
	boolean buildPane(View view) {
		return false;
	}
	
	public void update() {
	}
	
	
	// getters
	
	public Stage getTheStage() {
		return this.theStage;
	}
	
	public List<Image> getPlantImages() {
		return this.plantImages;
	}
	
	public BorderPane getCurrentScreen() {
		return this.currentScreen;
	}
	
	public void setPlantImages(List<Image> plantImages) {
		this.plantImages = plantImages;
	}
	
	public void setCurrentScreen(BorderPane pane) {
		//Changes the current pane in the scene. This method is attached
		//to the event handlers of the previous/next buttons
		Scene theScene = theStage.getScene();
		theStage.setScene(new Scene(pane, theScene.getWidth(), theScene.getHeight()));
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
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
	
}
