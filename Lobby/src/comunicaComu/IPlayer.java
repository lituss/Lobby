package comunicaComu;




public interface IPlayer {
Estats login(String user,String pass);
//Array<Room> getRooms(Room.Tipus tipus);
//Estats joinRoomSetimig(Room room);
//Array<SGame> getRoomGames(Room room);
Estats joinGame(String nom);
Estats createGame(int maxPlayers,String nom );
SRoom getRoom();
}
