package pkgView;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Ryan Dean
 * View class for pop-up in Edit Garden screen displaying full list of lep species supported by the garden.
 */
public class LepPopupView extends ListView {
	/**
	 * Constructor for this class takes in list of lep species and builds listView of them sorted by number
	 * of plants supporting them.
	 * @param sortedLeps 	List of Map.Entry containing name of lep species (key) and number of plants supporting them (value).
	 */
	public LepPopupView(ArrayList<Map.Entry<String, Integer>> sortedLeps) {
		for (Map.Entry<String, Integer> lepEntry : sortedLeps) {
			//this.getChildren().add(buildLepDisplay(lepEntry));
			this.getItems().add(buildLepDisplay(lepEntry));
		}
	}
	
	/**
	 * Method used by constructor to build a tilepane for each lep species. Each tilepane includes image of lep,
	 * scientific name, and number of plants supporting it.
	 * @param lepEntry 		Key-Value pair of lep species name and number of plants supporting it.
	 * @return 				TilePane with information for an individual lep species.
	 */
	public FlowPane buildLepDisplay(Map.Entry<String, Integer> lepEntry) {
		ImageView lep_img = new ImageView(new Image("/images/background-flowers.jpg"));
		lep_img.setFitHeight(100);
		lep_img.setFitWidth(100);
		Rectangle img_template = new Rectangle(lep_img.getFitWidth(), lep_img.getFitHeight());
		img_template.setArcHeight(15);
		img_template.setArcWidth(15);
		lep_img.setClip(img_template);
		
		VBox lep_text_box = new VBox();
		Label lep_name = new Label(lepEntry.getKey());
		lep_name.setFont(Font.font("Roboto", FontWeight.BOLD, 24));
		Label lep_details = new Label("Supported by " + lepEntry.getValue() + " of your plants.");
		lep_details.setFont(Font.font("Roboto", FontWeight.MEDIUM, 24));
		lep_text_box.getChildren().addAll(lep_name, lep_details);
		
		//HBox lepBox = new HBox();
		FlowPane lepBox = new FlowPane();
		lepBox.getChildren().addAll(lep_img, lep_text_box);
		lepBox.setAlignment(Pos.BASELINE_LEFT);
		lepBox.setHgap(10);
		
		return lepBox;
	}
}