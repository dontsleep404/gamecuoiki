package custom.cpacket;

import custom.Packet;

public class CPacketConnect extends Packet{
	private String name;

	public CPacketConnect(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
