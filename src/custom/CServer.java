package custom;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CServer {
	private ServerSocket server = null;
	private ArrayList<CSocket> clients = null;
	public CServer(int port) {
		clients = new ArrayList<CSocket>();
		try {
			server = new ServerSocket(port);
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		new Thread(()->{
			try {
				System.out.println("Server listen");
				while(true) {
					Socket socket = server.accept();
					onSocketAccpet(socket);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					server.close();
					clients.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	public void sendPacket(CSocket client, Packet packet) {
		client.sendPacket(packet);
	}
	public void boarcast(Packet packet) {
		for(CSocket client : clients) {
			client.sendPacket(packet);
		}
	}
	public void onSocketAccpet(Socket socket) {
		CSocket cSocket = new CSocket(socket) {
			@Override
			public void onConnect() {
				CServer.this.onConnect(getInstance());
			}
			@Override
			public void onDisconnect() {
				CServer.this.onDisconnect(getInstance());
			}
			@Override
			public void onPakcet(Packet packet) {
				CServer.this.onPacket(getInstance(), packet);
			}
		};
		cSocket.setPackets(PacketManager.getCPacketList());
		cSocket.listen();
		add(cSocket);
	}
	public void onConnect(CSocket client) {
		System.out.println(client + " Connect.");
	}
	public void onDisconnect(CSocket client) {
		System.out.println(client + " Disconnect.");
	}
	public void onPacket(CSocket client, Packet packet) {
		System.out.println(client.hashCode() + " " + packet);
	}
	public CServer getInstance() {
		return this;
	}
	public void remove(CSocket client) {
		if(clients.contains(client)) {
			clients.remove(client);
		}
	}
	public void add(CSocket client) {
		if(!clients.contains(client)) {
			clients.add(client);
		}
	}
	public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	public ArrayList<CSocket> getClients() {
		return clients;
	}
	public void setClients(ArrayList<CSocket> clients) {
		this.clients = clients;
	}	
	
}
