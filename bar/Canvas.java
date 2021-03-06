package bar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private int quantidadeDeAtores = 0;
	private int quantidadeDeCadeiras = 0;

	private BufferedImage cenario;
	private Ator[] atores = new Ator[20];
	private Bartender bartender;
	private Carro carro;
	private Carro caminhao;

	private ControleAtor[] controladores = new ControleAtor[20];
	private int espacamentoEntreAtor = 30;
	private Fila fila = new Fila(190,300,espacamentoEntreAtor, controladores);
	private Balcao balcao = new Balcao(294,550,espacamentoEntreAtor, controladores);
	
	private Casa[] casas = new Casa[20];
	private Cadeira[] cadeiras = new Cadeira[21];
	private Bebo Bebos[] = new Bebo[20];
	private boolean jogando;
	private Janela janela;
	private Thread gameloop = new Thread(this);
	private String scenePath = "Data/Scenes/background.png";
	
	public Canvas(int h, int w, Janela janela,Bebo Bebos[], int quantidadeDeCadeiras) {
		jogando = false;
		this.janela = janela;
		this.Bebos = Bebos;
		
		criarCasas();
		
		this.quantidadeDeCadeiras = quantidadeDeCadeiras;
		this.bartender = new Bartender(h,w);
		this.carro = new Carro(h,w,0,1118);
		this.caminhao = new Carro(h,w,1,1190);

		for(int i = 0; i <= quantidadeDeCadeiras; i++) {
			cadeiras[i] = new Cadeira(h,w);
			cadeiras[i].setPosX(balcao.getX() + (i * espacamentoEntreAtor));
			cadeiras[i].setPosY(balcao.getY() + 40);
			
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
		
	public void criarCasas() {
		int quantidadeDeCasas = 20;
		int i;
		for(i = 0; i < quantidadeDeCasas; i++) {
			int casaY = 60;
			int reset = 0;
			if(i > 17) {
				reset = 21;
				casaY -= 180;
			}else if(i > 15) {
				reset = 19;
				casaY = 60;
			}else if(i > 7) {
				casaY -= 180;
				reset = 8;
			}
			casas[i] = new Casa(640- ((i - reset) * 90), casaY);
		}
	}
	
	// para manter o refreshRate a 60fps => 1000ms/16 = 62
	public void sleep() {
		try {
			Thread.sleep(16);
		} 
		catch(InterruptedException e) {
			Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	// ciclo de atualizacoes
	public void run() {
		long timer = System.currentTimeMillis();
		
		while(true) {
			atualiza();	
			repaint();
			// mantem o refresh rate a 60 e conta os frames
			sleep();
			if(System.currentTimeMillis() - timer > 1000) 
			{
				atualizaConsoles();
				timer+= 1000;
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
	public void atualiza() {	
		fila.atualizar();
		balcao.atualizar();
		bartender.atualizar();
		carro.atualizar();
		caminhao.atualizar();

		for(int i = 0; i < quantidadeDeAtores; i++) {
			if(controladores[i] != null) {
				controladores[i].atualizar();
				casas[i].atualizar();
			}
		}
	}

	// desenha os componentes na tela
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		// paint background
		g2d.drawImage(cenario,  null,  0,  0);
		// pinta os atores
		if(jogando) {
			bartender.pintarAtor(g2d);
			carro.pintarAtor(g2d);
			caminhao.pintarAtor(g2d);
			
			for(int i = 0; i < quantidadeDeCadeiras; i++) {
				if(cadeiras[i] != null) {
					cadeiras[i].pintarAtor(g2d);
				}
			}
			
			for(int i = 0; i < 20; i++) {
				if(casas[i] != null) {
					casas[i].pintarCasa(g2d);
				}
			}
			for(int i = 0; i < quantidadeDeAtores; i++) {
				if(atores[i] != null) {
					atores[i].pintarAtor(g2d);
				}
			}
		}	
	}
	
	// adiciona um ator ao canvas
	public void addAtor(Ator ator) {
		controladores[quantidadeDeAtores] = new ControleAtor(ator, Bebos[quantidadeDeAtores], this.fila, casas[quantidadeDeAtores], this.balcao);
		atores[quantidadeDeAtores] = ator;
		quantidadeDeAtores+=1;
	}
	
	// redimensiona imagem
	public static BufferedImage resize(BufferedImage img, int W, int H) { 
	    Image temp = img.getScaledInstance(W, H, Image.SCALE_SMOOTH);
	    BufferedImage novaImagem = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = novaImagem.createGraphics();
	    g2d.drawImage(temp, 0, 0, null);
	    g2d.dispose();
	    return novaImagem;
	}  

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