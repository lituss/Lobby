package comunicaServer;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import utils.Array;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import comunicaComu.Estats;
import comunicaComu.IPlayer;
import comunicaComu.IProxyLobby;
import comunicaComu.Network;
import comunicaComu.SPlayer;
import comunicaServer.Room.Tipus;

public class LobbyPlayer extends Connection implements IPlayer,Observer{
	public Observable obs;
	private Estats estat;
	private static LobbyServer lobbyServer;
	private IProxyLobby iProxyLobby;
	private SPlayer sPlayer;
	
	public LobbyPlayer(){
		obs = new Observable();
		new ObjectSpace(this).register(Network.PLAYER, this);
		sPlayer = new SPlayer();
	}
	public static void putLobbyServer(LobbyServer auxLobbyServer){
		lobbyServer = auxLobbyServer;
		iProxyLobby = lobbyServer.
		}
	
	@Override
	public Estats login(String user, String pass) {
		// TODO Auto-generated method stub
		if (!lobbyServer.getsql().existeixUsuari(user)) {
			if (!lobbyServer.getsql().join(user, pass)){ 
				desconecta();
				return Estats.duplicateUser;
			}
		}
		if (!lobbyServer.getsql().login(user, pass)){
			desconecta();
			return Estats.unauthorizedUser;
		}
		else {
			estat = Estats.Logued;
			return estat;
		}
	}
		
	private void desconecta(){
		new Timer().schedule(new TimerTask(){
			@Override
			public void run(){
				lobbyServer.stop();
			}},1000);
	}

	@Override
	public Array<Room> getRooms(Tipus tipus) {
		// TODO Auto-generated method stub
		return lobbyServer.getRooms(tipus);
	}
	@Override
	public Estats joinRoomSetimig(Room room) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Array<LobbyGame> getRoomGames(Room room) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Estats joinGame(LobbyGame lobbyGame) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Estats createGame(LobbyGame lobbyGame) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
