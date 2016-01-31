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
import comunicaComu.SRoom;
import cues.ElementDeSortida;
import uiLobbyServer.WinServer.MissatgeSwing;
import uiLobbyServer.WinServer.Operations;


public class LobbyPlayer extends Observable implements IPlayer,Observer{
	private Connection connection;
	static LobbyServer lobbyServer;
	public SPlayer sPlayer;
	
	public LobbyPlayer(Connection connection){
		this.connection = connection;
		sPlayer = new SPlayer("Desconegut");
		sPlayer.estat = Estats.connectat;
		LobbyServer.timer.timeoutLogin(this);
		addObserver(lobbyServer);
		this.setChanged();
		this.notifyObservers();
		//new ObjectSpace(this).register(Network.PLAYER, this);
		//iProxyLobby = (IProxyLobby) ObjectSpace.getRemoteObject(this, Network.PROXY_LOBBY, IProxyLobby.class);
		//sPlayer = new SPlayer("Desconegut");	
	}
	
	@Override
	public Estats login(String user, String pass) {
		// TODO Auto-generated method stub
		if (!lobbyServer.getsql().existeixUsuari(user)) {
			if (!lobbyServer.getsql().join(user, pass)){ 
				LobbyServer.timer.badLogin(this);
				return Estats.duplicateUser;
			}
		}
		if (!lobbyServer.getsql().login(user, pass)){
			LobbyServer.timer.badLogin(this);
			return Estats.unauthorizedUser;
		}
		else {
			sPlayer.estat = Estats.logued;
			sPlayer.nom = user;
			setChanged();
			notifyObservers();
			return sPlayer.estat;
		}
	}
		
	

	@Override
	public void update(Observable ov, Object o) {
		if (ov instanceof LobbyServer){
			LobbyPlayer player = ((LobbyPlayer)o);
			Estats estat = player.getEstat();
			switch (estat){
			case logued :
				try {
					lobbyServer.senderDispatcher.llista.put(new ElementDeSortida(connection, ElementDeSortida.OperacioEnvia.tu, sPlayer));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					lobbyServer.winServer.llista.put(new MissatgeSwing(Operations.updatePlayer,null));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case disconnected :
				try {
					lobbyServer.senderDispatcher.llista.put(new ElementDeSortida(connection, ElementDeSortida.OperacioEnvia.tu, sPlayer));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					lobbyServer.winServer.llista.put(new MissatgeSwing(Operations.delPlayer,player.sPlayer));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public Estats joinGame(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Estats createGame(int maxPlayers, String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SRoom getRoom() {
		// TODO Auto-generated method stub
		return null;
	}
	public Estats getEstat(){
		return sPlayer.estat;
	}
	public Connection getConnection() {
		return connection;
	}
	public void dispose(){
		sPlayer.estat = Estats.disconnected;
		setChanged();
		notifyObservers();
		deleteObservers();
		
	}
}
