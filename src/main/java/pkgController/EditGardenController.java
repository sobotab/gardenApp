package pkgController;

import java.awt.Point;
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
		
		// Temporary source of plants until we get scraper running
		
		PlantModel Agalinis_purpurea = new PlantInfoModel("purple false foxglove", "Agalinis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 4, 6, "Example Description");
		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.DAMP, Soil.CLAY, 463, 20, "Example Description");
		PlantModel Anemone_virginiana = new PlantInfoModel("thimbleweed","Anemone-virginiana",1, Sun.FULLSUN,Moisture.DAMP,Soil.CLAY, 2, 6, "Example Description");
		PlantModel Aralia_racemosa = new PlantInfoModel("spikenard","Aralia-racemosa",1,Sun.PARTSUN,Moisture.DAMP,Soil.CLAY,6, 6, "Example Description");
		PlantModel Acer_rubrum = new PlantInfoModel("red maple","Acer-rubrum",75,Sun.FULLSUN,Moisture.DAMP,Soil.CLAY,256,20,"Example Description");
		
		List<PlantModel> plants2 = new ArrayList<PlantModel>();
		plants2.add(Acer_rubrum);
		plants2.add(Aralia_racemosa);
		plants2.add(Anemone_virginiana);
		plants2.add(Agalinis_purpurea);
		plants2.add(Quercus_stellata);
		
		this.view=view;
		this.gardenView = gardenView;

		for (PlantModel plant : plants2)
			gardenView.getPlantInput().add(plant.getSciName());
		
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
		
		gardenModel.dragPlant(index, event.getX(), event.getY(), gardenView.getGarden().getWidth() - 60, gardenView.getGarden().getHeight() - 60);
		
		gardenView.setX( index, gardenModel.getPlants().get(index).getX() );
		gardenView.setY( index, gardenModel.getPlants().get(index).getY() );
		
		checkInsideGarden(index);
		
		//System.out.println("Model x,y =  " + gardenModel.getPlants().get(index).getX() +",  " + gardenModel.getPlants().get(index).getY());
		
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
		//int index = gardenView.getPlants().indexOf(n);
		//gardenModel.setPlantLocation(index, event.getSceneX(), event.getSceneY());
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
	
	// Check point is within garden in model. Does not work.
	// Currently I'm compare model coordinates to points in view's outline,
	// Which is then drawn on a canvas, and stored in borderpane's center. 
	// So obviously, these don't line up. 
	
	public boolean checkInsideGarden(int index) {
		if ( gardenModel.checkInsideGarden(gardenView.getGardenOutline(), index) ) {
			System.out.println("inside!");
			return true;
		}
		return false;
	}
	
	public void release(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
		double x = gardenModel.getPlants().get(index).getX() - 10;
		double y = gardenModel.getPlants().get(index).getY() - 10;
		gardenView.drawSpread(index, x, y);
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
