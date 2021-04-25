package pkgView;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import pkgController.SelectCarouselController;

public class SelectCarouselView extends CarouselView{	
	// I don't see what this carousel does that isn't inherited from CarouselView.
	
	SelectCarouselController scc;
	
	public SelectCarouselView(View view) {
		scc = new SelectCarouselController(view, this);
		this.setHgap(10);
		String sun = "full sun";
		String moisture = "";
		String soil = "";
		
		images = scc.getImagesFromList();
		//for(ImageView image: images) {
			//image.setOnMousePressed(scc.getHandlerForPlantSelected());
		//}
		
		Button left = new Button("<<<");
		Button right = new Button(">>>");
		left.setOnAction(scc.getHandlerForClickedLeft());
		right.setOnAction(scc.getHandlerForClickedRight());
		
		this.getChildren().add(left);
		this.getChildren().add(right);
		this.filter(sun, moisture, soil);
		this.setAlignment(Pos.CENTER);
	}
	
	public void filter(String sun, String moisture, String soil) {
		scc.filterCarousel(sun, moisture, soil);
	}

	public SelectCarouselController getScc() {
		return scc;
	}

	public void setScc(SelectCarouselController scc) {
		this.scc = scc;
	}
	
	public void decrementCenter() {
		center--;
		if(center < 0) {
			center++;
		}
	}
	
	
}
