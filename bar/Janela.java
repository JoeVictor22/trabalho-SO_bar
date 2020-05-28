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

public class Janela implements Runnable, ActionListener
{

	List<String> threadInfo_bebendo = new ArrayList<>();
    List<String> threadInfo_dormindo = new ArrayList<>();
    
    Bar bar;
    Semaphore mutex;
    Semaphore esperaAmigos;
    Semaphore cadSemaphore;
    
    int bebosInseridos=0;
	Bebo Bebos[] = new Bebo[20];
    
	// jframe e jpanel do jogo
	private JFrame janela;
	private Canvas jogo;
	// componentes IO do user
	JPanel inputUser = new JPanel();
	
	static JLabel errorMessage = new JLabel("");
	JLabel tempoBebendoLabel = new JLabel("Informe o tempo no bar!");	
	JLabel tempoDormindoLabel = new JLabel("Informe o tempo de soneca!");
	static JTextField tempoBebendo = new JTextField("", 5);
	static JTextField tempoDormindo= new JTextField("", 5);
	JButton startButton = new JButton("Iniciar");
	JButton beboButton = new JButton("Listar Papudim");
	
	
	

	//keyListener
	
	//dimensoes
	private int h;
	private int w;
	
	public Janela(Bar bar, Semaphore mutex, Semaphore esperaAmigos, Semaphore cadSemaphore, int altura, int largura) 
	{
		this.h = altura;
		this.w = largura;
		this.bar = bar;
		this.mutex = mutex;
		this.esperaAmigos = esperaAmigos;
		this.cadSemaphore = cadSemaphore;
	}
	
	public void run() {
	}
	
	public void create() 
	{
		janela = new JFrame("Papudim simulator");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(w,h);
		janela.setResizable(false);
		
		
		/*
		 * Adicionar todo IO aqui
		 * */
		
		startButton.addActionListener(this);
		beboButton.addActionListener(this);

		inputUser.add(startButton);
		inputUser.add(tempoBebendoLabel);
		inputUser.add(tempoBebendo);
		inputUser.add(tempoDormindoLabel);
		inputUser.add(tempoDormindo);
		inputUser.add(beboButton);

		
		/* TODO: tratar o tamanho do canvas e da criacao 
		*dos personagens de acordo com o tamanho da janela
		*/
		jogo = new Canvas(w, h, this, Bebos);
		janela.add(jogo);
		jogo.add(inputUser);
		janela.setVisible(true);
	}


	
	public void start() 
	{
		jogo.setJogando(true);
        for (int i=0;i<bebosInseridos;i++)
        {
        	String ID=("Thread "+Integer.toString(i+1));
			Bebos [i] = new Bebo(bar, esperaAmigos, mutex, cadSemaphore, Integer.parseInt(threadInfo_dormindo.get(i)), Integer.parseInt(threadInfo_bebendo.get(i)), ID);
        }
        for (int i=0;i<bebosInseridos;i++) 
        {
        	Bebos[i].start();
        	addPersonagem();
        }
	}
	
	public void restart() {
		//start();
	}
	
	public void addPersonagem() 
	{
		int alturaDoPersonagem = 100;
		int larguraDoPersonagem = 50;
		
		int posX = new Random().nextInt(500) + 10;  // [10...501]
		int posY = new Random().nextInt(500) + 10;  // [10...501]
		int orientacao = new Random().nextInt(7);  // [0...6]
			
		Ator novoAtor = new Ator( posX, posY, alturaDoPersonagem, larguraDoPersonagem, 2, w, h);
		//novoAtor.setOrientacao(orientacao);
		novoAtor.setAcao(orientacao);
		//System.out.println(orientacao);
		jogo.addAtor(novoAtor);
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		String action = ae.getActionCommand();
		if (action.equals("Iniciar")) 
		{
			start();
			startButton.setVisible(false);
			beboButton.setText("Adiconar Papudim");
		}
		else if (action.equals("Listar Papudim")) 
		{
			if(bebosInseridos <= 19)
			{
				System.out.println("listou");

				threadInfo_bebendo.add(tempoBebendo.getText());
				threadInfo_dormindo.add(tempoDormindo.getText());
				bebosInseridos++;

				if(/*to do*/false){
					System.out.printf("Campo vazio!\n");
				}else {
					System.out.println(bebosInseridos);
					System.out.println(threadInfo_bebendo);
					System.out.println(threadInfo_dormindo);
				}
			}
			else{
				System.out.println("qnt max de atores excedida");
			}
		}

		else if(action.equals("Adiconar Papudim")) 
		{
			if(bebosInseridos <= 19) 
			{
				System.out.println("Inseriu");
				threadInfo_bebendo.add(tempoBebendo.getText());
				threadInfo_dormindo.add(tempoDormindo.getText());
				bebosInseridos++;
				String ID=("Thread "+Integer.toString(bebosInseridos));
				Bebos[bebosInseridos-1] = new Bebo(bar, esperaAmigos, mutex, cadSemaphore, Integer.parseInt(threadInfo_dormindo.get(bebosInseridos-1)), Integer.parseInt(threadInfo_bebendo.get(bebosInseridos-1)), ID);
				Bebos[bebosInseridos-1].start();
				addPersonagem();
					
				System.out.println(bebosInseridos);
				System.out.println(threadInfo_bebendo);
				System.out.println(threadInfo_dormindo);
	
			}
			else {
				System.out.println("qnt max de atores excedida");
			}
		}
	}
	  
	public int getbebosInseridos() {
		return bebosInseridos;
	}
}