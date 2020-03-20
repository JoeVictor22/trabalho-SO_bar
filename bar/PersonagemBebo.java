package bar;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class PersonagemBebo extends Personagem {
	
	/*
	 * Animacoes utilizadas pelo jogador
	 */
	private BufferedImage[] andandoLado;
	private BufferedImage[] andandoCima;
	private BufferedImage[] andandoBaixo;
	
	private BufferedImage[] parado;
	
	private int imagemAtual;

	
	private int timer;

	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
		
	private int tempoPausa;

	
	private int pausa;
	
	public PersonagemBebo(int posX, int posY, int altura, int largura, int velocidade, int fimDaTelaEsquerda, int fimDaTelaDireita, int fimDaTelaCima, int fimDaTelaBaixo, int tempoPausa) {
		super(posX, posY, altura, largura, velocidade, fimDaTelaEsquerda, fimDaTelaDireita, fimDaTelaCima, fimDaTelaBaixo);
		//variaveis que necessitam inicializacao
		
		timer = 0;
		imagemAtual = 0;
		
		pausa = 0;
		this.tempoPausa = tempoPausa;
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;
		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
	}
	
	public void criarAnimacoes() {
		
		andandoLado = carregarImagens("Data/Sprites/shane/walk/lado/tile00", 4, "png");
		andandoBaixo= carregarImagens("Data/Sprites/shane/walk/baixo/tile00", 4, "png");
		andandoCima = carregarImagens("Data/Sprites/shane/walk/cima/tile00", 4, "png");

	}
	
	
	public void atualizar() {

	}
		
	public void pintarBebo(Graphics2D g) {
		
		/*
		  1 = andando direita
		  2 = andando esquerda
		  3 = andando cima
		  4 = andando baixo 
		 */
		
		// organizar direcao pra printar os sprites
		if(getDirecao() == 1) {
			pintar(g, andandoLado, imagemAtual, false);
		}else if(getDirecao() == 2) {
			pintar(g, andandoLado, imagemAtual, true);
		}else if(getDirecao() == 3){
			pintar(g, andandoCima, imagemAtual, false);
		}else if(getDirecao() == 4) {
			pintar(g, andandoBaixo, imagemAtual, false);
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
	public int getPausa() {
		return pausa;
	}

	public void setPausa(int pausa) {
		this.pausa = pausa;
	}

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

	public int getTempoPausa() {
		return tempoPausa;
	}

	public void setTempoPausa(int tempoPausa) {
		this.tempoPausa = tempoPausa;
	}


	
	
}