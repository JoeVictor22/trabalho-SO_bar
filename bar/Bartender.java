package bar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class Bartender extends Personagem{

	private BufferedImage[] andandoEsquerda;
	private BufferedImage[] andandoDireita;
	private BufferedImage[] andandoCima;
	private BufferedImage[] andandoBaixo;
	private BufferedImage[] parado;

	private int imagemAtual;	
	private int timer;
	
	private int velocidadeDasAnimacoesBebendo;
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	
	private int tempoParado;
	private long timerParado;


	public Bartender(int h, int w) {
		super(0, 0, 60, 30, 2);
		//variaveis que necessitam inicializacao	
		
		timer = 0;
		imagemAtual = 0;
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;
		velocidadeDasAnimacoesBebendo = velocidadeDasAnimacoes + 15;
		posX = 324;
		posY = 482;
		this.setGotoX(324);
		this.setGotoY(482);

		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
		timerParado = System.currentTimeMillis();
	}
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		parado= carregarImagens("Data/Sprites/gus/gus-copo", 4, "png");
		andandoEsquerda = carregarImagens("Data/Sprites/gus/gus-e", 4, "png");
		andandoDireita = carregarImagens("Data/Sprites/gus/gus-d", 4, "png");
		andandoCima = carregarImagens("Data/Sprites/gus/gus-t", 4, "png");
		andandoBaixo = carregarImagens("Data/Sprites/gus/gus-f", 4, "png");
	}
	
	public void atualizar() 
	{
		if(System.currentTimeMillis() - timerParado > tempoParado) 
		{
			timerParado += tempoParado;
		
			if (this.getGotoX() == 650) {
				this.setGotoX(324);
			}else {
				this.setGotoX(650);
			}
			tempoParado = (int) (Math.random() * ( 45000 - 22000 ));
		}
		
		anda();
		atualizarContadorDeImagem();	
	}

	public void pintarAtor(Graphics2D g) 
	{		
		int acao = getAcao();
	
		switch(acao) 
		{
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
				break;
		}
	}
	


	public void atualizarContadorDeImagem() {

		int velocidade = velocidadeDasAnimacoes;
		
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
