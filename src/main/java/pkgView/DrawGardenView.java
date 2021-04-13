package pkgView;

import java.awt.Point;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pkgController.DrawGardenController;

public class DrawGardenView extends BorderPane {
	int userX;
	int userY;
	
	public DrawGardenView(View view) {
		
		DrawGardenController dgc = new DrawGardenController(view);
		
		Button back = new Button("Back");
		back.setOnAction(dgc.getHandlerForBack());
		Button finish = new Button("Finish");
		finish.setOnAction(dgc.getHandlerForNext());
		
		Label title = new Label("Draw Garden");
		
		HBox hBox = new HBox();
		hBox.setPrefSize(100,200);
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
		this.setBottom(hBox);
		//this.setCenter(canvasHolder);
		
		
	}
	
	public List<Point> draw() {
		List<Point> points = null;
		return points;
	}
	
	// getters
	public int getUserX() {
		return this.userX;
	}
	
	public int getUserY() {
		return this.userY;
	}
	
	// setters
	public void setUserX(int x) {
		this.userX = x;
	}
	
	public void setUserY(int y) {
		this.userY = y;
	}
	
}
