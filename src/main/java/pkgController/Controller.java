/**
 * @author - Ryan Dean
 * @author - Benjamin Sobota
 * @author - Zane Greenholt
 * @author - Rakesh Gadde
 */
package pkgController;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.View;

public class Controller extends Application {
	
	Model model;
	View view;
	List<VBox> plantImages;
	List<PlantModel> plants;
	HashMap<String,ImageView> lepImages;
	
	public Controller() {
		//May or may not make view first and use it here. 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage theStage) {
        view = new View(theStage, this);
		model = new Model();
		makePlantsFromData();
		plantImages = loadPlantImagesFromList();
		lepImages = loadLepImages();
        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.show();
    }
	
	public void makePlantsFromData() {
		plants = model.makePlants();
	}
	
	public List<VBox> loadPlantImagesFromList(){
		List<PlantModel> plants = this.plants;
		List<VBox> images = new ArrayList<>();
		for(PlantModel plant: plants) {
			PlantInfoModel infoPlant = (PlantInfoModel)plant;
			String sciName = infoPlant.getSciName();
			Image image = new Image(getClass().getResourceAsStream("/images/" + sciName + ".jpg"));
			ImageView img = new ImageView(image);
			double circleX = 75.0,circleY = 75.0,circleRadius = 75.0;
			Circle frame = new Circle(circleX,circleY,circleRadius);
			img.setClip(frame);
			Text label = new Text(infoPlant.getName() + "\n" + infoPlant.getSciName());
			label.setTextAlignment(TextAlignment.CENTER);
			Text leps = new Text("Leps supported: " + infoPlant.getNumLeps());
			int dollars = infoPlant.getDollars();
			Text price = new Text("Price: $" + dollars);
			Text type = new Text();
			if(dollars == 6) {
				type.setText("Type: Herbaceous");
			}
			else {
				type.setText("Type: Woody");
			}
			VBox box = new VBox();
			box.setAlignment(Pos.CENTER);
			box.getChildren().addAll(label, img, leps, price, type);
			images.add(box);
			view.setHoverHandlers(box);
		}
		return images;
	}
	
	public HashMap<String, ImageView> loadLepImages(){
		HashMap<String, ImageView> leps = new HashMap<String, ImageView>();
		for(PlantModel plant: plants) {
			List<String> lepNames = plant.getLeps();
			if(plant.getSciName().startsWith("Quercus") || plant.getSciName().startsWith("Agalinis")) {
				for(String sciName: lepNames) {
					if(!leps.containsKey(sciName)) {
						String fileName = "/images/" + sciName + ".jpg";
						Image image = new Image(getClass().getResourceAsStream(fileName));
						ImageView imv = new ImageView(image);
						double circleX = 240.0,circleY = 240.0,circleRadius = 240.0;
						Circle frame = new Circle(circleX,circleY,circleRadius);
						imv.setClip(frame);
						leps.put(sciName, imv);
					}
				}
			}
		}
		return leps;
	}
	
	


	
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	public EventHandler getHandlerForDraw() {
		return event -> draw((MouseEvent) event);
	}
	
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	public void drag(MouseEvent event) {}
	
	public void draw(MouseEvent event) {}
	
	public void press(MouseEvent event) {}
	
	void update() {}

	public List<VBox> getPlantImages() {
		return plantImages;
	}

	public void setPlantImages(List<VBox> images) {
		this.plantImages = images;
	}

	public List<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantModel> plants) {
		this.plants = plants;
	}
}
