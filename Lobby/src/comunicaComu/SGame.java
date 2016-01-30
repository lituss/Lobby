package comunicaComu;

import utils.Array;

public class SGame {
	public static enum Estat {Playing,WaitPlayers};
	protected Estat estat;
	protected int maxPlayers;
	public String nom;
	protected Array <SPlayer> players;
	protected String roomName;
	
	public SGame(Estat estat,int maxPlayers,String nom,Array<SPlayer> players,String roomName){
		this.estat = estat;
		this.maxPlayers = maxPlayers;
		this.nom = nom;
		this.players = players;
		this.roomName = roomName;
	}
}
