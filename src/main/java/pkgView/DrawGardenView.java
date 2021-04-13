package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pkgController.DrawGardenController;

public class DrawGardenView extends BorderPane {
	int userX;
	int userY;
	
	public DrawGardenView(View view) {
		DrawGardenController dgc = new DrawGardenController(view);
		
		Button back = new Button("Back");
		back.setOnAction(dgc.getHandlerForBack());
		Button finish = new Button("Finish");
		finish.setOnAction(dgc.getHandlerForNext());
		
		Label title = new Label("Draw Garden");
		
		this.setTop(title);
		this.setBottom(back);
		this.setCenter(finish);
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
