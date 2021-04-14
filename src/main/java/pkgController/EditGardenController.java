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
		Set<PlantModel> plants2 = new HashSet<PlantModel>();
		PlantModel Agalinis_purpurea = new PlantInfoModel("purple false foxglove", "Agalinis-purpurea", 1, Sun.FULLSUN, Moisture.WET, Soil.SANDY, 4, 6, "Example Description");
		PlantModel Quercus_stellata = new PlantInfoModel("iron oak", "Quercus-stellata", 50, Sun.FULLSUN, Moisture.DAMP, Soil.CLAY, 463, 20, "Example Description");
		PlantModel Anemone_virginiana = new PlantInfoModel("thimbleweed","Anemone-virginiana",1, Sun.FULLSUN,Moisture.DAMP,Soil.CLAY, 2, 6, "Example Description");
		PlantModel Aralia_racemosa = new PlantInfoModel("spikenard","Aralia-racemosa",1,Sun.PARTSUN,Moisture.DAMP,Soil.CLAY,6, 6, "Example Description");
		PlantModel Acer_rubrum = new PlantInfoModel("red maple","Acer-rubrum",75,Sun.FULLSUN,Moisture.DAMP,Soil.CLAY,256,20,"Example Description");
		plants2.add(Acer_rubrum);
		plants2.add(Aralia_racemosa);
		plants2.add(Anemone_virginiana);
		plants2.add(Agalinis_purpurea);
		plants2.add(Quercus_stellata);
		
		this.view=view;
		this.gardenView = gardenView;

		for (PlantModel plant : plants2)
			gardenView.getPlantInput().add(plant.getSciName());
		
		this.gardenModel = new PlantGardenModel(plants2, 0);
	}
	
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new SelectPlantsView(view));
		
	}
	
	public void clickNext(ActionEvent event) {
		
	}
	
	public void clickExit(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
	}
	
	public void drag(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
				
		gardenModel.getPlants().get(index).setX(gardenModel.getPlants().get(index).getX() + (int)event.getX()); 
		gardenModel.getPlants().get(index).setY(gardenModel.getPlants().get(index).getY() + (int)event.getY());
		gardenView.setX( index, gardenModel.getPlants().get(index).getX() );
		gardenView.setY( index, gardenModel.getPlants().get(index).getY() );
		//Point newLoc = new Point((int)gardenModel.getPlants().get(index).getX(), (int)gardenModel.getPlants().get(index).getY());
		//Point newLoc = new Point((int)(gardenModel.getPlants().get(index).getX() + 600), (int)(gardenModel.getPlants().get(index).getY() + 500));
		//boolean inside = gardenModel.isInsideGarden(gardenView.getGardenOutline(), newLoc);
		//System.out.println(inside);
		
		return;
	}
	
	public void press(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
		gardenModel.getPlants().get(index).setX((int)event.getSceneX());
		gardenModel.getPlants().get(index).setY((int)event.getSceneY());

		//Checks whether plant clicked is in the plant selection zone (grey bar). If it is, make a copy plant and control that instead.
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			gardenModel.getPlants().add(new PlantObjectModel("name", "sciname", 0, Sun.FULLSUN, Moisture.DAMP, Soil.CHALKY, 0, 0, 10, 10));
			PlantView newPlantView = gardenView.newPlantView("Acer-rubrum");
			gardenView.getGarden().getChildren().add(n);
			gardenView.getPlants().add(newPlantView);
			gardenView.getPlantCarousel().getChildren().add(newPlantView);
		}
		
		return;
	}
	
	public void release(MouseEvent event) {
		Node n = (Node)event.getSource();
		int index = gardenView.getPlants().indexOf(n);
		if (!(gardenView.getPlantCarousel().getChildren().contains(n))) {
			gardenView.drawSpread(index);
		}
		gardenModel.checkSpread();
		if (event.getTarget() == gardenView.getPlantCarousel().getChildren().get(0)) {
			drop(event);
		}
		return;
	}
	
	// not functional rn, seems MouseEvents aren't sufficient to track drop location
	// will probably need to swap drag drop mechanics to dragEvents instead
	public void drop(MouseEvent event) {
		System.out.println("running drop");
		Node n = (Node)event.getSource();
		
		int index = gardenView.getPlants().indexOf(n);
		gardenView.getPlants().remove(index);
		gardenModel.getPlants().remove(index);
		gardenView.getGarden().getChildren().remove(n);
		return;
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
	
	public EventHandler getHandlerForDrop() {
		return event -> drop((MouseEvent) event);
	}
	
}
