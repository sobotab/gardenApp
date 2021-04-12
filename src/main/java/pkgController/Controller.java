package pkgController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pkgModel.Model;
import pkgView.View;

public class Controller extends Application {
	
	Model model;
	View view;
	
	public Controller() {
		//May or may not make view first and use it here. 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage theStage) {
        view = new View(theStage);
		model = new Model();
        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.show();
    }
	
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	public EventHandler getHandlerForDraw() {
		return event -> draw((MouseEvent) event);
	}
	
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	public void drag(MouseEvent event) {}
	
	public void draw(MouseEvent event) {}
	
	public void press(MouseEvent event) {}
	
	void update() {}
}
