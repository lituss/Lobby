package comunicaServer;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import comunicaComu.Estats;
import comunicaComu.Network;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;
import cues.ReceiverDispatcher;
import cues.SenderDispatcher;
import uiLobbyServer.WinServer;
import uiLobbyServer.WinServer.MissatgeSwing;
import uiLobbyServer.WinServer.Operations;
import utils.Array;

public class LobbyServer extends Observable implements Observer{
private Array<Room> rooms;
public static ConcurrentMap <Integer , LobbyPlayer> players = new ConcurrentHashMap();
private Server server;
private WinServer winServer;
private sqlDB sql;
private boolean stop = false;
private ReceiverDispatcher receiverDispatcher;
public SenderDispatcher senderDispatcher;

public static MyTimer timer;
public LobbyServer(WinServer winServer) throws IOException{
	this.winServer = winServer;
	timer = new MyTimer();
	rooms = new Array<Room>();
	Room room = new SetimigRoom("Robinson",100,10);
	room.addObserver(this);
	rooms.add(room);
	LobbyPlayer.lobbyServer = this;
	sql = new sqlDB();
	receiverDispatcher = new ReceiverDispatcher();
	receiverDispatcher.start();
	senderDispatcher = new SenderDispatcher();
	senderDispatcher.start();
	
	comunications();
	}

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}

	private void comunications() throws IOException{
		server = new Server();
			
		// Register the classes that will be sent over the network.
		
		Network.register(server);
		
		server.addListener(new NetworkListener(this));
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
		LobbyServer lobbyServer;
		NetworkListener(LobbyServer lobbyServer){
			this.lobbyServer = lobbyServer;
		}
		@Override
		public void connected(Connection c) {
			LobbyPlayer player = new LobbyPlayer(c);
			
			
			lobbyServer.addObserver(player);
			lobbyServer.setChanged();
			lobbyServer.notifyObservers(player);
			players.put(player.getConnection().getID(), player);
			
			try {
				lobbyServer.winServer.llista.put(new MissatgeSwing(Operations.addPlayer,player.sPlayer));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			/*try {
				winServer.nodes.put(new SwingOperations(SwingOperations.operations.addPlayer,player.sPlayer));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		@Override
		public void disconnected(Connection c) {
			LobbyPlayer player = players.get(c.getID());
			lobbyServer.deleteObserver(player);
			lobbyServer.setChanged();
			lobbyServer.notifyObservers(player);
			player.dispose();
			try {
				lobbyServer.winServer.llista.put(new MissatgeSwing(Operations.delPlayer,player.sPlayer));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			players.remove(player.getConnection().getID(),player);
			
		}
		@Override
		public void received(Connection c, Object o) {
			
		}	
	}
	
	public class MyTimer extends Timer{
		void timeoutLogin(LobbyPlayer player){ // temps per tallar conexio si no entra login
			int timeout = 5000; //5 seg
			TimerTask task = new TimerTask(){
				@Override
				public void run() {
					if (player.getEstat() == Estats.connectat) player.getConnection().close();
					
				}
			 
			};
			this.schedule(task, timeout);
		}
		void badLogin(LobbyPlayer player){
			int timeout = 1000;
			TimerTask task = new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					player.getConnection().close();
				}
				
			};
		}
			
	}

	@Override
	public void update(Observable ov, Object o) {
		
	}
}