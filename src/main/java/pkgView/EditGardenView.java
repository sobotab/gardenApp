package pkgView;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pkgController.EditGardenController;

public class EditGardenView extends BorderPane{
	int userX;
	int userY;
	boolean clicked;
	DragDropCarouselView plantCarousel;
	StackPane garden;
	List<PlantView> plants;
	List<Point> gardenOutline;
	EditGardenController egc;
	Canvas gardenOutlineImg;
	
	public EditGardenView(View view) {
		// Initialize controller
		egc = new EditGardenController(view, this);
		
		// Build buttons
		Label title = new Label("Edit Garden");
		Button back = new Button("Back to plant select");
		back.setOnAction(egc.getHandlerForBack());
		Button save = new Button("Save");
		Button exit = new Button("Exit");
		exit.setOnAction(egc.getHandlerForExit());
				
		HBox hBox = new HBox();
		hBox.setPrefSize(100,200);
		hBox.getChildren().addAll(title, back, save, exit);
		hBox.setMaxSize(300, 50);
		
		
		// Hard-coded gardenOutline for testing
		gardenOutline = new ArrayList<Point>();
		gardenOutline.add(new Point(180, 0));
		gardenOutline.add(new Point(0, 180));
		gardenOutline.add(new Point(180, 360));
		gardenOutline.add(new Point(360, 180));
		gardenOutline.add(new Point(300, 50));
		gardenOutline.add(new Point(180, 0));

		
		// Make compost, put in carousel -- doesn't do anything till I figure out
		// if we can detect drag target using mouse events, otherwise major changes to drag drop
		ImageView compost = new ImageView();
    	compost.setImage(new Image(getClass().getResourceAsStream("/img/compost.png")));
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(80);
    	//compost.setOnMouseReleased(egc.getHandlerForDrop());

    	gardenOutlineImg = new Canvas(400, 400);
    	GraphicsContext gc = gardenOutlineImg.getGraphicsContext2D();
    	gc.setFill(Color.LIGHTGREEN);
    	
    	// Drawing garden outline based on list<point>
    	int j;
    	gc.setStroke(Color.DARKGREEN);
    	gc.beginPath();
    	for (j = 0; j < gardenOutline.size(); j++)
    		gc.lineTo(gardenOutline.get(j).getX(), gardenOutline.get(j).getY());
    		gc.stroke();
    	gc.closePath();
    	gc.fill();
    	
    	// Make garden as stackpane
		garden = new StackPane();
		garden.setBackground(new Background(new BackgroundFill(Color.WHITE, 
                CornerRadii.EMPTY, Insets.EMPTY)));
		garden.getChildren().add(gardenOutlineImg);
		garden.setAlignment(Pos.CENTER);
		
		this.plants = new ArrayList<PlantView>();
    	PlantView pv1 = newPlantView();
    	plants.add(pv1);

		// temp tilepane in place of carousel
		plantCarousel = new DragDropCarouselView();
		plantCarousel.getChildren().add(compost);
		plantCarousel.getChildren().add(pv1);
    	plantCarousel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
                CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	// Organize elements on screen
		this.setTop(hBox);
		this.setBottom(plantCarousel);
		this.setCenter(garden);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getBottom(), new Insets(10, 10, 10, 10));
    	
	}
	
	// Helper function to build new plantviews
	public PlantView newPlantView() {
    	PlantView pv = new PlantView();
    	pv.setImage(new Image(getClass().getResourceAsStream("/img/commonMilkweed.png")));
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(80);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
    	pv.setOnMouseReleased(egc.getHandlerForRelease());
    	return pv;
    }
	
	
	public void drawSpread(int index) {
		GraphicsContext gc = this.gardenOutlineImg.getGraphicsContext2D();
        //Circle spread = new Circle(this.getPlants().get(index).getX(), this.getPlants().get(index).getY(), 3);
        Circle spread = new Circle(10, 10, 3);
		gc.setStroke(Color.AZURE);
        gc.setFill(Color.BLACK);
        gc.beginPath();
		gc.fillOval(spread.getCenterX(), spread.getCenterY(), spread.getRadius(), spread.getRadius());
		gc.strokeOval(spread.getCenterX(), spread.getCenterY(), spread.getRadius(), spread.getRadius());
		gc.closePath();
		
	}
	
	public List<Point> movePlant() {
		List<Point> points = null;
		return points;
	}
	
	public void updatePlant() {}
	
	
	// getters
	public int getUserX() {
		return this.userX;
	}
	
	public int getUserY() {
		return this.userY;
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public DragDropCarouselView getPlantCarousel() {
		return this.plantCarousel;
	}
	
	public StackPane getGarden() {
		return this.garden;
	}
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	
	// setters
	public void setUserX(int x) {
		this.userX = x;
	}
	
	public void setUserY(int y) {
		this.userY = y;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setPlantCarousel(DragDropCarouselView carousel) {
		this.plantCarousel = carousel;
	}
	
	public void setGarden(StackPane garden) {
		this.garden = garden;
	}
	
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	public void setX(int index, double x) {
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateX(img.getLayoutX() + x);

    }
    
    public void setY(int index, double y) {
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateY(img.getLayoutY() + y);
    	
    }

	public List<Point> getGardenOutline() {
		return this.gardenOutline;
	}
	
	
	
}
