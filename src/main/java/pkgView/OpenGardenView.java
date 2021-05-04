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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pkgController.OpenGardenController;

public class OpenGardenView extends BorderPane{
	TableView gardenTable;
	
	public OpenGardenView(View view) {
		gardenTable = new TableView();
		TableColumn<Map, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new MapValueFactory<>("name"));
		nameColumn.setPrefWidth(200);
		
		TableColumn<Map, String> budgetColumn = new TableColumn<>("Budget");
		budgetColumn.setCellValueFactory(new MapValueFactory<>("budget"));
		
		//TableColumn<Map, String> currentBudgetColumn = new TableColumn<>("Current Budget");
		//currentBudgetColumn.setCellValueFactory(new MapValueFactory<>("budget"));
		
		//TableColumn<Map, String> maxBudgetColumn = new TableColumn<>("Max Budget");
		//maxBudgetColumn.setCellValueFactory(new MapValueFactory<>("budget"));
		
		TableColumn<Map, String> lepColumn = new TableColumn<>("Leps Supported");
		lepColumn.setCellValueFactory(new MapValueFactory<>("leps"));
		
		//budgetColumn.getColumns().addAll(currentBudgetColumn, maxBudgetColumn);
		
		gardenTable.getColumns().addAll(nameColumn, budgetColumn, lepColumn);
		
		OpenGardenController ogc = new OpenGardenController(view, this);
		
		Label title = new Label("Open Garden");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Cambria", 50));
		
		Button back = new Button("Back");
		Button open = new Button("Open");
		
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
    	
	}
	
	public void prepareListView(ObservableList<HashMap<String, Object>> gardenDataContainer) {		
		gardenTable.getItems().addAll(gardenDataContainer);
	}
	
	// getters & setters
	public TableView getGardenTable() {
		return this.gardenTable;
	}
	public void setgardenTable(TableView table) {
		this.gardenTable = table;
	}
}
