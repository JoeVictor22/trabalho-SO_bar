package bar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.awt.BorderLayout;



public class App implements Runnable, ActionListener{
    
    List<String> threadInfo_nomes = new ArrayList<>();
	List<String> threadInfo_bebendo = new ArrayList<>();
    List<String> threadInfo_dormindo = new ArrayList<>();

    Bar bar;
    Semaphore mutex;
    Semaphore esperaAmigos;
    Semaphore cadSemaphore;
    

    int bebosInseridos=0;
	
	public int getbebosInseridos() {
		return bebosInseridos;
	}
	
	JPanel panel = new JPanel();
	JButton startButton = new JButton("Iniciar");
	JLabel nomeLabel = new JLabel("Digite o nome do bebo!");
	static JTextField nome = new JTextField("", 15);
	JLabel tempoBebendoLabel = new JLabel("Informe o tempo no bar!");
	static JTextField tempoBebendo = new JTextField("", 5);
	JLabel tempoDormindoLabel = new JLabel("Informe o tempo de soneca!");
	static JTextField tempoDormindo= new JTextField("", 5);
	JButton newBeboButton = new JButton("Adicionar Papudim");
	
	static JLabel errorMessage = new JLabel("");
	


	public void run() {
		
		System.out.printf("%s\n",nome.getText());
		System.out.printf("%s\n",tempoBebendo.getText());
		System.out.printf("%s\n",tempoDormindo.getText());
	
	}
	
	public App(Bar bar, Semaphore mutex, Semaphore esperaAmigos, Semaphore cadSemaphore) {
		this.bar = bar;
		this.mutex = mutex;
		this.esperaAmigos = esperaAmigos;
		this.cadSemaphore = cadSemaphore;
	

	}
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Iniciar")) {
        	Bebo Bebos[] = new Bebo[bebosInseridos];
	        for (int i=0;i<bebosInseridos;i++){
	        	String ID=("Thread "+Integer.toString(i+1));
				Bebos [i] = new Bebo(bar, esperaAmigos, mutex, cadSemaphore, Integer.parseInt(threadInfo_dormindo.get(i)), Integer.parseInt(threadInfo_bebendo.get(i)), ID);
	        }
	        for (int i=0;i<bebosInseridos;i++) {
	        	Bebos[i].start();
	        }
        }
        else if (action.equals("Adicionar Papudim")) {
    		      	
        	threadInfo_nomes.add(nome.getText());
        	threadInfo_bebendo.add(tempoBebendo.getText());
        	threadInfo_dormindo.add(tempoDormindo.getText());
        	bebosInseridos++;
        	
        	if(/*to do*/1!=1) {
        		System.out.printf("Campo vazio!\n");
        	}else {
        		System.out.println(bebosInseridos);
        		System.out.println(threadInfo_nomes);
        		System.out.println(threadInfo_bebendo);
        		System.out.println(threadInfo_dormindo);
        	}
        	
        	
        }
    }
 
}
