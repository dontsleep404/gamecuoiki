package main;

import java.util.HashMap;

import custom.CServer;
import custom.CSocket;
import custom.Packet;
import custom.cpacket.*;
import custom.spacket.*;
import model.Player;
import model.Scoreboard;

public class Server extends CServer{
	private HashMap<CSocket, Player> clients;
	private Scoreboard scoreboard;
	private int index;
	public Server(int port) {
		super(port);
		clients = new HashMap<CSocket, Player>();
		index = 1;
		scoreboard = new Scoreboard();
	}
	public static void main(String[] args) {
		new Server(Integer.parseInt(args[0]));
		
	}
	@Override
	public void onPacket(CSocket client, Packet packet) {
		if(packet instanceof CPacketConnect) {
			CPacketConnect p = (CPacketConnect) packet;
			clients.put(client, new Player(p.getName()));
			client.sendPacket(new SPacketAccept(index++, 0, 0));
			return;
		}
		if(packet instanceof CPacketDisconnect) {
			if(clients.containsKey(client)) {
				clients.remove(client);
			}
			return;
		}
	}
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
}
