package comunicaComu;

import utils.Array;

public class SGame {
	protected static enum Estat {Playing,WaitPlayers};
	protected Estat estat;
	protected int maxPlayers;
	protected String nom;
	protected Array <SPlayer> players;
	
	public SGame(Estat estat,int maxPlayers,String nom,Array<SPlayer> players){
		this.estat = estat;
		this.maxPlayers = maxPlayers;
		this.nom = nom;
		this.players = players;
	}
}
