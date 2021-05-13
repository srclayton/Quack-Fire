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

import controle.SpawnerPatoDourado;
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
	private static LinkedList<Inimigo> ListaInimigos = new LinkedList<Inimigo>();
	private double tempoPatoDourado= 5000;
	private double tempoPatoPequeno = 1000;
	private double tempoPatoFedido = 1000;
	private double tempoPatoNormal =1000;
	private Timer timerFase;// timer
	public java.util.Timer timerPatoDourado;
	private Timer timerPatoPequeno;
	private Timer timerPatoFedido;
	private Timer timerPatoNormal;
	public Fase(){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		ImageIcon img = new ImageIcon("res\\City4.png"); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getLarguraJanela(), Janela.getAlturaJanela(), ABORT));
		this.fundo = img.getImage(); // seto o background cm o src anterior;
		inicializaJogadores();
		inicializaInimigos();
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		
		
		timerFase=  new Timer(5, this); // atualiza a cada 1seg
		timerFase.start();
		timerPatoDourado = new java.util.Timer();
		timerPatoDourado.scheduleAtFixedRate(new SpawnerPatoDourado(),0,(long) tempoPatoDourado);
	}
	
	public void inicializaJogadores() {
		player1 = new Jogador(1, "res\\mira.png", 100, 100);
		player1.load();
		player2 = new Jogador(2, "res\\mira2.png", 100, 100);
		player2.load();
	}
	public void inicializaInimigos() {
		PatoDourado p = new PatoDourado(500,500,500);
		p.load();
		ListaInimigos.add(p);
	}
	
	public void paint(Graphics g) { // função para mostrar os dados na tela
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		//for(int i =0; i < 50; i++) {
		Inimigo p = ListaInimigos.getLast();
			graficos.drawImage(p.getImg(),p.getX(),p.getY(),this);
			//graficos.drawImage(aux4.getImg(), aux4.getX(), aux4.getY(), this);
		
		graficos.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);
		graficos.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		player2.update();
		//for(int i =0; i < 50; i++) {
		ListaInimigos.getFirst().update();
		ListaInimigos.getLast().update();
		System.out.println(ListaInimigos.getLast().getX()+ " "+ListaInimigos.getFirst().getX());
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
//	public int atirou(int x,int y) {
//		int pontuacao;
//		//Testa para cada um dos patos
//		//If(esta na mira)
//				pontuacao += o[i].getPontos();
//				o[i] = null;
//	}
	
}
