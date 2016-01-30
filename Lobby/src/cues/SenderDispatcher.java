package cues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public  class SenderDispatcher extends Thread{
	public BlockingQueue <ElementDeSortida> llista = new LinkedBlockingQueue<ElementDeSortida>();
	boolean para = false;
	
	public void setPara(boolean para){
		this.para = para;
	}

	public void run(){
		while (!para){
			try {
				ElementDeSortida e = llista.take();
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
