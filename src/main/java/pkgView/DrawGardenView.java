package pkgView;

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
	
	Canvas canvas;
	GraphicsContext gc;
	Rectangle rect;
	Circle circ;
	Line line;
	LineTo lineTo;
	MoveTo moveTo;
	int lineThickness;
	ToggleButton drawButton, rectButton, circButton;
	Color color;
	boolean makingLine;
	
	public DrawGardenView(View view) {
		DrawGardenController dgc = new DrawGardenController(view);
		Label title = new Label("Draw Garden");
		Button back = new Button("Back");
		Button finish = new Button("Finish");
		back.setOnAction(dgc.getHandlerForBack());
		finish.setOnAction(dgc.getHandlerForNext());
		HBox bottomHBox = new HBox();
		bottomHBox.getChildren().addAll(back, finish);
		
		//Garden Drawing Tool
		canvas = new Canvas(600, 800);
		gc = canvas.getGraphicsContext2D();
		
		drawButton = new ToggleButton("Draw");
		rectButton = new ToggleButton("Rectangle");
		circButton = new ToggleButton("Circle");
		
		ToggleButton[] toolsArr = {drawButton, rectButton, circButton};	
		ToggleGroup tools = new ToggleGroup();
		
		for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
		
		line = new Line();
		makingLine = false;
		
		VBox buttons = new VBox();
		buttons.getChildren().addAll(drawButton, rectButton, circButton);
		
		canvas.setOnMousePressed(event -> mousePressed((MouseEvent) event));
		canvas.setOnMouseDragged(event -> mouseDragged((MouseEvent) event));
		canvas.setOnMouseReleased(event -> mouseReleased((MouseEvent) event));
		
		this.setTop(title);
		this.setLeft(buttons);
		this.setCenter(canvas);
		this.setBottom(bottomHBox);
	}
	
	public void mousePressed(MouseEvent e) {
		if(drawButton.isSelected()) {
			if (makingLine) {
				gc.strokeLine(line.getStartX(), line.getStartY(), e.getX(), e.getY());
			} else {
				line.setStartX(e.getX());
				line.setStartY(e.getY());
			}
		} else if (rectButton.isSelected()) {
			
		} else if (circButton.isSelected()) {
			
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(drawButton.isSelected()) {
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (drawButton.isSelected()) {
			if (!makingLine) {
				gc.strokeLine(line.getStartX(), line.getStartY(), e.getX(), e.getY());
			} else {
				line.setStartX(e.getX());
				line.setStartY(e.getY());
			}
			makingLine = true;
		} else if (rectButton.isSelected()) {
			
		} else if (circButton.isSelected()) {
			
		}
	}
}
