package model;

public class Box {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 850;

    private Player player;

    public Box(){
        setPlayer(new Player(250, 250, 25));
        player.setDeltaY(2);
        player.setDeltaX(1);

    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void update(){
        if (player.getDeltaX() > 0 && player.getX() + player.getDeltaX() + player.getR() > WIDTH)
            player.setDeltaX(-1*player.getDeltaX());

        if (player.getDeltaX() < 0 && player.getX() + player.getDeltaX() - player.getR() < 0)
            player.setDeltaX(-1*player.getDeltaX());

        if (player.getDeltaY() > 0 && player.getY() + player.getDeltaY() + player.getR() > HEIGHT)
            player.setDeltaY(-1*player.getDeltaY());

        if (player.getDeltaY() < 0 && player.getY() + player.getDeltaY() - player.getR() < 0)
            player.setDeltaY(-1*player.getDeltaY());

        player.move();
    }

}
