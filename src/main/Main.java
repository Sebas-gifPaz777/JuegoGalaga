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
import model.Box;

import java.util.Objects;

public class Main extends Application {

	private Box box;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../fxml/Sample.fxml")));
			BorderPane root = (BorderPane)loader.load();

			//SampleController controller = loader.getController();
			//controller.setMain(this);
			//controller.initialize();
			Scene scene = new Scene(root);

			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../styles/application.css")).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Galaga game");
			primaryStage.setResizable(false);

			Canvas canvas = new Canvas(1200, 850);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			String imgPath = "../resources/FondoGalaga.jpeg";
			Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgPath)));
			gc.drawImage(img, 0, 0, 1200, 850);

			primaryStage.show();
			root.getChildren().add(canvas);
			//controller.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Box getBox(){
		return box;
	}

	public void setBox(Box box){
		this.box = box;
	}
}
