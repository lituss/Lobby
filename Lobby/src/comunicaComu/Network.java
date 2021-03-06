package comunicaComu;

import java.lang.reflect.Array;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;


public class Network {
	static public final int port = 54777;

	// These IDs are used to register objects in ObjectSpaces.
	//static public final short PLAYER = 1;
	//static public final short PROXY_LOBBY = 2;

	// This registers objects that are going to be sent over the network.
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		// This must be called in order to use ObjectSpaces.
		//ObjectSpace.registerClasses(kryo);
		// The interfaces that will be used as remote objects must be registered.
		//kryo.register(IPlayer.class);
		//kryo.register(IProxyLobby.class);
		// The classes of all method parameters and return values
		// for remote objects must also be registered.
		kryo.register(String[].class);
		kryo.register(SGame.class);
		kryo.register(SPlayer.class);
		kryo.register(SPlayer[].class);
		kryo.register(SRoom.class);
		kryo.register(Estats.class);
		kryo.register(Array.class);
		kryo.register(SRoom.Tipus.class);
		
	}
}