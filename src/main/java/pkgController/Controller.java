package pkgController;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pkgModel.Model;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgView.View;

public class Controller extends Application {
	
	Model model;
	View view;
	List<VBox> images;
	List<PlantModel> plants;
	
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
		images = loadImagesFromList();
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
	
	public List<VBox> loadImagesFromList(){
		List<PlantModel> plants = this.plants;
		List<VBox> images = new ArrayList<>();
		for(PlantModel plant: plants) {
			String sciName = plant.getSciName();
			Image image = new Image(getClass().getResourceAsStream("/images/" + sciName + ".jpg"));
			ImageView img = new ImageView(image);
			Text label = new Text(plant.getName());
			VBox box = new VBox();
			box.getChildren().addAll(label, img);
			images.add(box);
		}
		return images;
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

	public List<VBox> getImages() {
		return images;
	}

	public void setImages(List<VBox> images) {
		this.images = images;
	}

	public List<PlantModel> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantModel> plants) {
		this.plants = plants;
	}
}
