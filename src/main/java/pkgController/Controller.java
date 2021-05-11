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

/**
 * Main controller class that is only instantiated one time and loads the plant and image data once
 * @author - Zane Greenholt
 * @author - Ryan Dean
 * @author - Benjamin Sobota
 * @author - Rakesh Gadde
 */
public class Controller extends Application {
	
	/**
	 * The main model object that can load in plants
	 */
	Model model;
	/**
	 * The main view of the program that is only loaded in once
	 */
	View view;
	/**
	 * A list of VBoxes containting plant images and their data as text
	 */
	List<VBox> plantImages;
	/**
	 * A list of plantModels with the plants' data as attributes
	 */
	List<PlantModel> plants;
	/**
	 * HashMap of lep names mapped to their images
	 */
	HashMap<String,ImageView> lepImages;
	
	/**
	 * Constructor creates a controller
	 */
	public Controller() {
		//May or may not make view first and use it here. 
	}
	
	/**
	 * Program is launched from the controller
	 * @param args any command line arguments (not used)
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	/**
	 * Start method starts the application and is called by the launch method
	 * @param theStage the main stage that the application will show on
	 */
	public void start(Stage theStage) {
        view = new View(theStage, this);
		model = new Model();
		//Load plants, plantImages, and lepImages
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
	
	/**
	 * Calls a method in the model to create an arrayList of plants with their data read in from a csv file.
	 */
	public void makePlantsFromData() {
		plants = model.makePlants();
	}
	
	/**
	 * Loads all plant images in from files and creates VBoxes for each that display some of their information
	 * @return List of VBoxes holding all the plants in the program
	 */
	public List<VBox> loadPlantImagesFromList(){
		List<PlantModel> plants = this.plants;
		List<VBox> images = new ArrayList<>();
		for(PlantModel plant: plants) {
			PlantInfoModel infoPlant = (PlantInfoModel)plant;
			String sciName = infoPlant.getSciName();
			Image image = new Image(getClass().getResourceAsStream("/images/" + sciName + ".jpg"));
			ImageView img = new ImageView(image);
			//Create circular clip
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
			//Set on hover for the VBox
			view.setHoverHandlers(box);
		}
		return images;
	}
	
	/**
	 * Loads all lepImages into ImageViews and places them in a hashmap with the scientific name as the key
	 * @return HashMap of lep names mapped to their images
	 */
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
	
	/**
	 * Getter for plantImages field
	 * @return list of VBoxes with all the program's plants
	 */
	public List<VBox> getPlantImages() {
		return plantImages;
	}
	
	/**
	 * Setter for plantImages field
	 * @param images List of VBoxes
	 */
	public void setPlantImages(List<VBox> images) {
		this.plantImages = images;
	}

	/**
	 * Getter for the plants field
	 * @return List of plantModels with all the program's plant data
	 */
	public List<PlantModel> getPlants() {
		return plants;
	}
	
	/**
	 * Setter for the plants field
	 * @param plants List of plantModels
	 */
	public void setPlants(List<PlantModel> plants) {
		this.plants = plants;
	}

	/**
	 * Getter for lepImages field
	 * @return HashMap of lep names mapped to their images
	 */
	public HashMap<String, ImageView> getLepImages() {
		return lepImages;
	}
	
	/**
	 * Setter for lepImages field
	 * @param lepImages HashMap of strings mapped to imageViews
	 */
	public void setLepImages(HashMap<String, ImageView> lepImages) {
		this.lepImages = lepImages;
	}
}
