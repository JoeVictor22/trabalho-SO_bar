package bar;

public class Balcao {
	//private ControleAtor[] controladores = new ControleAtor[20];
	private int x;
	private int y;
	private int distancia;
	private int maxFila = 22;
	private boolean[] flags = new boolean[maxFila];
	private Cadeira[] cadeiras = new Cadeira[22];

	// a_a_a_a_ _ _ _ _ _ _ _ _ _ _ _ _
	
	public Balcao(int x, int y, int distancia, ControleAtor[] controladores) {
		this.x = x;
		this.y = y;
		this.distancia = distancia;
		//this.controladores = controladores;
		
		for( int i = 0; i < maxFila; i++) {
			cadeiras[i] = new Cadeira(x - (i * 30), y);
			
			flags[i] = false;
		}

	}

	
	public void atualizar() {
	
		/*
		for(int i = 0; i < 20; i++) {
			
			if (controladores[i] != null && controladores[i].getPosCadeira() > 0) {
				if (flags[controladores[i].getPosCadeira()-1] == false ) {
					flags[controladores[i].getPosCadeira()] = false;
					flags[controladores[i].getPosCadeira()-1] = true;
					
					controladores[i].setPosCadeira(controladores[i].getPosCadeira()-1);
					//controladores[i].setCadeira(cadeiras[i]);
					//controladores[i].gotoCadeira();
					
					
				}
			}
		}
		*/	
		
	}
	
	
			
	public void pop(ControleAtor controlador) {
		if(controlador.getPosCadeira() > -1) {
			flags[controlador.getPosCadeira()] = false;
			controlador.setPosCadeira(-1);
		}
	}
	
	
	public void push(ControleAtor controlador) {
		if(controlador.getPosCadeira() == -1) {
			int i;
			for(i = 0; i < 21; i++) {
				if (flags[i] == false) {
					break;
				}
			}
			flags[i] = true;
			controlador.setPosCadeira(i);
			//controlador.setCadeira(cadeiras[ultimoDaFila]);

		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
}
