package custom.spacket;

import custom.Packet;

public class SPacketPlayerConnect extends Packet{
	private String name;
	private float posX;
	private float posY;
	
	public SPacketPlayerConnect(String name, float posX, float posY) {
		super();
		this.name = name;
		this.posX = posX;
		this.posY = posY;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
