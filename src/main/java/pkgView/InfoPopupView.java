package pkgView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

public class InfoPopupView extends BorderPane{
	
	public InfoPopupView(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description) {
		Label title = new Label(sciName + ", also known as: " + name);
		this.setTop(title);
		this.setAlignment(getTop(), Pos.CENTER);
		ImageView img_copy = new ImageView(img.getImage());
		this.setCenter(img_copy);
		Label leps = new Label("Supports " + numLeps + " lep species.");
		Label price = new Label("Costs " + dollars + " dollars.");
		Label info = new Label(description);
		HBox box = new HBox();
		box.setSpacing(10);
		box.getChildren().addAll(leps,info,price);
		box.setAlignment(Pos.CENTER);
		this.setBottom(box);
		this.setPadding(new Insets(10));
		}
	
}
