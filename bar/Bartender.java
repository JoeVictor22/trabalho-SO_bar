package bar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bartender extends Personagem{

	private BufferedImage[] parado;
	
	private int imagemAtual;	
	private int timer;
	
	private int velocidadeDasAnimacoesBebendo;
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	


	public Bartender(int h, int w) {
		super(0, 0, 60, 30, 2);
		//variaveis que necessitam inicializacao	
		
		timer = 0;
		imagemAtual = 0;
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;
		velocidadeDasAnimacoesBebendo = velocidadeDasAnimacoes + 15;

		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
	}
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		parado= carregarImagens("Data/Sprites/shane/idle/normal/tile00", 4, "png");
	}
	
	
	public void atualizar() 
	{
		anda();
		atualizarContadorDeImagem();	
	}

	public void pintarAtor(Graphics2D g) 
	{		
		int acao = getAcao();
		int orientacao = getOrientacao();
	
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
		 * 2 = direita
		 * 3 = cima
		 * 4 = baixo
		 * 5 = beber
		 * 6 = dormir
		 *  */
		switch(acao) 
		{
			case 0:
				pintar(g, parado, orientacao-1);
				break;
			default:
				break;
		}
	}
	


	public void atualizarContadorDeImagem() {

		int velocidade = 0;
		velocidade = (acao == 5 ? velocidadeDasAnimacoesBebendo: velocidadeDasAnimacoes );

		if(timer >= velocidade)
		{
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
