package bar;

public class Bar 
{
	private int cadeiras;
	private int cadBKP;
	private boolean barReservadoParaAmigos;
	
	
	public Bar(int cadeiras) 
	{
		this.cadeiras=cadeiras;
		this.cadBKP=cadeiras;
		this.setbarReservadoParaAmigos(true);
	}	
	
	public int getCadeiras() {
		return cadeiras;
	}

	public void setCadeiras(int cadeiras) {
		this.cadeiras = cadeiras;
	}
	
	public int getCadBKP() {
		return cadBKP;
	}

	public void setCadBKP(int cadBKP) {
		this.cadBKP = cadBKP;
	}

	public boolean isbarReservadoParaAmigos() {
		return barReservadoParaAmigos;
	}

	public void setbarReservadoParaAmigos(boolean barReservadoParaAmigos) {
		this.barReservadoParaAmigos = barReservadoParaAmigos;
	}
	
}