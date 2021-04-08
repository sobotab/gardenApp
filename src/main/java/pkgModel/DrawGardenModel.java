package pkgModel;
import java.util.Set;
import java.awt.Point;
import java.util.ArrayList;

public class DrawGardenModel {
	Set<Point> preOutline;
	Set<Point> preCondition;
	
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

	public Set<Point> getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(Set<Point> preCondition) {
		this.preCondition = preCondition;
	}
	
	
}
