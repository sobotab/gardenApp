package pkgModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class ModelTest {
	
	Model model = new Model();
	
	@Test
	public void testMakePlants() {
		List<PlantModel> plants = model.makePlants();
		assertFalse(plants.size() == 79);
	}


}
