package bar;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Ator extends Personagem {
	
	/*
	 * Animacoes utilizadas pelo jogador
	 */
	private BufferedImage[] andandoEsquerda;
	private BufferedImage[] andandoDireita;
	private BufferedImage[] andandoCima;
	private BufferedImage[] andandoBaixo;
	private BufferedImage[] parado;
	private BufferedImage[] bebendo;
	private BufferedImage[] dormindo;
	
	private int imagemAtual;
	
	private int timer;
	
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
		

	public Ator(int posX, int posY, int altura, int largura, 
			int velocidade,int h, int w) {
		super(posX, posY, altura, largura, velocidade, h, w);
		//variaveis que necessitam inicializacao	
			
		timer = 0;
		imagemAtual = 0;
	
		
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;
		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
	}
	
	public void criarAnimacoes() {
		
		andandoEsquerda = carregarImagens("Data/Sprites/Inimigo/Run/goblin-run-0", 4, "png");
		andandoDireita = carregarImagens("Data/Sprites/Inimigo/Run/goblin-run-0", 4, "png");
		andandoCima = carregarImagens("Data/Sprites/Inimigo/Run/goblin-run-0", 4, "png");
		andandoBaixo = carregarImagens("Data/Sprites/Inimigo/Run/goblin-run-0", 4, "png");
		
	}
	
	
	public void atualizar() {
		anda();
	}
		
	public void pintarInimigo(Graphics2D g) {
		
		int direcao = getDirecao();
		
		switch(direcao) {
			case 0:
				pintar(g, parado, imagemAtual);
				break;
			case 1:
				pintar(g, andandoEsquerda, imagemAtual);
				break;
			case 2:
				pintar(g, andandoDireita, imagemAtual);
				break;
			case 3:
				pintar(g, andandoCima, imagemAtual);
				break;
			case 4:
				pintar(g, andandoBaixo, imagemAtual);
				break;
			default:
				pintar(g, parado, imagemAtual);
				break;
		}
	}
	
		
	

	public void atualizarContadorDeImagem() {
		if(timer >= velocidadeDasAnimacoes){
			imagemAtual++;
			if(imagemAtual == quantidadeDeFrames){
				imagemAtual = 0;
			}
			timer = 0;
		}
		timer++;
	}

	
	

	
	
	
	
	/*
	 * setters e getters
	 */


	public BufferedImage[] getParado() {
		return parado;
	}

	public void setParado(BufferedImage[] parado) {
		this.parado = parado;
	}


	public int getImagemAtual() {
		return imagemAtual;
	}

	public void setImagemAtual(int imagemAtual) {
		this.imagemAtual = imagemAtual;
	}


	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}


	public int getVelocidadeDasAnimacoes() {
		return velocidadeDasAnimacoes;
	}

	public void setVelocidadeDasAnimacoes(int velocidadeDasAnimacoes) {
		this.velocidadeDasAnimacoes = velocidadeDasAnimacoes;
	}

	public int getQuantidadeDeFrames() {
		return quantidadeDeFrames;
	}

	public void setQuantidadeDeFrames(int quantidadeDeFrames) {
		this.quantidadeDeFrames = quantidadeDeFrames;
	}


	
}
