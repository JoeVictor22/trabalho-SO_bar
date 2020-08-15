package bar;

import java.util.concurrent.Semaphore;

import bar.Janela;
 
public class Jogo 
{
	
	public static void main(String[] args) 
	{
		Popup popup = new Popup();

		Bar bar= new Bar(popup.getNumero());
		
		Semaphore mutex = new Semaphore(1,true);
		Semaphore esperaAmigos = new Semaphore(0,true);
		Semaphore cadSemaphore = new Semaphore(bar.getCadeiras(),true);
		
		int h = 740;
		int w = 1280;
		Janela janela = new Janela(bar, mutex, esperaAmigos, cadSemaphore, h, w);
		janela.create();
		 
	}
}