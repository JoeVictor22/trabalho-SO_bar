package bar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Classe abstrata para servir de base na criacao dos personagens
 */

abstract public class Personagem 
{
	
	/*
	 * Atributos fundamentais que cada personagem devera ter
	 * Adcionar demais atributos na classe herdeira
	 */
	
	// Atributos relativos a localizacao
	protected int posX;
	protected int posY;
	
	private int gotoX;
	private int gotoY;
	
	// Atributos relativos as dimensoes
	private int largura;
	private int altura;
	
	// Atributos para controle de movimento
	protected int acao;
	protected int orientacao;
	private int velocidade;
	protected int h;
	protected int w;
	
	public Personagem(int posX, int posY, int altura, int largura, 
					  int velocidade) {
		this.posX = posX;
		this.posY = posY;
		this.gotoX = posX;
		this.gotoY = posY;
		this.altura = altura;
		this.largura = largura;
		this.velocidade = velocidade;
		
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
		 * 2 = direita
		 * 3 = cima
		 * 4 = baixo
		 * 5 = beber
		 * 6 = dormir
		 *  */
		this.acao = 0;
		this.orientacao = 1;
	
		/*
		 * Instanciacao das animacoes
		 */
		criarAnimacoes();	
	}
	
	//metodos abstratos	
	// Atualizar movimentos e variaveis de controle
	abstract public void atualizar();
	
	/*
	 * Utilizar esse metodo para fazer o carregamento de todas as animacoes necessarias no personagem
	 * EX :
	 * 		correndo = carregarImagens("enderecoBonitinho", 10);
	 *		parado = carregarImagens("enderecoBonitinho", 10);
	 *		pulando = carregarImagens("enderecoBonitinho", 10);
	 */
	abstract public void criarAnimacoes();
		
	/* metodo para pintar animacao
	 * enviar component de pintura vindo do canvas, e a animacao a ser utilizada
	 * o indice da animacao nesse metodo pode variar de acordo com a classe filha
	 */
	public void pintar(Graphics2D g, BufferedImage[] animacao, int imagemAtual) {
	    g.drawImage(
             animacao[imagemAtual],
             posX,posY,
             posX + largura, posY + altura,
             0, 0,
             animacao[imagemAtual].getWidth(), animacao[imagemAtual].getHeight(),
             null);
    
	}	
	
	/*
	 *  metodo para carregar imagens em um vetor
	 * enviar endereco, e quantidade de imagens e o tipo de extensao sem o ponto (.)
	 * o endereco das imagens devem ser iguais e enumerados no final de 1 ao size
	 * 
	 */
	public BufferedImage[] carregarImagens(String endereco, int size,  String extensao) {
		BufferedImage[] imagem = new BufferedImage[size];
		for(int i = 0; i < size; i++) {	
			try {
				imagem[i] = ImageIO.read(new File(endereco + (i+1) + "." + extensao));
			}
			catch(IOException e) {
				System.out.println("Metodo carregarImagens : nao carregou imagens "+ endereco+(i+1) +"." + extensao);
			}
			
		}
		return imagem;		
	}

	/*
	 * atualizacao da movimentacao 
	 */
	public void anda() {
		/* DIRECAO
		 * 0 = parado
		 * 1 = esquerda
		 * 2 = direita
		 * 3 = cima
		 * 4 = baixo
		 *  */
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
		 * 2 = direita
		 * 3 = cima
		 * 4 = baixo
		 * 5 = beber
		 * 6 = dormir
		 *  */
		if(gotoX > posX) {
			setAcao(2);
			posX += velocidade;
			if(gotoX <= posX) {
				setAcao(0);
				gotoX = posX;
			}
		}
		if(gotoX < posX) {
			setAcao(1);
			posX -= velocidade;
			if(gotoX >= posX) {
				setAcao(0);
				gotoX = posX;
			}
		}
		if(gotoY > posY) {
			setAcao(4);
			posY += velocidade;
			if(gotoY <= posY) {
				setAcao(0);
				gotoY = posY;
			}
		}
		if(gotoY < posY) {
			setAcao(3);
			posY -= velocidade;
			if(gotoY >= posY) {
				setAcao(0);
				gotoY = posY;
			}
		}			
	}
	
	/*
	 * define a direcao de observacao
	 */
	public void andar(int acao) {
		if(acao != 0) {
			this.acao = acao;
			this.orientacao = acao;
		}
		else {
			this.acao = acao;
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
	
	/*
	 * metodos para alterar posicao caso necessario
	 */
	public void somarPosY(int soma) {
		this.posY += soma;		
	}
	
	public void somarPosX(int soma) {
		this.posX += soma;		
	}	
	
	/*
	 * Metodos de acesso
	 */
	public int getOrientacao() {
		return orientacao;
	}

	/* a orientacao sempre deve ser um de 1 a 4*/
	public void setOrientacao(int orientacao) {
		if(orientacao > 0 && orientacao < 5) {
			this.orientacao = orientacao;
		}
		else {
			this.orientacao = 2;
		}
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAcao() {
		return acao;
	}

	public void setAcao(int acao) {
		this.acao = acao;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	public int getGotoX() {
		return gotoX;
	}

	public void setGotoX(int gotoX) {
		this.gotoX = gotoX;
	}

	public int getGotoY() {
		return gotoY;
	}

	public void setGotoY(int gotoY) {
		this.gotoY = gotoY;
	}
}