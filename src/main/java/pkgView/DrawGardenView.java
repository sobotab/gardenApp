package pkgView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pkgController.DrawGardenController;
import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class DrawGardenView extends BorderPane {
	
	DrawGardenController dgc;
	int canvasHeight = 500;
	int canvasWidth = 500;
	double scale;
	Canvas canvas;
	GraphicsContext gc;
	Polygon polygon;
	ToggleButton drawButton, polyButton;
	ComboBox<Soil> soilComboBox;
	Slider sun, moisture;
	TextField budget;
	Button undoButton, incButton, decButton;
	Color color;
	double lineWidth;
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
		lineWidth=2.0;
		canvas = new Canvas(canvasHeight, canvasWidth);
		gc = canvas.getGraphicsContext2D();
		scale = 25d;
		buildGrid();
		buildScaleText();
		
		polygon = new Polygon();
		drawing = false;
		
		canvas.setOnMousePressed(event -> mousePressed((MouseEvent) event));
		canvas.setOnMouseDragged(event -> mouseDragged((MouseEvent) event));
		canvas.setOnMouseReleased(event -> mouseReleased((MouseEvent) event));
		
		
		//Making sidetool		
		drawButton = new ToggleButton("Draw");
		//polyButton = new ToggleButton("Polygon");
		ToggleGroup tg = new ToggleGroup();
		tg.getToggles().addAll(drawButton);//, polyButton);
		
		soilComboBox= new ComboBox<>();
		soilComboBox.setPromptText("Choose Soil");
		soilComboBox.getItems().add(Soil.CLAY);
		soilComboBox.getItems().add(Soil.SANDY);
		soilComboBox.getItems().add(Soil.LOAMY);
		
		Label sunLabel = new Label("Sun");
		
		sun = new Slider();
		sun.setMin(0);
		sun.setMax(2);
		sun.setMinorTickCount(0);
		sun.setMajorTickUnit(1);
		sun.setSnapToTicks(true);
		sun.setShowTickMarks(true);
		sun.setShowTickLabels(true);
		sun.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "Shade";
                if (n < 1.5) return "Partial";
                if (n < 2.5) return "Full";
                return "Full";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Shade":
                        return 0d;
                    case "Partial":
                        return 1d;
                    case "Full":
                        return 2d;

                    default:
                        return 2d;
                }
            }
        });
		
		Label moistureLabel = new Label("Moisture");
		
		moisture = new Slider();
		moisture.setMin(0);
		moisture.setMax(3);
		moisture.setMinorTickCount(0);
		moisture.setMajorTickUnit(1);
		moisture.setSnapToTicks(true);
		moisture.setShowTickMarks(true);
		moisture.setShowTickLabels(true);
		moisture.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "Dry";
                if (n < 1.5) return "Moist";
                if (n < 2.5) return "Wet";
                if (n < 3.5) return "Flooded";
                return "Flooded";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "None":
                        return 0d;
                    case "Partial":
                        return 1d;
                    case "Full":
                        return 2d;
                    case "Flooded":
                    	return 3d;

                    default:
                        return 3d;
                }
            }
        });
		
		Label budgetLabel = new Label("$");
		budget = new TextField();
		budget.setPromptText("Budget");
		HBox budgetBox = new HBox();
		budgetBox.getChildren().addAll(budgetLabel, budget);
		
		undoButton = new Button("Undo");
		undoButton.setOnAction(event -> undoButtonPressed(event));
		
		incButton = new Button("+");
		incButton.setOnAction(event -> incButtonPressed(event));
		decButton = new Button("-");
		decButton.setOnAction(event -> decButtonPressed(event));
		HBox scaleButtonBox = new HBox();
		scaleButtonBox.getChildren().addAll(incButton, decButton);
		
		//Adding to borderpane 
		HBox toolBox = new HBox();
		toolBox.getChildren().addAll(drawButton);//, polyButton);
		
		VBox sideTool = new VBox();
		sideTool.getChildren().addAll(toolBox, soilComboBox, sunLabel, sun,
				moistureLabel, moisture, budgetBox, undoButton, scaleButtonBox);
		
		this.setTop(title);
		this.setLeft(sideTool);
		this.setCenter(canvas);
		this.setBottom(bottomHBox);
	}
	
	public void mousePressed(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		gc.setLineWidth(2d);
		if (drawButton.isSelected()) {
			setColor();
			drawing = true;
			dgc.draw();
			gc.setStroke(Color.BLACK);
			gc.setFill(color);
			gc.beginPath();
			gc.moveTo(e.getX(),e.getY());
		} else {
			errorPopup("Choose a drawing tool");
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		if(drawButton.isSelected() && drawing) {
			dgc.draw();
			gc.lineTo(e.getX(),e.getY());
			gc.stroke();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (drawButton.isSelected() && drawing) {
			drawing = false;
			Point2D.Double point = dgc.draw();
			gc.lineTo(point.getX(), point.getY());
			gc.stroke();
			gc.fill();
			gc.closePath();
		}
	}
	
	public void undoButtonPressed(ActionEvent event) {
		try {
			undo(dgc.undo());
		} catch (NullPointerException e) {
			System.out.println("Nothing to undo");
			buildScaleText();
		}
	}
	
	public void setColor() {
		try {
			switch (soilComboBox.getValue()) {
				case CLAY:
					color = Color.RED; break;
				case SANDY:
					color = Color.CORNSILK; break;
				case LOAMY:
					color = Color.BROWN; break;
				default:
					color = Color.BLACK; break;
			}
		} catch (NullPointerException e) {
			errorPopup("Choose a soil before drawing!");
		}
	}
	
	public void undo(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		buildGrid();
		buildPlots(plots);
		buildScaleText();
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
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public Soil getSoil() {
		return soilComboBox.getValue();
	}
	
	public Sun getSun() {
		if (sun.getValue() == 0d) {
			return Sun.SHADE;
		} else if (sun.getValue() == 1d) {
			return Sun.PARTSUN;
		} else if (sun.getValue() == 2d) {
			return Sun.FULLSUN;
		}
		return Sun.SHADE;
	}
	
	public Moisture getMoisture() {
		if (moisture.getValue() == 0d) {
			return Moisture.DRY;
		} else if (moisture.getValue() == 1d) {
			return Moisture.MOIST;
		} else if (moisture.getValue() == 2d) {
			return Moisture.WET;
		} else if (moisture.getValue() == 3d) {
			return Moisture.FLOODED;
		}
		return Moisture.DRY;
	}
	
	public int getBudget() {
		if (budget.getText().isBlank()) {
			return 0;
		}
		return Integer.valueOf(budget.getText());
	}
	
	public void errorPopup(String error) {
		Stage errorPopup = new Stage();
		errorPopup.setTitle("Error");
		Label errorLabel = new Label(error);
		Scene scene = new Scene(errorLabel);
		errorPopup.setScene(scene);
		errorPopup.show();
	}
	
	private void buildGrid() {
		gc.setLineWidth(1d);
		gc.setStroke(Color.YELLOW);
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0f, 0f, canvasWidth, canvasHeight);
		for (double i=1; i*scale < canvasWidth; i++) {
			gc.strokeLine(i*scale, 0, i*scale, canvasHeight);
			gc.strokeLine(0, i*scale, canvasWidth, i*scale);
		}
	}
	
	private void buildScaleText() {
		gc.setLineWidth(4);
		gc.setStroke(color.BLACK);
		gc.strokeLine(scale, scale, scale+scale, scale);
		gc.setLineWidth(1);
		gc.strokeLine(scale, scale-scale/3, scale, scale+scale/3);
		gc.strokeLine(scale+scale, scale-scale/3, scale+scale, scale+scale/3);
		gc.strokeText(Integer.valueOf((int) scale) + "ft", scale, scale+scale/2);
	}
	
	public void buildPlots(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		System.out.println(plots);
		for (ArrayList<Point2D.Double> points: plots.get(Soil.CLAY)) {
			System.out.println("clay");
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.RED);
			drawPlot(points);
		}
		for (ArrayList<Point2D.Double> points: plots.get(Soil.SANDY)) {
			System.out.println("sandy");
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.CORNSILK);
			drawPlot(points);
		}
		for (ArrayList<Point2D.Double> points: plots.get(Soil.LOAMY)) {
			System.out.println("loamy");
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.BROWN);
			drawPlot(points);
		}
	}
	
	private void drawPlot(ArrayList<Point2D.Double> points) {
		gc.beginPath();
		gc.setLineWidth(2d);
		gc.moveTo(points.get(0).getX(), points.get(0).getY());
		for (int i=0; i<points.size(); i++) {
			gc.lineTo(points.get(i).getX(), points.get(i).getY());
			gc.stroke();
		}
		gc.lineTo(points.get(0).getX(), points.get(0).getY());
		gc.stroke();
		gc.fill();
		gc.closePath();
	}
	
	public void incButtonPressed(ActionEvent e) {
		scale(1d);
	}
	
	public void decButtonPressed(ActionEvent e) {
		scale(-1d);
	}
	
	public void scale(double change) {
		scale += change;
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = dgc.scale(change);
		buildGrid();
		buildPlots(plots);
		buildScaleText();
	}

	public double getScale() {
		return this.scale;
	}
}
