package main;

import controller.SampleController;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import model.Sprite;

import java.util.Objects;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../controller/Sample.fxml")));
			BorderPane root = (BorderPane)loader.load();

			Scene scene = new Scene(root);

			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../styles/application.css")).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Galaga game");
			primaryStage.setResizable(false);
			Canvas canvas = new Canvas(1200, 850);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			root.setCenter(canvas);


			Sprite background = new Sprite("resources/FondoGalaga.jpeg");
			background.position.set(600, 425);


			String imgPath = "resources/Navecita.png";
			Image img = new Image(imgPath);
			Sprite spaceShip = new Sprite();
			spaceShip.velocity.set(50, 0);
			spaceShip.position.set(575, 750);
			SampleController controller = new SampleController();
			spaceShip.update(1/60.0);
			background.render(gc);
			gc.drawImage(img, spaceShip.position.x, spaceShip.position.y, 70, 70);


			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
