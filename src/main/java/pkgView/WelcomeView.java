package pkgView;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class WelcomeView extends BorderPane {
	Image backgroundImage;
	BackgroundSize bSize;
	
	public WelcomeView() {}
	
	public Image getBackgroundImage() {
		return this.backgroundImage;
	}
	public void setBackgroundImage(Image image) {
		this.setBackground(new Background(new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
	}
}