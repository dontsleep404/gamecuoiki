package custom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class CSocket {
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private HashMap<String, Class<? extends Packet>> packetList;
	public CSocket(String host, int port) {		
		try {
			this.socket = new Socket(host, port);
			this.input = new DataInputStream(this.socket.getInputStream());
			this.output = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public CSocket(Socket socket) {
		try {
			this.socket = socket;
			this.input = new DataInputStream(this.socket.getInputStream());
			this.output = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void listen() {	
		if(socket == null) return;
		new Thread(()->{
			try {				
				onConnect();
				while(true) {
					try {
						String line = input.readUTF();
						try {
							Gson gson = new Gson();
							CPacket cpacket = gson.fromJson(line, CPacket.class);
							if(packetList.containsKey(cpacket.getPacketName())) {
								Class<? extends Packet> cl = packetList.get(cpacket.getPacketName());
								Packet packet = cl.cast(gson.fromJson(cpacket.getPacket(), cl));
								onPakcet(packet);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;						
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					onDisconnect();
					input = null;
					output = null;
					socket.close();
					socket = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	public CSocket getInstance() {
		return this;
	}
	public void sendPacket(Packet packet) {
		try {
			Gson gson = new Gson();
			CPacket cpacket = new CPacket(gson.toJson(packet), packet.getClassName());			
			String out = gson.toJson(cpacket);
			output.writeUTF(out);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void onPakcet(Packet packet) {
		System.out.println(packet);
	}
	public void onDisconnect() {
		System.out.println("Dis");
	}
	public void onConnect() {
		System.out.println("Con");
	}
	public void setPackets(ArrayList<Class<? extends Packet>> arr) {
		packetList = new HashMap<String, Class<? extends Packet>>();
		for(Class<? extends Packet> cl : arr) {
			packetList.put(cl.getSimpleName(), cl);
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public DataInputStream getInput() {
		return input;
	}
	public void setInput(DataInputStream input) {
		this.input = input;
	}
	public DataOutputStream getOutput() {
		return output;
	}
	public void setOutput(DataOutputStream output) {
		this.output = output;
	}
	public HashMap<String, Class<? extends Packet>> getPacketList() {
		return packetList;
	}
	public void setPacketList(HashMap<String, Class<? extends Packet>> packetList) {
		this.packetList = packetList;
	}
	
}
