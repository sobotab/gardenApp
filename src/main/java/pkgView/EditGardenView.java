package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Pair;
import pkgController.EditGardenController;

public class EditGardenView extends BorderPane{
	int userX;
	int userY;
	boolean clicked;
	DragDropCarouselView plantCarousel;
	StackPane garden;
	List<PlantView> plants;
	List<Circle> plantSpreads;
	List<Point> gardenOutline;
	Polygon gardenOutlineShape;
	EditGardenController egc;
	List<Pair<String, Integer>> plantInput;
	double maxDimension;
	
	public EditGardenView(View view) {
		
		this.plantInput = new ArrayList<Pair<String, Integer>>();
		this.egc = new EditGardenController(view, this);
		this.plants = new ArrayList<PlantView>();
		this.plantSpreads = new ArrayList<Circle>();
		
		
		// Initialize carousel
		//NOTE: this.plants ONLY contains plants in garden. NOT those in carousel, for organization
		
		plantCarousel = new DragDropCarouselView();
		for (Pair plantInfo : plantInput)
			plantCarousel.addPlant(makePlantView((String)plantInfo.getKey(), (int)plantInfo.getValue()));
			System.out.println(plantInput.size());
		
			
		// Build & organize buttons, title
			
		Label title = new Label("Edit Garden   ");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 30));
		
		Button back = new Button("Back to plant select");
		back.setOnAction(egc.getHandlerForBack());
		
		Button save = new Button("Save");
		
		Button exit = new Button("Exit");
		exit.setOnAction(egc.getHandlerForExit());
		
		HBox hBox = new HBox();
		hBox.setPrefSize(100,500);
		hBox.getChildren().addAll(title, back, save, exit);
		hBox.setMaxSize(500, 50);
		
		
		// Background
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
		
		// Hard-coded gardenOutline for testing
		
		gardenOutline = new ArrayList<Point>();
		gardenOutline.add(new Point(180, 0));
		gardenOutline.add(new Point(0, 180));
		gardenOutline.add(new Point(180, 360));
		gardenOutline.add(new Point(360, 180));
		gardenOutline.add(new Point(300, 50));
		gardenOutline.add(new Point(180, 0));
		
		gardenOutlineShape = new Polygon();
    	for (Point point : gardenOutline) {
    		gardenOutlineShape.getPoints().add((double)point.x);
    		gardenOutlineShape.getPoints().add((double)point.y);
    	}
		gardenOutlineShape.setStroke(Color.DARKGREEN);
		gardenOutlineShape.setFill(Color.LIGHTGREEN);
    	
    	// Make garden as stackpane
    	
		garden = new StackPane();
		garden.setBackground(new Background(new BackgroundFill(Color.WHITE, 
                CornerRadii.EMPTY, Insets.EMPTY)));
		garden.getChildren().add(gardenOutlineShape);
		gardenOutlineShape.setViewOrder(2);
		garden.setAlignment(gardenOutlineShape, Pos.CENTER);
	
    	
    	// Organize elements on screen
    	
		this.setTop(hBox);
		this.setBottom(plantCarousel);
		this.setCenter(garden);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getBottom(), new Insets(10, 10, 10, 10));
    	
	}
	
	// Helper function to build new plantviews
	public PlantView makePlantView(String sci_name, int spread) {
    	PlantView pv = new PlantView(new Image(getClass().getResourceAsStream("/images/" + sci_name + ".jpg")), spread);
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(60);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
    	pv.setOnDragDetected(egc.getHandlerForDragDetect(pv));
    	pv.setOnMouseReleased(egc.getHandlerForRelease());
    	return pv;
    }
	
	// Same as above but builds from existing image
	public PlantView makePlantView(Image img, int spread) {
    	PlantView pv = new PlantView(img, spread);
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(60);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
    	pv.setOnDragDetected(egc.getHandlerForDragDetect(pv));
    	pv.setOnMouseReleased(egc.getHandlerForRelease());
    	return pv;
    }
	
	public void replacePlant(int index) {
		PlantView duplicatePlant = makePlantView(
				plantCarousel.getPlants().get(index).getImage(),
				plantCarousel.getPlants().get(index).getSpread()
				);
		plantCarousel.addPlantAtIndex(duplicatePlant, index+1);
	}

	public void drawSpread(int index, double x, double y) {
		
		if (plantSpreads.size() == index) {
	        Circle spread = new Circle(x, y, plants.get(index).getSpread()/2);
	        plantSpreads.add(spread);
	        garden.getChildren().add(spread);
	        garden.setAlignment(spread, Pos.TOP_LEFT);
	        plantSpreads.get(index).setViewOrder(1);
		}
		
		Circle spread = plantSpreads.get(index);
		spread.setCenterX(x - spread.getRadius() + plants.get(index).getFitHeight()/2);
		spread.setCenterY(y - spread.getRadius() + plants.get(index).getFitHeight()/2);
		spread.setTranslateX(spread.getCenterX());
		spread.setTranslateY(spread.getCenterY());
		
        spread.setStroke(Color.WHITE);
		spread.setFill(Color.LIGHTBLUE);

	}
	
	public void updateSpread(int index, boolean inGarden, boolean overlap) {
		Circle spread = plantSpreads.get(index);
		spread.setFill(Color.LIGHTBLUE);
		if (overlap)
			spread.setFill(Color.YELLOW);
		if (!inGarden)
			spread.setFill(Color.RED);
	}
	
	public void addPlantFromCarousel(int index, Node n, MouseEvent event) {
		plants.add(plantCarousel.removePlant(index));
		garden.getChildren().add(n);	// should also remove from carousel pane
		garden.setAlignment(n, Pos.TOP_LEFT);
		
		int newIndex = plants.indexOf(n);
		plants.get(newIndex).setFitHeight(plants.get(newIndex).spread/4 + 20);
		plants.get(newIndex).setFitWidth(plants.get(newIndex).spread/4 + 20);
		
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
	
	public List<Pair<String, Integer>> getPlantInput() {
		return this.plantInput;
	}
	
	public Polygon getGardenOutlineShape() {
		return gardenOutlineShape;
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
    	//img.setTranslateX(img.getLayoutX() + x);
    	img.setTranslateX(x);
    }
    
    public void setY(int index, double y) {
    	PlantView img = this.getPlants().get(index);
    	//img.setTranslateY(img.getLayoutY() + y);
    	img.setTranslateY(y);
    }

	public List<Point> getGardenOutline() {
		return this.gardenOutline;
	}
	
	public void setPlantInput(List<Pair<String, Integer>> input) {
		this.plantInput = input;
	}

	public void setGardenOutlineShape(Polygon gardenOutlineShape) {
		this.gardenOutlineShape = gardenOutlineShape;
	}
	
}
