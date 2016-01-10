package comunicaServer;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import comunicaComu.IProxyLobby;
import comunicaComu.Network;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;
import uiLobbyServer.WinServer;
import utils.Array;

public class LobbyServer {
private Array<Room> rooms;
private Array <LobbyPlayer> players;
private Server server;
private WinServer winServer;
private sqlDB sql;

public LobbyServer(WinServer winServer) throws IOException{
	this.winServer = winServer;
	rooms = new Array<Room>();
	rooms.add(new SetimigRoom("Robinson",100,10));
	LobbyPlayer.putLobbyServer(this);
	sql = new sqlDB();
	comunications();
	}
	

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}

	private void comunications() throws IOException{
		server = new Server() {
			protected Connection newConnection () {
				// Each connection represents a player and has fields
				// to store state and methods to perform actions.
				LobbyPlayer player = new LobbyPlayer();
				
				players.add(player);
				winServer.
				return player;
			}
		};

		// Register the classes that will be sent over the network.
		Network.register(server);

		server.addListener(new Listener() {
			public void disconnected (Connection connection) {
				LobbyPlayer player = (LobbyPlayer)connection;
				players.removeValue(player,true);
			}
		});
		server.bind(Network.port);
		server.start();

		// Open a window to provide an easy way to stop the server.
		
	}

	public void stop(){
		server.stop();
	}
	
	public sqlDB getsql(){return sql;}
	public Array<Room> getRooms(SRoom.Tipus tipus){
		Array<Room> auxRooms = new Array<Room>();
		for (Room auxRoom : rooms) if (auxRoom.getTipus() == tipus || tipus ==null) auxRooms.add(auxRoom);
		return auxRooms;
	}
	public void enviaSRooms(IProxyLobby iProxyLobby,LobbyPlayer lobbyPlayer){
		SRoom.Tipus tipus = iProxyLobby.getTipusRoom();
		Array<SRoom> auxSRooms = new Array<SRoom>();
		synchronized (rooms){
			
			for (Room auxRoom : rooms) if (auxRoom.getTipus() == tipus || tipus ==null){
				auxSRooms.add(auxRoom.getSRoom(lobbyPlayer));
			}
				
		}
		for (SRoom  aux : auxSRooms) iProxyLobby.addRoom(aux);	
		
	}
	
}