package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	private Balcao balcao;
	private Cadeira cadeira;
	
	private Fila fila;
	
	
	public ControleAtor(Ator ator,Bebo bebo, Fila fila, Casa casa, Balcao balcao, Cadeira cadeira) {
		this.ator = ator;
	
		this.bebo = bebo;
		this.fila = fila;
		
		this.casa = casa;
		this.balcao = balcao;
		this.cadeira = cadeira;
	}
	
	
	public void atualizar() {
		if (bebo !=null && ator != null) 
		{
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
		else if(ator != null) {
			ator.setAcao(1);
		}
	}
	
	
	public void irParaCasa() {
		if (casa != null) {
			ator.setAcao(6);
			ator.setPosX(casa.getPosX());
			ator.setPosY(casa.getPosY());			
		}

	}
	
	public void irParaBalcao() {
		if (balcao != null) {
			ator.setAcao(0);
			ator.setPosX(balcao.getPosX());
			ator.setPosY(balcao.getPosY());
			
		}

	}
	public void irParaCadeira() {
		if (cadeira != null) {
			ator.setAcao(5);
			ator.setPosX(cadeira.getPosX());
			ator.setPosY(cadeira.getPosY());
			
		}
	}
	
}
