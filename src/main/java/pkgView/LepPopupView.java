package pkgView;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LepPopupView extends ListView {
	
	public LepPopupView(ArrayList<Map.Entry<String, Integer>> sortedLeps) {
		/*
		Image background_image = new Image(getClass().getResourceAsStream("/images/background-flowers.jpg"));
		BackgroundSize bSize = new BackgroundSize(800.0, 500.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(background_image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		*/
		for (Map.Entry<String, Integer> lepEntry : sortedLeps) {
			//this.getChildren().add(buildLepDisplay(lepEntry));
			this.getItems().add(buildLepDisplay(lepEntry));
		}
	}
	
	TilePane buildLepDisplay(Map.Entry<String, Integer> lepEntry) {
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
		TilePane lepBox = new TilePane();
		lepBox.getChildren().addAll(lep_img, lep_text_box);
		//lepBox.setAlignment(Pos.CENTER_LEFT);
		
		return lepBox;
	}
}