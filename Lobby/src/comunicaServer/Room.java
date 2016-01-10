package comunicaServer;
import java.util.ArrayList;
import java.util.Observable;

import comunicaComu.SGame;
import comunicaComu.SGameSetimig;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;
//import comunicaComu.SRoom.Tipus;
import utils.Array;


public abstract class Room extends Observable{
	
	protected SRoom.Tipus tipus;
	public SRoom.Tipus getTipus() {
		return tipus;
	}

	public void setTipus(SRoom.Tipus tipus) {
		this.tipus = tipus;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	protected String nom;
	
	protected static Array<LobbyPlayer> players = new Array<LobbyPlayer>();
	protected static Array<LobbyGame> games = new Array<LobbyGame>();
	protected int maxPlayers;
	protected int maxGames;
	
	protected Room(String nom,int maxPlayers,int maxGames){
		this.nom = nom;
		this.maxPlayers = maxPlayers;
		this.maxGames = maxGames;
		}
	
	public boolean addPlayer(LobbyPlayer player){
		boolean retorn;
		synchronized(players){
			if (players.size < maxPlayers){
				players.add(player);
				retorn = true;
			}
			else retorn = false;
		}
		if (retorn) {
			notifyObservers(player);
			return true;
		}
		else return false;
		
	}
	public boolean removePlayer(LobbyPlayer player){
		boolean retorn;
		
		synchronized(players){
			retorn = players.removeValue(player, true);
		}
		if (retorn) notifyObservers(player);
		return retorn;
	}

	public boolean addGame(LobbyGame game){
		boolean retorn;
		synchronized(games){
			if (games.size < maxGames){
				games.add(game);
				retorn = true;
			}
			else retorn = false;
		}
		if (retorn){
			notifyObservers(game);
			return true;
		}
		else return false;
		
	}
	
	public boolean removeGame(LobbyGame game){
		boolean retorn;
		
		synchronized(games){
			retorn = games.removeValue(game, true);
		}
		if (retorn) notifyObservers(game);
		return retorn;
	}

	public SRoom getSRoom(LobbyPlayer lobbyPlayer) {
		Array <SPlayer> sPlayers = new Array<SPlayer>();
		Array <SGame> sGames = null;
		switch (tipus){
		case Setimig :
			sGames = new Array<SGame>();
			break;
		}
		synchronized (this){
			for (LobbyPlayer player : players) sPlayers.add(new SPlayer(player.sPlayer.nom));
			for (LobbyGame auxGame : games) {
				Array <SPlayer> sGamePlayers = auxGame.getSPlayers(lobbyPlayer);
				switch (tipus){
				case Setimig :
					sGames.add(new SGameSetimig(auxGame.estat,auxGame.maxPlayers,auxGame.nom,sGamePlayers,((GameSetimig)auxGame).credit,((GameSetimig)auxGame).maxAposta));
					break;
				}
			}
			addObserver(lobbyPlayer);
		}
		return (new SRoom(tipus, sGames, sPlayers, maxGames, maxPlayers, nom));
	}
	
	
}
