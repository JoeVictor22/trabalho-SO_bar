package bar;



public class Fila {
	private ControleAtor[] controladores = new ControleAtor[20];
	private int x;
	private int y;
	private int distancia;
	private int maxFila = 50;
	private boolean[] flags = new boolean[maxFila];
	
	public Fila(int x, int y, int distancia, ControleAtor[] controladores) {
		this.x = x;
		this.y = y;
		this.distancia = distancia;
		this.controladores = controladores;
		
		for( int i = 0; i < maxFila; i++) {
			flags[i] = false;
		}
	}

	
	public void atualizar() {
		for(int i = 1; i < 20; i++) {
			if (controladores[i] != null && controladores[i].getPosFila() > 0) {
				if (flags[controladores[i].getPosFila()-1] == false) {
					flags[controladores[i].getPosFila()] = false;
					flags[controladores[i].getPosFila()-1] = true;
					controladores[i].setPosFila(controladores[i].getPosFila()-1);

				}
			}
		}
	}
	
	public void pop(ControleAtor controlador) {
		if(controlador.getPosFila() != -1) {
			flags[controlador.getPosFila()] = false;
			controlador.setPosFila(-1);
		}
	}
	public void push(ControleAtor controlador) {
		if(controlador.getPosFila() == -1) {
			int ultimoDaFila = 0;
			for(int i = 0; i < maxFila ; i++) {
				if(flags[i] == true) {
					ultimoDaFila = i+1;
				}
			}
			flags[ultimoDaFila] = true;
			controlador.setPosFila(ultimoDaFila);
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
