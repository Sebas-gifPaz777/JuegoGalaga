package controller;

import javafx.animation.AnimationTimer;
import main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Sprite;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    @FXML
    private Canvas canvas;
    private final int SPEED = 4;
    private Main main;
    private GraphicsContext gc;
    public Sprite background = new Sprite("resources/FondoGalaga.jpeg");
    public Image spaceShipImage = new Image("resources/Navecita.png");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
    }

    public void setBackground(){
        background.position.set(600, 425);
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        Sprite spaceShip = new Sprite();
        spaceShip.velocity.set(50, 0);
        spaceShip.position.set(575, 750);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Procesar el input del usuario
                if(e.getCode() == KeyCode.LEFT)
                    spaceShip.rotation -= 3;
                else if(e.getCode() == KeyCode.RIGHT)
                    spaceShip.rotation += 3;
                else if (e.getCode() == KeyCode.UP){
                    spaceShip.velocity.setLength(150);
                    spaceShip.velocity.setAngle(spaceShip.rotation);
                } else if (e.getCode() == KeyCode.DOWN){
                   spaceShip.velocity.setLength(0);
                }


                if (e.getCode() == KeyCode.SPACE)
                    System.out.println("S");

                if (e.getCode() == KeyCode.A)
                    System.out.println("A");
                else if(e.getCode() == KeyCode.D)
                    System.out.println("D");
                else if (e.getCode() == KeyCode.W)
                    System.out.println("W");
                else if (e.getCode() == KeyCode.S)
                    System.out.println("S");

                spaceShip.update(1/60.0);
                background.render(gc);

                gc.drawImage(spaceShipImage, spaceShip.position.x, spaceShip.position.y, 70, 70);
            }
        };
        gameLoop.start();
    }

    public Main getMain(){

        return main;
    }

    public void setMain(Main main){
        this.main = main;
    }

}
