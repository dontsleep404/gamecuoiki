package model;

import java.awt.Graphics2D;

import ui.IRender;

public class Entity implements IRender{
	private int id;
	private float posX;
	private float posY;
	private float motionX;
	private float motionY;
	private float angle;
	public Entity() {
		this.id = 0;
		this.posX = 0;
		this.posY = 0;
		this.motionX = 0;
		this.motionY = 0;
		this.angle = 0;
	}
	public Entity(float posX, float posY, float motionX, float motionY, float angle) {
		super();
		this.id = 0;
		this.posX = posX;
		this.posY = posY;
		this.motionX = motionX;
		this.motionY = motionY;
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getMotionX() {
		return motionX;
	}
	public void setMotionX(float motionX) {
		this.motionX = motionX;
	}
	public float getMotionY() {
		return motionY;
	}
	public void setMotionY(float motionY) {
		this.motionY = motionY;
	}
	public void setPos(float posX, float posY) {
		setPosX(posX);
		setPosY(posY);
	}
	public void setMotion(float motionX, float motionY) {
		setMotionX(motionX);
		setMotionY(motionY);
	}
	@Override
	public void update(float delta) {
		setPos(getPosX() + getMotionX()*delta, getPosY() + getMotionY()*delta);
	}
	@Override
	public void render(Graphics2D g2d) {
		g2d.fillOval(- 5, - 5, 10, 10);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
