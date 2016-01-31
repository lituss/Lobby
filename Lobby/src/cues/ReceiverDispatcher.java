package cues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import comunicaComu.Estats;
import comunicaComu.SPlayer;
import comunicaServer.LobbyPlayer;
import comunicaServer.LobbyServer;
import cues.ElementDeSortida.OperacioEnvia;



public class ReceiverDispatcher extends Thread{
	public BlockingQueue <ElementDeEntrada> llista = new LinkedBlockingQueue<ElementDeEntrada>();
	private boolean para = false;

	public void setPara(boolean para){
		this.para = para;
	}
	public void run(){
		ElementDeEntrada e = null;
		while (!para){
			try {
				e = llista.take();
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			  }
			if (e.o instanceof SPlayer){
				Estats estat;
				LobbyPlayer player;
				player = LobbyServer.players.get(e.c.getID()); 
				estat = player.login(((SPlayer)e.o).nom, ((SPlayer)e.o).pass);
				try {
					SenderDispatcher.llista.put(new ElementDeSortida(e.c,OperacioEnvia.tu,estat));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		}
	}
}