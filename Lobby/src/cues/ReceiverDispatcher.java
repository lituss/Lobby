package cues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class ReceiverDispatcher extends Thread{
	BlockingQueue <ElementDeEntrada> llista = new LinkedBlockingQueue<ElementDeEntrada>();
	private boolean para = false;

	public void setPara(boolean para){
		this.para = para;
	}
	public void run(){
		while (!para){
			try {
				ElementDeEntrada e = llista.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		}
	}
}