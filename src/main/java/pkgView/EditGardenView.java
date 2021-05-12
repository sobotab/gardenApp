package pkgView;

import java.awt.geom.Point2D;
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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import pkgController.EditGardenController;
import pkgController.Soil;
/**
 * 
 * @author Ryan Dean
 * The EditGardenView class extends BorderPane, and is the implementation for the "Edit Garden" screen of the program.
 * This screen is where users drag and drop plant objects onto their previously drawn garden plot.
 */
public class EditGardenView extends BorderPane{
	/**
	 * Constant for height of canvas that holds drawn garden.
	 */
	public final int CANVASHEIGHT = 700;
	/**
	 * Constant for width of canvas that holds drawn garden.
	 */
	public final int CANVASWIDTH = 1000;
	/**
	 * Constant representing the "default scale," or how big the garden is 
	 * assumed to be (500 ft across) if no scaling changes were made in Draw Garden.
	 */
	public final int DEFAULTSCALE = 500;
	/**
	 * A DragDropCarouselView that represents the carousel of selected plants you can add to your garden.
	 */
	DragDropCarouselView plantCarousel;
	/**
	 * A Stackpane that represents the garden. It holds the canvas containing the drawn plots, as well
	 * as plant objects you have dragged into the garden.
	 */
	StackPane garden;
	/**
	 * A list of Circles and Rectangles that visually represent the spread of plant objects in the garden.
	 * A shape exists in this list for every plant in "plants".
	 */
	List<Shape> plantSpreads;
	/**
	 * An HBox displaying the current and maximum budget, as defined by the user in Draw Garden.
	 */
	HBox budgetBox;
	/**
	 * An HBox displaying the current number of leps supported, incremented as plants are added to the garden.
	 */
	HBox lepBox;
	/**
	 * A VBox displaying the 12 lep species most supported by the plants in the garden, as well as the exact
	 * number of plants that support them.
	 */
	VBox lepSpeciesBox;
	/**
	 * A List of the PlantView objects currently present in the garden (post-dragging from carousel).
	 */
	List<PlantView> plants;
	/**
	 * A Canvas that has the plots drawn in Draw Garden copied into it, and is placed into the garden StackPane.
	 */
	Canvas canvas;
	/**
	 * The Controller associated with this view class, controls communicate with EditGardenModel and EventHandlers.
	 */
	EditGardenController egc;
	/**
	 * Data structure holding information on the plants selected in Select Plants. The Strings include name, 
	 * scientific name, and soil type. The integer is spread.
	 */
	List<Pair<ArrayList<String>, Integer>> plantInput;
	/**
	 * Double representing the scale_factor computed by the ratio between DEFAULTSCALE and the scale used in Draw Garden.
	 */
	double scale_factor;
	/**
	 * Integer holding the maximum budget as input in Draw Garden.
	 */
	int budget;
	
	/**
	 * Constructor initializes this view class. Takes in plant and plot data from the controller to build garden
	 * shown in the scene. Instantiates and fills the DragDropCarouselView with plant objects.
	 * @param view 		The program's View that is only initialized once.
	 * @param loadName 	The user-input name of the garden being loaded. Null if a new garden is being made.
	 */
	public EditGardenView(View view, String loadName) {
		
		this.plantInput = new ArrayList< Pair<ArrayList<String>, Integer> >();
		this.plantCarousel = new DragDropCarouselView(view);
		//this.plantCarousel.setMaxWidth(CANVASWIDTH * 1.75);
		this.egc = new EditGardenController(view, this, loadName);
		this.plants = new ArrayList<PlantView>();
		this.plantSpreads = new ArrayList<Shape>();
		
		
		// Initialize carousel
		
		for (Pair plantInfo : plantInput)
			plantCarousel.initializePlant(makePlantView((ArrayList<String>)plantInfo.getKey(), (int)plantInfo.getValue()));
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

		back.setGraphic(back_img);
		back.setOnAction(egc.getHandlerForBack());
		
		ImageView save_img = new ImageView(new Image("/images/save-icon.png"));
		save_img.setFitHeight(30);
		save_img.setPreserveRatio(true);
		Button save = new Button();
		save.setGraphic(save_img);
		save.setOnAction(egc.getHandlerForSave());
		
		ImageView exit_img = new ImageView(new Image("/images/exit-icon.png"));
		exit_img.setFitHeight(30);
		exit_img.setPreserveRatio(true);
		Button exit = new Button();
		exit.setGraphic(exit_img);
		exit.setOnAction(egc.getHandlerForExit());
		
		ImageView print_img = new ImageView(new Image("/images/print-icon.png"));
		print_img.setFitHeight(30);
		print_img.setPreserveRatio(true);
		Button print = new Button();
		print.setGraphic(print_img);
		print.setOnAction(egc.getHandlerForPrint());
		
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(back, save, print, exit);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(5);
		
		
		// Build budget / lep / buttons tab
		
		FlowPane infoTab = new FlowPane();
		infoTab.setPrefWidth(200);
		infoTab.setAlignment(Pos.TOP_CENTER);
		infoTab.setVgap(10);
		
		// Budget display
		
		budgetBox = new HBox();
		budgetBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,0))));
		budgetBox.setPrefWidth(200);
		Label budgetTitle = new Label("$ ");
		budgetTitle.setTextFill(Color.GREEN);
		budgetTitle.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 40));
		
		Label budgetRatio = new Label(0 + " / " + budget);
		budgetRatio.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		
		budgetBox.getChildren().addAll(budgetTitle, budgetRatio);
		budgetBox.setAlignment(Pos.CENTER);
		budgetBox.setPadding(new Insets(0,0,0,0));
		
		// Lep number display
		
		lepBox = new HBox();
		lepBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,0))));
		lepBox.setPrefWidth(200);
		lepBox.setSpacing(10);
		
		ImageView lep_img = new ImageView(new Image("/images/lep-icon.png"));
		lep_img.setFitHeight(60);
		lep_img.setPreserveRatio(true);
		
		Label lepDisplay = new Label("" + 0);
		lepDisplay.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		
		lepBox.getChildren().addAll(lep_img, lepDisplay);
		lepBox.setAlignment(Pos.CENTER);
		
		// Lep species info display
		
		lepSpeciesBox = new VBox();		
		lepSpeciesBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,
				new CornerRadii(5), new Insets(0,0,0,0))));
		lepSpeciesBox.setPrefWidth(200);
		lepSpeciesBox.setAlignment(Pos.CENTER);
		lepSpeciesBox.setPadding(new Insets(10, 5, 10, 0));
		lepSpeciesBox.setSpacing(10);
		
		infoTab.setPadding(new Insets(10, 10, 0, 0));
		infoTab.getChildren().addAll(budgetBox, lepBox, lepSpeciesBox, buttonBox);
		
		
		// Background
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-leaves.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
		
    	// Make garden as stackpane & add compost
    	
		garden = new StackPane();
		garden.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, 
				new CornerRadii(5), Insets.EMPTY)));
		garden.getChildren().add(canvas);
		canvas.setViewOrder(2);
		garden.setAlignment(canvas, Pos.CENTER);	
		garden.setMaxSize(CANVASWIDTH + 350, CANVASHEIGHT + 10);
		garden.setClip(new Rectangle(CANVASWIDTH + 400, CANVASHEIGHT + 20));
		plantCarousel.setMaxWidth(CANVASWIDTH + 450);
		    	
		PlantView compost = new PlantView(new Image(getClass().getResourceAsStream("/images/compost-icon.png")), 0);
    	compost.setPreserveRatio(true);
    	compost.setFitHeight(80);
    	Tooltip compost_tip = new Tooltip("Drag plants here to remove them!");
    	compost_tip.setShowDelay(Duration.seconds(0.5));
    	Tooltip.install(compost, compost_tip);

    	garden.getChildren().add(compost);
    	garden.setAlignment(compost, Pos.BOTTOM_LEFT);

    	
    	// Organize elements on screen
    	
		this.setTop(title);
		this.setBottom(plantCarousel);
		this.setCenter(garden);
		this.setRight(infoTab);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getBottom(), new Insets(10, 10, 10, 10));
    	
    	
    	// If loading a garden, request model info from controller
    	
    	if (loadName != null)
    		egc.fetchGardenInfo();
    	
	}
	
	
	/**
	 * Method for re-drawing the garden plots input in Draw Garden onto this class's canvas.
	 * @param plots 	A hashmap holding the list of points of each plot drawn in Draw Garden, organized by soil type.
	 */
	public void makeCanvas(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		this.canvas = new Canvas(CANVASWIDTH, CANVASHEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(2.0);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.LIGHTGREEN);
		//gc.fillRect(0f, 0f, width, height);
		gc.fillRect(0f, 0f, CANVASWIDTH, CANVASHEIGHT);
		
		
		Iterator plotIter = plots.entrySet().iterator();
		
		while (plotIter.hasNext()) {
			
			Map.Entry plotEntry = (Map.Entry)plotIter.next();
			
			if (plotEntry.getKey() == Soil.CLAY) { 
				gc.setStroke(Color.rgb(108, 53, 21));
				gc.setFill(Color.rgb(216, 106, 43)); 
				} 
			else if (plotEntry.getKey() == Soil.LOAMY) { 
				gc.setStroke(Color.rgb(47, 32, 22));
				gc.setFill(Color.rgb(94, 64, 44)); 
			}
			else { 
				gc.setStroke(Color.BURLYWOOD);
				gc.setFill(Color.CORNSILK); 
				}
			
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
	

	/**
	 * Method for updating the values in the budget, lep number, and lep species display. Called whenever 
	 * a plan object is added or removed from the garden. Also makes a button that opens a pop-up window 
	 * containing a full list of lep species supported.
	 * @param dollars 		Current total cost of all plants in garden
	 * @param leps 			Current number of leps supported by plants in garden
	 * @param sortedLeps 	List of the lep species supported by the plants in the garden & number of plants that support them
	 */
	public void updateInfoPanel(int dollars, int leps, ArrayList<Map.Entry<String, Integer>> sortedLeps) {
		
		this.budgetBox.getChildren().remove(1);
		this.lepBox.getChildren().remove(1);
		this.lepSpeciesBox.getChildren().clear();
		
		Label newBudgetRatio = new Label(dollars + " / " + budget);
		newBudgetRatio.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		
		if (dollars > budget*0.75) {
			newBudgetRatio.setTextFill(Color.GOLDENROD);
		}
		if (dollars > budget) {
			newBudgetRatio.setTextFill(Color.CRIMSON);
		}
		
		Label newLepDisplay = new Label("" + leps);
		newLepDisplay.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		
	
		this.budgetBox.getChildren().add(newBudgetRatio);
		this.lepBox.getChildren().add(newLepDisplay);
		
		if (sortedLeps.size() != 0) {
			int lepDisplayCount = 0;
			for (Map.Entry<String, Integer> lepEntry : sortedLeps) {
				lepDisplayCount++;
				if (lepDisplayCount > 12) {
					break;
				}
				HBox species_info_box = new HBox();

				Label lepSpeciesVal = new Label(lepEntry.getValue() + "  ");
				lepSpeciesVal.setFont(Font.font("Roboto", FontWeight.BOLD, 15));
				
				Label lepSpecies = new Label(lepEntry.getKey());
				lepSpecies.setFont(Font.font("Roboto", FontWeight.MEDIUM, 15));
				
				species_info_box.getChildren().addAll(lepSpeciesVal, lepSpecies);
				species_info_box.setPadding(new Insets(0, 0, 0, 15));
				
				lepSpecies.setTextOverrun(OverrunStyle.ELLIPSIS);
				lepSpecies.setMaxWidth(160);
				
				this.lepSpeciesBox.getChildren().add(species_info_box);
				lepSpeciesBox.setAlignment(Pos.BASELINE_LEFT);
				
			}
			Button more = new Button("See more");
			more.setOnAction(egc.getHandlerForMoreLeps());
			more.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 15));
			more.setStyle("-fx-background-color: #3c7777, linear-gradient(#fafefe, #f0f5fc);");
			this.lepSpeciesBox.getChildren().add(more);
			this.lepSpeciesBox.setAlignment(Pos.CENTER);
		}		
	}
	
	
	/**
	 * Method called by the "See more" button in the lep species display. Opens a pop-up listing all lep species supported.
	 * @param sortedLeps 	List of all lep species supported by plants in garden, sorted by number of plants supporting them
	 */
	public void openLepPopup(ArrayList<Map.Entry<String, Integer>> sortedLeps) {
		Stage popupWindow = new Stage();
		popupWindow.initModality(Modality.NONE);
		popupWindow.setScene(new Scene(new LepPopupView(sortedLeps),800,500));
		popupWindow.setAlwaysOnTop(true);
		popupWindow.show(); 
	}
	
	
	/**
	 * Method for making PlantView objects, setting appropriate handlers and tooltips. Used for plants initially added to the carousel.
	 * @param name_soil_info 	List holding plant info put into tooltips
	 * @param spread 			Integer for plant spread diameter
	 * @return 					PlantView object ready to be placed in DragDropCarousel
	 */
	public PlantView makePlantView(ArrayList<String> name_soil_info, int spread) {
		String sci_name = name_soil_info.get(0);
		String name = name_soil_info.get(1);
		String soil = name_soil_info.get(2);
		
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
    	
    	String size;
    	if (spread <= 1) {
    		size = "small";
    	} else if (spread <= 20) {
    		size = "medium";
    	} else {
    		size = "large";
    	}
    	Tooltip tip = new Tooltip(sci_name + "\n" + "Size: " + size + "\n" + "Soil: " + soil);
    	Tooltip.install(pv, tip);
    	pv.getProperties().put("tip", name_soil_info);
    	
    	return pv;
    }
	
	
	/**
	 * Method that duplicates plants dragged out of carousel, and puts them in place of the removed plant.
	 * @param index 	Integer representing the position of the plant to duplicate in the carousel's plant list.
	 */
	public void replacePlant(int index) {
		PlantView plantToDuplicate = plantCarousel.getPlants().get(index);
		PlantView duplicatePlant = makePlantView(
				(ArrayList<String>)plantToDuplicate.getProperties().get("tip"),
				plantToDuplicate.getSpread()
				);
		plantCarousel.addPlantAtIndex(duplicatePlant, index+1);
	}
	

	/**
	 * Method that draws a Circle (if spread large enough to display) or rectangle (if not), visually representing 
	 * plant spread. These shapes are added to garden, continually updated to location of their associated plant.
	 * @param index 	Index of the plant in this class's plant list that the spread object is supposed to represent.
	 * @param x 		Double representing x-location of the plant this spread is associated with.
	 * @param y 		Double representing y-location of the plant this spread is associated with.
	 */
	public void drawSpread(int index, double x, double y) {
		PlantView plant = plants.get(index);
		if (plantSpreads.size() == index) {
			if (computeScaleSize(plant) <= 20) {
				Rectangle spread = new Rectangle(
						plant.getFitHeight(), 
						plant.getFitHeight());
				spread.setArcHeight(15);
				spread.setArcWidth(15);
				spread.setViewOrder(0);
				plantSpreads.add(spread);
		        garden.getChildren().add(spread);
		        garden.setAlignment(spread, Pos.TOP_LEFT);
		        spread.setMouseTransparent(true);
			} 
			else {
				Circle spread = new Circle(
	        		x,
	        		y,
	        		computeScaleSize(plant));
		        spread.setViewOrder(1);
		        plantSpreads.add(spread);
		        garden.getChildren().add(spread);
		        garden.setAlignment(spread, Pos.TOP_LEFT);
			}    
		}
		if (computeScaleSize(plant) <= 20) {
			Rectangle spread = (Rectangle)plantSpreads.get(index);
			spread.setX(x);
			spread.setY(y);
			spread.setTranslateX(x);
			spread.setTranslateY(y);
			spread.setStroke(null);
			spread.setFill(Color.rgb(173, 216, 230, 0.5));
			
		}
		else {
			Circle spread = (Circle)plantSpreads.get(index);
			spread.setCenterX(x - spread.getRadius() + plant.getFitHeight()/2);
			spread.setCenterY(y - spread.getRadius() + plant.getFitHeight()/2);
			spread.setTranslateX(spread.getCenterX());
			spread.setTranslateY(spread.getCenterY());
	        spread.setStroke(Color.WHITE);
			spread.setFill(Color.rgb(173, 216, 230, 0.5));
		}
	}
	

	/**
	 * Helper method used by DrawSpread to calculate the radius of the spread object according to scale factor.
	 * @param plant 	Plant whose spread should be altered by scale factor.
	 * @return 			Double representing the spread of the plant according to the scale factor of the garden.
	 */
	public double computeScaleSize(PlantView plant) {
		double default_radius = plant.getSpread()/2;
		return default_radius * scale_factor;
	}
	
	
	/**
	 * Method called whenever plant is dragged to change color of its associated spread object according to plot conditions.
	 * Blue: accepted. Yellow: overlaps another spread. Red: outside valid soil type.
	 * @param index 		Index of spread to be changed in this class's plantSpreads. 
	 * @param inGarden 		Boolean representing whether the base of the plant is in its correct soil type, computed by model.
	 * @param overlap 		Boolean representing whether plant's spread overlaps another's, computed by model.
	 */
	public void updateSpread(int index, boolean inGarden, boolean overlap) {
		Shape spread = plantSpreads.get(index);
		spread.setFill(Color.rgb(173, 216, 230, 0.5));			//blue
		if (overlap) {
			spread.setFill(Color.rgb(255,  255,  0, 0.5));		//yellow (precedence over blue)
		}
		if (!inGarden) {
			spread.setFill(Color.rgb(255, 0, 0, 0.5));			//red (precedence over all)
		}
	}
	
	
	/**
	 * Method facilitating transport of PlantView Node from DragDropCarousel to this class's garden stackpane.
	 * @param index 	Index of the plant in the carousel's plants list.
	 * @param n 		PlantView node being dragged.
	 */
	public void addPlantFromCarousel(int index, Node n) {
		plants.add(plantCarousel.removePlant(index));
		garden.getChildren().add(n);
		garden.setAlignment(n, Pos.TOP_LEFT);
		
		int newIndex = plants.size()-1;
		plants.get(newIndex).setFitHeight(plants.get(newIndex).spread/4 + 30);		// Make plantView small to see the spread object placed behind it.
		plants.get(newIndex).setFitWidth(plants.get(newIndex).spread/4 + 30);		// Size slightly affected by spread and has lower limit.
		
		Rectangle fit_template = new Rectangle(
				plants.get(newIndex).spread/4 + 30,
				plants.get(newIndex).spread/4 + 30);
		fit_template.setArcHeight(15);
		fit_template.setArcWidth(15);
		
		plants.get(newIndex).setClip(fit_template);
	}
		
	
	// Getters & Setters
	
	public DragDropCarouselView getPlantCarousel() {
		return this.plantCarousel;
	}
	
	public void setPlantCarousel(DragDropCarouselView carousel) {
		this.plantCarousel = carousel;
	}
	
	public StackPane getGarden() {
		return this.garden;
	}
	
	public void setGarden(StackPane garden) {
		this.garden = garden;
	}
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	public List<Shape> getSpreads() {
		return this.plantSpreads;
	}
	
	public List<Pair<ArrayList<String>, Integer>> getPlantInput() {
		return this.plantInput;
	}
	
	public void setPlantInput(List<Pair<ArrayList<String>, Integer>> input) {
		this.plantInput = input;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public int getBudget() {
		return(this.budget);
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void setX(int index, double x) {
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateX(x);
    }
    
    public void setY(int index, double y) {
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateY(y);
    }

	public void setScaleFactor(double scale_factor) {
		this.scale_factor = scale_factor;	
	}
	
}
