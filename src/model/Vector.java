package model;

public class Vector {

    public double x;
    public double y;

    public Vector(){
        this.set(0,0);
    }

    public Vector(double x, double y){
        this.set(x, y);
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public void multiply(double m){
        this.x *= m;
        this.y *= m;
    }

    public double getLength(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void setLength(double L){
        double currentLength = this.getLength();
        //Si se llega a divivr por cero

        if (currentLength == 0){
            this.set(L, 0);
            return;
        }else{
            //Escalar vector para tener longitud 1
            this.multiply(1/currentLength);

            //Escalar vector para tener longitud L
            this.multiply(L);
        }
    }

    public double getAngle(){

        return Math.atan2(this.y, this.x);
    }

    public void setAngle(double angleDegrees){
        double L = this.getLength();
        double angleRadians = Math.toDegrees(angleDegrees);
        this.x = L * Math.cos(angleRadians);
        this.y = L * Math.sin(angleRadians);
    }

}
