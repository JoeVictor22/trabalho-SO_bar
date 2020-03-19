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
		Random random = new Random();
		
		System.out.printf("Cadeiras, Quantidade de Bebados\n");
		
		Bar bar= new Bar(ler.nextInt(), ler.nextInt());
		ler.close();
		
		Semaphore mutex = new Semaphore(1);
		Semaphore esperaAmigos = new Semaphore(0);
		Semaphore cadSemaphore = new Semaphore(bar.getCadeiras(),true);
		
		Bebo Bebos[] = new Bebo[bar.getQtdBebos()];
		
		for (int i = 0; i < bar.getQtdBebos(); i++){
			int randInt1 = random.nextInt(6);
			int randInt2 = random.nextInt(4);
			String ID=("Thread "+Integer.toString(i+1));
			Bebos [i] = new Bebo(bar, esperaAmigos, mutex, cadSemaphore, randInt1+1, randInt2+1, ID);
			Bebos [i].start();
		}
	}
}
