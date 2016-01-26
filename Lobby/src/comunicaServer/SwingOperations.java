package comunicaServer;

public class SwingOperations{
	public static enum operations{addGame,addPlayer,addRoom,delGame,delPlayer,delRoom;}
	public operations operation;
	public Object data;
	public SwingOperations(operations operation,Object data){
		this.operation = operation;
		this.data = data;
	}
}