package pkgModel;
import java.util.Set;
import java.awt.Point;
import java.util.ArrayList;

public class DrawGardenModel extends GardenModel {
	Set<Point> preOutline;
	Set<Point> preCondition;
	
	int rectX, rectY, rectHeight, rectWidth;
	int circX, circY, circRad;
	int lineX, lineY, lineWidth;
	
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

	public int getRectX() {
		return rectX;
	}

	public int getRectY() {
		return rectY;
	}

	public int getRectHeight() {
		return rectHeight;
	}

	public int getRectWidth() {
		return rectWidth;
	}

	public int getCircX() {
		return circX;
	}

	public int getCircY() {
		return circY;
	}

	public int getCircRad() {
		return circRad;
	}

	public int getLineX() {
		return lineX;
	}

	public int getLineY() {
		return lineY;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setRectX(int rectX) {
		this.rectX = rectX;
	}

	public void setRectY(int rectY) {
		this.rectY = rectY;
	}

	public void setRectHeight(int rectHeight) {
		this.rectHeight = rectHeight;
	}

	public void setRectWidth(int rectWidth) {
		this.rectWidth = rectWidth;
	}

	public void setCircX(int circX) {
		this.circX = circX;
	}

	public void setCircY(int circY) {
		this.circY = circY;
	}

	public void setCircRad(int circRad) {
		this.circRad = circRad;
	}

	public void setLineX(int lineX) {
		this.lineX = lineX;
	}

	public void setLineY(int lineY) {
		this.lineY = lineY;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	
}
