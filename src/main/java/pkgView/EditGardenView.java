package pkgView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import pkgController.EditGardenController;

public class EditGardenView extends BorderPane{
	int userX;
	int userY;
	boolean clicked;
	DragDropCarouselView plantCarousel;
	FlowPane garden;
	List<PlantView> plants;
	List<Point> gardenOutline;
	//TilePane plantSelection;
	EditGardenController egc;
	
	public EditGardenView(View view) {	
		egc = new EditGardenController(view, this);
		
		Label title = new Label("Edit Garden");
		Button back = new Button("Back to plant select");
		back.setOnAction(egc.getHandlerForBack());
		Button save = new Button("Save");
		Button exit = new Button("Exit");
		exit.setOnAction(egc.getHandlerForExit());
		
		this.plants = new ArrayList<PlantView>();
		
		HBox hBox = new HBox();
		hBox.setPrefSize(100,200);
		hBox.getChildren().addAll(title, back, save, exit);
		hBox.setMaxSize(300, 50);
		
		// temp list<point>, draws a circle
		gardenOutline = new ArrayList<Point>();
		gardenOutline.add(new Point(180, 0));
		gardenOutline.add(new Point(0, 180));
		gardenOutline.add(new Point(180, 360));
		gardenOutline.add(new Point(360, 180));
		gardenOutline.add(new Point(300, 50));
		gardenOutline.add(new Point(180, 0));

		
    	PlantView pv1 = new PlantView();
    	Image im1 = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
    	pv1.setImage(im1);
    	pv1.setPreserveRatio(true);
    	pv1.setFitHeight(80);
    	plants.add(pv1);
    	
    	Canvas background = new Canvas(400, 400);
    	GraphicsContext gc = background.getGraphicsContext2D();
    	gc.setFill(Color.LIGHTGREEN);
    	
    	// Drawing garden outline based on list<point>
    	int j;
    	gc.setStroke(Color.DARKGREEN);
    	gc.beginPath();
    	for (j = 0; j < gardenOutline.size(); j++)
    		gc.lineTo(gardenOutline.get(j).getX(), gardenOutline.get(j).getY());
    		gc.stroke();
    	gc.closePath();
    	gc.fill();
    	    	
		garden = new FlowPane();
		garden.setBackground(new Background(new BackgroundFill(Color.WHITE, 
                CornerRadii.EMPTY, Insets.EMPTY)));
		garden.getChildren().add(background);
		garden.setAlignment(Pos.CENTER);

		// temp tilepane in place of carousel
		plantCarousel = new DragDropCarouselView();
		plantCarousel.getChildren().add(pv1);
    	plantCarousel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, 
                CornerRadii.EMPTY, Insets.EMPTY)));
    	
		this.setTop(hBox);
		this.setBottom(plantCarousel);
		this.setCenter(garden);
		
		this.setPadding(new Insets(10, 10, 10, 10));
    	this.setMargin(this.getBottom(), new Insets(10, 10, 10, 10));
    	
    	
    	for (PlantView plantImg : plants) {
    		plantImg.setOnMousePressed(egc.getHandlerForPress());
    		plantImg.setOnMouseDragged(egc.getHandlerForDrag());
    	}
    	
    	
	}
	
	public PlantView newPlantView() {
    	PlantView pv = new PlantView();
    	pv.setImage(new Image(getClass().getResourceAsStream("/img/commonMilkweed.png")));
    	pv.setPreserveRatio(true);
    	pv.setFitHeight(100);
		pv.setOnMousePressed(egc.getHandlerForPress());
    	pv.setOnMouseDragged(egc.getHandlerForDrag());
    	return pv;
    }
	
    public boolean isInsideGarden(List<Point> gardenOutline, Point plantLoc) {
      int i;
      int j;
      boolean result = false;
      for (i = 0, j = gardenOutline.size() - 1; i < gardenOutline.size(); j = i++) {
        if ((gardenOutline.get(i).y > plantLoc.y) != (gardenOutline.get(j).y > plantLoc.y) &&
            (plantLoc.x < (gardenOutline.get(j).x - gardenOutline.get(i).x) * (plantLoc.y - gardenOutline.get(i).y) / (gardenOutline.get(j).y-gardenOutline.get(i).y) + gardenOutline.get(i).x)) {
          result = !result;
         }
        System.out.println(" " + gardenOutline.get(i).y + " " + plantLoc.y);
      } 
      return result;
    }

	
	public List<Point> movePlant() {
		List<Point> points = null;
		return points;
	}
	
	public void updatePlant() {}
	
	
	// getters
	public int getUserX() {
		return this.userX;
	}
	
	public int getUserY() {
		return this.userY;
	}
	
	public boolean isClicked() {
		return this.clicked;
	}
	
	public DragDropCarouselView getPlantCarousel() {
		return this.plantCarousel;
	}
	
	public FlowPane getGarden() {
		return this.garden;
	}
	
	public List<PlantView> getPlants() {
		return this.plants;
	}
	
	
	// setters
	public void setUserX(int x) {
		this.userX = x;
	}
	
	public void setUserY(int y) {
		this.userY = y;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setPlantCarousel(DragDropCarouselView carousel) {
		this.plantCarousel = carousel;
	}
	
	public void setGarden(FlowPane garden) {
		this.garden = garden;
	}
	
	public void setPlants(List<PlantView> plants) {
		this.plants = plants;
	}
	
	public void setX(int index, double x) {
    	//iv1.setTranslateX(iv1.getLayoutX() - WIDTH/2 + x);
    	//iv1.setTranslateX(iv1.getLayoutX() + x);
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateX(img.getLayoutX() + x);

    }
    
    public void setY(int index, double y) {
    	//iv1.setTranslateY(iv1.getLayoutY() - HEIGHT/2 + y);
    	//iv1.setTranslateY(iv1.getLayoutY() + y);
    	PlantView img = this.getPlants().get(index);
    	img.setTranslateY(img.getLayoutY() + y);
    	
    }

	public List<Point> getGardenOutline() {
		return this.gardenOutline;
	}
	
	
	
}
