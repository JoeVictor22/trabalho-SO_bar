package bar;

public class Bar 
{
	private int cadeiras;
	private int terminados;
	private int cadBKP;
	
	
	public Bar(int cadeiras) 
	{
		this.cadeiras=cadeiras;
		this.cadBKP=cadeiras;
		this.terminados=0;
	}	
	
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

	public int getCadBKP() {
		return cadBKP;
	}

	public void setCadBKP(int cadBKP) {
		this.cadBKP = cadBKP;
	}
	
}