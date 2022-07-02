package custom;

public class CPacket {
	private String packet;
	private String packetName;
	
	public CPacket(String packet, String packetName) {
		super();
		this.packet = packet;
		this.packetName = packetName;
	}
	public String getPacket() {
		return packet;
	}
	public void setPacket(String packet) {
		this.packet = packet;
	}
	public String getPacketName() {
		return packetName;
	}
	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}
	@Override
	public String toString() {
		return "CPacket [packet=" + packet + ", packetName=" + packetName + "]";
	}	
	
}
