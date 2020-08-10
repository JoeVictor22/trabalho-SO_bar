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

		this.ator.setPosX(casa.getPosX() + 30);
		this.ator.setPosY(casa.getPosY()+ 20);
		
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
			ator.setPosX(ator.getPosX());
			ator.setAcao(6);
			casa.setAcao(1);
		}
	}
	
	public void irParaBalcao() {
		
		fila.push(this);
		ator.setGotoX(fila.getX() + fila.getDistancia() * posFila );
		ator.setGotoY(fila.getY());
		casa.setAcao(0);			
		

	}
	public void irParaCadeira() {

		
		fila.pop(this);
		ator.setGotoX(cadeira.getPosX());
		ator.setGotoY(cadeira.getPosY());
		
		if(ator.getPosX()==cadeira.getPosX() && ator.getPosY()==cadeira.getPosY()) {
			ator.setAcao(5);
			casa.setAcao(0);

		}
	}


	public int getPosFila() {
		return posFila;
	}


	public void setPosFila(int posFila) {
		this.posFila = posFila;
	}



	
}
