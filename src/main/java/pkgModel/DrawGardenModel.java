package pkgModel;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import pkgController.Soil;

public class DrawGardenModel extends GardenModel {
	ArrayList<Point2D.Double> preOutline;
	HashMap<Soil, ArrayList<ArrayList<Point2D.Double>>> plots;
	
	Point2D.Double endPoint;
	boolean set;
	
	public DrawGardenModel() {
		plots = new HashMap<>();
		plots.put(Soil.CLAY, new ArrayList<>());
		plots.put(Soil.SANDY, new ArrayList<>());
		plots.put(Soil.SILTY, new ArrayList<>());
		plots.put(Soil.PEATY, new ArrayList<>());
		plots.put(Soil.CHALKY, new ArrayList<>());
		plots.put(Soil.LOAMY, new ArrayList<>());
		endPoint = null;
		preOutline = new ArrayList<>();
		set = true;
	}


	public void addPreOutline(Point2D.Double point) {
		preOutline.add(point);
		setEndPoint(point);
	}

	public void setEndPoint(Point2D.Double point) {
		if (set) {
			endPoint = point;
			this.set = false;
		}
	}	
	
	public void addPlot(boolean done, Soil soil) {
		plots.get(soil).add(preOutline);
		preOutline = new ArrayList<>();
		set = true;
	}
}
