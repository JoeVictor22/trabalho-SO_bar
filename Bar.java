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
		
		
		Random random=new Random();
		
		String qtdBebosArg = "6"; // deve ser passado via argumentos com o max 10
								  //validar se foi digitado um numero e nao um char
		/*if(qtdBebosArg.isNumeric() || qtdBebosArg > 10){
			System.out.println("Digite um valor de 1 a 10");
		}*/
		
		int qtdBebos = Integer.parseInt(qtdBebosArg);
		//cadeiras = ler.nextInt();
		
		
		for (int i = 0; i < qtdBebos; i++){
			int randInt1 = random.nextInt(5);
			int randInt2 = random.nextInt(5);
			String ID=("Thread "+Integer.toString(i+1));
			Bebos [i] = new Bebo(randInt1+1, randInt2+1, ID);
		}
		
		for (int i = 0; i < qtdBebos; i++) {
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


class Bebo extends Thread{
	//Tempo Registrado em cada estado//
	private int timeCasa;
	private int timeBebendo;
	//Tempo Registrado em cada estado//

	//Estado da Thread//
	private int estadoCasa=0;
	private int estadoBebendo=0;
	//Estado da Thread//

	public Bebo(int timeCasa, int timeBebendo, String nome){ 	//Construtor
		super(nome);		// getName(); Recebe o nome da Thread
		this.timeCasa = timeCasa;
		this.timeBebendo = timeBebendo;	
	}

	public void run(){
		while(true) {	
		}
	}
	
	public void noBar() throws InterruptedException{
		this.estadoBebendo=1;
		while(this.estadoBebendo==1){
			System.out.printf("Eu %s Estou a beber por %d segundos\n", getName(),this.timeBebendo);
			super.sleep(this.timeBebendo*3000);
			this.estadoBebendo=0;
		}
	}
	
	public void emCasa() throws InterruptedException{
		this.estadoCasa=1;
		while(this.estadoCasa==1){
			System.out.printf("Eu %s Estou em casa por %d segundos\n", getName(),this.timeBebendo);
			super.sleep(this.timeBebendo*10000);
			this.estadoCasa=0;
		}
	}
	
	public void saida () throws InterruptedException{
		System.out.printf("%s %d %d\n", getName(), this.timeCasa, this.timeBebendo);
	}
}
