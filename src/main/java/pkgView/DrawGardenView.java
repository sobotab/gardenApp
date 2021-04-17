package pkgView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import pkgController.DrawGardenController;
import pkgController.Soil;

public class DrawGardenView extends BorderPane {
	
	DrawGardenController dgc;
	Canvas canvas;
	GraphicsContext gc;
	Polygon polygon;
	ToggleButton drawButton, polyButton, clayButton, sandyButton, siltyButton,
		peatyButton, chalkyButton, loamyButton;
	Color color;
	Point2D.Double start, current;
	boolean drawing, shapeDone;
	
	public DrawGardenView(View view) {
		dgc = new DrawGardenController(view, this);
		Label title = new Label("Draw Garden");

		Button back = new Button("Back");
		Button finish = new Button("Finish");
		back.setOnAction(dgc.getHandlerForBack());
		finish.setOnAction(dgc.getHandlerForNext());
		HBox bottomHBox = new HBox();
		bottomHBox.getChildren().addAll(back, finish);
		
		//Garden Drawing Tool
		canvas = new Canvas(400, 400);
		gc = canvas.getGraphicsContext2D();
		
		polygon = new Polygon();
		drawing = false;
		
		canvas.setOnMousePressed(event -> mousePressed((MouseEvent) event));
		canvas.setOnMouseDragged(event -> mouseDragged((MouseEvent) event));
		canvas.setOnMouseReleased(event -> mouseReleased((MouseEvent) event));
		
		
		//Making sidetool
		drawButton = new ToggleButton("Draw");
		polyButton = new ToggleButton("Polygon");
		
		ToggleGroup tools = new ToggleGroup();
		drawButton.setToggleGroup(tools);
		polyButton.setToggleGroup(tools);
		drawButton.setCursor(Cursor.HAND);
		polyButton.setCursor(Cursor.HAND);
		
		clayButton = new ToggleButton("Clay");
		sandyButton = new ToggleButton("Sandy");
		siltyButton = new ToggleButton("Silty");
		peatyButton = new ToggleButton("Peaty");
		chalkyButton = new ToggleButton("Chalky");
		loamyButton = new ToggleButton("Loamy");
		ArrayList<ToggleButton> soilButtons = new ArrayList<>();
		ToggleButton[] tmp = new ToggleButton[] {clayButton,
				sandyButton, siltyButton, peatyButton, chalkyButton, 
				loamyButton};
		ToggleGroup toggleSoil = new ToggleGroup();
		for (ToggleButton button : tmp) {
			soilButtons.add(button);
			button.setToggleGroup(toggleSoil);
			button.setCursor(Cursor.HAND);
		}
		
		HBox toolBox = new HBox();
		toolBox.getChildren().addAll(drawButton, polyButton);
		
		VBox soilBox = new VBox();
		soilBox.getChildren().addAll(soilButtons);
		
		VBox sideTool = new VBox();
		sideTool.getChildren().addAll(toolBox, soilBox);
		
		this.setTop(title);
		this.setLeft(sideTool);
		this.setCenter(canvas);
		this.setBottom(bottomHBox);
	}
	
	public void mousePressed(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		if (drawButton.isSelected() && drawing) {
			dgc.draw();
			drawing = true;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		if(drawButton.isSelected() && drawing) {
			dgc.draw();
			polygon.getPoints().add(e.getX());
			polygon.getPoints().add(e.getY());
			//TODO add polygon to the canvas
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (drawButton.isSelected() && drawing) {
			drawing = false;
			Point2D.Double point = dgc.draw();
            polygon.getPoints().add(point.getX());
            polygon.getPoints().add(point.getY());
		}
	}
	
	public Point2D.Double getStart() {
		return start;
	}

	public Point2D.Double getCurrent() {
		return current;
	}

	public void setStart(double x, double y) {
		start = new Point2D.Double(x, y);
	}

	public void setCurrent(double x, double y) {
		current = new Point2D.Double(x, y);
	}
	
	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
	
	public boolean getDrawing() {
		return drawing;
	}
	
	public Soil getSoil() {
		if (clayButton.isSelected()) {
			return Soil.CLAY;
		} else if (sandyButton.isSelected()) {
			return Soil.SANDY;
		} else if (siltyButton.isSelected()) {
			return Soil.SILTY;
		} else if (peatyButton.isSelected()) {
			return Soil.PEATY;
		} else if (chalkyButton.isSelected()) {
			return Soil.CHALKY;
		} else if (loamyButton.isSelected()) {
			return Soil.LOAMY;
		}
		return null;
	}
}
