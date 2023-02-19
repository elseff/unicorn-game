package entity;

import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject {
    boolean isRunning = false; //признак движения героя

    int counter = 0;
    int napr = 2; //-1 - влево, 1 - вверх, 2 - вправо, 3 - вниз

    BufferedImage image;

    public Player(int x, int y, Game game) {
        super(x, y, game);

        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("unicorn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setWidth(120);
        setHeight(120);
        setId(ObjectTypeID.PLAYER);
    }

    @Override
    public void move() {
        setDeltX(0);
        setDeltY(0);
        isRunning = false;
        if (getGame().getKeyManager().isRight) {
            napr = 2;
            setDeltX(3);
            isRunning = true;
        }
        if (getGame().getKeyManager().isLeft) {
            napr = -1;
            setDeltX(-3);
            isRunning = true;
        }
        if (getGame().getKeyManager().isUP) {
            napr = 1;
            setDeltY(-3);
            isRunning = true;
        }
        if (getGame().getKeyManager().isDown) {
            napr = 3;
            setDeltY(3);
            isRunning = true;
        }
        setX(getX() + getDeltX());
        setY(getY() + getDeltY());
        checkCollision();
    }

    private void checkCollision() {
        for (GameObject item : getGame().getGameObjectList()) {
            //совпадение объектов по оси x
            if ((getX()<(item.getX()+item.getWidth())) &&
               ((getX()+getWidth())>item.getX()))
            //совпадение по оси Y
            if ((getY() < (item.getY() + item.getHeight())) &&
               ((getY() + getHeight()) > item.getY())) {
                //System.out.println("Совпадение с объектом "+ item.getId());
                if (item.getId() == ObjectTypeID.GOBLIN) {
                    getGame().getInfo().minusLife(0.4);
                }
if((item.getId() == ObjectTypeID.HEALTH)){
    getGame().getInfo().plusLife(0.2);
}
                if (item.getId() == ObjectTypeID.STONE) {
                    getGame().getInfo().addStone();
                    getGame().getListRemoveObjects().add(item);
                }
                //встретили сундук
                if (item.getId() == ObjectTypeID.CHEST) {
                    Chest chest = (Chest)item;
                    //если сундук открыт
                    if (chest.isOpen()) {
                        int amount = chest.getAmount();
                        if (amount > 0) {
                            getGame().getInfo().addAmount(amount);
                            chest.setAmount(0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawRect(getX(),getY(),getWidth(),getHeight());
        counter++;
        if (counter == 30) counter = 0;
        int m = (int)counter/10; //состояние 0,1,2
        //если объект не движется - берем 0 состояние
        if (!isRunning) m = 0;
        //вправо
        if (napr == 2) g.drawImage(image.getSubimage(m*81+4,156,78,78),
                getX(),getY(),getWidth(),getHeight(),null);
         else //влево
        if (napr == -1) g.drawImage(image.getSubimage(m*81+3,78,78,78),
                getX(),getY(),getWidth(),getHeight(),null);
         else
        if (napr == 1) g.drawImage(image.getSubimage(m*81, 234, 80, 78),
                getX(), getY(), getWidth(), getHeight(), null);
         else //вниз
            g.drawImage(image.getSubimage(m*81, 0, 80, 78),
                    getX(), getY(), getWidth(), getHeight(), null);
    }
}
