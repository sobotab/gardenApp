package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import pkgController.DrawGardenController;

public class DrawGardenView extends BorderPane {
	int userX;
	int userY;
	
	public DrawGardenView(View view) {
		DrawGardenController dgc = new DrawGardenController(view);
		
		Button back = new Button("Back");
		back.setOnAction(dgc.getHandlerForBack());
		
		this.setBottom(back);
	}
	
	public List<Point> draw() {
		List<Point> points = null;
		return points;
	}
	
	// getters
	public int getUserX() {
		return this.userX;
	}
	
	public int getUserY() {
		return this.userY;
	}
	
	// setters
	public void setUserX(int x) {
		this.userX = x;
	}
	
	public void setUserY(int y) {
		this.userY = y;
	}
	
}
