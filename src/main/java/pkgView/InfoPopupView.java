package pkgView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	public InfoPopupView(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description) {
		Label title = new Label(sciName + ", also known as: " + name);
		this.setTop(title);
		this.setAlignment(getTop(), Pos.CENTER);
		ImageView img_copy = new ImageView(img.getImage());
		//this.setCenter(img_copy);
		Label leps = new Label("Supports " + numLeps + " lep species.");
		Label price = new Label("Costs " + dollars + " dollars.");
		Label info = new Label(description);
		info.setWrapText(true);
		info.setAlignment(Pos.CENTER);
//		HBox box = new HBox();
//		box.setSpacing(10);
//		box.getChildren().addAll(leps,img_copy,price);
//		box.setAlignment(Pos.CENTER);
		this.setRight(price);
		this.setLeft(leps);
		this.setCenter(img_copy);
		this.setBottom(info);
		this.setPadding(new Insets(10));
		}
	
}
