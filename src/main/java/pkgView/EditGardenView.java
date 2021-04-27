package pkgView;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Pair;
import pkgController.EditGardenController;
import pkgController.Soil;

public class EditGardenView extends BorderPane{
	final int CANVASHEIGHT = 500;
	final int CANVASWIDTH = 500;
	boolean clicked;
	DragDropCarouselView plantCarousel;
	StackPane garden;
	TilePane infoTab;
	VBox budgetBox;
	VBox lepBox;
	int budget;
	List<PlantView> plants;
	List<Circle> plantSpreads;
	EditGardenController egc;
	List<Pair<String, Integer>> plantInput;
	double maxDimension;
	Canvas canvas;
	
	public EditGardenView(View view, String loadName) {
		
		this.plantInput = new ArrayList<Pair<String, Integer>>();
		this.plantCarousel = new DragDropCarouselView(view);
		this.egc = new EditGardenController(view, this, loadName);
		this.plants = new ArrayList<PlantView>();
		this.plantSpreads = new ArrayList<Circle>();
		
		
		// Initialize carousel
		
		for (Pair plantInfo : plantInput)
			plantCarousel.initializePlant(makePlantView((String)plantInfo.getKey(), (int)plantInfo.getValue()));
		plantCarousel.update();
		
		
		// Build & organize buttons, title
			
		Label title = new Label("Edit Garden   ");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 30));
		
		Button back = new Button("Back to plant select");
		back.setOnAction(egc.getHandlerForBack());
		
		Button save = new Button("Save");
		save.setOnAction(egc.getHandlerForSave());
		
		Button exit = new Button("Exit");
		exit.setOnAction(egc.getHandlerForExit());
		
		VBox buttonBox = new VBox();
		buttonBox.getChildren().addAll(title, back, save, exit);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10,0,0,0));
		buttonBox.setSpacing(5);
		
		
		// Build budget/lep info tab
		
		infoTab = new TilePane();
		infoTab.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,10))));
		infoTab.setPrefWidth(150);
		infoTab.setAlignment(Pos.TOP_CENTER);
		
		budgetBox = new VBox();
		
		Label budgetTitle = new Label("Budget Tracker:");
		budgetTitle.setFont(Font.font("Trebuchet MS", 15));
		
		Label budgetRatio = new Label(0 + " / " + budget);
		budgetRatio.setFont(Font.font("Trebuchet MS", 30));
		
		budgetBox.getChildren().addAll(budgetTitle, budgetRatio);
		budgetBox.setAlignment(Pos.TOP_CENTER);
		budgetBox.setPadding(new Insets(10,0,0,0));
		
		
		lepBox = new VBox();
		
		Label lepTitle = new Label("Leps Supported:");
		lepTitle.setFont(Font.font("Trebuchet MS", 15));
		
		Label lepDisplay = new Label("" + 0);
		lepDisplay.setFont(Font.font("Trebuchet MS", 30));
		
		lepBox.getChildren().addAll(lepTitle, lepDisplay);
		lepBox.setAlignment(Pos.TOP_CENTER);
		lepBox.setPadding(new Insets(20,0,0,0));
		
		infoTab.getChildren().addAll(budgetBox, lepBox, buttonBox);
		
		
		// Background
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
		
    	// Make garden as stackpane
    	
		garden = new StackPane();
		garden.setBackground(new Background(new BackgroundFill(Color.WHITE, 
				new CornerRadii(5), Insets.EMPTY)));

		garden.getChildren().add(canvas);
		canvas.setViewOrder(2);
		garden.setAlignment(canvas, Pos.CENTER);
	
    	
    	// Organize elements on screen
    	
		this.setTop(title);
		//this.setTop(hBox);
		this.setBottom(plantCarousel);
		this.setCenter(garden);
		this.setRight(infoTab);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getBottom(), new Insets(10, 10, 10, 10));
    	
    	if (loadName != null)
    		egc.fetchGardenInfo();
	}
	
	// Draw garden on canvas according to plot data
	
	public void makeCanvas(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		this.canvas = new Canvas(CANVASHEIGHT, CANVASWIDTH);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(2.0);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(0f, 0f, CANVASWIDTH, CANVASHEIGHT);
		
		Iterator plotIter = plots.entrySet().iterator();
		
		while (plotIter.hasNext()) {
			
			Map.Entry plotEntry = (Map.Entry)plotIter.next();
			
			if (plotEntry.getKey() == Soil.CLAY) { gc.setFill(Color.RED); } 
			else if (plotEntry.getKey() == Soil.LOAMY) { gc.setFill(Color.BROWN); }
			else { gc.setFill(Color.CORNSILK); }
			
			Stack<ArrayList<Point2D.Double>> plotStack = (Stack<ArrayList<Point2D.Double>>)plotEntry.getValue();
	
			for (ArrayList<Point2D.Double> plot : plotStack) {
				gc.beginPath();
				for (Point2D.Double point : plot) {
					gc.lineTo(point.x, point.y);
					gc.stroke();
				}
				gc.fill();
				gc.closePath();
			}
		}
	}
	
	
	// Update info panel as new plants are added to garden
	
	public void updateInfoPanel(int dollars, int leps) {
		this.budgetBox.getChildren().remove(1);
		this.lepBox.getChildren().remove(1);
		
		Label newBudgetRatio = new Label(dollars + " / " + budget);
		newBudgetRatio.setFont(Font.font("Trebuchet MS", 30));
		
		if (dollars > budget*0.75) {
			newBudgetRatio.setTextFill(Color.GOLDENROD);
		}
		if (dollars > budget) {
			newBudgetRatio.setTextFill(Color.RED);
		}
		
		Label newLepDisplay = new Label("" + leps);
		newLepDisplay.setFont(Font.font("Trebuchet MS", 30));

		this.budgetBox.getChildren().add(newBudgetRatio);
		this.lepBox.getChildren().add(newLepDisplay);
		
		System.out.println(leps);
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
	
	
	// Helper function for replacing plants dragged from carousel
	
	public void replacePlant(int index) {
		PlantView duplicatePlant = makePlantView(
				plantCarousel.getPlants().get(index).getImage(),
				plantCarousel.getPlants().get(index).getSpread()
				);
		plantCarousel.addPlantAtIndex(duplicatePlant, index+1);
	}

	
	// Draw or update spread circle location and reset color 
	
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
	
	
	// Update spread circle color according to whether it is inside garden or overlaps other spreads
	
	public void updateSpread(int index, boolean inGarden, boolean overlap) {
		Circle spread = plantSpreads.get(index);
		spread.setFill(Color.LIGHTBLUE);
		if (overlap)
			spread.setFill(Color.YELLOW);
		if (!inGarden)
			spread.setFill(Color.RED);
	}
	
	
	// Helper for handling node transport from carousel to garden pane
	
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
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public int getBudget() {
		return(this.budget);
	}
	
	
	// setters
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
	
	public void setPlantInput(List<Pair<String, Integer>> input) {
		this.plantInput = input;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
}
