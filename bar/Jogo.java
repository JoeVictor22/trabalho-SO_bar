package bar;

import java.util.concurrent.Semaphore;

public class Jogo {
	public static void main(String[] args) 
	{
		Popup popup = new Popup();

		Bar bar= new Bar(popup.getNumero());
		
		Semaphore mutex = new Semaphore(1,true);
		Semaphore cadSemaphore = new Semaphore(bar.getCadeiras(),true);
		
		int h = 740;
		int w = 1280;
		Janela janela = new Janela(bar, mutex, cadSemaphore, h, w);
		janela.create();
	}
}