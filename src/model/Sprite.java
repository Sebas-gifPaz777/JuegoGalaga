package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    public Vector position;
    public Vector velocity;
    public double rotation; //En grados
    public Rectangle boundary;
    public Image image;

    public Sprite(){
        this.position = new Vector();
        this.velocity = new Vector();
        this.rotation = 0;
        this.boundary = new Rectangle();
    }

    public Sprite(String imageFileName){
        this();
        setImage(imageFileName);
    }

    public void setImage(String imageFileName){
        this.image = new Image(imageFileName);
        this.boundary.setSize(this.image.getWidth(), this.image.getHeight());
    }

    public Rectangle getBoundary(){
        this.boundary.setPosition(this.position.x, this.position.y);
        return this.boundary;
    }

    public boolean overLaps(Sprite other){
        return this.getBoundary().overLaps(other.getBoundary());
    }

    public void wrap(double screenWidth, double screenHeight){

        double halfWidth = this.image.getWidth()/2;
        double halfHeight = this.image.getHeight()/2;
    //Metodo para evitar que la nave desaparezca, así que aparece al otro lado de la pantalla
        if (this.position.x + halfWidth < 0)
            this.position.x = halfWidth;
        if (this.position.x > halfWidth)
            this.position.x = -halfWidth;
        if (this.position.y + halfHeight< 0)
            this.position.y = screenHeight + halfHeight;
        if (this.position.y > screenHeight+ halfHeight)
            this.position.y = -halfHeight;
    }

    public void update(double deltaTime){
        //Actualizar la posición, dependiendo la velocidad
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
        this.wrap(1200, 850); //Tamaño del canvas
    }

    public void render(GraphicsContext gc){
        gc.save();
        gc.translate(this.position.x, this.position.y);
        gc.rotate(this.rotation);
        gc.translate(-this.image.getWidth()/2, -this.image.getHeight()/2);
        gc.drawImage(this.image, 0, 0);
        gc.restore();
    }


}