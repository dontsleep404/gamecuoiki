package custom.spacket;

import custom.Packet;

public class SPacketAccept extends Packet{
	private int id;
	private float posX;
	private float posY;
	
	public SPacketAccept(int id, float posX, float posY) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
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
	
}
