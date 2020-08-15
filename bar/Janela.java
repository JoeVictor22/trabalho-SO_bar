package bar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import bar.Canvas;

public class Janela implements Runnable, ActionListener
{

	List<String> threadInfo_bebendo = new ArrayList<>();
    List<String> threadInfo_dormindo = new ArrayList<>();
    
    Bar bar;
    Semaphore mutex;
    Semaphore cadSemaphore;
    
    int bebosInseridos=0;
	Bebo Bebos[] = new Bebo[20];
    
	// jframe e jpanel do jogo
	private JFrame janela;
	private Canvas jogo;
	// componentes IO do user
	JPanel inputUser = new JPanel();
	JPanel output = new JPanel();

	static JLabel errorMessage = new JLabel("");
	JLabel tempoBebendoLabel = new JLabel("Informe o tempo no bar!");	
	JLabel tempoDormindoLabel = new JLabel("Informe o tempo de soneca!");
	static JTextField tempoBebendo = new JTextField("", 5);
	static JTextField tempoDormindo= new JTextField("", 5);
	JButton beboButton = new JButton("Adicionar Papudim");
	JLabel consoleLabel = new JLabel("Console\nConsole\nConsole\nConsole\nConsole\n");
	private JTextArea textAreaConsole = new JTextArea(5,60);
	private TextAreaOutputStream consoleStream = new TextAreaOutputStream(textAreaConsole, "Console");
		
	
	

	//keyListener
	
	//dimensoes
	private int h;
	private int w;
	
	public Janela(Bar bar, Semaphore mutex, Semaphore cadSemaphore, int altura, int largura) 
	{
		this.h = altura;
		this.w = largura;
		this.bar = bar;
		this.mutex = mutex;
		this.cadSemaphore = cadSemaphore;
	}

	public void create() 
	{
		janela = new JFrame("Papudim simulator");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(w,h+30);
		janela.setResizable(false);
		
		// acoes para os butoes
		beboButton.addActionListener(this);
 
		// add componentes de tela ao jpanel correspondente
		inputUser.add(tempoBebendoLabel);
		inputUser.add(tempoBebendo);
		inputUser.add(tempoDormindoLabel);
		inputUser.add(tempoDormindo);
		inputUser.add(beboButton);

		
		// console redirect implementation
		/*
		output.setLayout(new BorderLayout());
		output.add(new JScrollPane(textAreaConsole, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		System.setOut(new PrintStream(consoleStream));
		*/
		
		
		// criacao de instancia principal do jogo
		jogo = new Canvas(w, h, this, Bebos);
		// add jogo ao jframe
		janela.add(jogo);
		// add jpanels ao jogo
		jogo.add(output);
		jogo.add(inputUser);
		janela.setVisible(true);
		jogo.setJogando(true);
	}
	
	public void addPersonagem() 
	{
		jogo.addAtor(Bebos[bebosInseridos-1].getAtor());
	}

	public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if(action.equals("Adicionar Papudim")) {
        	if(bebosInseridos <= 19)
        	{
        		if(tempoBebendo.getText().isEmpty() && tempoDormindo.getText().isEmpty()) {
        			System.out.println("Campo(s) Vazio(s)");
        		}
        		else {
        			System.out.println("Inseriu");
    	        	threadInfo_bebendo.add(tempoBebendo.getText());
    	        	threadInfo_dormindo.add(tempoDormindo.getText());
    	        	bebosInseridos++;
    	        	String ID=("Thread "+Integer.toString(bebosInseridos));
    	    		Ator novoAtor = new Ator(w, h);

    				Bebos[bebosInseridos-1] = new Bebo(novoAtor, bar, mutex, cadSemaphore, Integer.parseInt(threadInfo_dormindo.get(bebosInseridos-1)), Integer.parseInt(threadInfo_bebendo.get(bebosInseridos-1)), ID);
    				Bebos[bebosInseridos-1].start();
    				addPersonagem();
    					
    				System.out.println(bebosInseridos);
    				System.out.println(threadInfo_bebendo);
    				System.out.println(threadInfo_dormindo);
        		}
			}
			else {
				System.out.println("Quantidade maxima de atores excedida");
			}
		}
	}
	  
	public int getbebosInseridos() {
		return bebosInseridos;
	}
	public int getQuantidadeCadeiras() {
		return this.bar.getCadeiras();
	}
	public void run() {
	}
	public void restart() {
	}
}