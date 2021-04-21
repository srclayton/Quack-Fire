package fase;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import inimigos.Inimigo;
import janela.Janela;
import player.Player;
import player.Player2;

public class Fase extends JPanel implements ActionListener{
	private Image fundo; // background da fase
	private Player player; // declaro o primeiro player
	private Player2 player2; // segundo player
	private Inimigo inimigo[];

	
	
	private Timer timer;	// timer
	public Fase(){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		ImageIcon img = new ImageIcon("res\\City4.png"); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getAlturaJanela(), Janela.getLarguraJanela(), ABORT));
		this.fundo = img.getImage(); // seto o background cm o src anterior;
		player = new Player(100,100); // construo o primeiro jogador
		player.load(); // atualizo o mesmo, imagem e mais detalhes;
		player2= new Player2(100,100);
		player2.load();
		inicializaInimigos();
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		
		timer=  new Timer(1, this); // atualiza a cada 1seg
		timer.start();
	}
	
	public void inicializaInimigos() {
		inimigo = new Inimigo[Inimigo.getQntd()];
		Random dice = new Random();
		for(int i =0; i < Inimigo.getQntd(); i++) {
			Inimigo aux = new Inimigo(dice.nextInt(Janela.getLarguraJanela()+8000), dice.nextInt(Janela.getAlturaJanela())+1);
			aux.load();
			inimigo[i] = aux;
			
		}
	}
	
	public void paint(Graphics g) { // fun��o para mostrar os dados na tela
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(player.getImg(), player.getX(), player.getY(), this);
		graficos.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);
		for(int i =0; i < Inimigo.getQntd(); i++) {
			Inimigo aux = inimigo[i];
			graficos.drawImage(aux.getImg(), aux.getX(), aux.getY(), this);
			System.out.println(i);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		player2.update();
		for(int i =0; i < Inimigo.getQntd(); i++) {
			Inimigo aux = inimigo[i];
			aux.update();
		}
		repaint();
	}
	
	private class TecladoAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
			player2.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			player.KeyRelease(e);
			player2.KeyRelease(e);
		}
	}

	
}
