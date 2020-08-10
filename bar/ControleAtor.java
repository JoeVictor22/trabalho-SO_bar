package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	private Balcao balcao;
	private Cadeira cadeira;
	
	private Fila fila;
	private int posFila;

	
	private boolean flagBebendo = false;
	private boolean flagEsperando = false;
	private boolean flagDormindo = false;
	
	
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
		if (bebo.getEstadoBebendo()==true && this.flagBebendo == false) {
			irParaCadeira();
		}
		else if (bebo.getEstadoCasa()==true && this.flagDormindo == false) {
			irParaCasa();
		}
		else if(bebo.getEstadoNaFila()==true && this.flagEsperando == false) {
			irParaBalcao();
		}
		
		
		
		// checar se o ator chegou em casa ou na cadeira para trocar de animacao
		if(ator.getPosX()==casa.getPosX() && ator.getPosY()==casa.getPosY()) {
			//ator.setPosX(ator.getPosX());
			ator.setAcao(6);
			casa.setAcao(1);
		}else if(ator.getPosX()==cadeira.getPosX() && ator.getPosY()==cadeira.getPosY()) {
			ator.setAcao(5);
			casa.setAcao(0);
			//cadeiras.sentar();
		}
		
		
		
	}
	
	
	
	public void irParaCasa() {	
		this.flagDormindo = true;
		this.flagBebendo = false;
		this.flagEsperando= false;
		
		ator.setGotoX(casa.getPosX());
		ator.setGotoY(casa.getPosY());
		
	}
	
	public void irParaBalcao() {
		this.flagEsperando = true;
		this.flagDormindo = false;
		this.flagBebendo= false;
		
		fila.push(this);
		

	}
	public void irParaCadeira() {
		this.flagBebendo = true;
		this.flagDormindo = false;
		this.flagEsperando = false;
		
		fila.pop(this);
		ator.setGotoX(cadeira.getPosX());
		ator.setGotoY(cadeira.getPosY());

	}


	public int getPosFila() {
		return posFila;
	}


	public void setPosFila(int posFila) {
		this.posFila = posFila;
		
		// sempre que a posFila mudar o personagem vai andar ate aquela pos
		ator.setGotoX(fila.getX() + fila.getDistancia() * posFila );
		ator.setGotoY(fila.getY());
		casa.setAcao(0);			

	}



	
}
