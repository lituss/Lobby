package comunicaComu;

import utils.Array;

public class SRoom {
	protected Array<SGame> games;
	protected Array<SPlayer> players;
	protected int maxGames;
	protected int maxPlayers;
	protected String nom;
	
	public SRoom (Array<SGame> games,Array<SPlayer> players,int maxGames,int maxPlayers,String nom){
		this.games = games;
		this.players = players;
		this.maxGames = maxGames;
		this.maxPlayers = maxPlayers;
		this.nom = nom;
	}
}
