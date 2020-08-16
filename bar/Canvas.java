package bar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable
{
	
	private static final long serialVersionUID = 1L;
	
	private int h;
	private int w;
		
	private int quantidadeDeAtores = 0;
	private int quantidadeDeCadeiras = 0;

	private BufferedImage cenario;
	private Ator[] atores = new Ator[20];
	private Bartender bartender;
	private ControleAtor[] controladores = new ControleAtor[20];
	private int espacamentoEntreAtor = 30;
	private Fila fila = new Fila(190,300,espacamentoEntreAtor, controladores);
	private Balcao balcao = new Balcao(750,550,espacamentoEntreAtor, controladores);
	
	
	
	private Casa[] casas = new Casa[20];
	
	private Cadeira[] cadeiras = new Cadeira[21];
	
	private Bebo Bebos[] = new Bebo[20];
	

	private boolean jogando;
	private boolean pausado;
	
	private Janela janela;
	
	private Thread gameloop = new Thread(this);
	
	private String scenePath = "Data/Scenes/background.png";
	
	public Canvas(int h, int w, Janela janela,Bebo Bebos[], int quantidadeDeCadeiras) 
	{
		pausado = false;
		jogando = false;
		
		
		this.janela = janela;
		this.Bebos = Bebos;
		this.h = h;
		this.w = w;
		
		this.quantidadeDeCadeiras = quantidadeDeCadeiras + 1;
		this.bartender = new Bartender(h,w);
	
		for(int i = 0; i < quantidadeDeCadeiras+1; i++) {
			cadeiras[i] = new Cadeira(h,w);
			cadeiras[i].setGotoX(balcao.getX() - (i * espacamentoEntreAtor));
			cadeiras[i].setGotoY(balcao.getY() + 40);
			
		}
		
		//Load background
		try {
			cenario = ImageIO.read(new File(scenePath));
			cenario = resize(cenario, h, w);
		}
		catch(IOException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
		gameloop.start();
	}
	
	// para manter o refreshRate a 60fps => 1000ms/16 = 62
	public void sleep() 
	{
		try {
			Thread.sleep(16);
		} 
		catch(InterruptedException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	// ciclo de atualizacoes
	public void run() 
	{
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(true) 
		{
			
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			//System.out.println("x:" + mouse.x + ", y: " + mouse.y);
			atualiza();	
			
			repaint();
			// mantem o refresh rate a 60 e conta os frames
			sleep();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) 
			{
				atualizaConsoles();

				timer+= 1000;
				//System.out.println("frames = " + frames);
				frames = 0;
			}			
		}
	}
	
	
	public void atualizaConsoles() {
		String saida1 = "";
		for(int i = 0; i < this.quantidadeDeAtores; i++) {
			saida1 += Bebos[i].status() + "\n";
		}
		this.janela.setOutpuStatus(saida1);
		
	}
	
	// atualiza o estado logico dos componentes
	public void atualiza() 
	{	
		fila.atualizar();
		balcao.atualizar();
		bartender.atualizar();
		
		for(int i = 0; i < quantidadeDeCadeiras; i++) 
		{
			if(cadeiras[i] != null) {
				cadeiras[i].atualizar();
			}
		}
		
		for(int i = 0; i < quantidadeDeAtores; i++) 
		{
			if(controladores[i] != null) {
				controladores[i].atualizar();
			}
		}		
	}

	// desenha os componentes na tela
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		// paint background
		g2d.drawImage(cenario,  null,  0,  0);
		
		// pinta os atores
		if(jogando) 
		{
			bartender.pintarAtor(g2d);
			for(int i = 0; i < quantidadeDeCadeiras; i++) 
			{
				if(cadeiras[i] != null) {
					cadeiras[i].pintarAtor(g2d);
				}
			}
			
			for(int i = 0; i < quantidadeDeAtores; i++) 
			{
				if(atores[i] != null) {
					atores[i].pintarAtor(g2d);
				}
		
				if(casas[i] != null) {
					casas[i].pintarCasa(g2d);
				}
			}

		}	
	}
	// adiciona um ator ao canvas
	public void addAtor(Ator ator) {
		int casaY = 140;
		int reset = 0;
		if(this.quantidadeDeAtores > 9) {
			casaY -= 100;
			reset = 10;
		}
		
		casas[quantidadeDeAtores] = new Casa(20 + ((quantidadeDeAtores - reset) * 60), casaY);		
		
		controladores[quantidadeDeAtores] = new ControleAtor(ator, Bebos[quantidadeDeAtores], this.fila, casas[quantidadeDeAtores], this.balcao);

		atores[quantidadeDeAtores] = ator;
		quantidadeDeAtores+=1;
	}
	
	/* UTILS */
	// redimensiona imagem
	public static BufferedImage resize(BufferedImage img, int W, int H) 
	{ 
		
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