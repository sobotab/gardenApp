package pkgView;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import pkgController.Controller;

public class View {
	final int SCENEWIDTH = 800;
	final int SCENEHEIGHT = 600;
	//int background;  /* Commented out for now. Seems obsolete due to borderpane's background? */
	List<Node> widgets;
	List<Image> plantImages;
	GraphicsContext gc;
	public Controller controller;
	Window window;
	BorderPane currentScreen; // replaced screen for borderpane, effectively the same
	//All individual screens now inherit borderpane: gives us children, height/width, background, padding, margins (everything that was in screen)
	Stage theStage;
	Scene theScene;
	
	public View(Stage theStage) {
		this.theStage = theStage;
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
	
	public List<Node> getWidgets() {
		return this.widgets;
	}
	
	public List<Image> getPlantImages() {
		return this.plantImages;
	}
	
	public BorderPane getCurrentScreen() {
		return this.currentScreen;
	}
	
	void setWidgets(List<Node> widgets) {
		this.widgets = widgets;
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
	
}
