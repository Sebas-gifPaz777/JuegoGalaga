package controller;

import main.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Box;
import model.Player;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    @FXML
    private Canvas canvas;
    private final int SPEED = 4;
    private Main main;
    private GraphicsContext gc;
    private Box box;
    private Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

    }

    @FXML
    private void keyPressed(KeyEvent e){
        if (e.getCode() == KeyCode.LEFT)
            player.setX(SPEED);
        else if(e.getCode() == KeyCode.RIGHT)
            player.setY(-SPEED);
        else if (e.getCode() == KeyCode.UP)
            player.setY(-SPEED);
        else if (e.getCode() == KeyCode.DOWN)
            player.setY(SPEED);

        if (e.getCode() == KeyCode.SPACE)
            //Acción del disparo

        if (e.getCode() == KeyCode.A)
            player.setX(SPEED);
        else if(e.getCode() == KeyCode.D)
            player.setY(-SPEED);
        else if (e.getCode() == KeyCode.W)
            player.setY(-SPEED);
        else if (e.getCode() == KeyCode.S)
            player.setY(SPEED);

        String imgPath1 = "../resources/Marcianito.png";
        Player player = main.getBox().getPlayer();
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgPath1)));
        gc.drawImage(img1, player.getX(), player.getY(), 200, 200);
    }

    public Main getMain(){
        return main;
    }

    public void setMain(Main main){
        this.main = main;
    }
/*
    public void paint(){
        Platform.runLater(()->{
            String imgPath = "../resources/FondoGalaga.jpeg";
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgPath)));
            gc.drawImage(img, 0, 0, 1200, 850);

            String imgPath1 = "../resources/Marcianito.png";
            Player player = main.getBox().getPlayer();
            Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgPath1)));
            gc.drawImage(img1, 0, 0, 200, 200);

        });
    }



    public void start(){
        Thread thread = new Thread(()->{
           while (true){
               try{
                   pause();
               }catch (Exception e){
                   e.printStackTrace();
               }

           }
        });
        thread.start();
    }

    public void pause(){
        try {
            Thread.sleep(30);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

 */
}
