package model;

import controller.SampleController;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite extends Thread {
    public Vector position; // Position of the sprite
    public Vector velocity; // Velocity of the sprite
    public double rotation; // Rotation of the sprite un degrees
    public Rectangle boundary; // Boundary of the sprite
    public Image image; // Image of the sprite
    public boolean next;
    public SampleController main;
    
    boolean dead;
    String type;


    public Sprite(){ //Constructor
        this.position = new Vector(); //Posición inicial
        this.velocity = new Vector(); //Velocidad inicial
        this.rotation = 0; //Rotación inicial
        this.boundary = new Rectangle(); //Borde inicial
        this.next=true;
        
        dead = false;      
    }
    public Sprite(double x, double y, double w, double h) {
    	boundary = new Rectangle(x, y, w, h);
    	position = new Vector(x, y);
    }
    

    public Sprite(String imageFileName){ //Constructor con parámetros
        this(); //Llamada al constructor sin parámetros
        setImage(imageFileName); //Llamada al método setImage para cargar la imagen
    }

    public void setImage(String imageFileName){ //Método para cargar la imagen del sprite
        this.image = new Image(imageFileName); //Cargar la imagen
        this.boundary.setSize(this.image.getWidth(), this.image.getHeight()); //Tamaño del sprite
    }

    public Rectangle getBoundary(){ //Método para obtener el borde del sprite
        this.boundary.setPosition(this.position.x, this.position.y); //Posición del borde
        return this.boundary; //Devolver el borde
    }
    public void setBoundary(Rectangle boundary) {
		this.boundary = boundary;
	}

    public boolean overLaps(Sprite other){ //Método para comprobar si el sprite está sobre otro  (true si está)
        return this.getBoundary().overLaps(other.getBoundary()); //Devolver true si está sobre otro  (false si no está)
    }
    public void setY(double y) {
    	this.position.y = y;
    	this.boundary.y = y;
    }

    public void wrap(double screenWidth) { //Método para que el sprite no se salga del canvas
        //Para que el sprite rebote en el canvas
        if (this.position.x <= 0) { //Si la posición x es menor que 0
            this.position.x = 0; //La posición x es igual a la anchura del canvas
        } else if (this.position.x+70 >= screenWidth) { //Si la posición x es mayor que la anchura del canvas
            this.position.x = screenWidth-70; //La posición x es igual a 0
        }
    }

    public void update(){ //Método para actualizar el sprite (deltaTime es el tiempo que ha pasado desde el último frame)
        this.wrap(750); //Tamaño del canvas (1200, 850)
    }

    public void render(GraphicsContext gc){ //Método para dibujar el sprite (gc es el contexto gráfico) (dibuja el sprite en la posición actual)
        gc.save(); //Guardar el contexto gráfico  (para poder restaurarlo después) / (para poder dibujar el sprite en la posición actual)
        gc.translate(this.position.x, this.position.y); //Trasladar el contexto gráfico a la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(x, y)
        gc.rotate(this.rotation); //Rotar el contexto gráfico en la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(angulo)
        gc.translate(-this.image.getWidth()/2, -this.image.getHeight()/2); //Trasladar el contexto gráfico a la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(x, y)
        gc.drawImage(this.image, 0, 0); //Dibujar el sprite en la posición actual  (para poder dibujar el sprite en la posición actual)
        gc.restore(); //Restaurar el contexto gráfico  (para poder dibujar el sprite en la posición actual)
    }
    
    @Override
	public void run() {
		try {
			Thread.sleep(2000);
			while(next) {
				position.set(position.x,position.y+10);
				Platform.runLater(() ->{
					next=main.checkEnemy(this);
				});
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    public void setMain(SampleController main) {
    	this.main=main;
    } 
    
    
    public Shot shoot() {
		//return new Shot( getBoundary().getX() + getBoundary().width/2 - Shot.SIZE/2, getBoundary().getY() - Shot.SIZE );
    	return new Shot( getBoundary().getX() +30, getBoundary().getY() - Shot.SIZE );
    	
    }
}
