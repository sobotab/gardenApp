package pkgView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.StringConverter;
import pkgController.DrawGardenController;
import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

public class DrawGardenView extends BorderPane {
	
	DrawGardenController dgc;
	final int CANVASHEIGHT = 500;
	final int CANVASWIDTH = 500;
	Canvas canvas;
	GraphicsContext gc;
	Polygon polygon;
	ToggleButton drawButton, polyButton;
	ComboBox<Soil> soilComboBox;
	Slider sun, moisture;
	TextField budget;
	Button undoButton;
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
		canvas = new Canvas(CANVASHEIGHT, CANVASWIDTH);
		gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(lineWidth);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(0f, 0f, CANVASWIDTH, CANVASHEIGHT);
		
		polygon = new Polygon();
		drawing = false;
		
		canvas.setOnMousePressed(event -> mousePressed((MouseEvent) event));
		canvas.setOnMouseDragged(event -> mouseDragged((MouseEvent) event));
		canvas.setOnMouseReleased(event -> mouseReleased((MouseEvent) event));
		
		
		//Making sidetool		
		drawButton = new ToggleButton("Draw");
		polyButton = new ToggleButton("Polygon");
		ToggleGroup tg = new ToggleGroup();
		tg.getToggles().addAll(drawButton, polyButton);
		
		soilComboBox= new ComboBox<>();
		soilComboBox.setPromptText("Choose Soil");
		//loamy, clay, sandy
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
		
		budget = new TextField();
		budget.setPromptText("Budget");
		UnaryOperator<TextFormatter.Change> filter = change -> {
			if (change.getControlNewText().startsWith("$")) {
				return change;
			} else {
				return null;
			}
		};
		TextFormatter<String> formatter = new TextFormatter<>(filter);
		budget.setTextFormatter(formatter);
		
		undoButton = new Button("Undo");
		undoButton.setOnAction(event -> undoButtonPressed(event));
		
		//Adding to borderpane 
		HBox toolBox = new HBox();
		toolBox.getChildren().addAll(drawButton, polyButton);
		
		VBox sideTool = new VBox();
		sideTool.getChildren().addAll(toolBox, soilComboBox, sunLabel, sun,
				moistureLabel, moisture, budget, undoButton);
		
		this.setTop(title);
		this.setLeft(sideTool);
		this.setCenter(canvas);
		this.setBottom(bottomHBox);
	}
	
	public void mousePressed(MouseEvent e) {
		setColor();
		setCurrent(e.getX(), e.getY());
		if (drawButton.isSelected()) {
			drawing = true;
			dgc.draw();
			gc.setStroke(Color.BLACK);
			gc.setFill(color);
			gc.beginPath();
			gc.moveTo(e.getX(),e.getY());
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
			System.out.println(point.toString());
			gc.lineTo(point.getX(), point.getY());
			gc.stroke();
			gc.fill();
			gc.closePath();
		}
	}
	
	public void undoButtonPressed(ActionEvent event) {
		undo(dgc.undo());
	}
	
	public void setColor() {
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
	}
	
	public void undo(ArrayList<Point2D.Double> points) {
		gc.setStroke(Color.LIGHTGREEN);
		gc.setFill(Color.LIGHTGREEN);
		gc.moveTo(points.get(0).getX(), points.get(0).getY());
		for (int i=0; i<points.size(); i++) {
			gc.lineTo(points.get(i).getX(), points.get(i).getY());
			gc.stroke();
		}
		gc.fill();
		gc.closePath();
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
}
