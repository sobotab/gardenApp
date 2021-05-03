package pkgView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * 
 * @author Zane Greenholt
 * Class that organizes and displays the data of a plant which had its image clicked in the info carousel
 */
public class InfoPopupView extends BorderPane{
	
	/**
	 * Constructor which displays all the data that is passed into the InfoPopupView
	 * @param view The program's View which is initialized once
	 * @param img The same image that was clicked
	 * @param name The common name of the plant that had its image clicked
	 * @param sciName The scientific name of the plant that had its image clicked
	 * @param numLeps The number of lep species supported by the plant that had its image clicked
	 * @param dollars The price of the plant that had its image clicked
	 * @param description A short description of the plant that had its image clicked
	 */
	public InfoPopupView(ImageView img, String name, String sciName, int numLeps, int dollars, String description) {
		BackgroundFill bFill = new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(bFill);
		this.setBackground(background);
		Label title = new Label(sciName + ", also known as: " + name);
		title.setFont(Font.font("Cambria"));
		this.setTop(title);
		this.setAlignment(getTop(), Pos.CENTER);
		ImageView img_copy = new ImageView(img.getImage());
		Rectangle frame = new Rectangle(150, 150);
		frame.setArcWidth(20);
		frame.setArcHeight(20);
		img_copy.setClip(frame);
		Label leps = new Label("Supports " + numLeps + " lep species.");
		Label price = new Label("Costs " + dollars + " dollars.");
		Label info = new Label(description);
		leps.setFont(Font.font("Cambria"));
		price.setFont(Font.font("Cambria"));
		info.setFont(Font.font("Cambria"));
		TilePane tilePane = new TilePane();
		tilePane.getChildren().addAll(leps, img_copy, price);
		tilePane.setAlignment(Pos.CENTER);
		info.setWrapText(true);
		info.setAlignment(Pos.CENTER);
		this.setCenter(tilePane);
		this.setBottom(info);
		this.setPadding(new Insets(10));
		}
	
}
