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



public class App extends JFrame implements Runnable, ActionListener{
    private static final long serialVersionUID = 1L;
    
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
	

	
	/*
	String tabela[] = {
		"opcao 1",
		"opcao 2",
		"opcao 3",
		"opcao 4"
	};
	// remover select se nao usado
	JComboBox box = new JComboBox(tabela);
	*/
	public void run() {
		
		System.out.printf("%s\n",nome.getText());
		System.out.printf("%s\n",tempoBebendo.getText());
		System.out.printf("%s\n",tempoDormindo.getText());
	
	}
	
	public App(Bar bar, Semaphore mutex, Semaphore esperaAmigos, Semaphore cadSemaphore) {
		super("Bar do Seu Batista");
		setSize(800,600);
		setResizable(true);
		this.bar = bar;
		this.mutex = mutex;
		this.esperaAmigos = esperaAmigos;
		this.cadSemaphore = cadSemaphore;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startButton.addActionListener(this);
		newBeboButton.addActionListener(this);

		
		panel.add(startButton);
		panel.add(nomeLabel);
		panel.add(nome);
		panel.add(tempoBebendoLabel);
		panel.add(tempoBebendo);
		panel.add(tempoDormindoLabel);
		panel.add(tempoDormindo);
		panel.add(newBeboButton);
		

		errorMessage.setText("mensagem de erro");
		errorMessage.setForeground (Color.red);

		panel.add(errorMessage);
		// remover select se nao usado
		//panel.add(box);

		Canvas grafico = new Canvas(800, 600);
		
		panel.add(grafico);
        this.add(panel, BorderLayout.CENTER);

		//add(panel);
		
		setVisible(true);
		PlayMusic.playMusic("Data/SFX/Undertale_-_Determination.mid");

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
