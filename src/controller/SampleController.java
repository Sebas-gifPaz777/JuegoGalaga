package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyEvent;
import model.Sprite;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Sprite spaceShip;

    private Sprite background;

    private ArrayList<String> keyPressedList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        paint();
    }

    public void paint(){
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                background = new Sprite("resources/FondoGalaga.jpeg");
                background.position.set(375, 225); //Posición del fondo

                spaceShip = new Sprite("resources/Navecita.png");
                spaceShip.position.set(340, 450); //Posición inicial
                spaceShip.velocity.set(40, 0); //Velocidad inicial

                background.render(gc); //Dibuja el fondo en el canvas
            }
        };
        at.start();
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        AnimationTimer gameLoop = new AnimationTimer() {  //Crea una animación de juego
            @Override
            public void handle(long now) { //Método que se ejecuta cada vez que se actualiza la animación
                if(e.getCode().toString().equals("LEFT") || e.getCode().toString().equals("A")){ //Si se presiona la tecla izquierda
                    spaceShip.position.set(spaceShip.position.x - 12, spaceShip.position.y);  //Mueve la nave a la izquierda
                }
                if(e.getCode().toString().equals("RIGHT") || e.getCode().toString().equals("D")){ //Si se presiona la tecla derecha
                    spaceShip.position.set(spaceShip.position.x + 12, spaceShip.position.y); //Mueve la nave a la derecha
                }
                spaceShip.update(1/144.0);
                //Dibujar el fondo
                background.render(gc);
                //Dibujar la nave

                gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
            }
        };
        gameLoop.start();
    }
}
