package pkgModel;
import java.util.Set;
import java.awt.Point;
import java.util.ArrayList;

public class DrawGardenModel {
	Set<Point> preOutline;
	Set<Point> preCondtion;
	
	public DrawGardenModel() {
		
	}
	
	public boolean checkOutline(boolean complete) {
		return false;
	}
	
	public boolean checkConditions(boolean complete) {
		return false;
	}

	public Set<Point> getPreOutline() {
		return preOutline;
	}

	public void setPreOutline(Set<Point> preOutline) {
		this.preOutline = preOutline;
	}

	public Set<Point> getPreCondtion() {
		return preCondtion;
	}

	public void setPreCondtion(Set<Point> preCondtion) {
		this.preCondtion = preCondtion;
	}
	
	
}
