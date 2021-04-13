package pkgController;

import java.awt.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pkgModel.Model;
import pkgModel.PlantGardenModel;
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
		this.view=view;
		this.gardenView = gardenView;
		this.gardenModel = new PlantGardenModel();
		gardenModel.addPlant(new PlantObjectModel(0, 0, 10, 10));
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
		
		//int viewIndex = gardenView.getPlants().indexOf(n);
		//int modelIndex = gardenModel.getPlants().indexOf(n);
		int index = gardenView.getPlants().indexOf(n);
				
		gardenModel.getPlants().get(index).setX(gardenModel.getPlants().get(index).getX() + (int)event.getX()); 
		gardenModel.getPlants().get(index).setY(gardenModel.getPlants().get(index).getY() + (int)event.getY());
		gardenView.setX( index, gardenModel.getPlants().get(index).getX() );
		gardenView.setY( index, gardenModel.getPlants().get(index).getY() );
		//Point newLoc = new Point((int)gardenModel.getPlants().get(index).getX(), (int)gardenModel.getPlants().get(index).getY());
		//boolean inside = gardenView.isInsideGarden(gardenView.getGardenOutline(), newLoc);
		//System.out.println(inside);
		
		return;
	}
	
	public void press(MouseEvent event) {
		Node n = (Node)event.getSource();
		
		
		//Checks whether plant clicked is in the plant selection zone (grey bar). If it is, make a copy plant and control that instead.
		if (gardenView.getPlantCarousel().getChildren().contains(n)) {
			gardenModel.getPlants().add(new PlantObjectModel(0, 200, 10, 10));
			PlantView newPlantView = gardenView.newPlantView();
			gardenView.getGarden().getChildren().add(n);
			gardenView.getPlants().add(newPlantView);
			gardenView.getPlantCarousel().getChildren().add(newPlantView);
		}
		
		
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
	
}
