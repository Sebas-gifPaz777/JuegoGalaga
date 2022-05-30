package model;

public class Vector {

    public double x; // x-coordinate
    public double y; // y-coordinate

    public Vector(){ //Constructor de vector nulo (0,0)
        this.set(0,0); //Asignar valores a x e y del vector
    }

    public Vector(double x, double y){ //Constructor de vector con x e y
        this.set(x, y); //Asignar valores a x e y del vector
    }

    public void set(double x, double y){
        this.x = x; //Asignar x al vector
        this.y = y; //Asignar valores a x e y del vector
    }

    public void add(double dx, double dy){ //Sumar vector a otro vector (x1+x2, y1+y2)
        this.x += dx; //Sumar dx a x del vector (x = x + dx)
        this.y += dy; //Sumar dy a y del vector (y = y + dy)
    }

    public void multiply(double m){ //Multiplicar vector por un escalar m (m*x, m*y)
        this.x *= m; //Multiplicar x por m (m = 1/L)
        this.y *= m; //Multiplicar por m para tener longitud L (m = L) (m = 1) (m = L) (m = 1/L) (m = L/L)
    }

    public double getLength(){ //Longitud del vector
        return Math.sqrt(this.x * this.x + this.y * this.y); //Longitud = raiz cuadrada de x^2 + y^2
    }

    public void setLength(double L){ //Longitud del vector
        double currentLength = this.getLength(); //Longitud actual del vector
        //Si se llega a divivr por cero

        if (currentLength == 0){//Si el vector es nulo (0,0) no se puede dividir
            this.set(L, 0); //Si el vector es nulo, se le asigna el vector (L,0)
            return;
        }else{
            //Escalar vector para tener longitud 1
            this.multiply(1/currentLength); // 1/L = L/currentLength -> L = currentLength * 1/L

            //Escalar vector para tener longitud L
            this.multiply(L); //Multiplicar por L para tener longitud L
        }
    }

    public double getAngle(){ //Angulo del vector en grados (0-360)
        return Math.atan2(this.y, this.x); //Angulo del vector en coordenadas cartesianas (0-2PI) -> (0-360)
    }

    public void setAngle(double angleDegrees){
        double L = this.getLength(); //Longitud del vector
        double angleRadians = Math.toDegrees(angleDegrees); //Angulo en radianes del vector en grados (0-360) -> (0-2PI)
        this.x = L * Math.cos(angleRadians); //Longitud * cos(angulo) = x del vector en coordenadas cartesianas (0-L) -> (0-2PI) -> (0-360)
        this.y = L * Math.sin(angleRadians); //Longitud * sin(angulo) = y del vector en coordenadas cartesianas (0-L) -> (0-2PI) -> (0-360)
    }
    
}
