package custom.spacket;

import custom.Packet;

public class SPacketSpawnParticle extends Packet{
	private int id;
	private float posX;
	private float posY;
	private float motionX;
	private float motionY;
	private float angle;
	
	public SPacketSpawnParticle(int id, float posX, float posY, float motionX, float motionY, float angle) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.motionX = motionX;
		this.motionY = motionY;
		this.angle = angle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
}
