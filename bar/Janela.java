package bar;

import java.util.Random;

import javax.swing.JFrame;

import bar.Canvas;

public class Janela {

	// jframe e jpanel do jogo
	private JFrame janela;
	private Canvas jogo;
	
	//keyListener
	
	//dimensoes
	private int h;
	private int w;
	
	public Janela(int altura, int largura) {
		this.h = altura;
		this.w = largura;
	}
	
	
	public void create() {
		janela = new JFrame("Papudim simulator");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(w,h);
		janela.setResizable(false);
		
		jogo = new Canvas(w, h, this);
		janela.add(jogo);
		janela.setVisible(true);
		start();
		
	}


	
	public void start() {
		/* Rodar funcoes para dar inicio a animacao dos elementos*/
		jogo.setJogando(true);	
		for(int i = 0; i < 4; i++) {
			addPersonagem();
		}
	}
	
	public void restart() {
		/* Reiniciar animacao*/
		jogo.setJogando(false);
	}
	
	public void addPersonagem() {
		int alturaDoPersonagem = 128;
		int larguraDoPersonagem = 64;

		
		int posX = new Random().nextInt(500) + 10;  // [10...501]
		int posY = new Random().nextInt(500) + 10;  // [10...501]
		int orientacao = new Random().nextInt(5);  // [0...4]
			
		Ator novoAtor = new Ator( posX, posY, alturaDoPersonagem, larguraDoPersonagem, 6, w, h);
		novoAtor.setOrientacao(orientacao);
		System.out.println(orientacao);
		jogo.addAtor(novoAtor);
	}
}
