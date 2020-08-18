package bar;

import javax.swing.JOptionPane;

public class Popup{
	int numero;
	String titulo="Papudim Simulator";
	String informacao="Informa a quantidade de cadeiras";
	
	public Popup() {
		this.receber();
	}
    
	public void receber() {
		while(true) {
		    String cadeira = JOptionPane.showInputDialog(null,
	               informacao, titulo, JOptionPane.INFORMATION_MESSAGE);
		    try {
		    	this.setNumero(Integer.parseInt(cadeira));
		    }catch (NumberFormatException nfe) {		        
		    }
		    if(this.getNumero() > 0 && this.getNumero() < 21) break;
		    else if(cadeira==null)System.exit(0);
		}
    }
	
    public int getNumero() {
		return numero;
	}
    
	public void setNumero(int numero) {
		this.numero = numero;
	}
}