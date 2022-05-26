package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    public Vector position; // Position of the sprite
    public Vector velocity; // Velocity of the sprite
    public double rotation; // Rotation of the sprite un degrees
    public Rectangle boundary; // Boundary of the sprite
    public Image image; // Image of the sprite

    public Sprite(){ //Constructor
        this.position = new Vector(); //Posición inicial
        this.velocity = new Vector(); //Velocidad inicial
        this.rotation = 0; //Rotación inicial
        this.boundary = new Rectangle(); //Borde inicial
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

    public boolean overLaps(Sprite other){ //Método para comprobar si el sprite está sobre otro  (true si está)
        return this.getBoundary().overLaps(other.getBoundary()); //Devolver true si está sobre otro  (false si no está)
    }

    public void wrap(double screenWidth, double screenHeight) { //Método para que el sprite no se salga del canvas

        //Para que el sprite no pase los límites de la pantalla
        if (this.position.x < 0)//Si la posición x es menor que 0
            this.position.x = screenWidth; //La posición x es igual a la anchura de la pantalla
        if (this.position.x > screenWidth)//Si la posición x es mayor que la anchura de la pantalla
            this.position.x = 0; //La posición x es igual a 0
        if (this.position.y < 0)//Si la posición y es menor que 0
            this.position.y = screenHeight; //La posición y es igual a la altura de la pantalla
        if (this.position.y > screenHeight)//Si la posición y es mayor que la altura de la pantalla
            this.position.y = 0; //La posición y es igual a 0

        double halfWidth = this.image.getWidth() / 2; //Obtener la mitad de la anchura del sprite  (anchura del sprite / 2)
        double halfHeight = this.image.getHeight() / 2; //Obtener la mitad de la altura del sprite  (altura del sprite / 2)
        if (this.position.x + halfWidth < 0) //Si la posición x + la mitad de la anchura del sprite es menor que 0  (si el sprite se sale de la pantalla)
            this.position.x = screenWidth + halfWidth; //La posición x es igual a la anchura de la pantalla + la mitad de la anchura del sprite
        if (this.position.x > screenWidth + halfWidth) //Si la posición x + la mitad de la anchura del sprite es mayor que la anchura de la pantalla  (si el sprite se sale de la pantalla)
            this.position.x = -halfWidth; //La posición x es igual a - la mitad de la anchura del sprite
        if (this.position.y + halfHeight < 0) //Si la posición y + la mitad de la altura del sprite es menor que 0  (si el sprite se sale de la pantalla)
            this.position.y = screenHeight + halfHeight; //La posición y es igual a la altura de la pantalla + la mitad de la altura del sprite
        if (this.position.y > screenHeight + halfHeight) //Si la posición y + la mitad de la altura del sprite es mayor que la altura de la pantalla  (si el sprite se sale de la pantalla)
            this.position.y = -halfHeight; //La posición y es igual a - la mitad de la altura del sprite
    }

    public void update(double deltaTime){ //Método para actualizar el sprite (deltaTime es el tiempo que ha pasado desde el último frame)
        //Actualizar la posición, dependiendo la velocidad y el tiempo que ha pasado desde el último frame
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime); //Añadir la velocidad al vector de posición
        this.wrap(1200, 850); //Tamaño del canvas (1200, 850)
        //Para que el sprite no se mueva más rápido que la velocidad máxima del sprite  (si la velocidad es mayor que la velocidad máxima del sprite)
        this.velocity.x = 0;
        this.velocity.y = 0;

    }

    public void render(GraphicsContext gc){ //Método para dibujar el sprite (gc es el contexto gráfico) (dibuja el sprite en la posición actual)
        gc.save(); //Guardar el contexto gráfico  (para poder restaurarlo después) / (para poder dibujar el sprite en la posición actual)
        gc.translate(this.position.x, this.position.y); //Trasladar el contexto gráfico a la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(x, y)
        gc.rotate(this.rotation); //Rotar el contexto gráfico en la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(angulo)
        gc.translate(-this.image.getWidth()/2, -this.image.getHeight()/2); //Trasladar el contexto gráfico a la posición del sprite  (para poder dibujar el sprite en la posición actual)  //(x, y)
        gc.drawImage(this.image, 0, 0); //Dibujar el sprite en la posición actual  (para poder dibujar el sprite en la posición actual)
        gc.restore(); //Restaurar el contexto gráfico  (para poder dibujar el sprite en la posición actual)
    }
}
