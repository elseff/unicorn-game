package entity;
import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Chest extends GameObject {

    Image image;
    Image imageOpen;

    private int amount = 1000;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private boolean isOpen = false;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Chest(int x, int y, Game game) {
        super(x, y, game);
        setWidth(130);
        setHeight(130);
        setId(ObjectTypeID.CHEST);
        try {
            //image = ImageIO.read(getClass().getClassLoader().getResource("chest.png"));
            image = ImageIO.read(getClass().getClassLoader().getResource("chest.png"))
                    .getSubimage(0, 30, 450, 450);
            imageOpen = ImageIO.read(getClass().getClassLoader().getResource("chest.png"))
                    .getSubimage(470, 30, 450, 450);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void render(Graphics g) {
        if (isOpen) g.drawImage(imageOpen, getX(), getY(), getWidth(), getHeight(), null);
        else
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        g.drawRect(getX(),getY(),getWidth(),getHeight());
    }
}
