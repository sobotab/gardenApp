package pkgController;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Pair;
import pkgModel.Model;
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
	
	public EditGardenController(View view, EditGardenView gardenView) {	
		
		// Hard-coded max dimension
		double max_height = 500;
		
		// Temporary source of plants until we get scraper running
		
		PlantModel Agalinis_purpurea = new PlantInfoModel("purple false foxglove", "Agalinis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 4, 6, "Example Description");
		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.MOIST, Soil.CLAY, 463, 20, "Example Description");
		PlantModel Anemone_virginiana = new PlantInfoModel("thimbleweed","Anemone-virginiana",1, Sun.FULLSUN,Moisture.MOIST,Soil.CLAY, 2, 6, "Example Description");
		PlantModel Aralia_racemosa = new PlantInfoModel("spikenard","Aralia-racemosa",1,Sun.PARTSUN,Moisture.MOIST,Soil.CLAY,6, 6, "Example Description");
		PlantModel Acer_rubrum = new PlantInfoModel("red maple","Acer-rubrum",75,Sun.FULLSUN,Moisture.MOIST,Soil.CLAY,256,20,"Example Description");
		
		List<PlantModel> plants2 = new ArrayList<PlantModel>();
		plants2.add(Acer_rubrum);
		plants2.add(Aralia_racemosa);
		plants2.add(Anemone_virginiana);
		plants2.add(Agalinis_purpurea);
		plants2.add(Quercus_stellata);
		
		// Add plants to view
		this.view=view;
		this.gardenView = gardenView;

		for (PlantModel plant : plants2) {
			gardenView.getPlantInput().add(new Pair<>(plant.getSciName(), plant.getSpreadDiameter()));
			//gardenView.getPlantCarousel().addPlant(
			//		gardenView.makePlantView(plant.getSciName(), plant.getSpreadDiameter()));
		}
		
		// Add plants to model
		this.gardenModel = new PlantGardenModel(plants2);
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
		
		checkInsideGarden(index);

		for (PlantView plant : gardenView.getPlants()) {
			int spreadIndex = gardenView.getPlants().indexOf(plant);
			gardenView.updateSpread(spreadIndex, checkInsideGarden(spreadIndex), gardenModel.checkSpread(spreadIndex));
		}
		
		
		return;
	}
	
	// Handle press, extract from carousel if necessary
	
	public void press(MouseEvent event) {
		
		Node n = (Node)event.getSource();
		
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			
			int indexCarousel = gardenView.getPlantCarousel().getPlants().indexOf(n);
			
			gardenModel.getCarousel().replacePlant(indexCarousel-1);									// Subtract 1 from model carousel index b/c it does not contain compost
			gardenModel.addPlantFromCarousel(indexCarousel-1, 0, 0);	
			
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
	
	public boolean checkInsideGarden(int index) {
		
		// Initialize model outline
		if (gardenModel.getGardenOutline() == null) {
			Polygon gardenOutlineModel = new Polygon();
			for (Point coord : gardenView.getGardenOutline()) {
				gardenOutlineModel.addPoint(
						coord.x + (int)gardenView.getGardenOutlineShape().getLayoutX(), 
						coord.y + (int)gardenView.getGardenOutlineShape().getLayoutY()
						);
			}
			gardenModel.setGardenOutline(gardenOutlineModel);
		}
		
		return gardenModel.checkInsideGarden(index);
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
