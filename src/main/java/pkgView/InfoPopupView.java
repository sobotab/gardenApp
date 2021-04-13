package pkgView;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;

public class InfoPopupView extends BorderPane{
	
	public InfoPopupView(View view) {
		Label title = new Label("Information about plant");
		this.setTop(title);
	}
	
}
