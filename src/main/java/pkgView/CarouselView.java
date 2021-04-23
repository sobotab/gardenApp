package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
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
	private final double CENTER_IMAGE_SCALING = 1.3;
	private final double SIDE_IMAGE_SCALING = .75;
	
	public CarouselView() {
	}
	
	public void rotateLeft() {
		if(filteredImages.size() > 0) {
			center -= 1;
			if(center < 0) {
				center = filteredImages.size()-1;
			}
			update();
		}
	}
	
	public void rotateRight() {
		center += 1;
		if(center >= filteredImages.size()) {
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
		List<Node> sublist = new ArrayList<>();
		if(filteredImages.size() >= 3) {
			int leftMostNode = center-1;
			if(leftMostNode < 0) {
				leftMostNode = filteredImages.size() - 1;
			}
			int rightMostNode = center + 1;
			if(rightMostNode >= filteredImages.size()) {
				rightMostNode = 0;
			}
			sublist = makeFullCarousel(leftMostNode, rightMostNode);
			this.setHgap(10.0);
		}
		else if (filteredImages.size() >= 1) {
			sublist = makeSmallCarousel();
			this.setHgap(50.0);
		}
    
    
		this.getChildren().addAll(1,sublist);
		
	}
	
	private List<Node> makeFullCarousel(int leftMostNode, int rightMostNode) {
		List<Node> sublist = new ArrayList<Node>();
		
		ImageView left = filteredImages.get(leftMostNode);
		ImageView middle = filteredImages.get(center);
		ImageView right = filteredImages.get(rightMostNode);
		
		left.setScaleX(SIDE_IMAGE_SCALING);
		left.setScaleY(SIDE_IMAGE_SCALING);
		sublist.add(left);
		
		middle.setScaleX(CENTER_IMAGE_SCALING);
		middle.setScaleY(CENTER_IMAGE_SCALING);
		sublist.add(middle);
		
		right.setScaleX(SIDE_IMAGE_SCALING);
		right.setScaleY(SIDE_IMAGE_SCALING);;
		sublist.add(right);
		
		return sublist;
	}
	
	private List<Node> makeSmallCarousel(){
		List<Node> sublist = new ArrayList<>();
		ImageView middle = filteredImages.get(center);
		
		middle.setScaleX(CENTER_IMAGE_SCALING);
		middle.setScaleY(CENTER_IMAGE_SCALING);
		sublist.add(middle);
		
		return sublist;
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
		if(center >= filteredImages.size()) {
			if(filteredImages.size() == 0)
				center = 0;
			else {
				center = filteredImages.size() - 1;
			}
		}
	}

	public int getCenter() {
		return center;
	}

	public void setCenter(int center) {
		this.center = center;
	}
}
