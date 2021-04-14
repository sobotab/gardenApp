package pkgModel;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Set;

public class DrawGardenModel extends GardenModel {
	ArrayList<Point2D.Double> preOutline;
	Set<Point2D.Double> preCondition;
	
	Point2D.Double endPoint;
	double range;
	boolean bigEnough;
	boolean set;
	
	public DrawGardenModel() {
		endPoint = null;
		preOutline = new ArrayList<>();
		range=1.0;
		bigEnough = false;
		set = true;
	}
	
	public boolean checkOutline(boolean complete) {
		return false;
	}
	
	public boolean checkConditions(boolean complete) {
		return false;
	}

	public ArrayList<Point2D.Double> getPreOutline() {
		return preOutline;
	}

	public void addPreOutline(Point2D.Double point) {
		preOutline.add(point);
		setEndPoint(point.getX(), point.getY(), set);
		setBigEnough(point);
	}
	
	public void setBigEnough(Point2D.Double point) {
		if(point.getX() > endPoint.getX()+range) {
			if(point.getY() > endPoint.getY()+range || point.getY() < endPoint.getY()-range) {
				bigEnough=true;
			}
		} else if ( point.getX() < endPoint.getX()-range) {
			if(point.getY() > endPoint.getY()+range || point.getY() < endPoint.getY()-range) {
				bigEnough=true;
			}
		}
	}

	public Set<Point2D.Double> getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(Set<Double> preCondition) {
		this.preCondition = preCondition;
	}

	public void setEndPoint(double x, double y, boolean set) {
		if (set) {
			endPoint = new Point2D.Double(x, y);
			this.set = false;
		}
	}
	
	public boolean checkEnd(Point2D.Double point) {
		if (point.getX() >= endPoint.getX()-range && point.getY() >= endPoint.getY()-range) {
			if (point.getX() <= endPoint.getX()+range && point.getY()<= endPoint.getY()+range) {
				if (bigEnough) {
					set=true;
					bigEnough=false;
					return true;
				}
			}
		}
		return false;
	}
	
	
}
