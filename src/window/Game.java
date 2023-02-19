package window;

import entity.*;
import manage.GameState;
import manage.KeyManager;
import manage.MouseManager;
import statistics.Info;
import world.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Game implements Runnable {

	private MouseManager mouseManager;
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	//метод получения объекта главного героя
	public Player getPlayer() {
		return player;
	}

	private GameState state = GameState.RUN;
	Image winImage;
	Image gameoverImage;


	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	PanelInfo panelInfo;

	/* info - хранит статистику по игре
		(жизнь героя, кол-во собранных предметов)
	*/
	private Info info = new Info();
	public Info getInfo() {
		return info;
	}

	private List<GameObject> listRemoveObjects = new ArrayList<>();
	public List<GameObject> getListRemoveObjects() {
		return listRemoveObjects;
	}

	private List<GameObject> gameObjectList = new ArrayList<>();
	public List<GameObject> getGameObjectList() {
		return gameObjectList;
	}

	private Level level;
	public Level getLevel() {
		return level;
	}

	//private Chest chest = new Chest(500, 100, this);
	private KeyManager keyManager;
	public KeyManager getKeyManager() {
		return keyManager;
	}

	private Player player = new Player( 200,200,this);

	private Display display;
	
	public int width, height;
	public String title;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running = false;

	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;

		try {
			winImage = ImageIO.read(new File("images/win.jpg"));
			gameoverImage = ImageIO.read(new File("images/gameover.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	//!!!!!Инициализация
	private void init() {
		display = new Display(this.title, width, height);
		keyManager = new KeyManager();
		display.getJFrame().addKeyListener(keyManager);
		level = new Level(this);
		panelInfo = new PanelInfo(this);
		mouseManager = new MouseManager();
		display.getCanvas().addMouseListener(mouseManager);

	}

	//!!!ОБНОВЛЕНИЕ
	private void move() {
		if (state == GameState.RUN) {
			for (GameObject item : gameObjectList) item.move();
			player.move();
			for (GameObject removeItem : listRemoveObjects)
				gameObjectList.remove(removeItem);
			level.move();
		}
	}

	//!!ПРОРИСОВКА
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Рисуем!!!
		///////////////////////////////////////////////////////////////
		if (state == GameState.RUN) {
			g.clearRect(0, 0, width, height);
			level.render(g);
			for (GameObject item : gameObjectList) item.render(g);
			//stone1.render(g);
			//chest.render(g);
			player.render(g);
			panelInfo.render(g);
		}
		else
			if (state == GameState.WIN)
				g.drawImage(winImage, 0,0,width,height,null);
		else
			if (state == GameState.GAMEOVER)
				g.drawImage(gameoverImage, 0,0,width,height,null);

		///////////////////////////////////////////////////////////////


		//end draw
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		
		init(); //инициализация
		
		int fps = 60;
		double kolNS = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		//бесконечный цикл пока running
		while (running) {
			now = System.nanoTime();

			delta += (now - lastTime) / kolNS;
			lastTime = now;
			
			if (delta >= 1) {
			//	System.out.println("60fps");
				move();  //пересчет объектов
				render();  //перерисовка объектов
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
