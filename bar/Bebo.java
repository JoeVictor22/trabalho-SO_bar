package bar;

import java.util.concurrent.Semaphore;

public class Bebo extends Thread{
	
	Semaphore cadSemaphore;
	Semaphore esperaAmigos;
	Semaphore mutex;
	Bar bar;
	
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

	public Bebo(Bar bar, Semaphore esperaAmigos, Semaphore mutex, Semaphore cadSemaphore, int timeCasa, int timeBebendo, String nome){ 	//Construtor
		super(nome);		//getName(); Recebe o nome da Thread
		this.bar = bar;
		this.esperaAmigos = esperaAmigos;
		this.mutex = mutex;
		this.cadSemaphore = cadSemaphore;
		this.timeCasa = timeCasa;
		this.timeBebendo = timeBebendo;	
	}
	
	public void entrarBar() throws InterruptedException {
		cadSemaphore.acquire();
		mutex.acquire();
		bar.setCadeiras(bar.getCadeiras()-1);
		mutex.release();
		//System.out.printf("%d\n",bar.getCadeiras());
	}
	
	public void sairBar() throws InterruptedException {
		if(bar.getCadeiras()!=0) {
			mutex.acquire();
			bar.setCadeiras(bar.getCadeiras()+1);
			cadSemaphore.release();
			mutex.release();
		}else{
			esperarAmigos();
		}
	}
	
	public void esperarAmigos() throws InterruptedException {
		mutex.acquire();
		bar.setTerminados(bar.getTerminados()+1);
		if (bar.getTerminados()!=bar.getCadBKP()) {
			mutex.release();
			//System.out.println("Dormi " + getName());
			esperaAmigos.acquire();
		}else if (bar.getTerminados()==bar.getCadBKP()) {
			bar.setTerminados(0);
			bar.setCadeiras(bar.getCadBKP());
			cadSemaphore.release(bar.getCadeiras());
			esperaAmigos.release();
			//System.out.println(esperaAmigos.toString());
			mutex.release();
		}
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
