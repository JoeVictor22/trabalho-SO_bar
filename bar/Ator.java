package bar;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Ator extends Personagem 
{
	private BufferedImage[] andandoEsquerda;
	private BufferedImage[] andandoDireita;
	private BufferedImage[] andandoCima;
	private BufferedImage[] andandoBaixo;
	private BufferedImage[] parado;
	private BufferedImage[] bebendo;
	private BufferedImage[] dormindo;
	
	private int imagemAtual;	
	private int timer;
	
	private int velocidadeDasAnimacoesBebendo;
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	
	private Casa casa;
	private Balcao balcao;
	private Cadeira cadeira;

	public Ator(int h, int w) {
		super(0, 0, 60, 30, 2, h, w);
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
		andandoEsquerda = carregarImagens("Data/Sprites/shane/walk/esquerda/tile00", 4, "png");
		andandoDireita = carregarImagens("Data/Sprites/shane/walk/direita/tile00", 4, "png");
		andandoCima = carregarImagens("Data/Sprites/shane/walk/cima/tile00", 4, "png");
		andandoBaixo = carregarImagens("Data/Sprites/shane/walk/baixo/tile00", 4, "png");
		parado= carregarImagens("Data/Sprites/shane/idle/normal/tile00", 4, "png");
		bebendo= carregarImagens("Data/Sprites/shane/drinking/cima/tile00", 4, "png");
		dormindo= carregarImagens("Data/Sprites/shane/sleep/tile00", 4, "png");
	}
	
	
	public void atualizar() 
	{
		if(posX >= h ) {
			andar(1);
		}else if(posX <= 0) {
			andar(2);
		}else if(posY >= w) {
			andar(3);
		}else if(posY <= 0) {
			andar(4);
		}
		
		acao();
		anda();
		atualizarContadorDeImagem();	
	}
		
	public void acao() 
	{
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
		 * 2 = direita
		 * 3 = cima
		 * 4 = baixo
		 * 5 = beber
		 * 6 = dormir
		 *  */
		acao = getAcao();
		
		switch(acao) 
		{
			case 5:
				break;
			case 6:
				break;
			default:
				break;
		}
		
		
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
			case 5:
				pintar(g, bebendo, imagemAtual);
				break;
			case 6:
				pintar(g, dormindo, imagemAtual);
				break;
			default:
				break;
		}
	}
	
	public void irParaCasa() {
		this.acao = 6;
		this.posX = casa.getPosX();
		this.posY = casa.getPosY();
		this.casa.setAcao(1);
	}
	
	public void irParaBalcao() {
		this.acao = 0;
		this.posX = balcao.getPosX();
		this.posY = balcao.getPosY();
		this.casa.setAcao(0);
	}
	public void irParaCadeira() {
		this.acao = 5;
		this.posX = cadeira.getPosX();
		this.posY = cadeira.getPosY();
		this.casa.setAcao(0);
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
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	public void setCadeira(Cadeira cadeira) {
		this.cadeira = cadeira;
	}
	public void setBalcao(Balcao balcao) {
		this.balcao = balcao;
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
	
}