package bar;
import java.util.concurrent.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class main{
	
//	public static void printThreads() throws IOException{
//		for (Bebo t : Bebos){
//			//Runtime.getRuntime().exec("clear");
//			String nome = t.getName();
//			Thread.State state = t.getState();
//			int prioridade = t.getPriority();
//			System.out.printf("%-20s \t %s \t %d \n",nome , state, prioridade);
//			
//		}
//	}
	
	/*public void addBebo(){
			int randInt1 = random.nextInt(5);
			int randInt2 = random.nextInt(5);
			Bebo novoBebo = new Bebo(randInt1, randInt2, ID).start();
			ID=ID+1;
	}*/
	
	
	public static void main(final String[] args) throws InterruptedException, IOException{
		Scanner ler = new Scanner(System.in);
		
		System.out.printf("Cadeiras\n");
		
		Bar bar= new Bar(ler.nextInt());
		ler.close();
		
		Semaphore mutex = new Semaphore(1);
		Semaphore esperaAmigos = new Semaphore(0,true);
		Semaphore cadSemaphore = new Semaphore(bar.getCadeiras(),true);
		
		App ap = new App(bar, mutex, esperaAmigos, cadSemaphore);
		ap.run();
		
	}
}
