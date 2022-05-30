package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot extends Sprite{
	
	public static final int SIZE = 10;
	
	boolean dead;
	int speed = 10;
	double posX;
	double posY;
	
	public Shot(double x, double y) {
		super(x, y, SIZE, SIZE);
		posX = x;
		posY = y;
	}
	
	public void update() {
		posY -= speed;
		super.setY(posY);
	}
	
	public void render(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(posX, posY, SIZE, SIZE);
	}
	
}
