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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        //Para que el sprite se mueva por el canvas
    }

    public Main getMain(){

        return main;
    }

    public void setMain(Main main){
        this.main = main;
    }

}
