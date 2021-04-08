package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.layout.BorderPane;

public class DrawGardenView extends BorderPane {
	int userX;
	int userY;
	
	public DrawGardenView() {}
	
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
