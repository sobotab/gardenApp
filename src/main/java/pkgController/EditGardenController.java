package pkgController;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import pkgModel.DrawGardenModel;
import pkgModel.Model;
import pkgModel.ObjectCarouselModel;
import pkgModel.PlantGardenModel;
import pkgModel.PlantInfoModel;
import pkgModel.PlantModel;
import pkgModel.PlantObjectModel;
import pkgView.EditGardenView;
import pkgView.PlantView;
import pkgView.SelectPlantsView;
import pkgView.View;
import pkgView.WelcomeView;

public class EditGardenController {
	View view;
	EditGardenView gardenView;
	PlantGardenModel gardenModel;
	
	public EditGardenController(View view, EditGardenView gardenView, HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {	
		
		// Hard-coded max dimension
		//double max_height = 500;
		/*
		PlantModel Agalinis_purpurea = new PlantInfoModel("purple false foxglove", "Agalinis-purpurea", 1, "full sun", "wet", "sandy", 4, 6, "Example Description");
		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, "full sun", "moist", "clay", 463, 20, "Example Description");
		PlantModel Anemone_virginiana = new PlantInfoModel("thimbleweed","Anemone-virginiana",1, "full sun","moist","clay", 2, 6, "Example Description");
		PlantModel Aralia_racemosa = new PlantInfoModel("spikenard","Aralia-racemosa",1,"shade","moist","clay",6, 6, "Example Description");
		PlantModel Acer_rubrum = new PlantInfoModel("red maple","Acer-rubrum",75,"full sun","moist","clay",256,20,"Example Description");
		
		List<PlantModel> plants = new ArrayList<PlantModel>();
		plants.add(Acer_rubrum);
		plants.add(Aralia_racemosa);
		plants.add(Anemone_virginiana);
		plants.add(Agalinis_purpurea);
		plants.add(Quercus_stellata);
		*/
		ArrayList<PlantInfoModel> plants2 = null;
		try {
			FileInputStream fis = new FileInputStream("plantData.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        plants2 = (ArrayList<PlantInfoModel>)ois.readObject();
	        ois.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		// Initialize & Add plants to view
		this.view=view;
		this.gardenView = gardenView;

		for (PlantModel plant : plants2) {
			gardenView.getPlantInput().add(new Pair<>(plant.getSciName(), plant.getSpreadDiameter()));
		}
		
		// Initialize & Add plants to model
		ObjectCarouselModel carouselModel = gardenView.getPlantCarousel().getController().carouselModel;
		this.gardenModel = new PlantGardenModel(carouselModel, plants2, plots);
		
	}
	
	// Screen control
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));	
	}
	
	public void clickNext(ActionEvent event) {}
	
	public void clickExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
		
	// Handle drag
	
	public void drag(MouseEvent event) {
		Node n = (Node)event.getSource();
		
		int index = gardenView.getPlants().indexOf(n);
		
		gardenModel.dragPlant(index, event.getX(), event.getY(),
				gardenView.getGarden().getWidth() - gardenView.getPlants().get(index).getFitHeight(), 
				gardenView.getGarden().getHeight() - gardenView.getPlants().get(index).getFitHeight());
		
		double x_loc = gardenModel.getPlants().get(index).getX();
		double y_loc = gardenModel.getPlants().get(index).getY();
		gardenView.setX( index, x_loc );
		gardenView.setY( index, y_loc );
		
		gardenView.drawSpread(index, x_loc, y_loc);
				
		for (PlantView plant : gardenView.getPlants()) {
			int spreadIndex = gardenView.getPlants().indexOf(plant);
			gardenView.updateSpread(
					spreadIndex, 
					gardenModel.checkCanvas(spreadIndex, gardenView.getCanvas().getLayoutX(), gardenView.getCanvas().getLayoutY()),
					gardenModel.checkSpread(spreadIndex)
					);
		}
		return;
	}
	
	// Handle press, extract from carousel if necessary
	
	public void press(MouseEvent event) {
		
		Node n = (Node)event.getSource();
		
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			
			int indexCarousel = gardenView.getPlantCarousel().getPlants().indexOf(n);
			
			gardenModel.getCarousel().replacePlant(indexCarousel);									// Subtract 1 from model carousel index b/c it does not contain compost
			gardenModel.addPlantFromCarousel(indexCarousel, 0, 0);	
			
			gardenView.replacePlant(indexCarousel);
			gardenView.addPlantFromCarousel(indexCarousel, n, event);
		}
		return;
	}
	
	// Used to run startFullDrag(), which can only be run inside setOnDragDetected.
	// Makes JavaFX start delivering drag events WITHOUT interfering with mouse events!
	// This lets me do stuff to what's UNDER what I'm dragging
	
	public void dragDetect(MouseEvent event, PlantView pv) {
		//pv.startFullDrag();
		System.out.print("drag detected       ");
		return;
	}
	
	public void release(MouseEvent event) {
		
	}
	
	
	//Make more methods for organizing the gardens
	
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	public EventHandler getHandlerForSave() {
		return event -> clickNext((ActionEvent) event);
	}
	
	public EventHandler getHandlerForExit() {
		return event -> clickExit((ActionEvent) event);
	}
	
	
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	public EventHandler getHandlerForRelease() {
		return event -> release((MouseEvent) event);
	}
	
	public EventHandler getHandlerForDragDetect(PlantView pv) {
		return event -> dragDetect((MouseEvent) event, pv);
	}
	
	
	
}
