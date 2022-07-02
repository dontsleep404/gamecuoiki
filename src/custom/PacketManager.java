package custom;

import java.util.ArrayList;

import custom.cpacket.*;

public class PacketManager {
	public static ArrayList<Class<? extends Packet>> getCPacketList() {
		ArrayList<Class<? extends Packet>> list = new ArrayList<Class<? extends Packet>>();
		
		list.add(CPacketConnect.class);
		
		return list;
	}
	public static ArrayList<Class<? extends Packet>> getSPacketList() {
		ArrayList<Class<? extends Packet>> list = new ArrayList<Class<? extends Packet>>();
		
		
		return list;
	}
}
