package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import model.Sprite;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
	
	private boolean play;
	private AnimationTimer gameLoop;
	private ArrayList<Sprite> enemies;
	private SampleController main= this;
	private AnimationTimer at;
	private int n1=0;
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
        play=true;
        paint();
    }

    public void paint(){
    	
    	  int min=0;
          int max=70;
          int miny=0;
          int maxy=70;
          int n=1;
          //Crea enemigos en espacios aleatorios del canvas (x,y)
          enemies = new ArrayList<>();  //Crea una lista de enemigos
          for (int i = 0; i < 2; i++) {  //Crea 10 enemigos
          	for (int j = 0; j < 5; j++) {
          		Sprite enemy = new Sprite();  //Crea un enemigo
          		enemy.position.set(Math.random()*(max-min+1)+min, Math.random()*(maxy-miny+1)+miny);  //Posición aleatoria
          		enemy.velocity.set(Math.random() * 10, Math.random() * 10); //Velocidad aleatoria
          		enemy.image= new Image("resources/Marcianito.png"); //Asigna la imagen
          		enemy.boundary.setSize(70, 70);
          		enemy.setMain(main);
          		enemies.add(enemy); //Agrega el enemigo a la lista
          		min=(140*n);
          		max=(140*(n+1))-70;
          		n++;
          	}
          	miny=140;
          	maxy=280;
          	max=70;
          	min=0;
          	n=1;
          }
          
       at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                background = new Sprite("resources/FondoGalaga.jpeg");
                background.position.set(375, 275); //Posición del fondo

                spaceShip = new Sprite("resources/Navecita.png");
                spaceShip.position.set(340, 530); //Posición inicial
                spaceShip.velocity.set(40, 0); //Velocidad inicial

                background.render(gc); //Dibuja el fondo en el canvas
            }
        };
        at.start();
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        gameLoop = new AnimationTimer() {  //Crea una animación de juego
            @Override
            public void handle(long now) { //Método que se ejecuta cada vez que se actualiza la animación
                if(e.getCode().toString().equals("LEFT") || e.getCode().toString().equals("A")){ //Si se presiona la tecla izquierda
                    spaceShip.position.set(spaceShip.position.x - 12, spaceShip.position.y);  //Mueve la nave a la izquierda
                }
                if(e.getCode().toString().equals("RIGHT") || e.getCode().toString().equals("D")){ //Si se presiona la tecla derecha
                    spaceShip.position.set(spaceShip.position.x + 12, spaceShip.position.y); //Mueve la nave a la derecha
                }
                spaceShip.update(1/150.0);
                //Dibujar el fondo
                background.render(gc);
                //Dibujar la nave

                gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
                for(Sprite enemy:enemies) {
                	gc.drawImage(enemy.image,  enemy.position.x,  enemy.position.y, 70, 70);
                }
            }
        };
       
        gameLoop.start();
        if(n1==0) {
        	for(Sprite enemy:enemies) {
        		enemy.start();
        	}
        	n1++;
        }
    }
    
    public boolean checkEnemy(Sprite enemy) {
    	if(enemy.position.y+70 >= spaceShip.position.y+12) {
    		if(enemy.position.x <= spaceShip.position.x+70 && enemy.position.x >= spaceShip.position.x
    				|| enemy.position.x+70 >= spaceShip.position.x && enemy.position.x+70 <= spaceShip.position.x+70 ) {
    			gameOver();
    			gc.drawImage(new Image("resources/explosion.png"), spaceShip.position.x, spaceShip.position.y, 70, 70);
    		}
    		else if(enemy.position.y+70 >= canvas.getHeight()) {
    			gameOver();
    			gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
    		}
    	}
    	return play;
    }
    
    public void gameOver() {
    	gameLoop.stop();
    	at.stop();
    	spaceShip.position.set(340, 530);
		play=false;
		background.render(gc);
		for(Sprite enemy1 : enemies) {
			gc.drawImage(enemy1.image, enemy1.position.x, enemy1.position.y, 70, 70);
		}
	}
}
