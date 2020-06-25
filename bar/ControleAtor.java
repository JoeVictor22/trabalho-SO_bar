package bar;

public class ControleAtor {

	private Ator ator;
	private Bebo bebo;

	private Casa casa;
	private Balcao balcao;
	private Cadeira cadeira;
	
	private Fila fila;
	

	private int estado;
	
	
	public ControleAtor(Ator ator,Bebo bebo, Fila fila, Casa casa, Balcao balcao, Cadeira cadeira) {
		this.ator = ator;


		this.bebo = bebo;
		this.fila = fila;
		
		this.casa = casa;
		this.balcao = balcao;
		this.cadeira = cadeira;
		
		this.estado = 0;
		
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
			
		
		ator.setAcao(6);
		
	

	}
	
	public void irParaBalcao() {
		//System.out.println(balcao.getPosX() + " - " + balcao.getPosY());
		ator.setGotoX(balcao.getPosX());
		ator.setGotoY(balcao.getPosY());
			
		

	}
	public void irParaCadeira() {
		ator.setGotoX(cadeira.getPosX());
		ator.setGotoY(cadeira.getPosY());
			
		ator.setAcao(5);
	
			
		
	}



	
}
