package bar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Cadeira extends Personagem{


	private BufferedImage[] parado;

	private int imagemAtual;	
	private int timer;
	
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	



	public Cadeira(int h, int w) {
		super(0, 0, 20, 24, 2);
		//variaveis que necessitam inicializacao	
		
		timer = 0;
		imagemAtual = 0;
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;

		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
	}
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		parado= carregarImagens("Data/Sprites/outros/banco", 1, "png");

	}
	
	public void atualizar() 
	{
	}

	public void pintarAtor(Graphics2D g) 
	{		
		int acao = getAcao();
	
		switch(acao) 
		{
			case 0:
				pintar(g, parado, 0);
				break;
			default:
				pintar(g, parado, 0);
				break;
		}
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
