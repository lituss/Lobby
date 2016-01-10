package comunicaServer;

import java.util.Observable;

import utils.Array;

public abstract class LobbyGame extends Observable{
	protected String nom;
	public static enum Estat {WaitPlayers,Playing}
	protected Estat estat;
	protected Array<LobbyPlayer> players;
	protected int maxPlayers;
	
	protected LobbyGame(String nom,int maxPlayers,LobbyPlayer player){
		this.nom = nom;
		this.maxPlayers = maxPlayers;
		addPlayer(player);
	}
	protected boolean addPlayer(LobbyPlayer player){
		boolean retorn;
		synchronized(players){
			if (players.size < maxPlayers){
				players.add(player);
				retorn = true;
			}
			else retorn = false;
		}
		if (retorn){ 
			notifyObservers(player);
			return true;
		}
		else return false;
	}
	
	protected boolean removePlayer(LobbyPlayer player){
		synchronized(players){
			players.removeValue(player, true);
		}
		notifyObservers(player);
		return true;
	}
}
