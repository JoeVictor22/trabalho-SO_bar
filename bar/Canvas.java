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
import javax.swing.JPanel;


public class Canvas extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private int h;
	private int w;
	
	
	private int quantidadeDeAtores = 0;
	private BufferedImage cenario;
	private Ator[] atores = new Ator[20];

	private int alturaDoAtor;
	private int larguraDoAtor;
	
	private boolean jogando;
	private boolean pausado;
	
	private Janela janela;
	
	private Thread gameloop = new Thread(this);
	
	private String scenePath = "Data/Scenes/cenario.png";
	
	public Canvas(int h, int w, Janela janela) {
		alturaDoAtor = 150;
		larguraDoAtor= 100;
		pausado = false;
		jogando = false;
		
		this.janela = janela;
		
		this.h = h;
		this.w = w;
		
		
		//Load background
		try {
			cenario = ImageIO.read(new File(scenePath));
			//cenario = resize(cenario, h, w);
		}catch(IOException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
		gameloop.start();
	}
	
	// para manter o refreshRate a 60fps => 1000ms/16 = 62
	public void sleep() {
		try {
			Thread.sleep(16);
		} catch(InterruptedException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	// ciclo de atualizacoes
	public void run() {
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(true) {
			if(jogando) {
				atualiza();	
			}
			
			repaint();
			
			// mantem o refresh rate a 60 e conta os frames
			sleep();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println("frames = " + frames);
				frames = 0;
			}
			
		}
	}
	
	
	public void atualiza() {
			
		for(int i = 0; i < quantidadeDeAtores; i++) {
			if(atores[i] != null) {
				atores[i].atualizar();
			}
		}
		
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		// paint background
		g2d.drawImage(cenario,  null,  0,  0);
		
		if(jogando) {
			for(int i = 0; i < quantidadeDeAtores; i++) {
				if(atores[i] != null) {
					atores[i].pintarAtor(g2d);
				}
			}
		}
	
	}

	
	public void addAtor(Ator ator) {
		atores[quantidadeDeAtores] = ator;
		quantidadeDeAtores+=1;
	}
	
	/* UTILS */
	public static BufferedImage resize(BufferedImage img, int W, int H) { 
		
	    Image temp = img.getScaledInstance(W, H, Image.SCALE_SMOOTH);
	    BufferedImage novaImagem = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = novaImagem.createGraphics();
	    g2d.drawImage(temp, 0, 0, null);
	    g2d.dispose();

	    return novaImagem;
	}  
	
	
	/* SETTERS E GETTERS */
	public void setJogando(boolean jogando) {
		this.jogando = jogando;
	}
	public boolean isJogando() {
		return this.jogando;
	}
	public int getQuantidadeDeAtores() {
		return this.quantidadeDeAtores;
	}
	


}