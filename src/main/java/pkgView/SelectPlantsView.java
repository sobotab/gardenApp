package pkgView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pkgController.SelectPlantsController;
import pkgController.Soil;

/**
 * 
 * @author Zane Greenholt
 * Class that contains and displays a selectCarouselView as well as a list of selected plants. This class represents the entire select plants screen.
 */
public class SelectPlantsView extends BorderPane {
	/**
	 * A SelectCarouselView with plant images that can be clicked on to be selected
	 */
	SelectCarouselView selectionCarousel; 
	/**
	 * The controller for the selectPlants screen
	 */
	SelectPlantsController spc;
	/**
	 * A list of selected plant images
	 */
	List<ImageView> selectedPlants;
	/**
	 * A flowpane that will display the select plants and allow them to be deselected
	 */
	FlowPane plants;
	/**
	 * The program's View that is only initialized once
	 */
	View view;
	/**
	 * Scaling of a plant's size that should be used when the plant is selected
	 */
	private final double SELECTED_PLANT_SCALING = 0.75;
	/**
	 * Preferred width of the ComboBoxes used for filtering
	 */
	private final double FILTER_WIDTH = 200.0;
	/**
	 * Min height of the ComboBoxes used for filtering
	 */
	private final double FILTER_HEIGHT = 75.0;
	/**
	 * Listview of VBoxes that displays the plants that have been selected
	 */
	ListView<VBox> plantsSelected;
	/**
	 * Text that displays the number of plants currently shown in the filtered carousel
	 */
	Text numPlants;
	
	/**
	 * Constructor for SelectPlantsView that initializes the SelectPlantsCarousel and all other necessary buttons and images for the select plants screen.
	 * @param view The program's View that is only initialized once
	 */
	public SelectPlantsView(View view) {
		this.view = view;
		selectionCarousel = new SelectCarouselView(view);
		selectedPlants = new ArrayList<ImageView>();
		spc = new SelectPlantsController(view, this, selectionCarousel.getScc());
		numPlants = new Text("");
		numPlants.setFont(Font.font("cambria"));
		numPlants.setFill(Color.WHITE);
		numPlants.setFont(Font.font("Cambria", 40));
		updateNumPlants();
		
		String buttonStyle = "-fx-font-size:" + Double.valueOf(18).toString() +";"
				+ " -fx-font-weight: bold;"
				+ "-fx-background-color: linear-gradient(#fafafa , #afd9f5 );"
				+ "-font-family: Helvetica";
		
		for(VBox image: selectionCarousel.getFilteredImages()) {
			if(image.getChildren().size() == 6) {
				image.getChildren().remove(5);
			}
			Button add = new Button("Add");
			add.setOnMouseClicked(spc.getHandlerForPlantSelected());
			add.setStyle("-fx-font-size:" + Double.valueOf(15).toString() +";"
					+ " -fx-font-weight: bold;"
					+ "-fx-background-color: linear-gradient(#fafafa , #afd9f5 );"
					+ "-font-family: Helvetica");
			add.setPrefSize(60.0, 25.0);
			image.getChildren().add(add);
			image.setOnMousePressed(selectionCarousel.getScc().getHandlerForPopup());
		}
		
		BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-blurred.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		

		
		List<String> soils = selectionCarousel.getScc().getSoil();
		ComboBox<String> soil = new ComboBox();
		soil.setPromptText("Soil Type");
		ObservableList<String> options = FXCollections.observableArrayList("");
		options.addAll(soils);
		soil.setItems(options);
		soil.setPrefWidth(FILTER_WIDTH);
		soil.setStyle(buttonStyle);

		
		ComboBox<String> type = new ComboBox();
		type.setPromptText("Plant Type");
		type.setItems(FXCollections.observableArrayList("","woody","herbaceous"));
		type.setPrefWidth(FILTER_WIDTH);
		type.setStyle(buttonStyle);
		
		String sun = selectionCarousel.getScc().getSun();
		String moisture = selectionCarousel.getScc().getMoisture();
		
		Button filter = new Button("FILTER");
		filter.setPrefSize(FILTER_WIDTH, 50);
		filter.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		filter.setStyle("-fx-background-color: linear-gradient(#fafafa , #afd9f5 );");
		filter.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				String soilType = "";
				if(soils.size() > 1) {
					soilType = soil.getValue();
				}
				String plantType = type.getValue();
				selectionCarousel.filter(plantType, soilType, sun, moisture, soils);
				updateNumPlants();
			}
		});
		

		
		Label title = new Label("Plant Select");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 80));
		title.setPadding(new Insets(10,5,10,5));
		
		VBox filterBox = new VBox();
		if(soils.size() > 1) {
			filterBox.getChildren().addAll(type,soil,filter, numPlants);
		}
		else {
			filterBox.getChildren().addAll(type,filter, numPlants);
		}
		HBox filterAndTitle = new HBox(title,filterBox);
		filterAndTitle.setPadding(new Insets(10,5,10,5));
		
		
		Button back = new Button("<<<");
		back.setOnAction(spc.getHandlerForBack());
		back.setStyle(buttonStyle);
		Button finish = new Button(">>>");
		finish.setOnAction(spc.getHandlerForNext());
		finish.setStyle(buttonStyle);
		
		Label selectedPlantsTitle = new Label("Selected Plants");
		selectedPlantsTitle.setTextFill(Color.WHITE);
		selectedPlantsTitle.setFont(Font.font("Cambria", 30));
		
		plantsSelected = new ListView(FXCollections.observableArrayList());
		plantsSelected.setPrefHeight(view.getTheStage().getHeight());
		
		VBox plantsSelectedBox = new VBox();
		plantsSelectedBox.getChildren().addAll(selectedPlantsTitle, plantsSelected);
		plantsSelectedBox.setMinHeight(this.getHeight());
		
		HBox box = new HBox();
		box.getChildren().add(back);
		box.getChildren().add(finish);
		box.setPrefWidth(this.getWidth());
		box.setPadding(new Insets(10,5,10,5));	
		
		this.setRight(plantsSelectedBox);
		this.setTop(filterAndTitle);
		this.setBottom(box);
		this.setCenter(selectionCarousel);
		
	}
	
	/**
	 * Method that moves a plant's image from the carousel to the plants flowPane when the add button is clicked. This also creates a remove button
	 * with an OnMouseClicked handler to be ready for deselection if clicked again.
	 * @param box A VBox that was clicked in order to be selected from the carousel
	 */
	public void selectPlant(VBox box) {
		ImageView imv = (ImageView)box.getChildren().get(1);
		box.setScaleX(SELECTED_PLANT_SCALING);
		box.setScaleY(SELECTED_PLANT_SCALING);
		selectedPlants.add(imv);
		plantsSelected.getItems().add(box);
		box.getChildren().remove(5);
		Button remove = new Button("Remove");
		remove.setOnMouseClicked(spc.getHandlerForPlantDeSelected());
		remove.setStyle("-fx-font-size:" + Double.valueOf(15).toString() +";"
				+ " -fx-font-weight: bold;"
				+ "-fx-background-color: linear-gradient(#fafafa , #afd9f5 );"
				+ "-font-family: Helvetica");
		remove.setPrefSize(100.0, 25.0);
		box.getChildren().add(remove);
	}
	
	/**
	 * Method that moves a plant's image from the plants flowPane to the selectionCarousel when its remove button is clicked. This also creates an add button
	 * with an OnMouseClicked handler to be ready for selection if clicked again.
	 * @param box A VBox that was clicked in order to be deselected from the plants flowPane
	 */
	public void deSelectPlant(VBox box) {
		ImageView imv = (ImageView)box.getChildren().get(1);
		selectedPlants.remove(imv);
		plantsSelected.getItems().remove(box);
		box.getChildren().remove(5);
		Button add = new Button("Add");
		add.setOnMouseClicked(spc.getHandlerForPlantSelected());
		add.setStyle("-fx-font-size:" + Double.valueOf(15).toString() +";"
				+ " -fx-font-weight: bold;"
				+ "-fx-background-color: linear-gradient(#fafafa , #afd9f5 );"
				+ "-font-family: Helvetica");
		add.setPrefSize(60.0, 25.0);
		box.getChildren().add(add);
		
	}
	
	/**
	 * Updates the text displaying the number of plants shown based on the current number of plants after filtering
	 */
	public void updateNumPlants() {
		numPlants.setText("Plants shown: " + selectionCarousel.getFilteredImages().size());
	}
	
	// getters & setters
	/**
	 * Getter for the selectionCarousel field
	 * @return selectionCarousel field - A SelectCarouselView that displays plant images that can be clicked to be selected
	 */
	public SelectCarouselView getSelectionCarousel() {
		return this.selectionCarousel;
	}
	
	/**
	 * Setter for the selectionCarousel field
	 * @param carousel A SelectCarouselView that will replace the current selectionCarousel field
	 */
	public void setSelectionCarousel(SelectCarouselView carousel) {
		this.selectionCarousel = carousel;
	}
	
}
