package world;

import entity.*;
import manage.GameState;
import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Level {

    public final int countLevels = 3; //количество уровней игры
    private int currentLevel;    //текущий уровень игры
    private Game game;

    //массив изображений для уровней
    public Image[] images = new Image[countLevels];

    public Level(Game game) {
        this.game = game;
        currentLevel = 1;
        try {
            images[0] = ImageIO.read(new File("images/level1.jpg"));
            images[1] = ImageIO.read(new File("images/level2.jpg"));
            images[2] = ImageIO.read(new File("images/level3.jpg"));
        } catch (Exception e) {
            System.out.println("error load image" + e.getMessage());
        }
        changeLevel(currentLevel);
    }

    public void changeLevel(int level) {
        //очистка списка объектов предыдущего уровня
        game.getGameObjectList().clear();
        if (level == 1) {
            Health h1 = new Health(300,100,null);
            game.getGameObjectList().add(h1);
            Goblin goblin1 = new Goblin(500,50,game);
            game.getGameObjectList().add(goblin1);
            Goblin goblin2 = new Goblin(300,50,game);
            game.getGameObjectList().add(goblin2);
            Chest chest1 = new Chest(500, 100, game);
            game.getGameObjectList().add(chest1); //можно добавить один объект
            for (int i = 0; i < 10; i++) {
                Stone stone1 = new Stone(100 + (i*70),400, game);
                game.getGameObjectList().add(stone1); //можно добавить один объект
            }
        } else
        if (level == 2) {
            Health h1 = new Health(300,100,null);
            game.getGameObjectList().add(h1);
            Chest chest1 = new Chest(500, 100, game);
            game.getGameObjectList().add(chest1);
            game.getPlayer().setX(50);
            game.getPlayer().setY(200);
            Goblin goblin1 = new Goblin(500,50,game);
            game.getGameObjectList().add(goblin1);
            Goblin goblin2 = new Goblin(200,150,game);
            game.getGameObjectList().add(goblin2);
            for (int i = 0; i < 10; i++) {
                Stone stone1 = new Stone(100 + (i*70),400, game);
                game.getGameObjectList().add(stone1); //можно добавить один объект
            }
        }  //конец игры
        if(level==3){
            Health h1 = new Health(300,100,null);
            game.getGameObjectList().add(h1);
            game.getPlayer().setX(100);
            game.getPlayer().setY(200);
            Goblin goblin1 = new Goblin(500,50,game);
            game.getGameObjectList().add(goblin1);
            Goblin goblin2 = new Goblin(200,150,game);
            game.getGameObjectList().add(goblin2);
            for (int i = 0; i < 15; i++) {
                Stone stone1 = new Stone(100 + (i*70),400, game);
                game.getGameObjectList().add(stone1); //можно добавить один объект
            }
        }
        }


    public void move() {

if(game.getInfo().getLife()<=0)game.setState(GameState.GAMEOVER);
        boolean nextLevel = false;
        if (currentLevel == 1) {
            //получить сундук с 1 уровня игры
            for (GameObject gameObject : game.getGameObjectList()) {
                if ((gameObject.getId() == ObjectTypeID.CHEST) && (game.getInfo().getCountStone() == 10))
                {
                    Chest chest = (Chest)gameObject;
                    ((Chest)gameObject).setOpen(true);
                    if (chest.getAmount()== 0)nextLevel=true;
                }

            }
        }
        if (currentLevel==2){
            for (GameObject gameObject : game.getGameObjectList()) {
                if ((gameObject.getId() == ObjectTypeID.CHEST) && (game.getInfo().getCountStone() == 10))
                {
                    Chest chest = (Chest)gameObject;
                    ((Chest)gameObject).setOpen(true);
                    if (chest.getAmount()== 0)nextLevel=true;
                }

            }
           if(game.getInfo().getLife()<0)game.setState(GameState.GAMEOVER);

           // if (game.getInfo().getCountStone() == 10)game.setState(GameState.WIN);
            //return;
        }
        if(currentLevel==3) {
            if (game.getInfo().getCountStone()>9)game.setState(GameState.WIN);
            if (game.getInfo().getLife() < 0) game.setState(GameState.GAMEOVER);
        }

        if (nextLevel){
            currentLevel++;
                    game.getInfo().reset();
changeLevel(currentLevel);
        }
    }




    public void render(Graphics g) {
        if ((currentLevel<0) || (currentLevel>countLevels)) return;
        g.drawImage(images[currentLevel-1],0,0,
                game.width,game.height, null);
    }
}
