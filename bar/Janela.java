package bar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Janela implements Runnable, ActionListener {
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
	JLabel nomeDaThreadLabel = new JLabel("Nomeie o seu Papudim!");
	static JTextField tempoBebendo = new JTextField("", 5);
	static JTextField tempoDormindo = new JTextField("", 5);
	static JTextField nomeDaThread = new JTextField("", 5);
	JButton beboButton = new JButton("Adicionar Papudim");
	JLabel consoleLabel = new JLabel("Console\nConsole\nConsole\nConsole\nConsole\n");
	private JTextArea textAreaClientes = new JTextArea(5,33);
	private JTextArea textAreaStatus = new JTextArea(5,33);
	private JTextArea textAreaLog = new JTextArea(5,33);

	private TextAreaOutputStream consoleStream = new TextAreaOutputStream(textAreaLog, "/");
		
	//dimensoes
	private int h;
	private int w;
	
	private String soundtrack = "Data/SFX/fortaleza8bit.wav";
	public Janela(Bar bar, Semaphore mutex, Semaphore cadSemaphore, int altura, int largura) {
		this.h = altura;
		this.w = largura;
		this.bar = bar;
		this.mutex = mutex;
		this.cadSemaphore = cadSemaphore;
	}

	public void create() {
		janela = new JFrame("Papudim simulator");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(w,h+160);
		janela.setResizable(false);
		janela.setLayout(new BorderLayout());
		
		// acoes para os butoes
		beboButton.addActionListener(this);
 
		// add componentes de tela ao jpanel correspondente
		inputUser.add(nomeDaThreadLabel);
		inputUser.add(nomeDaThread);
		inputUser.add(tempoBebendoLabel);
		inputUser.add(tempoBebendo);
		inputUser.add(tempoDormindoLabel);
		inputUser.add(tempoDormindo);
		inputUser.add(beboButton);

		// console redirect implementation
		output.setLayout(new BorderLayout());
		textAreaStatus.setCaretColor(Color.WHITE);
		textAreaLog.setCaretColor(Color.WHITE);

		output.add(new JScrollPane(textAreaStatus, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		output.add(new JScrollPane(textAreaLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);

		System.setOut(new PrintStream(consoleStream));
		
		// criacao de instancia principal do jogo
		jogo = new Canvas(w, h, this, Bebos, bar.getCadeiras());
		
		janela.add(jogo, BorderLayout.CENTER);
		janela.add(inputUser, BorderLayout.PAGE_START);
		janela.add(output, BorderLayout.PAGE_END);
		
		janela.setVisible(true);
		jogo.setJogando(true);
		BGM.play(this.soundtrack);

	}
	
	public void setOutpuClientes(String texto) {
		this.textAreaClientes.setText("<>CLIENTES</>\n" + texto);
	}
	public void setOutpuStatus(String texto) {
		this.textAreaStatus.setText("<>STATUS</>\n" + texto);
	}
	
	public void setOutpuLog(String texto) {
		this.textAreaLog.setText("<>LOG</>\n" + texto);
	}
	
	public void addPersonagem() {
		jogo.addAtor(Bebos[bebosInseridos-1].getAtor());
	}

	public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if(action.equals("Adicionar Papudim")) {
        	if(bebosInseridos <= 19) {
        		int tmpBebendo=0;
        		int tmpDormindo=0;
        		try {
					tmpBebendo=Integer.parseInt(tempoBebendo.getText());
					tmpDormindo=Integer.parseInt(tempoDormindo.getText());
					nomeDaThread.setText(nomeDaThread.getText().strip());
					if(tmpBebendo<=0 || tmpDormindo<=0 || nomeDaThread.getText().isEmpty()) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException entradaInvalida) {
					System.out.println("Entrada(s) invalida(s)");
					tempoBebendo.setText(null);
					tempoDormindo.setText(null);
					nomeDaThread.setText(null);
				}
        		
        		if(tmpBebendo>0 && tmpDormindo>0 && nomeDaThread.getText().isEmpty()!=true) {        			
		        	threadInfo_bebendo.add(tempoBebendo.getText());
		        	threadInfo_dormindo.add(tempoDormindo.getText());
		        	bebosInseridos++;
		        	String ID=("Bebo "+nomeDaThread.getText());
		    		Ator novoAtor = new Ator(w, h);
					Bebos[bebosInseridos-1] = new Bebo(novoAtor, bar, mutex, cadSemaphore, Integer.parseInt(threadInfo_dormindo.get(bebosInseridos-1)), Integer.parseInt(threadInfo_bebendo.get(bebosInseridos-1)), ID);
					Bebos[bebosInseridos-1].start();
					addPersonagem();
        		}
			} else {
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