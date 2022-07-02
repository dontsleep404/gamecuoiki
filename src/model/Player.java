package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity{
	private String name;
	private int score;
	private float gravity;
	protected static final int RADIUS = 10;
	public Player(String name) {
		super();
		this.name = name;
		this.score = 0;
		this.gravity = 1f;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fillOval((int) (getPosX() - RADIUS), (int) (getPosY() - RADIUS), RADIUS*2, RADIUS*2);
	}
	@Override
	public void update(float delta) {
		setPos(getPosX() + getMotionX()*delta, getPosY() + getMotionY()*delta);
		super.update(delta);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public float getGravity() {
		return gravity;
	}
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	public boolean isCollision(Ball ball) {
		 return Math.sqrt(Math.pow(ball.getPosX() - getPosX(), 2) + Math.pow(ball.getPosY() - getPosY(), 2)) >= (ball.getRadius() + RADIUS);
	}
}
