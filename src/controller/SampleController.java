package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Shot;
import model.Sprite;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
	
	private boolean play;
	
	private ArrayList<Sprite> enemies;
	
	private ArrayList<Shot> bullets;
	
	private SampleController main= this;
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
          
          bullets = new ArrayList<>();
          
          background = new Sprite("resources/FondoGalaga.jpeg");
          background.position.set(375, 275); //Posición del fondo

          spaceShip = new Sprite("resources/Navecita.png");
          spaceShip.position.set(340, 530); //Posición inicial
          spaceShip.velocity.set(40, 0); //Velocidad inicial

          background.render(gc); //Dibuja el fondo en el canvas
          gc.setStroke(Color.RED);
          gc.setFont(new Font(30));
          gc.strokeText("PRESIONE R PARA INICIAR", 200, 340);
          gc.drawImage(new Image("resources/Logito.png"), 200, 100, 400, 200);
         
    }

    @FXML
    private void keyPressed(KeyEvent e) {
    	if(e.getCode().toString().equals("LEFT") || e.getCode().toString().equals("A")){ //Si se presiona la tecla izquierda
    		spaceShip.position.set(spaceShip.position.x - 12, spaceShip.position.y);  //Mueve la nave a la izquierda
    	}
    	if(e.getCode().toString().equals("RIGHT") || e.getCode().toString().equals("D")){ //Si se presiona la tecla derecha
    		spaceShip.position.set(spaceShip.position.x + 12, spaceShip.position.y); //Mueve la nave a la derecha
    	}
    	if(e.getCode().toString().equals("SPACE") ){ //Si se presiona la tecla ESPACIO
    		shoot(spaceShip); //Dispara
    	}
    	if(e.getCode().toString().equals("R") && n1==0) {
    		contiUpdate();
    	}

    }
    
    private void shoot(Sprite s) {
		bullets.add(s.shoot());
	}

	public boolean checkEnemy(Sprite enemy) {
    	if(enemy.position.y+70 >= spaceShip.position.y+13) {
    		if(enemy.position.x <= spaceShip.position.x+70 && enemy.position.x >= spaceShip.position.x
    				|| enemy.position.x+70 >= spaceShip.position.x && enemy.position.x+70 <= spaceShip.position.x+70 ) {
    			gameOver();
    			gc.drawImage(new Image("resources/Explosion.png"), spaceShip.position.x, spaceShip.position.y, 70, 70);
    		}
    		else if(enemy.position.y+70 >= canvas.getHeight()) {
    			gameOver();
    			gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
    		}
    	}
    	return play;
    }
    
    public void gameOver() {
		play=false;
		background.render(gc);
		for(Sprite enemy1 : enemies) {
			gc.drawImage(enemy1.image, enemy1.position.x, enemy1.position.y, 70, 70);
		}
	}
    
    public void updateSprites() {
    	if(play) {
    		spaceShip.update();
            //Dibujar el fondo
            background.render(gc);
            //Dibujar la nave

            gc.drawImage(spaceShip.image, spaceShip.position.x, spaceShip.position.y, 70, 70);
            for(Sprite enemy:enemies) {
            	gc.drawImage(enemy.image,  enemy.position.x,  enemy.position.y, 70, 70);
            }
            
            for(Shot s : bullets) {
            	s.update();
            	s.render(gc);
            }
            
            if(n1==0) {
            	for(Sprite enemy:enemies) {
            		enemy.start();
            		System.out.println("ok");
            	}
            	n1++;
            }
    	}
    }
    
    public void contiUpdate() {
    	Thread thread = new Thread(() ->{
    		while(play) {
    			try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			Platform.runLater(()->{
    				updateSprites();
    			});
    		}
    	}); thread.start();
    }
}
