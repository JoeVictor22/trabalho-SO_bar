package bar;



public class Fila {
	private Ator[] atores = new Ator[20];
	private int x;
	private int y;
	private int distancia;
	
	public Fila(int x, int y, int distancia, Ator[] atores) {
		this.x = x;
		this.y = y;
		this.distancia = distancia;
		this.atores = atores;
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
