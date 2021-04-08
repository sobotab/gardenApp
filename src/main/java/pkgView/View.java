package pkgView;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import pkgController.Controller;

public class View {
	int canvasWidth;
	int canvasHeight;
	//int background;  /* Commented out for now. Seems obsolete due to borderpane's background? */
	List<Node> widgets;
	List<Image> plantImages;
	GraphicsContext gc;
	Controller controller;
	Window window;
	BorderPane currentScreen; // replaced screen for borderpane, effectively the same
	//All individual screens now inherit borderpane: gives us children, height/width, background, padding, margins (everything that was in screen)
	
	public View(Stage theStage) {}
	
	public void ImportImages() {}
	
	Image createImage(String image_file) {}
	
	boolean clearPane() {}
	
	boolean buildPane(View view) {}
	
	void update() {}
	
	
	// getters
	public int getCanvasWidth() {
		return this.canvasWidth;
	}
	
	public int getCanvasHeight() {
		return this.canvasHeight;
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
	
	
	// setters
	public void setCanvasWidth(int width) {
		this.canvasWidth = width;
	}
	
	public void setCanvasHeight(int height) {
		this.canvasHeight = height;
	}
	
	public void setWidgets(List<Node> widgets) {
		this.widgets = widgets;
	}
	
	public void setPlantImages(List<Image> plantImages) {
		this.plantImages = plantImages;
	}
	
	public void setCurrentPane(BorderPane pane) {
		this.currentScreen = pane;
	}
	
}
