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
	
	private int imagemAtual;	
	private int timer;
	private int velocidadeDasAnimacoesBebendo;
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	
	public Ator(int h, int w) {
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
		andandoEsquerda = carregarImagens("Data/Sprites/shane/walk/esquerda/tile00", 4, "png");
		andandoDireita = carregarImagens("Data/Sprites/shane/walk/direita/tile00", 4, "png");
		andandoCima = carregarImagens("Data/Sprites/shane/walk/cima/tile00", 4, "png");
		andandoBaixo = carregarImagens("Data/Sprites/shane/walk/baixo/tile00", 4, "png");
		parado= carregarImagens("Data/Sprites/shane/idle/normal/tile00", 4, "png");
		bebendo= carregarImagens("Data/Sprites/shane/drinking/cima/tile00", 4, "png");
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
