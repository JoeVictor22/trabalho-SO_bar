package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	private Balcao balcao;
	private Cadeira cadeira;
	
	private Fila fila;
	private int posFila;
	
	
	public ControleAtor(Ator ator,Bebo bebo, Fila fila, Casa casa, Cadeira cadeira) {
		this.ator = ator;

		this.ator.setPosX(casa.getPosX());
		this.ator.setPosY(casa.getPosY());
		
		this.posFila = -1;
		this.bebo = bebo;
		this.fila = fila;
		
		this.casa = casa;
		this.cadeira = cadeira;
		
		irParaCasa();
	}
	
	
	public void atualizar() {		
		ator.atualizar();
		if (bebo.getEstadoBebendo()==true) {
			irParaCadeira();
		}
		else if (bebo.getEstadoCasa()==true) {
			irParaCasa();
		}
		else if(bebo.getEstadoNaFila()==true) {
			irParaBalcao();
		}
		
	}
	
	
	
	public void irParaCasa() {	
		ator.setGotoX(casa.getPosX());
		ator.setGotoY(casa.getPosY());
		
		if(ator.getPosX()==casa.getPosX() && ator.getPosY()==casa.getPosY()) {
			ator.setAcao(6);
		}
	}
	
	public void irParaBalcao() {
		
		fila.push(this);
		ator.setGotoX(fila.getX() + fila.getDistancia() * posFila );
		ator.setGotoY(fila.getY());
			
		

	}
	public void irParaCadeira() {

		
		fila.pop(this);
		ator.setGotoX(cadeira.getPosX());
		ator.setGotoY(cadeira.getPosY());
		
		if(ator.getPosX()==cadeira.getPosX() && ator.getPosY()==cadeira.getPosY()) {
			ator.setAcao(5);
		}
	}


	public int getPosFila() {
		return posFila;
	}


	public void setPosFila(int posFila) {
		this.posFila = posFila;
	}



	
}
