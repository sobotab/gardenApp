package pkgView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pkgController.DrawGardenController;
import pkgController.Moisture;
import pkgController.Soil;
import pkgController.Sun;

/**
 * @author Benjamin Sobota
 * View class to put the drawing tool onto the screen and collect input from the user
 */
/**
 * @author Benjamin Sobota
 *
 */
public class DrawGardenView extends BorderPane {
	
	/**
	 * Controller for scaling and storing information
	 */
	DrawGardenController dgc;
	
	/**
	 * xScale: the number of pixels between columns in the grid
	 * yScale: the number of pixels between the rows in the grid
	 * scale: the smaller of canvasWidth and canvasHeight in pixels
	 * rows: the number of rows on the grid
	 * columns: the number of columns on the grid
	 * canvasWidth: width of the canvas
	 * canvasHeight: height of the canvas
	 */
	double xScale, yScale, scale, rows, columns,
		canvasWidth, canvasHeight;
	/**
	 * Canvas that is resizable by changing the size of its parent
	 */
	ResizableCanvas canvas;
	/**
	 * Tool used in canvas for drawing
	 */
	GraphicsContext gc;
	/**
	 * drawButton: tool for tracking the users mouse
	 */
	ToggleButton drawButton;
	/**
	 * Drop down menu to let the user select a soil
	 */
	ComboBox<Soil> soilComboBox;
	/**
	 * Sliders to let user choose sun and moisture levels
	 */
	Slider sun, moisture;
	/**
	 * Entry box that allows user to enter a budget
	 */
	TextField budget;
	/**
	 * undoButton: button to let user undo last drawing
	 * incButton: button to allow the user to zoom in
	 * decButton: button to allow the user to zoom out
	 */
	Button undoButton, incButton, decButton;
	/**
	 * Current fill color based on the soil
	 */
	Color color;
	/**
	 * line width in pixels for drawing on the screen
	 */
	double lineWidth;
	/**
	 * current: point where the users mouse currently is
	 */
	Point2D.Double current;
	/**
	 * drawing: boolean to set the start point in the model
	 * shapeDon: boolean to add the outline to plots in the model
	 */
	boolean drawing, shapeDone;
	
	/**
	 * Initializes javafx components, sets handlers, and adds nodes to the BorderPane
	 * @param passes in View to communicate with global variables
	 */
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
		canvas = new ResizableCanvas();
		canvas.heightProperty().addListener(event -> resize());
		gc = canvas.getGraphicsContext2D();
		rows = 15;
		columns = 15;
		
		buildGrid();
		buildScaleText();
		
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
	
	/**
	 * Sets up the drawing tool: color, fill, beginPath(), and sets the currents coords
	 * @param event the coordinates of the mouse
	 */
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
	
	/**
	 * Called when mouse is clicked and moving.
	 * Sets the current, sends coords to model, draws a line
	 * @param event mouse coordinates
	 */
	public void mouseDragged(MouseEvent e) {
		setCurrent(e.getX(), e.getY());
		if(drawButton.isSelected() && drawing) {
			dgc.draw();
			gc.lineTo(e.getX(),e.getY());
			gc.stroke();
		}
	}
	
	/**
	 * Called when mouse released
	 * Closes shape and fills it with appropriate color in respect to soil
	 * @param e
	 */
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
	
	/**
	 * Handler for the undo button
	 * Error handling for the case that nothing is drawn
	 * @param event
	 */
	public void undoButtonPressed(ActionEvent event) {
		try {
			undo(dgc.undo());
		} catch (NullPointerException e) {
			System.out.println("Nothing to undo");
			buildScaleText();
		}
	}
	
	/**
	 * Sets the parameter color to a Color depending on the
	 * soil in the drop down menu that is selected.
	 * Error handling for if there is no soil chosen.
	 */
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
	
	/**
	 * Redraws the entire canvas with plots that has one fewer plot
	 * than it did previously. Draws grid, draws plots, draws the scale
	 * @param plots
	 */
	public void undo(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		resizeCanvas();
		buildGrid();
		buildPlots(plots);
		buildScaleText();
	}

	/**
	 * Used for transferring user mouse data to the model
	 * @return the current mouse x and y
	 */
	public Point2D.Double getCurrent() {
		return current;
	}

	/**
	 * Sets the current point to a Point2D.Doble to be transferred
	 * to the model
	 * @param x coordinate of the mouse
	 * @param y coordinate of the mouse
	 */
	public void setCurrent(double x, double y) {
		current = new Point2D.Double(x, y);
	}
	
	/**
	 * Ensures canvas is clicked before drawing
	 * and sending data to the model
	 * @param drawing sets drawing to this
	 */
	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
	
	/**
	 * Returns if the user is drawing or not
	 * @return current state of drawing
	 */
	public boolean getDrawing() {
		return drawing;
	}
	
	/**
	 * Returns soil value in drop down menu
	 * @return current value of drop down menu
	 */
	public Soil getSoil() {
		return soilComboBox.getValue();
	}
	
	/**
	 * Returns the amount of sun in the slider
	 * @return the sun value in the slider
	 */
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
	
	/**
	 * Returns the amount of moisture in the slider
	 * @return the moisture value in the slider
	 */
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
	
	/**
	 * Returns the value in the budget entry box.
	 * If the value is not an integer, it returns 0, 
	 * or else it returns the integer inside the box
	 * @return a 0 or integer entered in the budget entry box
	 */
	public int getBudget() {
		if (budget.getText().isBlank()) {
			return 0;
		}
		return Integer.valueOf(budget.getText());
	}
	
	/**
	 * Whenever called creates error pop up message
	 * @param error text to be displayed in a popup
	 */
	public void errorPopup(String error) {
		Stage errorPopup = new Stage();
		errorPopup.setTitle("Error");
		Label errorLabel = new Label(error);
		Scene scene = new Scene(errorLabel);
		errorPopup.setScene(scene);
		errorPopup.show();
	}
	
	/**
	 * Builds a grid by drawing lines on the canvas.
	 * Will only build squares.
	 */
	private void buildGrid() {
		gc.setLineWidth(1d);
		gc.setStroke(Color.YELLOW);
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0f, 0f, canvasWidth, canvasHeight);
		double tmpScale = (canvasHeight < canvasWidth) ? scale/rows : scale/columns;
		for (double i=0.0; i<rows; i++) {
			gc.strokeLine(0.0,i*tmpScale,canvasWidth,i*tmpScale);
		}
		for (double i=0.0; i<columns; i++) {
			gc.strokeLine(i*tmpScale,0.0,i*tmpScale,canvasHeight);
		}
	}
	
	/**
	 * Builds the scale and its text in the appropriate spot on the canvas
	 */
	private void buildScaleText() {
		double tmpScale = scale / rows;
		gc.setLineWidth(4);
		gc.setStroke(color.BLACK);
		gc.strokeLine(tmpScale, tmpScale, tmpScale*2.0, tmpScale);
		gc.setLineWidth(1);
		gc.strokeLine(tmpScale, tmpScale-tmpScale/3, tmpScale,  tmpScale+tmpScale/3);
		gc.strokeLine(tmpScale*2.0, tmpScale-tmpScale/3, tmpScale*2.0,  tmpScale+tmpScale/3);
		gc.strokeText(Integer.valueOf(9) + "ft", tmpScale, tmpScale+20.0);
	}
	
	/**
	 * Builds the plots on coordinates
	 * @param plots a data structure containing all of the plots
	 */
	public void buildPlots(HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots) {
		for (ArrayList<Point2D.Double> points: plots.get(Soil.CLAY)) {
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.RED);
			drawPlot(points);
		}
		for (ArrayList<Point2D.Double> points: plots.get(Soil.SANDY)) {
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.CORNSILK);
			drawPlot(points);
		}
		for (ArrayList<Point2D.Double> points: plots.get(Soil.LOAMY)) {
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.BROWN);
			drawPlot(points);
		}
	}
	
	/**
	 * Draws a single plot using coords from an ArrayList
	 * @param points coords in the ArrayList
	 */
	private void drawPlot(ArrayList<Point2D.Double> points) {
		gc.beginPath();
		gc.setLineWidth(2d);
		gc.moveTo(points.get(0).getX(), points.get(0).getY());
		for (int i=0; i<points.size(); i++) {
			gc.lineTo(points.get(i).getX(), points.get(i).getY());
			gc.stroke();
		}
		gc.stroke();
		gc.fill();
		gc.closePath();
	}
	
	/**
	 * Calls scale with a positive 1 whenver the + button is pressed
	 * @param event the + button is pressed
	 */
	public void incButtonPressed(ActionEvent e) {
		scale(1d);
	}
	
	/**
	 * Calls scale with a negative 1 whenever the - button is pressed
	 * @param event the - button is pressed
	 */
	public void decButtonPressed(ActionEvent e) {
		scale(-1d);
	}
	
	/**
	 * Changes current size of the canvas if its parent has changed
	 * Scales the points based off the model. Redraws the canvas
	 * @param change value to increase, decrease, or maintain the scale
	 */
	public void scale(double change) {
		rows-=change;
		columns-=change;
		resizeCanvas();
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = dgc.scale(columns, rows);
		buildGrid();
		buildPlots(plots);
		buildScaleText();
	}
	
	/**
	 * Updates the size of the canvas whenever its parent node is 
	 * changed
	 */
	public void resize() {
		resizeCanvas();
		HashMap<Soil, Stack<ArrayList<Point2D.Double>>> plots = dgc.scale(columns, rows);
		buildGrid();
		buildPlots(plots);
		buildScaleText();
		System.out.println(plots);
	}
	
	/**
	 * @return the minimum of the canvasHeight and the canvasWidth
	 */
	public double getScale() {
		return scale;
	}
	
	/**
	 * @return the current height of the canvas
	 */
	public double getCanvasHeight() {
		return this.canvasHeight;
	}
	
	/**
	 * @return the current width of the canvas
	 */
	public double getCanvasWidth() {
		return this.canvasWidth;
	}
	
	public void resizeCanvas() {
		if (canvas.getHeight() < canvas.getWidth()) {
			columns = (int)((rows/canvas.getHeight())*canvas.getWidth());
			canvasWidth = (canvas.getHeight()/rows)*columns;
			canvasHeight = canvas.getHeight();
		} else {
			rows = (int)((columns/canvas.getWidth())*canvas.getHeight());
			canvasHeight = (canvas.getWidth()/columns)*rows;
			canvasWidth = canvas.getWidth();
		}
		if (canvasHeight > 0 && canvasWidth > 0) {
			canvas.resize(canvasWidth, canvasHeight);
		}
		scale = ((canvasHeight < canvasWidth) ? canvasHeight : canvasWidth);
	}
	
	public double getRows() {
		return rows;
	}
	
	public double getColumns() {
		return columns;
	}
}
