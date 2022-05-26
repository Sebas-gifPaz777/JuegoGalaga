package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import model.Sprite;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../controller/Sample.fxml")));
			BorderPane root = (BorderPane) loader.load();

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

			Sprite spaceShip = new Sprite();
			String imgPath = "resources/Navecita.png";
			Image img = new Image(imgPath);
			spaceShip.position.set(575, 750);
			spaceShip.velocity.set(40, 0);
			spaceShip.image = img;

			//Generar enemigos en espacios aleatorios del canvas (x,y)
			ArrayList<Sprite> enemies = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				Sprite enemy = new Sprite();
				enemy.position.set(Math.random() * 1200, Math.random() * 850);
				enemy.velocity.set(Math.random() * 10, Math.random() * 10);
				enemy.image = new Image("resources/Marcianito.png");
				enemies.add(enemy);
			}

			//Mostrar enemigos en el canvas
			for (Sprite enemy : enemies) {
				gc.drawImage(enemy.image, enemy.position.x, enemy.position.y);
			}

			ArrayList<String> keyPressedList = new ArrayList<String>();

			root.requestFocus();
			root.setOnKeyPressed(
			(KeyEvent e)->{
				String keyName = e.getCode().toString();
				//Previene añadir duplicados a la lista
				if(!keyPressedList.contains(keyName)){
					keyPressedList.add(keyName);
				}
			});
			root.requestFocus();
			root.setOnKeyReleased(
			(KeyEvent e)->{
				String keyName = e.getCode().toString();
				//Elimina las teclas ya presionadas en la lista
				if (keyPressedList.contains(keyName))
					keyPressedList.remove(keyName);
			});

			root.requestFocus();
			AnimationTimer gameLoop = new AnimationTimer() {

				@Override
				public void handle(long now) {
					//Procesa los eventos
					if(keyPressedList.contains("LEFT")){
						spaceShip.position.set(spaceShip.position.x - 5, spaceShip.position.y);
					}
					if(keyPressedList.contains("RIGHT")){
						spaceShip.position.set(spaceShip.position.x + 5, spaceShip.position.y);
					}
					if(keyPressedList.contains("UP")){
						spaceShip.position.set(spaceShip.position.x, spaceShip.position.y - 5);
					}
					if(keyPressedList.contains("DOWN")){
						spaceShip.position.set(spaceShip.position.x, spaceShip.position.y + 5);
					}
					if(keyPressedList.contains("SPACE")){
						System.out.println("Space pressed");
					}

					//Actualiza el estado de la nave
					spaceShip.update(1/60.0);

					//Dibuja el fondo
					background.render(gc);

					//Dibuja la nave
					gc.drawImage(img, spaceShip.position.x, spaceShip.position.y, 70, 70);
				}
			};
			gameLoop.start();
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
