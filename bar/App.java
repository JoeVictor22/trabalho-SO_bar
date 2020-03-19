package bar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import java.awt.BorderLayout;



public class App extends JFrame implements Runnable, ActionListener{
    private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	JButton startButton = new JButton("Iniciar");
	JLabel nomeLabel = new JLabel("Digite o nome do bebo!");
	static JTextField nome = new JTextField("", 15);
	JLabel tempoBebendoLabel = new JLabel("Informe o tempo no bar!");
	static JTextField tempoBebendo = new JTextField("", 5);
	JLabel tempoDormindoLabel = new JLabel("Informe o tempo de soneca!");
	static JTextField tempoDormindo= new JTextField("", 5);
	JButton newBeboButton = new JButton("Adicionar Papudim");
	
	static JLabel errorMessage = new JLabel("");
	

	
	/*
	String tabela[] = {
		"opcao 1",
		"opcao 2",
		"opcao 3",
		"opcao 4"
	};
	// remover select se nao usado
	JComboBox box = new JComboBox(tabela);
	*/
	public void run() {

		System.out.printf("%s\n",nome.getText());
		System.out.printf("%s\n",tempoBebendo.getText());
		System.out.printf("%s\n",tempoDormindo.getText());
	
	}
	
	public static void main(String[] args) {
		App ap = new App();
		ap.run();
		
		
	}
	
	public App() {
		super("Bar do Seu Batista");
		setSize(1920,1080);
		setResizable(true);
		
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startButton.addActionListener(this);
		newBeboButton.addActionListener(this);

		
		panel.add(startButton);
		panel.add(nomeLabel);
		panel.add(nome);
		panel.add(tempoBebendoLabel);
		panel.add(tempoBebendo);
		panel.add(tempoDormindoLabel);
		panel.add(tempoDormindo);
		panel.add(newBeboButton);
		

		errorMessage.setText("mensagem de erro");
		errorMessage.setForeground (Color.red);

		panel.add(errorMessage);
		// remover select se nao usado
		//panel.add(box);

		Canvas grafico = new Canvas(1366, 768);
		
		panel.add(grafico);
        this.add(panel, BorderLayout.CENTER);

		//add(panel);
		
		setVisible(true);
		PlayMusic.playMusic("Data/SFX/Undertale_-_Determination.mid");

	}
	
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Iniciar")) {
            System.out.println("comecou!");
        }
        else if (action.equals("Adicionar Papudim")) {
        	String x1 = nome.getText();
        	String x2 = tempoBebendo.getText();
        	String x3 = tempoDormindo.getText();
        	
        	if(x1.length() <= 0 || x2.length() <= 0  || x3.length() <= 0 ) {
        		System.out.printf("Campo vazio!\n");
        	}else {
        		System.out.printf("%s - %s - %s\n",x1, x2, x3);
        	}
        	
        	
        }
    }
 
}