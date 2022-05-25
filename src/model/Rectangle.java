package model;

public class Rectangle {

    //(x, y) representa la esquina izquierda del rectangulo

    double x;
    double y;
    double width;
    double height;

    public Rectangle(){
        this.setPosition(0, 0);
        this.setSize(1, 1);
    }

    public Rectangle(double x, double y, double w, double h){
        this.setPosition(x, y);
        this.setSize(w, h);
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setSize(double w, double h){
        this.width = w;
        this.height = h;
    }

    public boolean overLaps(Rectangle other){
        //A la izquierda de otro
        //A la derecha de otro
        //Encima de otro
        //Debajo de otro
        boolean noOverLap = this.x + this.width < other.x || other.x + other.width < this.x
                || this.y + this.height < other.y || other.y + other.height < this.y;
        return !noOverLap;
    }
}
