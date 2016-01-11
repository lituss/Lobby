package comunicaComu;


import utils.Array;

public class SRoom {
	public static enum Tipus {Setimig};
	public Tipus tipus;
	public Array<SGame> games;
	public Array<SPlayer> players;
	public int maxGames;
	public int maxPlayers;
	public String nom;
	
	public SRoom (Tipus tipus,Array<SGame> games,Array<SPlayer> players,int maxGames,int maxPlayers,String nom){
		this.tipus = tipus;
		this.games = games;
		this.players = players;
		this.maxGames = maxGames;
		this.maxPlayers = maxPlayers;
		this.nom = nom;
	}
}
