package bar;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import bar.Janela;
 
public class Jogo {
	
	public static void main(String[] args) {
	
		
		Scanner ler = new Scanner(System.in);
		
		System.out.printf("Cadeiras\n");
		
		Bar bar= new Bar(ler.nextInt());
		ler.close();
		
		Semaphore mutex = new Semaphore(1);
		Semaphore esperaAmigos = new Semaphore(0,true);
		Semaphore cadSemaphore = new Semaphore(bar.getCadeiras(),true);
		
		int h = 740;
		int w = 1280;
		Janela janela = new Janela(bar, mutex, esperaAmigos, cadSemaphore, h, w);
		janela.create();
		 
	}
	
}
  