package comunicaServer;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import comunicaComu.Network;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;
import uiLobbyServer.WinServer;
import utils.Array;

public class LobbyServer extends Observable{
private Array<Room> rooms;
private static ConcurrentMap <Integer , LobbyPlayer> players = new ConcurrentHashMap();
private Server server;
private WinServer winServer;
private sqlDB sql;
private boolean stop = false;
private ReceiverDispatcher receiverDispatcher;

public LobbyServer(WinServer winServer) throws IOException{
	this.winServer = winServer;
	rooms = new Array<Room>();
	rooms.add(new SetimigRoom("Robinson",100,10));
	LobbyPlayer.putLobbyServer(this);
	sql = new sqlDB();
	receiverDispatcher = new ReceiverDispatcher();
	receiverDispatcher.start();
	
	comunications();
	}

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}

	private void comunications() throws IOException{
		server = new Server();
			
		// Register the classes that will be sent over the network.
		
		Network.register(server);
		
		server.addListener(new NetworkListener());
		server.bind(Network.port);
		server.start();

		// Open a window to provide an easy way to stop the server.
		
	}

	public void stop(){
		server.stop();
		stop = true;
	}
	
	public sqlDB getsql(){return sql;}
	
	/*public Array<Room> getRooms(SRoom.Tipus tipus){
		Array<Room> auxRooms = new Array<Room>();
		synchronized (rooms){
			for (Room auxRoom : rooms) if (auxRoom.getTipus() == tipus || tipus ==null) auxRooms.add(auxRoom);
		}
		return auxRooms;
	}*/
	public void getSRooms(LobbyPlayer lobbyPlayer, SRoom.Tipus tipus){
		Array<SRoom> auxSRooms = new Array<SRoom>();
		synchronized (rooms){
			
			for (Room auxRoom : rooms) if (auxRoom.getTipus() == tipus || tipus ==null){
				auxSRooms.add(auxRoom.getSRoom(lobbyPlayer));
			}
				
		}
		
	}
	//private void processReceived (Connection c, Object object){
		//if (object instanceof SPlayer){
		//	c. ((SPlayer)object).nom
		//}
	//}
	
	static class NetworkListener extends Listener{
		@Override
		public void connected(Connection c) {
			LobbyPlayer player = new LobbyPlayer(c);
			
			players.put(c.getID(), player);
			
			
			/*try {
				winServer.nodes.put(new SwingOperations(SwingOperations.operations.addPlayer,player.sPlayer));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		@Override
		public void disconnected(Connection c) {
			players.remove(c.getID());
		}
		@Override
		public void received(Connection c, Object o) {
			
		}
		
		
	}
	public class elementDeEntrada{
		Connection c;
		Object o;
	}
	
	public class ReceiverDispatcher extends Thread{
		BlockingQueue <elementDeEntrada> llista = new LinkedBlockingQueue<elementDeEntrada>();
	
	public void run(){
		while (!stop){
			try {
				elementDeEntrada e = llista.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}
	
	
	
}