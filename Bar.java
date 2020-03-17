import java.util.concurrent.*;
import java.io.IOException;
import java.util.Random;


public class Bar{
	static Bebo Bebos[] = new Bebo[6];
	
	
	public static void printThreads() throws IOException{
		for (Bebo t : Bebos){
			//Runtime.getRuntime().exec("clear");
			String nome = t.getName();
			Thread.State state = t.getState();
			int prioridade = t.getPriority();
			System.out.printf("%-20s \t %s \t %d \n",nome , state, prioridade);
			
		}
	}
	
	/*public void addBebo(){
			int randInt1 = random.nextInt(5);
			int randInt2 = random.nextInt(5);
			Bebo novoBebo = new Bebo(randInt1, randInt2, ID).start();
			ID=ID+1;
	}*/
	
	
	public static void main(String[] args) throws InterruptedException, IOException{
		int cadeiras = 3; 		//Qtd de cadeira no bar
		//int qtdOcupado=0;		//Qtd de cadeira no bar Ocupadas
		//int jantandoJuntos=0;	//True se Cadeiras==qtdOcupado
		
		Semaphore cheio = new Semaphore(0);
		Semaphore mutex = new Semaphore(1);
		int ID=1;
		
		Random random=new Random();
		
		String qtdBebosArg = "6"; // deve ser passado via argumentos com o max 10
								  //validar se foi digitado um numero e nao um char
		/*if(qtdBebosArg.isNumeric() || qtdBebosArg > 10){
			System.out.println("Digite um valor de 1 a 10");
		}*/
		
		int qtdBebos = Integer.parseInt(qtdBebosArg);
		//cadeiras = ler.nextInt();
		
		
		for(int i = 0; i < qtdBebos; i++){
			int randInt1 = random.nextInt(5);
			int randInt2 = random.nextInt(5);
			Bebos [i] = new Bebo(randInt1+1, randInt2+1, ID);
			ID=ID+1;
			Bebos[i].start();
		}
		
		while(true){
			for (int i=0;i<qtdBebos;i++) {
				if (cadeiras>0) {
					System.out.printf("%d Cadeiras Livres\n",cadeiras);
					mutex.acquire();
					cadeiras--;
					mutex.release();
					Bebos[i].noBar();
					printThreads();
					System.out.printf("%d Cadeiras Livres\n",cadeiras);
				}else{
					mutex.acquire();
					cadeiras=3;
					mutex.release();
				}
			}
		}
		/* Get all threads */
		// Set<Thread> threads = Thread.getAllStackTraces().keySet();
		// ciclo de exec
		
	}
}
