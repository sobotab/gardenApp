package pkgView;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import pkgController.ResourcesController;

public class ResourcesView extends BorderPane{
	
	public ResourcesView(View view) {
		ResourcesController rc = new ResourcesController(view);
		
		Button back = new Button("Back");
		back.setOnAction(rc.getHandlerForBack());
		
		this.setBottom(back);
	}
	
}
