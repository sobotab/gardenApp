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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Pair;
import pkgController.EditGardenController;
import pkgController.Soil;

public class EditGardenView extends BorderPane{
	final int CANVASHEIGHT = 500;
	final int CANVASWIDTH = 500;
	final int DEFAULTSCALE = 25;
	DragDropCarouselView plantCarousel;
	StackPane garden;
	HBox budgetBox;
	HBox lepBox;
	int budget;
	List<PlantView> plants;
	List<Circle> plantSpreads;
	EditGardenController egc;
	List<Pair<String, Integer>> plantInput;
	int scale;
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
		title.setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 40));
		
		ImageView back_img = new ImageView(new Image("/images/back-icon.png"));
		back_img.setFitHeight(30);
		back_img.setPreserveRatio(true);
		back_img.setRotationAxis(Rotate.Y_AXIS);
		back_img.setRotate(180);
		Button back = new Button();
		//Button back = new Button("Back");
		//back.setFont(Font.font("Trebuchet MS", 18));
		//back.setPrefWidth(100);
		//back.setPrefHeight(40);
		back.setGraphic(back_img);
		//back.setGraphicTextGap(5);
		back.setOnAction(egc.getHandlerForBack());
		
		ImageView save_img = new ImageView(new Image("/images/save-icon.png"));
		save_img.setFitHeight(30);
		save_img.setPreserveRatio(true);
		Button save = new Button();
		//Button save = new Button("Save");
		//save.setFont(Font.font("Trebuchet MS", 18));
		//save.setPrefWidth(100);
		//save.setPrefHeight(40);
		save.setGraphic(save_img);
		//save.setGraphicTextGap(5);
		save.setOnAction(egc.getHandlerForSave());
		
		ImageView exit_img = new ImageView(new Image("/images/exit-icon.png"));
		exit_img.setFitHeight(30);
		exit_img.setPreserveRatio(true);
		Button exit = new Button();
		//Button exit = new Button("Exit");
		//exit.setFont(Font.font("Trebuchet MS", 18));
		//exit.setPrefWidth(100);
		//exit.setPrefHeight(40);
		exit.setGraphic(exit_img);
		//exit.setGraphicTextGap(5);
		exit.setOnAction(egc.getHandlerForExit());
		
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(back, save, exit);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10,0,0,0));
		buttonBox.setSpacing(5);
		
		
		// Build budget/lep info tab
		
		TilePane infoTab = new TilePane();
		//infoTab.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
		//		new CornerRadii(5), new Insets(0,0,0,10))));
		infoTab.setPrefWidth(200);
		infoTab.setAlignment(Pos.TOP_CENTER);
		infoTab.setVgap(10);
		
		budgetBox = new HBox();
		budgetBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,10))));
		budgetBox.setPrefWidth(200);
		Label budgetTitle = new Label("$ ");
		budgetTitle.setTextFill(Color.GREEN);
		budgetTitle.setFont(Font.font("Trebuchet MS", FontWeight.EXTRA_BOLD, 50));
		
		Label budgetRatio = new Label(0 + " / " + budget);
		budgetRatio.setFont(Font.font("Trebuchet MS", FontWeight.SEMI_BOLD, 40));
		
		budgetBox.getChildren().addAll(budgetTitle, budgetRatio);
		budgetBox.setAlignment(Pos.CENTER);
		budgetBox.setPadding(new Insets(0,0,0,0));
		
		
		lepBox = new HBox();
		lepBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,10))));
		lepBox.setPrefWidth(200);
		
		Label lepTitle = new Label("Leps Supported:");
		lepTitle.setTextFill(Color.GREEN);
		lepTitle.setFont(Font.font("Trebuchet MS", FontWeight.EXTRA_BOLD, 50));
		
		
		Label lepDisplay = new Label("" + 0);
		lepDisplay.setFont(Font.font("Trebuchet MS", 30));
		
		lepBox.getChildren().addAll(lepTitle, lepDisplay);
		lepBox.setAlignment(Pos.TOP_CENTER);
		lepBox.setPadding(new Insets(20,0,0,0));
		
		infoTab.getChildren().addAll(budgetBox, lepBox, buttonBox);
		
		
		// Background
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-leaves.jpg")),
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
    	
		PlantView compost = new PlantView(new Image(getClass().getResourceAsStream("/images/compost.png")), 0);
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(60);

    	garden.getChildren().add(compost);
    	garden.setAlignment(compost, Pos.BOTTOM_LEFT);
		
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
    	
    	// Adding some listeners so plant coordinates stay within window
    	/*
    	canvas.layoutXProperty().addListener((obs, oldVal, newVal) -> {
    		if ((double)newVal != 0.0 && (double)oldVal != 0.0) {
    			egc.fitCoordinatesToWindowWidth((double)oldVal, (double)newVal);
    			System.out.println(oldVal + " " + (double)oldVal);
    		}
    	 });
    	
    	canvas.layoutYProperty().addListener((obs, oldVal, newVal) -> {
    		if ((double)newVal != 0.0 && (double)oldVal != 0.0) {
    			egc.fitCoordinatesToWindowHeight((double)oldVal, (double)newVal);
    			System.out.println(oldVal + " " + (double)oldVal);
    		}
    	 });
    	 */
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
			newBudgetRatio.setTextFill(Color.CRIMSON);
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
    	
    	Rectangle img_template = new Rectangle(60,60);
    	img_template.setArcWidth(15);
    	img_template.setArcHeight(15);
    	pv.setClip(img_template);
    	
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(60);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
    	pv.setOnMouseReleased(egc.getHandlerForRelease());
    	return pv;
    }
	
	
	// Same as above but builds from existing image
	
	public PlantView makePlantView(Image img, int spread) {
    	PlantView pv = new PlantView(img, spread);
    	
    	Rectangle img_template = new Rectangle(60,60);
    	img_template.setArcWidth(15);
    	img_template.setArcHeight(15);
    	pv.setClip(img_template);
    	
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(60);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
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
	        Circle spread = new Circle(
	        		x,
	        		y,
	        		computeScaleSize(plants.get(index)));
	        plantSpreads.add(spread);
	        garden.getChildren().add(spread);
	        garden.setAlignment(spread, Pos.TOP_LEFT);
	        plantSpreads.get(index).setViewOrder(1);
		}
		
		Circle spread = plantSpreads.get(index);
		spread.setCenterX(x - spread.getRadius() + plants.get(index).getFitHeight()/2);
		spread.setCenterY(y - spread.getRadius() + plants.get(index).getFitHeight()/2);
		//spread.setCenterX(x);
		//spread.setCenterY(y);
		spread.setTranslateX(spread.getCenterX());
		spread.setTranslateY(spread.getCenterY());
		
        spread.setStroke(Color.WHITE);
		spread.setFill(Color.LIGHTBLUE);

	}
	
	// Calculate spread bubble size according to scale factor
	
	public double computeScaleSize(PlantView plant) {
		double default_radius = plant.getSpread()/2;
		if (Math.abs(DEFAULTSCALE - this.scale) < 1) {
			return default_radius;
		}
		//double scaled_radius = default_radius + ( default_radius / (DEFAULTSCALE - this.scale) );
		double scaled_radius = default_radius + 30*((DEFAULTSCALE - this.scale) / default_radius);
		System.out.println("default: " + default_radius + ", this scale: " + this.scale + ", " + "new radius: " + scaled_radius);
		return Math.max(1.0, scaled_radius);
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
		
		Rectangle fit_template = new Rectangle(
				plants.get(newIndex).spread/4 + 20,
				plants.get(newIndex).spread/4 + 20);
		fit_template.setArcHeight(15);
		fit_template.setArcWidth(15);
		
		plants.get(newIndex).setClip(fit_template);
	}
		
	
	// getters
	public DragDropCarouselView getPlantCarousel() {
		return this.plantCarousel;
	}
	
	public StackPane getGarden() {
		return this.garden;
	}
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	public List<Circle> getSpreads() {
		return this.plantSpreads;
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

	public void setScale(int scale) {
		this.scale = scale;
		
	}
	
}
