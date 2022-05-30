package model;

public class Rectangle {

    //(x, y) representa la esquina izquierda del rectangulo

    double x;
    double y;
    double width;  //ancho
    double height;  //alto

    public Rectangle(){ //Constructor por defecto
        this.setPosition(0, 0); //(0,0) representa la esquina superior izquierda del rectangulo
        this.setSize(1, 1); //(1,1) representa el ancho y alto del rectangulo  (1,1)
    }

    public Rectangle(double x, double y, double w, double h){ //Constructor con parametros
        this.setPosition(x, y);  //(x,y) representa la esquina superior izquierda del rectangulo
        this.setSize(w, h); //(w,h) representa el ancho y alto del rectangulo
    }

    public void setPosition(double x, double y){ //Metodo para cambiar la posicion del rectangulo
        this.x = x;
        this.y = y;
    }
    
    public void setSize(double w, double h){ //Metodo para cambiar el tamaño del rectangulo
        this.width = w;
        this.height = h;
    }

    public boolean overLaps(Rectangle other){ //Metodo para verificar si dos rectangulos se superponen
        //Para que no se sobre ponga de otro objeto.
        //Si el otro objeto esta dentro del rectangulo, no se sobrepone.
        //Primer caso: A la izquierda de otro
        //Segundo caso: A la derecha de otro
        //Tercer caso: Encima de otro
        //Cuarto caso: Debajo de otro
        boolean noOverLap = this.x + this.width < other.x || other.x + other.width < this.x  //Primer caso  (A la izquierda de otro)
                // ||  (A la derecha de otro)  ||  (Encima de otro)  ||  (Debajo de otro)
                || this.y + this.height < other.y || other.y + other.height < this.y;  //Si alguno de los casos se cumple, no se sobrepone
        return !noOverLap; //Si no se sobrepone, retorna true, si se sobrepone, retorna false
    }
    
    public double getX() {
		return x;
	}
    public double getY() {
		return y;
	}
}
