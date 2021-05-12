package pkgController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pkgModel.EditGardenModel;
import pkgView.EditGardenView;
import pkgView.OpenGardenView;
import pkgView.View;
import pkgView.WelcomeView;

/**
 * 
 * @author Ryan Dean
 * Controller class for the Open Garden screen. Reads in serialized garden models if any exist.
 */
public class OpenGardenController {
	/**
	 * View class for the program. Initialized once.
	 */
	View view;
	/**
	 * View class for the Open Garden screen. Contains TableView used to present saved garden info.
	 */
	OpenGardenView ogv;
	/**
	 * Hashmap holding data on saved gardens, key = garden name, value = garden model.
	 */
	HashMap<String, EditGardenModel> gardenData;
	
	/**
	 * Constructor reads in serialized garden models if any exist, sends data to OpenGardenView.
	 * 
	 * @param view 		View class for the program.
	 * @param ogv 		View class for this screen.
	 */
	public OpenGardenController(View view, OpenGardenView ogv) {
		this.view = view;
		this.ogv = ogv;
		
		// Read in existing garden data, if any
		
		gardenData = null;
		try {
			FileInputStream fis = new FileInputStream("finalGardenData.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        gardenData = (HashMap<String, EditGardenModel>)ois.readObject();
	        ois.close();
		} catch (FileNotFoundException e) {
        	System.out.println("File not found");
        } catch (IOException e) {
        	System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		// Sort model data into hashmap objects for input into tableview
		
		ObservableList<HashMap<String, Object>> gardenDataContainer = FXCollections.<HashMap<String, Object>>observableArrayList();
		Iterator iter = gardenData.entrySet().iterator();
		EditGardenModel gardenEntryModel;
		
		while (iter.hasNext()) {
			Map.Entry gardenEntry = (Map.Entry)iter.next();
			gardenEntryModel = (EditGardenModel) gardenEntry.getValue();
			
			HashMap<String, Object> gardenMap = new HashMap<String, Object>();
			
			gardenMap.put("name", (String)gardenEntry.getKey());
			gardenMap.put("current budget", gardenEntryModel.getDollars());
			gardenMap.put("max budget", gardenEntryModel.getBudget());
			gardenMap.put("leps", gardenEntryModel.getNumLeps());

			gardenDataContainer.add(gardenMap);
		}
		ogv.getGardenTable().getItems().addAll(gardenDataContainer);
	}
	
	/**
	 * Handler for clickedBack. Returns to welcome screen.
	 * 
	 * @param event 	ActionEvent caused by clicking back button.
	 */
	public void clickedBack(ActionEvent event) {
		view.setCurrentScreen(new WelcomeView(view));
		
	}
	
	/**
	 * Handler for clickedOpen. Opens Edit Garden screen with the currently selected garden in the tableview.
	 * 
	 * @param event 	ActionEvent caused by clicked open button.
	 */	
	public void clickedOpen(ActionEvent event) {
		int selectedIndex = ogv.getGardenTable().getSelectionModel().getSelectedIndex();
		HashMap<String, Object> item = (HashMap<String, Object>) ogv.getGardenTable().getSelectionModel().getSelectedItem();
		String name = (String) item.get("name");
		if (name != null)
			view.setCurrentScreen(new EditGardenView(view, name));
	}
	
	/**
	 * Getter for the clickedBack method.
	 * 
	 * @return 		EventHandler for clickedBack.
	 */
	public EventHandler getHandlerForBack() {
		return event -> clickedBack((ActionEvent) event);
	}
	
	/**
	 * Getter for the clickedOpen method.
	 * 
	 * @return 		EventHandler for clickedOpen.
	 */
	public EventHandler getHandlerForOpen() {
		return event -> clickedOpen((ActionEvent) event);
	}
}
