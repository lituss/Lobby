package comunicaServer;

public class GameSetimig extends LobbyGame {
private int maxPlayers,credit,maxAposta;

	public GameSetimig(String nom,LobbyPlayer player,int numPlayers,int credit,int apostaMaxima){
		super(nom,numPlayers,player);
		this.credit = credit;
		maxAposta = apostaMaxima;
	}
}
