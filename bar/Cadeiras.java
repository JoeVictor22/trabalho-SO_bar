package bar;

public class Cadeiras {
	private int quantidade_cadeiras = 0;
	
	private Cadeira[] cadeiras = new Cadeira[20];
	private boolean[] flag_cadeiras = new boolean[20];
	
	public Cadeiras(int quantidade_cadeiras){
		this.quantidade_cadeiras = quantidade_cadeiras;
		
		for(int i = 0; i < 20; i++) {
			cadeiras[i] = new Cadeira(250 + (i * 30), 580);
			flag_cadeiras[i] = false;
		}
	}
	
	public void ocuparCadeira(ControleAtor ator) {			
		int i = 0;
		for(i = 0; i < quantidade_cadeiras; i++) {
			if(flag_cadeiras[i] == false) {
				break;
			}
		}
		flag_cadeiras[i] = true;
		ator.setPosCadeira(i);
		ator.setCadeira(cadeiras[i]);
	}
	
	public void liberarCadeira(ControleAtor ator) {
		if (ator.getPosCadeira() > -1) {
			flag_cadeiras[ator.getPosCadeira()] = false; 
			ator.setPosCadeira(-1);
		}
	}
	
	
}
