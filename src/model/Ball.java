package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Entity{
	private int radius;
	private boolean dead;
	public Ball(float x, float y, int radius, boolean dead) {
		super();
		setPos(x, y);
		this.radius = radius;
		this.dead = dead;
	}
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillOval((int)(getPosX() - radius), (int) (getPosY() -  radius), radius, radius);
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
