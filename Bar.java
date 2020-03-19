import java.util.concurrent.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Bar{
	static class Bebo extends Thread{
		
		Semaphore sem;
		
		//Tempo Registrado em cada estado//
		private int timeCasa;
		private int timeBebendo;
		//Tempo Registrado em cada estado//

		//Estado da Thread//
		private boolean estadoCasa=false;
		public boolean getEstadoCasa() {
			return estadoCasa;
		}

		public void setEstadoCasa(boolean estadoCasa) {
			this.estadoCasa = estadoCasa;
		}

		private boolean estadoBebendo=true;
		public boolean getEstadoBebendo() {
			return estadoBebendo;
		}

		public void setEstadoBebendo(boolean estadoBebendo) {
			this.estadoBebendo = estadoBebendo;
		}
		//Estado da Thread//

		public Bebo(Semaphore sem, int timeCasa, int timeBebendo, String nome){ 	//Construtor
			super(nome);		// getName(); Recebe o nome da Thread
			this.sem = sem;
			this.timeCasa = timeCasa;
			this.timeBebendo = timeBebendo;	
		}
		
		
		public void noBar(){
			try {
				sem.acquire();
				System.out.printf("--%s Estou a beber por %d segundos--\n", getName(),this.timeBebendo);
				try {
					sleep(this.timeBebendo*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.estadoBebendo=false;
				this.estadoCasa=true;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			sem.release();
			
		}
		
		public void emCasa(){
			System.out.printf("**%s Estou em casa por %d segundos**\n", getName(),this.timeCasa);
			try {
				sleep(this.timeCasa*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.estadoCasa=false;
			this.estadoBebendo=true;
			
		}
		public void saida () throws InterruptedException{
			System.out.printf("%s %d-%s %d-%s\n", getName(), this.timeCasa,getEstadoCasa(), this.timeBebendo,getEstadoBebendo());
		}

		public void run(){
			while(true) {
				if(this.estadoBebendo==true){
					noBar();
				}else if(this.estadoCasa==true){
					emCasa();
				}
			}
		}
	}
	
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
		//Semaphore mutex = new Semaphore(1);
		
		int qtdBebos = ler.nextInt();
		int cadeiras = ler.nextInt();
		
		Semaphore cad = new Semaphore(cadeiras,true);
		
		Bebo Bebos[] = new Bebo[qtdBebos];
		
		for (int i = 0; i < qtdBebos; i++){
			int randInt1 = random.nextInt(6);
			int randInt2 = random.nextInt(4);
			String ID=("Thread "+Integer.toString(i+1));
			Bebos [i] = new Bebo(cad, randInt1+1, randInt2+1, ID);
			Bebos [i].start();
		}
	}
}
