package bar;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.awt.BorderLayout;

import bar.Canvas;

public class Janela implements Runnable, ActionListener{
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
	
	
	
	// jframe e jpanel do jogo
	private JFrame janela;
	private Canvas jogo;
	// componentes IO do user
	JPanel inputUser = new JPanel();
	JButton startButton = new JButton("Iniciar");
	JLabel nomeLabel = new JLabel("Digite o nome do bebo!");
	static JTextField nome = new JTextField("", 15);
	JLabel tempoBebendoLabel = new JLabel("Informe o tempo no bar!");
	static JTextField tempoBebendo = new JTextField("", 5);
	JLabel tempoDormindoLabel = new JLabel("Informe o tempo de soneca!");
	static JTextField tempoDormindo= new JTextField("", 5);
	JButton newBeboButton = new JButton("Adicionar Papudim");
	
	static JLabel errorMessage = new JLabel("");
	

	//keyListener
	
	//dimensoes
	private int h;
	private int w;
	
	public Janela(Bar bar, Semaphore mutex, Semaphore esperaAmigos, Semaphore cadSemaphore, int altura, int largura) {
		this.h = altura;
		this.w = largura;
	
		
		
		this.bar = bar;
		this.mutex = mutex;
		this.esperaAmigos = esperaAmigos;
		this.cadSemaphore = cadSemaphore;
	
	}
	
	public void run() {
		
		System.out.printf("%s\n",nome.getText());
		System.out.printf("%s\n",tempoBebendo.getText());
		System.out.printf("%s\n",tempoDormindo.getText());
	
	}
	
	public void create() {
		janela = new JFrame("Papudim simulator");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(w,h);
		janela.setResizable(false);
		
		
		/*
		 * Adicionar todo IO aqui
		 * */
		startButton.addActionListener(this);
		newBeboButton.addActionListener(this);

		inputUser.add(startButton);
		inputUser.add(nomeLabel);
		inputUser.add(nome);
		inputUser.add(tempoBebendoLabel);
		inputUser.add(tempoBebendo);
		inputUser.add(tempoDormindoLabel);
		inputUser.add(tempoDormindo);
		inputUser.add(newBeboButton);

		
		/* TODO: tratar o tamanho do canvas e da criacao 
		*dos personagens de acordo com o tamanho da janela
		*/
		jogo = new Canvas(w, h, this);
		janela.add(jogo);
		jogo.add(inputUser);
		janela.setVisible(true);
		start();
		run();
		
	}


	
	public void start() {
		/* Rodar funcoes para dar inicio a animacao dos elementos*/
		jogo.setJogando(true);	
		/*for(int i = 0; i < 6; i++) {
			addPersonagem();
		}*/
	}
	
	public void restart() {
		/* Reiniciar animacao*/
		jogo.setJogando(false);
	}
	
	public void addPersonagem() {
		int alturaDoPersonagem = 100;
		int larguraDoPersonagem = 50;

		
		int posX = new Random().nextInt(500) + 10;  // [10...501]
		int posY = new Random().nextInt(500) + 10;  // [10...501]
		int orientacao = new Random().nextInt(7);  // [0...6]
			
		Ator novoAtor = new Ator( posX, posY, alturaDoPersonagem, larguraDoPersonagem, 2, w, h);
		//novoAtor.setOrientacao(orientacao);
		novoAtor.setAcao(orientacao);
		System.out.println(orientacao);
		jogo.addAtor(novoAtor);
	}
	  public void actionPerformed(ActionEvent ae) {
	        String action = ae.getActionCommand();
	        if (action.equals("Iniciar")) {
	        	if(jogo.isJogando()) {
	        		restart();
	        	}else {
	        		start();
	        	}
	        	
	        	/* Funcoes do raynan */
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
	        	if(jogo.getQuantidadeDeAtores() <= 19) {
		        	System.out.println("adicionou");
					addPersonagem();
			    	
					/* RAYNAn*/
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
		        	
	        	}else {
	        		System.out.println("qnt max de atores excedida");
	        	}
	        }
	    }
}
