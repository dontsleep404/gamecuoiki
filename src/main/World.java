package main;

import java.awt.Color;
import java.awt.Graphics2D;

import ui.Panel;

public class World extends Panel{
	private Game game;
	private int radius;
	public World(Game game) {
		this.game = game;
		this.radius = 100;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.drawOval(-radius, -radius, radius*2, radius*2);
	}
	@Override
	public void update(float delta) {
		
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
