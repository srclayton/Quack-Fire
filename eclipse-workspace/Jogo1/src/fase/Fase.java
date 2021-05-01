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

import inimigos.*;
import janela.Janela;
import player.Jogador;
import player.Player;
import player.Player2;


public class Fase extends JPanel implements ActionListener{
	private Image fundo; // background da fase
	//private Player player; // declaro o primeiro player
	//private Player2 player2; // segundo player
	private Jogador player1, player2;
	
	private Inimigo inimigos[];
	private int nPatoNormal= 50;
	private int nPatoFedorento= 30;
	private int nPatoPequeno= 80;
	private int nPatoDourado= 100;
	private int inimigosVivos=0;
	
	private Timer timer;	// timer
	public Fase(){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		ImageIcon img = new ImageIcon("res\\City4.png"); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getLarguraJanela(), Janela.getAlturaJanela(), ABORT));
		this.fundo = img.getImage(); // seto o background cm o src anterior;
		inicializaJogadores();
		inicializaInimigos();
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		
		timer=  new Timer(5, this); // atualiza a cada 1seg
		timer.start();
	}
	public void inicializaJogadores() {
		//player = new Player(100,100); // construo o primeiro jogador
		//player.load(); // atualizo o mesmo, imagem e mais detalhes;
		//player2= new Player2(100,100);
		//player2.load();
		player1 = new Jogador(1, "res\\mira.png", 100, 100);
		player1.load();
		player2 = new Jogador(2, "res\\mira2.png", 100, 100);
		player2.load();
		
	}
	public void inicializaInimigos() {
		
		inimigos = new Inimigo[nPatoNormal+nPatoFedorento+nPatoPequeno+nPatoDourado];
		for
		
	}
	
	public void paint(Graphics g) { // função para mostrar os dados na tela
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		for(int i =0; i < 50; i++) {
			PatoNormal aux = inimigos[i];
			PatoDourado aux2 = inimigoDourado[i];
			PatoPequeno aux3 = inimigoPequeno[i];
			PatoFedido aux4 = inimigoFedido[i];
			graficos.drawImage(aux.getImg(), aux.getX(), aux.getY(), this);
			graficos.drawImage(aux2.getImg(), aux2.getX(), aux2.getY(), this);
			graficos.drawImage(aux3.getImg(), aux3.getX(), aux3.getY(), this);
			graficos.drawImage(aux4.getImg(), aux4.getX(), aux4.getY(), this);

		}
		graficos.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);
		graficos.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		player2.update();
		for(int i =0; i < 50; i++) {
			PatoNormal aux = inimigos[i];
			PatoDourado aux2 = inimigoDourado[i];
			PatoPequeno aux3= inimigoPequeno[i];
			PatoFedido aux4 = inimigoFedido[i];
			aux.update();
			aux2.update();
			aux3.update();
			aux4.update();
		}
		repaint();
	}
	
	private class TecladoAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			player1.keyPressed(e);
			player2.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			player1.KeyRelease(e);
			player2.KeyRelease(e);
		}
	}
	public int atirou(int x,int y) {
		int pontuacao;
		//Testa para cada um dos patos
		//If(esta na mira)
				pontuacao += o[i].getPontos();
				o[i] = null;
	}
	
}
