package bar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Carro  extends Personagem{

	private BufferedImage[] parado;

	private int imagemAtual;	
	private int timer;
	
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	
	private int tempoParado;
	private long timerParado;
	
	private int resetY1 =-200;
	private int resetY2 = 800;

	private int carro = 0;
	public Carro(int h, int w, int carro, int posX) {
		super(0, 0, 120, 70, 2);
		//variaveis que necessitam inicializacao	
		
		timer = 0;
		imagemAtual = 0;
		this.carro = carro;
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;
		this.posX = posX;
		posY = resetY1;
		this.setGotoX(posX);
		this.setGotoY(resetY2);

		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 4;
		timerParado = System.currentTimeMillis();
	}
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		parado= carregarImagens("Data/Sprites/outros/carro", 2, "png");
	
	}
	
	public void atualizar() 
	{
		if (this.getPosY() >= resetY2) {
			if(System.currentTimeMillis() - timerParado > tempoParado) 
			{
				timerParado += tempoParado;
				this.setPosY(resetY1);

				this.tempoParado = (int) (Math.random() * ( 60000 - 22000 ));
				this.carro = (int) (Math.random() * (2 - 0));
			}
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
				pintar(g, parado, carro);
				break;
			default:
				pintar(g, parado, carro);

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
