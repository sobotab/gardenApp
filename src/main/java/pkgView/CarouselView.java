
package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgController.CarouselController;

/**
 * 
 * @author - Zane Greenholt
 * CarouselView class holds the basic functionality of a carousel object. These carousels display limited ranges of a List
 * at one time, and the List can be traversed by using the left and right buttons. As the name suggests, the carousels can be imagined
 * as circular and loop back around to the start of the list when the end is reached.
 *
 */

public abstract class CarouselView extends FlowPane {
	/**
	 * A list of images that should be in the carousel after filtering
	 */
	List<VBox> filteredImages;
	/**
	 * The list of all plants' images in the program
	 */
	List<VBox> images;
	/**
	 * int representing the index of the image in the center of the carousel's focus
	 */
	int center;
	/**
	 * Image scaling for the image in the center of the carousel's focus
	 */
	private final double CENTER_IMAGE_SCALING = 2.0;
	/**
	 * Image scaling for the image on the sides of the carousel's focus.
	 */
	private final double SIDE_IMAGE_SCALING = 1.3;
	
	/**
	 * CarouselView is abstract and each child has different implementations so the constructor initializes nothing.
	 */
	public CarouselView() {
	}
	
	/**
	 * Method that is called when the left button in a carousel is pressed.
	 * This will shift the focus of the carousel one index to the left, or to the highest index if the index becomes negative.
	 */
	public void rotateLeft() {
		if(filteredImages.size() > 0) {
			center -= 1;
			if(center < 0) {
				center = filteredImages.size()-1;
			}
			update();
		}
	}
	/**
	 * Method that is called when the right button in a carousel is pressed.
	 * This will shift the focus of the carousel one index to the right, or to zero if the index surpasses the list size.
	 */
	public void rotateRight() {
		center += 1;
		if(center >= filteredImages.size()) {
			center = 0;
		}
		update();
	}
	
	/**
	 * Part of the handler for opening a popup screen when an image is clicked. It has plant data passed into it from the controller to be displayed.
	 * 
	 * @param view The program's view that is only initialized once
	 * @param img The same image that was clicked
	 * @param name The common name of the plant that had its image clicked
	 * @param sciName The scientific name of the plant that had its image clicked
	 * @param numLeps The number of lep species supported by the plant that had its image clicked
	 * @param dollars The price of the plant that had its image clicked
	 * @param description A short description of the plant that had its image clicked
	 * @param leps A list of scientific names of the leps supported by a host plant
	 */
	public void openInfoPopUp(View view, ImageView img, String name, String sciName, int numLeps, int dollars, String description, List<String> leps) {
		Stage popupWindow = new Stage();
		popupWindow.initModality(Modality.NONE);
		popupWindow.setScene(new Scene(new InfoPopupView(view, img, name, sciName, numLeps, dollars, description, leps),800,500));
		popupWindow.setAlwaysOnTop(true);
		popupWindow.show(); 
		}
	
	/**
	 * The update method properly displays the VBox's that are held in the carouselView.
	 * This method is called any time the carousel is rotated or the list of VBox's is altered, so that
	 * the user can see the correct images at all times.
	 */
	public void update() {
		
		this.getChildren().removeAll(images);
		List<VBox> sublist = new ArrayList<>();
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
			this.setHgap(120.0);
		}
		else if (filteredImages.size() >= 1) {
			sublist = makeSmallCarousel();
			this.setHgap(90.0);
		}
    
    
		this.getChildren().addAll(1,sublist);
		
	}
	
	/**
	 * A helper method for the update method. This method places nodes correctly in the carousel when the size of the carousel is 
	 * at least three nodes.
	 * 
	 * @param leftMostNode An int that represents the index of the node to the left of the node in the center of the carousel's focus.
	 * @param rightMostNode An int that represents the index of the node to the right of the ndoe in the center of the carousel's focus.
	 * @return A list of VBox's that contains a subset of the nodes in CarouselView's filteredImages. This subset of size 3 will be displayed.
	 */
	private List<VBox> makeFullCarousel(int leftMostNode, int rightMostNode) {
		List<VBox> sublist = new ArrayList<VBox>();
		
		VBox left = filteredImages.get(leftMostNode);
		VBox middle = filteredImages.get(center);
		VBox right = filteredImages.get(rightMostNode);
		
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
	/**
	 * A helper method for the update method. This method places nodes correctly in the carousel when the size of the carousel is less than three nodes.
	 * 
	 * @return A List of VBox's that contains a subset of the nodes in CarouselView's filteredImages. This subset of size 1 will be displayed.
	 */
	private List<VBox> makeSmallCarousel(){
		List<VBox> sublist = new ArrayList<>();
		VBox middle = filteredImages.get(center);
		
		middle.setScaleX(CENTER_IMAGE_SCALING);
		middle.setScaleY(CENTER_IMAGE_SCALING);
		sublist.add(middle);
		
		return sublist;
	}
	
	// getters
	/**
	 * Getter for images field
	 * 
	 * @return images field - a List of VBox's
	 */
	public List<VBox> getImages() {
		return this.images;
	}

	/**
	 * Setter for images field
	 * 
	 * @param images A list of VBox's that will replace the current images field
	 */
	public void setImages(List<VBox> images) {
		this.images = images;
	}
	
	/**
	 * Getter for filteredImages field
	 * 
	 * @return filteredImages field - A List of VBox's filtered by the users choices for soil, sun, moisture, and plant type
	 */
	public List<VBox> getFilteredImages(){
		return filteredImages;
	}
	
	/**
	 * Setter for filteredImages field. This also updated the center if the size of filteredImages is zero
	 * 
	 * @param filteredImages A List of VBox's that will replace the current filteredImages field
	 */
	public void setFilteredImages(List<VBox> filteredImages) {
		this.filteredImages = filteredImages;
		if(center >= filteredImages.size()) {
			if(filteredImages.size() == 0)
				center = 0;
			else {
				center = filteredImages.size() - 1;
			}
		}
	}
	/**
	 * Getter for center field
	 * 
	 * @return center field - int representing index of image focused on by the carousel
	 */
	public int getCenter() {
		return center;
	}
	
	/**
	 * Setter for center field
	 * 
	 * @param center An int that will replace the current center index
	 */
	public void setCenter(int center) {
		this.center = center;
	}
}
