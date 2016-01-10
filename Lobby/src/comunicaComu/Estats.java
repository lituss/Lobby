package comunicaComu;


public enum Estats {
	Connectat(0),Logued(1),OnLobby(2),OnRoom(3),OnGame(4),OnPlay(5),usuariInexistent(6),
	unauthorizedUser(7),duplicateUser(8);
int valor;
	Estats (int i){valor = i;}
}
