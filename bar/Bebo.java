package bar;

import java.util.concurrent.Semaphore;

public class Bebo extends Thread{
	
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
