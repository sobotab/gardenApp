package pkgView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import pkgController.OpenGardenController;

/**
 * 
 * @author Ryan Dean
 * The view class for the Open Garden screen. Consists of listView containing saved gardens.
 */
public class OpenGardenView extends BorderPane{
	/**
	 * TableView used to hold  the names/budget/leps of saved gardens.
	 */
	TableView gardenTable;
	
	/**
	 * Constructor creates TableView to hold information on all saved gardens, prepares for data entry from controller.
	 * @param view 		View class of the program. Initialized once.
	 */
	public OpenGardenView(View view) {
		
		gardenTable = new TableView();
		//gardenTable.setPrefWidth(view.getTheStage().getWidth() - 50);
		gardenTable.setPrefWidth(view.SCENEWIDTH - 40);
		gardenTable.setStyle("-fx-font-size:16px;");
		
		TableColumn<Map, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new MapValueFactory<>("name"));
		nameColumn.setPrefWidth(gardenTable.getPrefWidth() / 2);
		

		TableColumn<Map, String> budgetColumn = new TableColumn<>("Budget");
		budgetColumn.setCellValueFactory(new MapValueFactory<>("budget"));
		budgetColumn.setPrefWidth(gardenTable.getPrefWidth() / 4);

		TableColumn<Map, String> currentBudgetColumn = new TableColumn<>("Current");
		currentBudgetColumn.setCellValueFactory(new MapValueFactory<>("current budget"));
		currentBudgetColumn.setPrefWidth(gardenTable.getPrefWidth() / 8);

		TableColumn<Map, String> maxBudgetColumn = new TableColumn<>("Max");
		maxBudgetColumn.setCellValueFactory(new MapValueFactory<>("max budget"));
		maxBudgetColumn.setPrefWidth(gardenTable.getPrefWidth() / 8);

		budgetColumn.getColumns().addAll(currentBudgetColumn, maxBudgetColumn);
		
		
		TableColumn<Map, String> lepColumn = new TableColumn<>("Leps Supported");
		lepColumn.setCellValueFactory(new MapValueFactory<>("leps"));
		lepColumn.setPrefWidth(gardenTable.getPrefWidth() / 4);
				
		gardenTable.getColumns().addAll(nameColumn, budgetColumn, lepColumn);
		
		OpenGardenController ogc = new OpenGardenController(view, this);
		
		Label title = new Label("Open Garden");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 50));
		
		ImageView back_img = new ImageView(new Image("/images/back-icon.png"));
		back_img.setFitHeight(50);
		back_img.setPreserveRatio(true);
		back_img.setRotationAxis(Rotate.Y_AXIS);
		back_img.setRotate(180);

		Button back = new Button();
		back.setPrefSize(40, 40);
		back.setGraphic(back_img);
		
		ImageView open_img = new ImageView(new Image("/images/open-icon.png"));
		open_img.setFitHeight(50);
		open_img.setPreserveRatio(true);
		Button open = new Button();
		open.setPrefSize(100, 50);
		open.setGraphic(open_img);

		
		back.setOnAction(ogc.getHandlerForBack());
		open.setOnAction(ogc.getHandlerForOpen());
		
		
		VBox vBox = new VBox(gardenTable, open);
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(vBox);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getCenter(), new Insets(10, 10, 10, 10));
    	
    	BackgroundSize bSize = new BackgroundSize(600.0, 800.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-leaves.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
		// Resize table columns if the window is resized
		
		gardenTable.widthProperty().addListener((obs, oldVal, newVal) -> {
    		if ((double)newVal != 0.0) {

    			nameColumn.setPrefWidth(gardenTable.getWidth() / 2);
    			currentBudgetColumn.setPrefWidth(gardenTable.getWidth() / 8);
    			maxBudgetColumn.setPrefWidth(gardenTable.getWidth() / 8);
    			lepColumn.setPrefWidth(gardenTable.getWidth() / 4);

    			System.out.println(oldVal + " " + (double)oldVal);
    		}
    	 });
    	
	}
	
	// Getters & Setters
	
	public TableView getGardenTable() {
		return this.gardenTable;
	}
	public void setgardenTable(TableView table) {
		this.gardenTable = table;
	}
}
