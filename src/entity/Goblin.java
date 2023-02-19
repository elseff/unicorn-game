package entity;
import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Goblin extends GameObject {

    private boolean isFreeze = false;

    Image image;
    Image imageFreeze;

    public Goblin(int x, int y, Game game) {
        super(x, y, game);
        setWidth(90);
        setHeight(90);
        setId(ObjectTypeID.GOBLIN);
        try {
            image = ImageIO.read(new File("images/goblin.png"))
                    .getSubimage(0,0,64,64);
            imageFreeze = ImageIO.read(new File("images/env.png"))
                    .getSubimage(480,159,30,30);
        }
        catch (Exception e) {
            System.out.println("error load file " + e.getMessage());
        }
    }

    @Override
    public void move() {

        int goblinX = getX();
        int goblinY = getY();

        //проверка на заморозку - разморозку
        if (getGame().getMouseManager().isPressMouse) {
            int mouseX = getGame().getMouseManager().userPressX;
            int mouseY = getGame().getMouseManager().userPressY;

            if ((mouseX>=goblinX && mouseX<=goblinX+getWidth()) &&
                (mouseY>=goblinY && mouseY<=goblinY+getHeight())) {

                getGame().getMouseManager().isPressMouse = false;
                isFreeze = !isFreeze;
            }
        }

        if (isFreeze) return;

        int playerX = getGame().getPlayer().getX();
        int playerY = getGame().getPlayer().getY();

        int deltX = Math.abs(playerX-goblinX);
        int deltY = Math.abs(playerY-goblinY);

        if ((deltX<300) && (deltY<300)) {
            if (goblinX > playerX) goblinX--;
            if (goblinX < playerX) goblinX++;
            if (goblinY > playerY) goblinY--;
            if (goblinY < playerY) goblinY++;
        }
        //установка гоблину новых координат
        setX(goblinX);
        setY(goblinY);
    }

    @Override
    public void render(Graphics g) {
        g.drawRect(getX(),getY(),getWidth(),getHeight());
        if (isFreeze)
            g.drawImage(imageFreeze,getX(),getY(),getWidth(),getHeight(),null);
        else
        g.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }
}
