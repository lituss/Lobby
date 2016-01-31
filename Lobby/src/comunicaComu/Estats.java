package comunicaComu;


public enum Estats {
	connectat(0),logued(1),onLobby(2),onRoom(3),onGame(4),onPlay(5),usuariInexistent(6),
	unauthorizedUser(7),duplicateUser(8), disconnected(9) , userAlreadyLogued(10);
int valor;
	Estats (int i){valor = i;}
}
