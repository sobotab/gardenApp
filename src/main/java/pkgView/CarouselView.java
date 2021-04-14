package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import pkgController.CarouselController;

public abstract class CarouselView extends FlowPane {
	List<ImageView> filteredImages;
	List<ImageView> images;
	boolean rotateLeft;
	boolean rotateRight;
	List<Button> list;
	int center;
	int x;
	int y;
	
	public CarouselView() {
	}
	
	public void rotateLeft() {
		// I imagine this is where we will use flowpane.add() and .remove() to simulate rotating carousel
		// We can even choose index in .add() so a new plant appears on the left side
		center -= 1;
		if(center < 0) {
			center = filteredImages.size()-1;
		}
		update();
	}
	
	public void rotateRight() {
		center += 1;
		if(center == filteredImages.size()) {
			center = 0;
		}
		update();
	}
	
	List<Point> movePlant() {
		List<Point> points = null;
		return points;
	}
	
	public void update() {
		this.getChildren().removeAll(images);
		int leftMostNode = center-1;
		if(leftMostNode < 0) {
			leftMostNode = filteredImages.size() -1;
		}
		int rightMostNode = center + 1;
		if(rightMostNode == filteredImages.size()) {
			rightMostNode = 0;
		}
		List<Node> sublist = new ArrayList<Node>();
		filteredImages.get(leftMostNode).setScaleX(.75);
		filteredImages.get(leftMostNode).setScaleY(.75);
		sublist.add(filteredImages.get(leftMostNode));
		filteredImages.get(center).setScaleX(1.5);
		filteredImages.get(center).setScaleY(1.5);
		sublist.add(filteredImages.get(center));
		filteredImages.get(rightMostNode).setScaleX(.75);
		filteredImages.get(rightMostNode).setScaleY(.75);;
		sublist.add(filteredImages.get(rightMostNode));
		this.getChildren().addAll(1,sublist);
	}
	
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
	
	public List<ImageView> getFilteredImages(){
		return filteredImages;
	}
	
	public void setFilteredImages(List<ImageView> filteredImages) {
		this.filteredImages = filteredImages;
	}
}
