import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Popup{
	int numero;

	public Popup() {
		super();
		this.receber();
	}
    
    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void receber() {
		final JFrame parent = new JFrame();
        String cadeira = JOptionPane.showInputDialog(parent,
                "Informa a quantidade de cadeiras", null);
        this.setNumero(Integer.parseInt(cadeira));
    }
}