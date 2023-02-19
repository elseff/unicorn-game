package entity;
import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
public class Health extends GameObject {
    Image image;
    public Health(int x, int y, Game game) {
        super(x, y, game);
        setWidth(120);
        setHeight(110);
        setId(ObjectTypeID.HEALTH);
        try {
            image = ImageIO.read(new File("images/apt.png"))
                    .getSubimage(0,0,470,438);
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
