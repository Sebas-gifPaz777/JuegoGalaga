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
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../fxml/Sample.fxml")));
			BorderPane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../styles/application.css")).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Galaga game");
			primaryStage.setResizable(false); //No permite cambiar el tamaño de la ventana
			//Inicia el canvas
			Canvas canvas = new Canvas(1200, 850);  //Tamaño del canvas (1200, 850)
			GraphicsContext gc = canvas.getGraphicsContext2D();
			root.setCenter(canvas);  //Agrega el canvas al BorderPane (root)
			//Crea el fondo del juego
			Sprite background = new Sprite("resources/FondoGalaga.jpeg");
			background.position.set(600, 425); //Posición del fondo

			//Crea el jugador/nave
			Sprite spaceShip = new Sprite();
			String imgPath = "resources/Navecita.png";
			Image img = new Image(imgPath);  //Crea la imagen
			spaceShip.position.set(575, 750); //Posición inicial
			spaceShip.velocity.set(40, 0); //Velocidad inicial
			spaceShip.image = img; //Asigna la imagen

			//Crea enemigos en espacios aleatorios del canvas (x,y)
			ArrayList<Sprite> enemies = new ArrayList<>();  //Crea una lista de enemigos
			for (int i = 0; i < 10; i++) {  //Crea 10 enemigos
				Sprite enemy = new Sprite();  //Crea un enemigo
				enemy.position.set(Math.random() * 1200, Math.random() * 850);  //Posición aleatoria
				enemy.velocity.set(Math.random() * 10, Math.random() * 10); //Velocidad aleatoria
				enemy.image = new Image("resources/Marcianito.png"); //Asigna la imagen
				enemies.add(enemy); //Agrega el enemigo a la lista
			}

			//Muestra enemigos en el canvas
			for (Sprite enemy : enemies) { //Recorre la lista de enemigos
				gc.drawImage(enemy.image, enemy.position.x, enemy.position.y); //Muestra enemigo en el canvas
			}

			//Lista para teclas presionadas
			ArrayList<String> keyPressedList = new ArrayList<>();

			root.requestFocus();  //Solicita el foco del teclado
			root.setOnKeyPressed(  //Evento cuando se presiona una tecla
			(KeyEvent e)->{
				String keyName = e.getCode().toString(); //Obtiene el nombre de la tecla presionada
				//Previene añadir duplicados a la lista
				if(!keyPressedList.contains(keyName)){  //Si no contiene la tecla presionada en la lista
					keyPressedList.add(keyName); //Agrega la tecla presionada a la lista
				}
			});
			root.requestFocus();

			root.setOnKeyReleased(
			(KeyEvent e)->{
				String keyName = e.getCode().toString();
				//Elimina las teclas ya presionadas en la lista
				keyPressedList.remove(keyName);
			});

			root.requestFocus();
			AnimationTimer gameLoop = new AnimationTimer() {  //Crea una animación de juego
				@Override
				public void handle(long now) { //Método que se ejecuta cada vez que se actualiza la animación
					//Procesa los eventos
					if(keyPressedList.contains("LEFT") || keyPressedList.contains("A"))  //Si se presiona la tecla izquierda o A
						spaceShip.position.set(spaceShip.position.x - 6, spaceShip.position.y);  //Mueve la nave a la izquierda
					if(keyPressedList.contains("RIGHT") || keyPressedList.contains("D")) //Si se presiona la tecla derecha o D
						spaceShip.position.set(spaceShip.position.x + 6, spaceShip.position.y); //Mueve la nave a la derecha
					if(keyPressedList.contains("UP") || keyPressedList.contains("W")) //Si se presiona la tecla arriba o W
						spaceShip.position.set(spaceShip.position.x, spaceShip.position.y - 6); //Mueve la nave hacia arriba
					if(keyPressedList.contains("DOWN") || keyPressedList.contains("S")) //Si se presiona la tecla abajo o S
						spaceShip.position.set(spaceShip.position.x, spaceShip.position.y + 6); //Mueve la nave hacia abajo
					if(keyPressedList.contains("SPACE")){ //Si se presiona la tecla espacio
						System.out.println("Space pressed"); //Debe disparar
						//TODO: Disparar bala
					}

					spaceShip.update(1/60.0);  //Actualiza la nave en 60 fps

					background.render(gc); //Dibuja el fondo en el canvas

					gc.drawImage(img, spaceShip.position.x, spaceShip.position.y, 70, 70); //Dibuja la nave en el canvas
				}
			};
			gameLoop.start(); //Inicia la animación
			primaryStage.show(); //Muestra la ventana

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
