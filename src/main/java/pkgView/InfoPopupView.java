package pkgView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

/**
 * 
 * @author Zane Greenholt
 * Class that organizes and displays the data of a plant which had its image clicked in the info carousel
 */
public class InfoPopupView extends BorderPane{
	
	private final double LEP_SCALING = 0.5;
	/**
	 * Constructor which displays all the data that is passed into the InfoPopupView
	 * 
	 * @param view The program's View which is initialized once
	 * @param img The same image that was clicked
	 * @param name The common name of the plant that had its image clicked
	 * @param sciName The scientific name of the plant that had its image clicked
	 * @param numLeps The number of lep species supported by the plant that had its image clicked
	 * @param dollars The price of the plant that had its image clicked
	 * @param description A short description of the plant that had its image clicked
	 */
	public InfoPopupView(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description, List<String> leps) {
		BackgroundFill bFill = new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(bFill);
		this.setBackground(background);
<<<<<<< HEAD
		VBox vbox1 = new VBox();
		Label title = new Label(sciName + ", also known as: " + name);
		title.setFont(Font.font("Cambria"));
	
		Label info = new Label(description);
		info.setFont(Font.font("Cambria"));
		info.setWrapText(true);
		info.setAlignment(Pos.BASELINE_CENTER);
		
		vbox1.getChildren().addAll(title,info);
		vbox1.setAlignment(Pos.CENTER);
		this.setTop(vbox1);
		
		
		
		
=======
		BackgroundSize bSize = new BackgroundSize(800.0, 550.0, false, false, false, true);
		this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background-flowers.jpg")),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		
		VBox title = new VBox();
		Label nameLabel = new Label(name);
		Label sciNameLabel = new Label(sciName);
		nameLabel.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 24));
		sciNameLabel.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 24));
		nameLabel.setTextFill(Color.WHITE);
		sciNameLabel.setTextFill(Color.WHITE);

		title.getChildren().add(nameLabel);
		title.getChildren().add(sciNameLabel);
		title.setAlignment(Pos.CENTER);
		this.setTop(title);
		this.setAlignment(getTop(), Pos.CENTER);
>>>>>>> 091469956567606b0a94bad04f81e6ce37dc466c
		ImageView img_copy = new ImageView(img.getImage());
		img_copy.setFitHeight(300);
		img_copy.setFitWidth(300);
		Rectangle frame = new Rectangle(300, 300);
		frame.setArcWidth(20);
		frame.setArcHeight(20);
		img_copy.setClip(frame);

		VBox lepBox = new VBox();
		Label lepCount = new Label("Supports " + numLeps + " lep species.");
		ObservableList<VBox> lepNames = FXCollections.observableArrayList();
		HashMap<String, ImageView> lepImages = view.getController().getLepImages();
		for(String lep: leps) {
			ImageView lepImage = null;
			if(lepImages.containsKey(lep)) {
				lepImage = lepImages.get(lep);
				//lepImage.setScaleX(LEP_SCALING);
				//lepImage.setScaleY(LEP_SCALING);
				lepImage.setFitHeight(100);
				lepImage.setFitWidth(100);
				Rectangle lep_img_frame = new Rectangle(lepImage.getFitWidth(), lepImage.getFitHeight());
				lep_img_frame.setArcHeight(20);
				lep_img_frame.setArcWidth(20);
				lepImage.setClip(lep_img_frame);
			}
			Text lepName = new Text(lep);
			lepName.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
			lepName.setTextAlignment(TextAlignment.CENTER);
			VBox box = new VBox();
			if(lepImage != null) {
				box.getChildren().addAll(lepName, lepImage);
			}
			else {
				box.getChildren().add(lepName);
			}
			box.setAlignment(Pos.CENTER);
			lepNames.add(box);
		}
		ListView<VBox> lepSpecies = new ListView<VBox>();
		
		lepSpecies.setItems(lepNames);
		lepBox.getChildren().addAll(lepCount, lepSpecies);
		Label price = new Label("Costs " + dollars + " dollars.");
<<<<<<< HEAD
		lepCount.setFont(Font.font("Cambria"));
		price.setFont(Font.font("Cambria"));
		

		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(img_copy, price,lepBox);
		vbox2.setAlignment(Pos.CENTER);
		this.setCenter(vbox2);

		
=======
		Label info = new Label(description);
		lepCount.setFont(Font.font("Cambria", FontWeight.MEDIUM, 16));
		lepCount.setTextFill(Color.WHITE);
		price.setFont(Font.font("Cambria", FontWeight.MEDIUM, 16));
		price.setTextFill(Color.WHITE);

		info.setFont(Font.font("Cambria", FontWeight.MEDIUM, 20));
		info.setTextFill(Color.WHITE);

		info.setPadding(new Insets(5, 0, 0, 0));
		TilePane tilePane = new TilePane();
		//tilePane.getChildren().addAll(lepBox, img_copy, price);
		VBox vbox = new VBox();
		vbox.setMaxWidth(500);
		vbox.getChildren().add(img_copy);
		vbox.getChildren().add(price);
		vbox.getChildren().add(info);
		vbox.setAlignment(Pos.CENTER);
		tilePane.setAlignment(Pos.CENTER);
		info.setWrapText(true);
		info.setAlignment(Pos.BASELINE_CENTER);
		//this.setCenter(tilePane);
		//this.setBottom(info);
		this.setLeft(vbox);
>>>>>>> 091469956567606b0a94bad04f81e6ce37dc466c
		this.setPadding(new Insets(10));
		this.setRight(lepBox);
		}
	
}