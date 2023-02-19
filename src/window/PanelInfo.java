package window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class PanelInfo {
    private Game game;
    private int width;
    private int height;

    private Image imageStone;

    public PanelInfo(Game refGame) {
        this.game = refGame;
        width = game.width;
        height = 60;

        try {
            imageStone = ImageIO.read(new File("images/env.png"))
                    .getSubimage(320,30,30,30);
        }
        catch (Exception e) {
            System.out.println("error load file " + e.getMessage());
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 255, 255));
        g.setFont(new Font("Arial", 1,40));
        g.drawString(String.valueOf(game.getInfo().getAmount()), 400, game.height-15);

        g.setColor(new Color(10,0,0,100));
        g.fillRect(1,game.height-height+1, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(0,game.height-height, width,height);

        //отображение статистики по камням
        g.drawImage(imageStone,60, game.height-height+2, 50,50,null);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", 1,40));
        g.drawString(String.valueOf(game.getInfo().getCountStone()), 120, game.height-15);

        //уровень жизни героя
        g.setColor(Color.WHITE);
        g.drawRect(200, game.height-40, 100, 20);

        g.setColor(Color.RED);
        g.fillRect(201, game.height-39,99,19);
        g.setColor(Color.GREEN);
        g.fillRect(201, game.height-39,(int)game.getInfo().getLife(),19);
    }
}
