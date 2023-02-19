package entity;
import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
public class Stone extends GameObject {
    Image image;
    public Stone(int x, int y, Game game) {
        super(x, y, game);
        setWidth(40);
        setHeight(40);
        setId(ObjectTypeID.STONE);
        try {
            image = ImageIO.read(new File("images/env.png"))
                    .getSubimage(320,30,30,30);
        }
        catch (Exception e) {
            System.out.println("error load file " + e.getMessage());
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawRect(getX(),getY(),getWidth(),getHeight());
        g.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }
}
