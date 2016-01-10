package comunicaServer;

public class GameSetimig extends LobbyGame {
int credit;
int maxAposta;

	public GameSetimig(String nom,LobbyPlayer player,int numPlayers,int credit,int apostaMaxima){
		super(nom,numPlayers,player);
		this.credit = credit;
		maxAposta = apostaMaxima;
	}
}
