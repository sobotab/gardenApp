package pkgView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pkgController.ResourcesController;

public class ResourcesView extends BorderPane{
	
	public ResourcesView(View view) {
		ResourcesController rc = new ResourcesController(view);
		
		Label title = new Label("Resources");
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		
		this.setBottom(back);
		this.setTop(title);
	}
	
}
