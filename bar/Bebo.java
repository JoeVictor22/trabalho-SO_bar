package bar;

import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bebo extends Thread {
	Semaphore cadSemaphore;
	Semaphore mutex;
	Bar bar;
		
	//Tempo Registrado em cada estado//
	private int timeCasa;
	private int timeBebendo;
	private long timeRestante;
	
	//Controle de animações da Thread//
	private Ator ator;

	//Estado da Thread//
	private boolean estadoCasa=false;
	private boolean estadoBebendo=false;
	private boolean estadoNaFila=true;
	
	//Posições da Thread//
	private boolean posicaoCasa=false;
	private boolean posicaoBar=false;
	private boolean posicaoPrimeiro=false;
	private boolean inicio=true;
	private boolean possuiFicha=true;
	
	public Bebo(Ator ator, Bar bar, Semaphore mutex, Semaphore cadSemaphore, 
				int timeCasa, int timeBebendo, String nome) {
		super(nome);
		this.bar = bar;
		this.ator = ator;
		this.mutex = mutex;
		this.cadSemaphore = cadSemaphore;
		this.timeCasa = timeCasa;
		this.timeBebendo = timeBebendo;
		this.timeRestante = 0;
		this.setPriority(1);
	}
	
	public void run() {
		while(true) {
			sleep();
			if(this.estadoNaFila) {
				try {
					if(this.isPossuiFicha()) {
						cadSemaphore.acquire();
						this.setPossuiFicha(false);
					}
					if(bar.isbarReservadoParaAmigos()!=true && this.isPosicaoPrimeiro() && this.isInicio()!=true) {
						entrarBar();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(this.posicaoBar) {
				try {
					encherCara();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			else if(this.posicaoCasa) {
				try {
					emCasa();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void sleep() {
		try {
			Thread.sleep(16);
		} 
		catch(InterruptedException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
	}
		
	public void entrarBar() throws InterruptedException {
		mutex.acquire();
		bar.setCadeiras(bar.getCadeiras()-1);
		if(bar.getCadeiras()==0) {
			bar.setbarReservadoParaAmigos(true);
		}
		this.setEstadoNaFila(false);
		this.setEstadoBebendo(true);
		mutex.release();
	}
	
	public void sairBar() throws InterruptedException {
		mutex.acquire();
		bar.setCadeiras(bar.getCadeiras()+1);
		if(bar.getCadBKP()==bar.getCadeiras() && bar.isbarReservadoParaAmigos()) {
			bar.setbarReservadoParaAmigos(false);
			cadSemaphore.release(bar.getCadBKP());
		}else if(bar.isbarReservadoParaAmigos()!=true) {
			cadSemaphore.release();
		}
		mutex.release();
		this.setEstadoBebendo(false);
		this.setEstadoCasa(true);		
		this.setPosicaoBar(false);
		this.setPossuiFicha(true);
		this.setPosicaoPrimeiro(false);
	}
	
	public void encherCara() throws InterruptedException {
		timeHolder(this.timeBebendo);
		this.ator.setOrientacao(4);
		this.ator.setAcao(0);
		sairBar();
	}
	
	public void emCasa() throws InterruptedException {
		timeHolder(this.timeCasa);
		this.estadoCasa = false;
		this.inicio= true;
		this.estadoNaFila = true;
		this.posicaoCasa = false;
	}
	
	public void timeHolder(int tempo) {
		SimpleDateFormat tempoAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		long tempoInicial = tempoAtual.getCalendar().getTimeInMillis();
		tempo=tempo*1000;
		while ((tempoAtual.getCalendar().getTimeInMillis() - tempoInicial) < tempo ) {
			tempoAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			this.timeRestante = 1+(tempo-(tempoAtual.getCalendar().getTimeInMillis() - tempoInicial))/1000;
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
	
	public Ator getAtor() {
		return ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}

	public boolean isPosicaoCasa() {
		return posicaoCasa;
	}

	public void setPosicaoCasa(boolean posicaoCasa) {
		this.posicaoCasa = posicaoCasa;
	}

	public boolean isPosicaoBar() {
		return posicaoBar;
	}

	public void setPosicaoBar(boolean posicaoBar) {
		this.posicaoBar = posicaoBar;
	}
	
	public void printarBar() {
		System.out.println("sentei");
	}
	public void printarCasa() {
		System.out.println("dormi");
	}
	public String toString() {
		return("Nome: "+getName()+"   Tempo Bebendo: "+this.timeBebendo+"   Tempo Em Casa: "+this.timeCasa);
	}
	public String status() {
		if(this.estadoBebendo && this.posicaoBar) {
			return(getName()+" esta Bebendo!"+"   Tempo restante: "+this.timeRestante);
		}
		else if(this.estadoBebendo && this.posicaoBar==false) {
			return(getName()+" pegando uma cadeira!");
		}
		else if(this.estadoCasa && this.posicaoCasa) {
			return(getName()+" esta Dormindo!"+"   Tempo restante: "+this.timeRestante);
		}
		else if(this.estadoCasa && this.posicaoCasa==false) {
			return(getName()+" esta indo para Casa!");
		}
		else if(this.estadoNaFila) {
			return(getName()+" foi para Fila!");
		}
		return null;
	}

	public boolean isPosicaoPrimeiro() {
		return posicaoPrimeiro;
	}

	public void setPosicaoPrimeiro(boolean posicaoPrimeiro) {
		this.posicaoPrimeiro = posicaoPrimeiro;
	}

	public boolean isInicio() {
		return inicio;
	}

	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
	
	public boolean isPossuiFicha() {
		return possuiFicha;
	}

	public void setPossuiFicha(boolean possuiFicha) {
		this.possuiFicha = possuiFicha;
	}
}

