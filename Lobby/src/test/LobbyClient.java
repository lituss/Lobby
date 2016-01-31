package test;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import comunicaComu.Estats;
import comunicaComu.IPlayer;
import comunicaComu.Network;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;

public class LobbyClient {
	Client client;
	ProxyLobby proxyLobby;
	WinClient winClient;
	public boolean connected = false;
	public SRoom.Tipus tipusRoom = SRoom.Tipus.Setimig; 
	Connection connection;
	ProcessaEntrades processaEntrades;
	ProcessaSortides processaSortides;

	public LobbyClient (WinClient winServer) {
		this.winClient = winServer;
		client = new Client();
		client.start();

		// Register the classes that will be sent over the network.
		Network.register(client);

		// Get the Player on the other end of the connection.
		// This allows the client to call methods on the server.
		//player = ObjectSpace.getRemoteObject(client, Network.PLAYER, IPlayer.class);
		proxyLobby = new ProxyLobby(this);
		// Register the chat frame so the server can call methods on it.
		//new ObjectSpace(client).register(Network.PROXY_LOBBY, proxyLobby);

		client.addListener(new NetworkListener());

		

		// The chat frame contains all the Swing stuff.
		//--proxyLobby = new ProxyLobby(this);
		// Register the chat frame so the server can call methods on it.
		//--new ObjectSpace(client).register(Network.PROXY_LOBBY, proxyLobby);
		// This listener is called when the send button is clicked.
		/*chatFrame.setSendListener(new Runnable() {
			public void run () {
				player.sendMessage(chatFrame.getSendText());
			}
		});
		// This listener is called when the chat window is closed.
		chatFrame.setCloseListener(new Runnable() {
			public void run () {
				client.stop();
			}
		});
		chatFrame.setVisible(true);
*/
		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, "localhost", Network.port);
					// Server communication after connection can go here, or in Listener#connected().
					//player.registerName(name);
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
		processaEntrades = new ProcessaEntrades();
		processaEntrades.start();
		processaSortides = new ProcessaSortides();
		processaSortides.start();
	}
	public class NetworkListener extends Listener{
		@Override
		public void connected(Connection c) {
			connection = c;
			//super.connected(c);
		}
		@Override
		public void disconnected(Connection arg0) {
			EventQueue.invokeLater(new Runnable() {
				public void run () {
					// Closing the frame calls the close listener which will stop the client's update thread.
					//chatFrame.dispose();
					// aqui el que hem de fer quan es desconecta 
				}
			});
		
			//super.disconnected(arg0);
		}
		@Override
		public void received(Connection arg0, Object o) {
			// TODO Auto-generated method stub
			try {
				processaEntrades.llista.put(o);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//super.received(arg0, arg1);
		}
	}
	public class ProcessaEntrades extends Thread{
		public BlockingQueue<Object> llista = new LinkedBlockingQueue<Object>();
		public boolean para = false;
		
		public void run(){
			Object o = null;
			while (!para){
				try {
					o = llista.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (o instanceof Estats){
					winClient.show(((Estats)o).toString());
				}
			}
		}
	}
	
	public class ProcessaSortides extends Thread{
		public BlockingQueue<Object> llista = new LinkedBlockingQueue<Object>();
		public boolean para = false;
		
		public void run(){
			Object o = null;
			while (!para){
				try {
					o = llista.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection.sendTCP(o);
			}
		}
	}
	public void login(String user,String pass){
		try {
			processaSortides.llista.put(new SPlayer(user,pass));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
