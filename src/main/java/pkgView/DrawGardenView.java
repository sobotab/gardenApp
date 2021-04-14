package pkgView;

import java.awt.geom.Point2D;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Rectangle;

import pkgController.DrawGardenController;

public class DrawGardenView extends BorderPane {
	
	DrawGardenController dgc;
	Canvas canvas;
	GraphicsContext gc;
	Line line;
	int lineThickness;
	ToggleButton drawButton, polyButton, rectButton, circButton;
	Color color;
	Point2D.Double start, current;
	boolean makingLine, shapeDone;
	
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
		
		drawButton = new ToggleButton("Draw");
		polyButton = new ToggleButton("Polygon");
		rectButton = new ToggleButton("Rectangle");
		circButton = new ToggleButton("Circle");
		
		ToggleButton[] toolsArr = {drawButton, polyButton, rectButton, circButton};	
		ToggleGroup tools = new ToggleGroup();
		
		for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
		
		line = new Line();
		makingLine = false;
		
		VBox buttons = new VBox();
		buttons.getChildren().addAll(drawButton, polyButton);//, rectButton, circButton);
		
		canvas.setOnMousePressed(event -> mousePressed((MouseEvent) event));
		canvas.setOnMouseDragged(event -> mouseDragged((MouseEvent) event));
		canvas.setOnMouseReleased(event -> mouseReleased((MouseEvent) event));
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(back, finish);
		/*
		StackPane canvasHolder = new StackPane();
		canvasHolder.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, 
                CornerRadii.EMPTY, Insets.EMPTY)));
		
		Canvas drawCanvas = new Canvas(250,300);
		GraphicsContext gc = drawCanvas.getGraphicsContext2D();
		canvasHolder.getChildren().add(drawCanvas);
		
		drawCanvas.setOnDragDetected(dgc.getHandlerForDraw(drawCanvas));
		*/
		this.setTop(title);
		this.setLeft(buttons);
		this.setCenter(canvas);
		this.setBottom(hBox);
	}
	
	public void mousePressed(MouseEvent e) {
		if(polyButton.isSelected()) {
			if (makingLine) {
				gc.strokeLine(line.getStartX(), line.getStartY(), e.getX(), e.getY());
				line.setStartX(e.getX());
				line.setStartY(e.getY());
			} else {
				line.setStartX(e.getX());
				line.setStartY(e.getY());
				setStart(e.getX(), e.getY());
				makingLine = true;
			}
			setCurrent(e.getX(), e.getY());
			dgc.draw();
			if (shapeDone) {
				polyButton.setSelected(false);
				shapeDone = false;
				makingLine = false;
			}
		} else if (drawButton.isSelected()) {
			setStart(e.getX(), e.getY());
			gc.setStroke(color);
            gc.beginPath();
            dgc.draw();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		if (shapeDone) {
			drawButton.setSelected(false);
			shapeDone = false;
			makingLine = false;
		}
		
		if(drawButton.isSelected()) {
			if (shapeDone) {
				gc.closePath();
			} else {
				gc.lineTo(e.getX(), e.getY());
	            gc.stroke();
			}
			dgc.draw();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (drawButton.isSelected()) {
            gc.closePath();
		}
		
		if (shapeDone) {
			polyButton.setSelected(false);
			drawButton.setSelected(false);
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

	public void setShapeDone(boolean shapeDone) {
		this.shapeDone = shapeDone;
	}
}
