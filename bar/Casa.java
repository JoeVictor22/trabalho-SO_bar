package bar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class Casa extends Personagem {
	private BufferedImage[] cabana;
	private BufferedImage[] cabanaDormindo;

	private int imagemAtual;	
	private int timer;
	private int velocidadeDasAnimacoes;
	private int quantidadeDeFrames;
	
	public Casa(int posX, int posY) {
		super(posX, posY, 350, 280, 2);
		this.acao = 0;
		timer = 0;
		imagemAtual = 0;
		
		//velocidadeDasAnimacoes; quanto menor mais rapido
		velocidadeDasAnimacoes= 12;

		//quantidadeDeFrames deve ser igual ao tamanho das animacoes usado no criar imagens - 1
		quantidadeDeFrames = 5;
	}
	
	public void criarAnimacoes() {
		cabana = carregarImagens("Data/Sprites/casa/1Elliott_s_Cabin", 1, "png");
		cabanaDormindo = carregarImagens("Data/Sprites/casa/Elliott_s_Cabin", 5, "png");
	}
	
	public void anda() {
	}
	
	public void atualizar() 
	{
		atualizarContadorDeImagem();	
	}

	public void atualizarContadorDeImagem() {
		int velocidade = velocidadeDasAnimacoes;
		
		if(timer >= velocidade) {
			imagemAtual++;
			if(imagemAtual == quantidadeDeFrames){
				imagemAtual = 0;
			}
			timer = 0;
		}
		timer++;
	}
	
	public void pintarCasa(Graphics2D g) {			
		int acao = getAcao();
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
	
		 *  */
		switch(acao) {
			case 0:
				pintar(g, cabana, 0);
				break;
			case 1:
				pintar(g, cabanaDormindo, imagemAtual);
				break;
			default:
				break;
		}
	}
	
	public static BufferedImage resize(BufferedImage img, int W, int H) { 
	    Image temp = img.getScaledInstance(W, H, Image.SCALE_SMOOTH);
	    BufferedImage novaImagem = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = novaImagem.createGraphics();
	    g2d.drawImage(temp, 0, 0, null);
	    g2d.dispose();
	    return novaImagem;
	}  
}
