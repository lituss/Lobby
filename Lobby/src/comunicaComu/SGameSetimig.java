package comunicaComu;

import utils.Array;

public class SGameSetimig extends SGame{
	public int credit,maxAposta;
	
	public SGameSetimig(Estat estat, int maxPlayers, String nom, Array<SPlayer> players,int credit,int maxAposta) {
		super(estat, maxPlayers, nom, players);
		this.credit = credit;
		this.maxAposta = maxAposta;
		
		// TODO Auto-generated constructor stub
	}


}
