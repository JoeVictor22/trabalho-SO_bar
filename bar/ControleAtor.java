package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	
	private Cadeira cadeira;
	private Cadeiras cadeiras;
	
	private Balcao balcao;
	private int posCadeira;
	
	private Fila fila;
	private int posFila;

	private boolean flagBebendo = false;
	private boolean flagEsperando = false;
	private boolean flagDormindo = false;
	
	private boolean flagEsperandoOutros = false;
	
	/*
	 * caminho p/ fila
	  700, 270
	  700, 300	  	 
	 */
	
	/*
	922, 590
	922, 580
	1012, 580 
	1012, 300
	 	 */
	private Coordenada[] caminhoCasa = {
			new Coordenada(922, 570),
			new Coordenada(922, 540),
			new Coordenada(1012, 540),
			new Coordenada(1012, 300)

			};
	
	private Coordenada[] caminhoBar = {
			new Coordenada(150, 300),
			new Coordenada(150, 510),
			new Coordenada(240, 510),
			new Coordenada(240, 640),
			new Coordenada(510, 640),
			new Coordenada(550, 590)
			};
	
	// casa.getPosX();
	// casa.getPosY();
	// ator.getPosX();
	// ator.getPosY();
	
	public ControleAtor(Ator ator,Bebo bebo, Fila fila, Casa casa, Balcao balcao) {
		this.ator = ator;

		this.ator.setPosX(casa.getPosX() + 30);
		this.ator.setPosY(casa.getPosY()+ 20);
		
		this.posFila = 21;
		this.bebo = bebo;
		this.fila = fila;
		
		this.casa = casa;
		this.balcao = balcao;
		
		
		this.cadeira = new Cadeira(0,0);
		this.posCadeira = -1;
		irParaCasa();
	}
	
	
	public void atualizar() {		
		// atualizar movimentacao
		ator.atualizar();

		// checar se o ator chegou em casa ou na cadeira para trocar de animacao

		if (bebo.getEstadoBebendo()==true) {
			// caminho ate o bar
			if(this.flagBebendo == false) {
				irParaCadeira();	
				resetarCaminhos(this.caminhoBar);
			
			}else if(segueCaminho(this.caminhoBar)) {

				
				int cadeiraX = balcao.getX() - balcao.getDistancia() * posCadeira;
				int cadeiraY = balcao.getY();
				
				ator.setGotoX(cadeiraX);
				ator.setGotoY(cadeiraY);
				
				if(ator.getPosX()==cadeiraX && ator.getPosY()==cadeiraY) {
					if(ator.getAcao() != 5 && flagEsperandoOutros==false) {
						flagEsperandoOutros=true;
						bebo.setPosicaoBar(true);
						bebo.setPosicaoCasa(false);
						ator.setAcao(5);
						casa.setAcao(0);				
					}
				} 	
			}
			
		}
		else if (bebo.getEstadoCasa()==true) {
			// caminho ate a casa
			if(this.flagDormindo == false) {
				irParaCasa();				
				resetarCaminhos(this.caminhoCasa);

			}else if(segueCaminho(this.caminhoCasa)) {
				
				ator.setGotoX(casa.getPosX());
				ator.setGotoY(casa.getPosY());
				
				if(ator.getPosX()==casa.getPosX() && ator.getPosY()==casa.getPosY()) {
					if(ator.getAcao() != 6) {
						
						flagEsperandoOutros=false;
						
						bebo.setPosicaoCasa(true);
						bebo.setPosicaoBar(false);
						ator.setAcao(6);
						casa.setAcao(1);
					}			
				}
			}
			
		}
		else if(bebo.getEstadoNaFila()==true && this.flagEsperando == false) {
			irParaBalcao();
		}
		
		
		
		
		
		
	}
	
	public void resetarCaminhos(Coordenada[] caminho) {
		for(int j = 0; j < caminho.length; j++) {
			caminho[j].setChegou(false);
		}
	}
	public boolean segueCaminho(Coordenada[] caminho) {
		int i;
		
		for(i = 0; i < caminho.length; i++) {
			if(caminho[i].isChegou() == false) {
				break;
			}
		}
		
		if (i == caminho.length) {
			return true;
		}
		
		if(!caminho[i].chegou(ator)) {
			ator.setGotoX(caminho[i].getPosX());
			ator.setGotoY(caminho[i].getPosY());	
		}
		
		return false;
	}
	
	public void irParaCasa() {	
		
		this.flagDormindo = true;
		this.flagBebendo = false;
		this.flagEsperando= false;
		
	
		
		balcao.pop(this);

	}
	
	public void irParaBalcao() {
		this.flagEsperando = true;
		this.flagDormindo = false;
		this.flagBebendo= false;
		
		this.ator.setOrientacao(1);
		fila.push(this);
		
	}
	public void irParaCadeira() {

		this.flagBebendo = true;
		this.flagDormindo = false;
		this.flagEsperando = false;
		
		fila.pop(this);
		balcao.push(this);



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


	public boolean isFlagEsperandoOutros() {
		return flagEsperandoOutros;
	}


	public void setFlagEsperandoOutros(boolean flagEsperandoOutros) {
		this.flagEsperandoOutros = flagEsperandoOutros;
	}



	
}
