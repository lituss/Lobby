package cues;

import com.esotericsoftware.kryonet.Connection;

public class ElementDeSortida{
	public static enum OperacioEnvia {tots,totsElsDemes,tu};
	Connection connection_sender;
	OperacioEnvia operacio;
	Object packet;
	
	public ElementDeSortida(Connection connection_sender,OperacioEnvia operacio,Object packet){
		this.connection_sender = connection_sender;
		this.operacio = operacio;
		this.packet = packet;
	}
}
