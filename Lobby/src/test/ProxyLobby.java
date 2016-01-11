package test;

import comunicaComu.IProxyLobby;
import comunicaComu.SRoom;
import comunicaComu.SRoom.Tipus;

public class ProxyLobby implements IProxyLobby{
private LobbyClient lobbyClient;

public ProxyLobby(LobbyClient lobbyClient){
	this.lobbyClient = lobbyClient;
}
	@Override
	public Tipus getTipusRoom() {
		// TODO Auto-generated method stub
		return lobbyClient.tipusRoom;
	}

	@Override
	public void addRoom(SRoom sRoom) {
		lobbyClient.winserver.addRoom(sRoom);
		// TODO Auto-generated method stub
		
	}

}
