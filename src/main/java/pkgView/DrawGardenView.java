package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.layout.BorderPane;

public class DrawGardenView extends BorderPane {
	int userX;
	int userY;
	
	public DrawGardenView() {}
	
	public List<Point> draw() {}
	
	// getters & setters
	public int getUserX() {
		return this.userX;
	}
	public int getUserY() {
		return this.userY;
	}
	public void setUserX(int x) {
		this.userX = x;
	}
	public void setUserY(int y) {
		this.userY = y;
	}
}
