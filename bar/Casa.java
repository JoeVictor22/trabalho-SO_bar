package bar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Casa extends Personagem{
	private BufferedImage[] cabana;

	public Casa(int posX, int posY) {
		super(posX, posY, 73, 60, 2);
		this.acao = 0;
	}
	
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		cabana = carregarImagens("Data/Sprites/casa/cabana", 2, "png");
	
	}
	
	public void anda() {
	}
	public void atualizar() 
	{
	}
		
	public void pintarCasa(Graphics2D g) 
	{			
		
		int acao = getAcao();
	
		/* ACOES
		 * 0 = parado
		 * 1 = esquerda
	
		 *  */
		switch(acao) 
		{
			case 0:
				pintar(g, cabana, 0);
				break;
			case 1:
				pintar(g, cabana, 1);
				break;
			default:
				break;
		}


	}
	public static BufferedImage resize(BufferedImage img, int W, int H) 
	{ 
		
	    Image temp = img.getScaledInstance(W, H, Image.SCALE_SMOOTH);
	    BufferedImage novaImagem = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = novaImagem.createGraphics();
	    g2d.drawImage(temp, 0, 0, null);
	    g2d.dispose();

	    return novaImagem;
	}  
}
