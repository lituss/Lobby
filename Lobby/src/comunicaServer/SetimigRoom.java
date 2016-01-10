package comunicaServer;

public class SetimigRoom extends Room {

	public SetimigRoom(String nomSala,int maxPlayers,int maxGames) {
		// TODO Auto-generated constructor stub
		super(nomSala,maxPlayers,maxGames);
		tipus = Tipus.Setimig;
	}
	public boolean createGame(String nom,LobbyPlayer player,int numPlayers,int credit,int apostaMaxima){
		LobbyGame lobbyGame = new GameSetimig(nom,player,numPlayers,credit,apostaMaxima);
		return true;
		
	}


}
