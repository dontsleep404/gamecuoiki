package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import custom.CSocket;
import model.Ball;
import model.Entity;
import model.Player;
import ui.IRender;
import utils.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Game extends JPanel implements KeyListener, IRender{
	private static final long serialVersionUID = 1L;
	private boolean keys[];
	private Player player;
	private List<Entity> entities;
	private CSocket socket;
	private Point mouse;
	private World world;
	public Game(String host, int port, String name) {
		setFocusable(true);
		addKeyListener(this);
		keys = new boolean[1000];
		player = new Player(name);
		entities = new ArrayList<Entity>();		
		world = new World(this);
		world.setRadius(1000);		
		for(int i = 0 ; i < 100 ; i ++) {
			spawnBall();
		}
	}
	public void spawnBall() {
		Random rand = new Random();
		float r = rand.nextFloat() * world.getRadius();
		float an = (float) (rand.nextFloat() * Math.PI * 2);
		entities.add(new Ball((float) (r * Math.cos(an)), (float) (r * Math.sin(an)), 10, false));
	}
	public void init() {		
		run();
	}
	public void run() {
		new Thread(()->{
			try {
				long lastUpdate = new Date().getTime();
				while(true) {
					long now = new Date().getTime();
					float delta = (now - lastUpdate) / 1000f;
					lastUpdate = now;
					update(delta);
					repaint();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Helper.smoothPaint(g2d);
		g2d.setBackground(new Color(54,55,59));
		g2d.clearRect(0, 0, getWidth(), getHeight());
		render(g2d);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void update(float delta) {
		mouse = this.getMousePosition();		
		world.update(delta);
		for(Entity e : entities) {
			e.update(delta);
		}
		// player update
		if(keys[KeyEvent.VK_SPACE]) {
			float an1 = (float) Math.atan2(mouse.getY() - getHeight() / 2, mouse.getX() - getWidth() / 2);
			player.setMotion((float)(200 * Math.cos(an1)), (float) (200 * Math.sin(an1)));
		}
		if(dist(player.getPosX(), player.getPosY(), 0, 0) >= world.getRadius()) {
			float an1 = (float) Math.atan2(player.getPosY(), player.getPosX());
			float an2 = (float) (an1 + Math.PI);
			float an3 = (float) (Math.atan2(player.getMotionY(), player.getMotionX()) + Math.PI);
			float an4 = 2*an2 - an3;
			float speed = dist(player.getMotionX(), player.getMotionY(),0,0);
			player.setPos((float)(world.getRadius() * Math.cos(an1)), (float) (world.getRadius() * Math.sin(an1)));
			player.setMotion((float)(speed * Math.cos(an4)), (float) (speed * Math.sin(an4)));
		}
		ArrayList<Entity> rem = new ArrayList<Entity>();
		for(Entity e : entities) {
			if(e instanceof Ball && dist(player.getPosX(), player.getPosY(), e.getPosX(), e.getPosY()) <= 30) {
				rem.add(e);
				float an1 = (float) (Math.random() * Math.PI * 2);
				//player.setMotion((float)(200 * Math.cos(an1)), (float) (200 * Math.sin(an1)));
				spawnBall();
				spawnBall();
				break;
			}
		}
		for(Entity e : rem) {
			entities.remove(e);
		}
		player.update(delta);
	}

	@Override
	public void render(Graphics2D g2d) {
		int hw = getWidth() / 2;
		int hh = getHeight() / 2;
		g2d.translate(hw - player.getPosX(), hh - player.getPosY());
		world.render(g2d);
		for(Entity e : entities) {
			e.render(g2d);
		}
		player.render(g2d);
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	public CSocket getSocket() {
		return socket;
	}
	public void setSocket(CSocket socket) {
		this.socket = socket;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Point getMouse() {
		return mouse;
	}
	public void setMouse(Point mouse) {
		this.mouse = mouse;
	}
	public boolean[] getKeys() {
		return keys;
	}
	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public float dist(float x1, float y1, float x2, float y2) {
		 return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
