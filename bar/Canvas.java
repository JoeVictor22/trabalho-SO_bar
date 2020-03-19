package bar;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.Dimension;
import java.awt.BorderLayout;

public class Canvas extends JPanel implements Runnable{


	private static final long serialVersionUID = 6244965887359695579L;
		
	private BufferedImage cenario;
		

	//constructor
	public Canvas(int h, int w) {
		
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(h,w));

        revalidate();
        repaint();
        this.setVisible(true);

		//carrega cenario
		try {
			cenario = ImageIO.read(new File("Data/Scenes/cenario.png"));
			cenario = resize(cenario,h, w);
		}catch(IOException e) {
			System.out.println("nao carregou background");
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
			
		}
		
	}
	
	//instrucoes
	public void run() {
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(true) {
			
	
			repaint();
			dorme();	
	
	
			
			/*
			 * contador de frames
			 */
			frames++;			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println("frames = " + frames);
				frames = 0;
			}
		}	
	}
	

	// sleep para canvas rodar a 60fps
	public void dorme() {
		try {
			Thread.sleep(16);
		} catch(InterruptedException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		//pinta as imagens de background
		g2d.drawImage(cenario, null, 0,0 );

	
	}
	
	
	
	/*  UTILS */
	// funcao para dar resize em uma imagem
	public static BufferedImage resize(BufferedImage img, int W, int H) { 
		
	    Image temp = img.getScaledInstance(W, H, Image.SCALE_SMOOTH);
	    BufferedImage novaImagem = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = novaImagem.createGraphics();
	    g2d.drawImage(temp, 0, 0, null);
	    g2d.dispose();

	    return novaImagem;
	}  
	
}