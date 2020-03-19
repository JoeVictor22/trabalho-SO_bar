package bar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Aperte implements Runnable, KeyListener {
	/**
	 * 
	 */
	
	
	//instrucoes
	public void run() {
		while(true) {
			System.out.println("comecou");
		}	
	}
	
	
	
	//implementacao de keyListener
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			System.out.println("Apertei A");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			System.out.println("Apertei D");
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			System.out.println("Soltei A");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			System.out.println("Soltei D");		}
		
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}
