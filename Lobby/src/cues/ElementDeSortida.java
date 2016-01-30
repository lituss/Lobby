package cues;



public class ElementDeSortida{
	public static enum OperacioEnvia {tots,totsElsDemes,tu};
	int id_sender;
	OperacioEnvia operacio;
	Object packet;
	
	public ElementDeSortida(int id_server,OperacioEnvia operacio,Object packet){
		this.id_sender = id_sender;
		this.operacio = operacio;
		this.packet = packet;
	}
}
