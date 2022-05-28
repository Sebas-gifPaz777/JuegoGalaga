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
        try{
            paint();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paint(){
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                background = new Sprite("resources/FondoGalaga.jpeg");
                background.position.set(600, 425); //Posición del fondo

                spaceShip = new Sprite("resources/Navecita.png");
                spaceShip.position.set(575, 750); //Posición inicial
                spaceShip.velocity.set(40, 0); //Velocidad inicial

                background.render(gc); //Dibuja el fondo en el canvas
            }
        };
        at.start();
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        keyPressedList = new ArrayList<>();

        String keyName = e.getCode().toString(); //Obtiene el nombre de la tecla presionada
        //Previene añadir duplicados a la lista
        if(!keyPressedList.contains(keyName)){  //Si no contiene la tecla presionada en la lista
            keyPressedList.add(keyName); //Agrega la tecla presionada a la lista
        }

        keyName = e.getCode().toString();
        //Elimina las teclas ya presionadas en la lista
        keyPressedList.remove(keyName);

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
                spaceShip.update(1/60.0);
                //Dibujar el fondo
                background.render(gc);
                //Dibujar la nave
                gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
            }
        };
        gameLoop.start();
    }
}
