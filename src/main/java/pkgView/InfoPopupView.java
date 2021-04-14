package pkgView;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;

public class InfoPopupView extends BorderPane{
	
	public InfoPopupView(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description) {
		Label title = new Label(sciName + ", also known as: " + name);
		this.setTop(title);
	}
	
}
