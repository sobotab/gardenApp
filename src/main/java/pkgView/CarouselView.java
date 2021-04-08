package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public abstract class CarouselView extends FlowPane {
	List<ImageView> images;
	boolean rotateLeft;
	boolean rotateRight;
	int x;
	int y;
	
	public CarouselView() {}
	
	void rotateLeft() {
		// I imagine this is where we will use flowpane.add() and .remove() to simulate rotating carousel
		// We can even choose index in .add() so a new plant appears on the left side
	}
	
	void rotateRight() {}
	
	List<Point> movePlant() {}
	
	// getters
	public List<ImageView> getImages() {
		return this.images;
	}
	
	public boolean isRotateLeft() {
		return this.rotateLeft;
	}
	
	public boolean isRotateRight() {
		return this.rotateRight;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	// setters
	public void setImages(List<ImageView> images) {
		this.images = images;
	}
	
	public void setRotateLeft(boolean rotate) {
		this.rotateLeft = rotate;
	}
	
	public void setRotateRight(boolean rotate) {
		this.rotateRight = rotate;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
