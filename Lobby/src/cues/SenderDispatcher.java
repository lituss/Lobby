package cues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public  class SenderDispatcher extends Thread{
	public static BlockingQueue <ElementDeSortida> llista = new LinkedBlockingQueue<ElementDeSortida>();
	boolean para = false;
	
	public void setPara(boolean para){
		this.para = para;
	}

	public void run(){
		while (!para){
			try {
				ElementDeSortida e = llista.take();
				switch (e.operacio) {
				case tu :
					e.connection_sender.sendTCP(e.packet);
				}
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
