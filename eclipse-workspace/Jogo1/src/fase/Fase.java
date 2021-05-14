package fase;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import controle.*;
import inimigos.*;
import janela.Janela;
import player.Jogador;
import player.Player;
import player.Player2;

public class Fase extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fundo; // background da fase
	//private Player player; // declaro o primeiro player
	//private Player2 player2; // segundo player
	public int faseAtual;
	private Jogador player1, player2;
	private static LinkedList<Inimigo> ListaInimigos = new LinkedList<Inimigo>();
	private long tempoExcluiInimigo=1000;
	private Timer timerFase;// timer
	private java.util.Timer timerPatoDourado;
	private java.util.Timer timerPatoPequeno;
	private java.util.Timer timerPatoFedido;
	private java.util.Timer timerPatoNormal;
	private java.util.Timer timerExcluidor;
	public Fase(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno,String imgURL,int NumeroFase){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		ImageIcon img = new ImageIcon(imgURL); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getLarguraJanela(), Janela.getAlturaJanela(), ABORT));
		this.fundo = img.getImage(); // seto o background cm o src anterior;
		inicializaJogadores();
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		this.faseAtual=NumeroFase;
		
		timerFase=  new Timer(6, this); // atualiza a cada 1seg
		timerFase.start();
		timerPatoDourado = new java.util.Timer();
		timerPatoDourado.scheduleAtFixedRate(new SpawnerPatoDourado(),0,tempoPatoDourado);
		timerPatoFedido = new java.util.Timer();
		timerPatoFedido.scheduleAtFixedRate(new SpawnerPatoFedido(),0,tempoPatoFedido);
		timerPatoNormal = new java.util.Timer();
		timerPatoNormal.scheduleAtFixedRate(new SpawnerPatoNormal(),0,tempoPatoNormal);
		timerPatoPequeno = new java.util.Timer();
		timerPatoPequeno.scheduleAtFixedRate(new SpawnerPatoPequeno(),0,tempoPatoPequeno);
		timerExcluidor = new java.util.Timer();
		timerExcluidor.scheduleAtFixedRate(new ExcluiInimigos(), 1, tempoExcluiInimigo);
		
	}
	
	public void inicializaJogadores() {
		player1 = new Jogador(1, "res\\mira.png", 100, 100);
		player1.load();
		player2 = new Jogador(2, "res\\mira2.png", 100, 100);
		player2.load();
	}
	
	public void paint(Graphics g) { // função para mostrar os dados na tela
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		Iterator<Inimigo> it = getListaInimigos().iterator();
		while(it.hasNext()) {
			try{
				Inimigo p = it.next();
				graficos.drawImage(p.getImg(),p.getX(),p.getY(),this);}
			catch(Exception e) {
				break;
			}
				
		}
		graficos.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);
		graficos.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		player2.update();
		Iterator<Inimigo> it = getListaInimigos().iterator();
		while(it.hasNext()) {
			Inimigo p = it.next();
			p.update();
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
	public static LinkedList<Inimigo> getListaInimigos() {
		return ListaInimigos;
	}
}
	
	
