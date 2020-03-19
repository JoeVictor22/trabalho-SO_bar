package bar;

public class Bar {
	private int cadeiras;
	private int terminados;
	private int qtdBebos;
	private int cadBKP;
	
	public int getCadeiras() {
		return cadeiras;
	}

	public void setCadeiras(int cadeiras) {
		this.cadeiras = cadeiras;
	}

	public int getTerminados() {
		return terminados;
	}

	public void setTerminados(int terminados) {
		this.terminados = terminados;
	}

	public int getQtdBebos() {
		return qtdBebos;
	}

	public void setQtdBebos(int qtdBebos) {
		this.qtdBebos = qtdBebos;
	}
	
	public int getCadBKP() {
		return cadBKP;
	}

	public void setCadBKP(int cadBKP) {
		this.cadBKP = cadBKP;
	}
	
	public Bar(int cadeiras, int qtdBebos) {
		this.cadeiras=cadeiras;
		this.cadBKP=cadeiras;
		this.terminados=0;
		this.qtdBebos=qtdBebos;	
	}
	
}
