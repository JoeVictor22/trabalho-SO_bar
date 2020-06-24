package bar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Casa extends Personagem{
	private BufferedImage[] desligado;
	private BufferedImage[] ligado;

	public Casa(int posX, int posY) {
		super(posX, posY, 60, 30, 2, posX, posY);
		this.acao = 0;
	}
	
	
	public void criarAnimacoes() 
	{
		// Data\Sprites\shane
		desligado = carregarImagens("Data/Sprites/shane/walk/esquerda/tile00", 4, "png");
		ligado= carregarImagens("Data/Sprites/shane/walk/direita/tile00", 4, "png");

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
				pintar(g, desligado, 0);
				break;
			case 1:
				pintar(g, ligado, 1);
				break;
			default:
				break;
		}


	}
}
