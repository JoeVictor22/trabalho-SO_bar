import java.util.concurrent.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Bar{
	static class Bebo extends Thread{
		
		Semaphore cadSemaphore;
		Semaphore mutex;
		int cadeiras;
		int cadBKP;
		int terminados;
		
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

		public Bebo(int terminado, int cadeiras,Semaphore mutex, Semaphore cadSemaphore, int timeCasa, int timeBebendo, String nome){ 	//Construtor
			super(nome);		//getName(); Recebe o nome da Thread
			this.cadeiras = cadeiras;
			this.cadBKP = cadeiras;
			this.terminados = terminado;
			this.mutex = mutex;
			this.cadSemaphore = cadSemaphore;
			this.timeCasa = timeCasa;
			this.timeBebendo = timeBebendo;	
		}
		
		public void entrarBar() throws InterruptedException {
			cadSemaphore.acquire();
			mutex.acquire();
			cadeiras--;
			mutex.release();
			System.out.printf("%d\n",cadeiras);
		}
		
		public void sairBar() throws InterruptedException {
			if(cadeiras!=0) {
				mutex.acquire();
				cadeiras++;
				cadSemaphore.release();
				mutex.release();
			}else{
				esperarAmigos();
			}
		}
		
		public void esperarAmigos() throws InterruptedException {
			mutex.acquire();
			terminados++;
			mutex.release();
			while(terminados!=cadBKP){
				//esperando amigos//
			}
			mutex.acquire();
				if(terminados!=0) {
					terminados=0;
					cadeiras=cadBKP;
					cadSemaphore.release(cadeiras);	
				}
			mutex.release();
		}
		
		public void noBar() throws InterruptedException{
			entrarBar();
			System.out.printf("--%s Estou a beber por %d segundos--\n", getName(),this.timeBebendo);
			sleep(this.timeBebendo*1000);
			sairBar();
			this.estadoBebendo=false;
			this.estadoCasa=true;
		}
		
		public void emCasa() throws InterruptedException{
			System.out.printf("**%s Estou em casa por %d segundos**\n", getName(),this.timeCasa);
			sleep(this.timeCasa*1000);
			this.estadoCasa=false;
			this.estadoBebendo=true;
			
		}
		public void saida () throws InterruptedException{
			System.out.printf("%s %d-%s %d-%s\n", getName(), this.timeCasa,getEstadoCasa(), this.timeBebendo,getEstadoBebendo());
		}

		public void run(){
			while(true) {
				if(this.estadoBebendo==true){
					try {
						noBar();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else if(this.estadoCasa==true){
					try {
						emCasa();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
		
		
		
		int qtdBebos = ler.nextInt();
		int cadeiras = ler.nextInt();
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
