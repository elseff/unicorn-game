package entity;

import window.Game;

import java.awt.*;

public class GameObject {
    private int x; //позиция объекта по x
    private int y; //позиция объекта по y
    private int width; //ширина объекта
    private int height; //высота объекта
    private Game game; //ссылка на объект данных
    private ObjectTypeID id; //идентификатор объекта
    private int deltX = 0; //шаг движения объекта по x
    private int deltY = 0; //шаг движение объекта по y

    public GameObject(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public void move() {
        x = x + deltX;
        y = y + deltY;
    }

    public void render(Graphics g) { }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ObjectTypeID getId() {
        return id;
    }

    public void setId(ObjectTypeID id) {
        this.id = id;
    }

    public int getDeltX() {
        return deltX;
    }

    public void setDeltX(int deltX) {
        this.deltX = deltX;
    }

    public int getDeltY() {
        return deltY;
    }

    public void setDeltY(int deltY) {
        this.deltY = deltY;
    }
}
