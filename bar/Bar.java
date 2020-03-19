package bar;
import java.util.concurrent.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Bar{
	
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
	
	
	public static void main(String[] args) throws InterruptedException, IOException{
		Scanner ler = new Scanner(System.in);
		Random random = new Random();
		
		
		
		int qtdBebos = ler.nextInt();
		int cadeiras = ler.nextInt();
		ler.close();
		int terminado = 0;
		
		
		Semaphore mutex = new Semaphore(1);
		Semaphore cadSemaphore = new Semaphore(cadeiras,true);
		
		Bebo Bebos[] = new Bebo[qtdBebos];
		
		for (int i = 0; i < qtdBebos; i++){
			int randInt1 = random.nextInt(6);
			int randInt2 = random.nextInt(4);
			String ID=("Thread "+Integer.toString(i+1));
			Bebos [i] = new Bebo(terminado, cadeiras, mutex, cadSemaphore, randInt1+1, randInt2+1, ID);
			Bebos [i].start();
		}
	}
}
