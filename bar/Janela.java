package bar;

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
		
	}


	
	public void start() {
		/* Rodar funcoes para dar inicio a animacao dos elementos*/
		jogo.setJogando(true);		
	}
	
	public void restart() {
		/* Reiniciar animacao*/
		jogo.setJogando(false);
	}
	
	public void addPersonagem() {
		int alturaDoPersonagem = 100;
		int larguraDoPersonagem = 150;

		//Ator novoAtor = new Ator( 580, 480, alturaDoPersonagem, larguraDoPersonagem, 6,vidaDoJogador,danoDoJogador, 0-(larguraDoPersonagem/2)+36, w-(larguraDoPersonagem/2)-54);
		//jogo.addAtor(novoAtor);
	}
}
