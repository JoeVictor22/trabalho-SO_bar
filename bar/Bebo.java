package bar;

import java.util.concurrent.Semaphore;

public class Bebo extends Thread
{
	
	Semaphore cadSemaphore;
	Semaphore esperaAmigos;
	Semaphore mutex;
	Bar bar;
	
	//Tempo Registrado em cada estado//
	private int timeCasa;
	private int timeBebendo;

	//Estado da Thread//
	private boolean estadoCasa=false;
	private boolean estadoBebendo=false;
	private boolean estadoNaFila=true;
	
	public Bebo(Bar bar, Semaphore esperaAmigos, Semaphore mutex, Semaphore cadSemaphore, 
				int timeCasa, int timeBebendo, String nome)
	{
		super(nome);		//getName(); Recebe o nome da Thread
		this.bar = bar;
		this.esperaAmigos = esperaAmigos;
		this.mutex = mutex;
		this.cadSemaphore = cadSemaphore;
		this.timeCasa = timeCasa;
		this.timeBebendo = timeBebendo;	
	}
	
	public void entrarBar() throws InterruptedException 
	{
		cadSemaphore.acquire();
		mutex.acquire();
		bar.setCadeiras(bar.getCadeiras()-1);
		this.estadoNaFila=false;
		this.estadoBebendo=true;
		mutex.release();
		//System.out.printf("%d\n",bar.getCadeiras());
	}
	
	public void sairBar() throws InterruptedException 
	{
		if(bar.getCadeiras()!=0) 
		{
			mutex.acquire();
			bar.setCadeiras(bar.getCadeiras()+1);
			cadSemaphore.release();
			mutex.release();
		}else{
			esperarAmigos();
		}
	}
	
	public void esperarAmigos() throws InterruptedException 
	{
		mutex.acquire();
		esperaAmigos.drainPermits();
		bar.setTerminados(bar.getTerminados()+1);
		if (bar.getTerminados()!=bar.getCadBKP()) 
		{
			//System.out.println(esperaAmigos.availablePermits()+" "+bar.getTerminados()+" "+getName());
			mutex.release();
			esperaAmigos.acquire();
			//System.out.println(esperaAmigos.availablePermits()+" "+bar.getTerminados()+" "+getName());
		}
		else if (bar.getTerminados()==bar.getCadBKP()) 
		{
			
			bar.setTerminados(0);
			bar.setCadeiras(bar.getCadBKP());
			cadSemaphore.release(bar.getCadeiras());
			esperaAmigos.release(bar.getCadeiras()-1);
			//System.out.println("Sai por ultimo " + getName());
			//System.out.println(esperaAmigos.availablePermits()+" "+bar.getTerminados()+" "+getName());
			//System.out.println(esperaAmigos.toString());
			mutex.release();
		}
	}
	
	public void noBar() throws InterruptedException
	{
		entrarBar();
		System.out.printf("--%s Estou a beber por %d segundos--\n", getName(),this.timeBebendo);
		sleep(this.timeBebendo*1000);
		sairBar();
		this.estadoBebendo=false;
		this.estadoCasa=true;
	}
	
	public void emCasa() throws InterruptedException
	{
		System.out.printf("**%s Estou em casa por %d segundos**\n", getName(),this.timeCasa);
		sleep(this.timeCasa*1000);
		this.estadoCasa=false;
		this.estadoNaFila=true;
	}
	
	public void saida () throws InterruptedException {
		System.out.printf("%s %d-%s %d-%s\n", getName(), this.timeCasa,getEstadoCasa(), this.timeBebendo,getEstadoBebendo());
	}

	public void run()
	{
		while(true) 
		{
			if(this.estadoNaFila==true)
			{
				try {
					noBar();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(this.estadoCasa==true)
			{
				try {
					emCasa();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean getEstadoCasa() {
		return estadoCasa;
	}
	
	public void setEstadoCasa(boolean estadoCasa) {
		this.estadoCasa = estadoCasa;
	}
	
	public boolean getEstadoBebendo() {
		return estadoBebendo;
	}
	
	public void setEstadoBebendo(boolean estadoBebendo) {
		this.estadoBebendo = estadoBebendo;
	}
	
	public boolean getEstadoNaFila() {
		return estadoNaFila;
	}
	
	public void setEstadoNaFila(boolean estadoNaFila) {
		this.estadoNaFila = estadoNaFila;
	}
}