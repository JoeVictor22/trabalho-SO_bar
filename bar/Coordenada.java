package bar;

public class Coordenada {
	private int posX;
	private int posY;
	private boolean chegou;
	
	public Coordenada(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.chegou = false;
	}
	
	public boolean chegou(Ator ator) {
		if(this.chegou == false) {
			if (this.posX == ator.getPosX() && this.posY == ator.getPosY()) {
				this.chegou = true;
				return true;
			}
		}
		return false;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isChegou() {
		return chegou;
	}

	public void setChegou(boolean chegou) {
		this.chegou = chegou;
	}
}
