package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	
	private Cadeira cadeira;
	private Cadeiras cadeiras;
	private int posCadeira;
	
	private Fila fila;
	private int posFila;

	private boolean flagBebendo = false;
	private boolean flagEsperando = false;
	private boolean flagDormindo = false;
	
	
	public ControleAtor(Ator ator,Bebo bebo, Fila fila, Casa casa, Cadeiras cadeiras) {
		this.ator = ator;

		this.ator.setPosX(casa.getPosX() + 30);
		this.ator.setPosY(casa.getPosY()+ 20);
		
		this.posFila = 21;
		this.bebo = bebo;
		this.fila = fila;
		
		this.casa = casa;
		this.cadeiras = cadeiras;
		
		
		this.cadeira = new Cadeira(0,0);
		this.posCadeira = -1;
		irParaCasa();
	}
	
	
	public void atualizar() {		
		ator.atualizar();

		if (bebo.getEstadoBebendo()==true && this.flagBebendo == false) {
			System.out.println("bebe");
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
			if(ator.getAcao() != 6) {
				bebo.setPosicaoCasa(true);
				bebo.setPosicaoBar(false);
				ator.setAcao(6);
				casa.setAcao(1);
			
			}
		}else if(ator.getPosX()==cadeira.getPosX() && ator.getPosY()==cadeira.getPosY()) {
			if(ator.getAcao() != 5) {
				bebo.setPosicaoBar(true);
				bebo.setPosicaoCasa(false);
				ator.setAcao(5);
				casa.setAcao(0);				
			}
		}
		
		
		
	}
	
	
	
	public void irParaCasa() {	
		
		this.flagDormindo = true;
		this.flagBebendo = false;
		this.flagEsperando= false;
		
		ator.setGotoX(casa.getPosX());
		ator.setGotoY(casa.getPosY());
		
		cadeiras.liberarCadeira(this);

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
		cadeiras.ocuparCadeira(this);

		ator.setGotoX(this.cadeira.getPosX());
		ator.setGotoY(this.cadeira.getPosY());

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


	public boolean isFlagBebendo() {
		return flagBebendo;
	}


	public void setFlagBebendo(boolean flagBebendo) {
		this.flagBebendo = flagBebendo;
	}


	public boolean isFlagEsperando() {
		return flagEsperando;
	}


	public void setFlagEsperando(boolean flagEsperando) {
		this.flagEsperando = flagEsperando;
	}


	public boolean isFlagDormindo() {
		return flagDormindo;
	}


	public void setFlagDormindo(boolean flagDormindo) {
		this.flagDormindo = flagDormindo;
	}


	public int getPosCadeira() {
		return posCadeira;
	}


	public void setPosCadeira(int posCadeira) {
		this.posCadeira = posCadeira;
	}


	public Cadeira getCadeira() {
		return cadeira;
	}


	public void setCadeira(Cadeira cadeira) {
		this.cadeira = cadeira;
	}



	
}
