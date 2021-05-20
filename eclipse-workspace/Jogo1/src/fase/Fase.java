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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controle.*;
import dao.*;
import inimigos.*;
import janela.Janela;
import player.Jogador;

public class Fase extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fundo; // background da fase
	public int faseAtual;
	private Jogador player1, player2;
	private static LinkedList<Inimigo> ListaInimigos = new LinkedList<Inimigo>();
	private long tempoExcluiInimigo=1000;
	private Timer timerFase;// timer
	private boolean ballonBoy = false;
	private java.util.Timer timerPatoDourado;
	private java.util.Timer timerPatoPequeno;
	private java.util.Timer timerPatoFedido;
	private java.util.Timer timerPatoNormal;
	private java.util.Timer timerExcluidor;
	private java.util.Timer timerBallonBoy;

	public int testando =0;

	/*===================================================================================
	 *Construtora da Fase, que atraves dela é feita a implementação da fase, 
	 *recebemos O tempo de spawn de cada inimigo, o src de background da fase e o numero 
	 *da fase em questão, 
	 *===================================================================================*/
	public Fase(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno, int tempoBallonBoy,String imgURL,int NumeroFase){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		ImageIcon img = new ImageIcon(imgURL); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getLarguraJanela(), Janela.getAlturaJanela(), ABORT));
		this.fundo = img.getImage();
		construirSaveAntigo();// seto o background cm o src anterior;
		inicializaJogadores();
		inicializaInimigos(tempoPatoDourado, tempoPatoFedido, tempoPatoNormal,tempoPatoPequeno, tempoBallonBoy);
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		this.faseAtual=NumeroFase;
		timerFase=  new Timer(6, this); // 
		timerFase.start();
		
		System.out.println(player1.getPontuacao());
	}
	public void construirSaveAntigo() {
		InimigoDao iDAO = new InimigoDao();
		iDAO.Construtora("username");
//		Iterator<Inimigo> it = getListaInimigos().iterator();
//		while(it.hasNext()) {
//			try{
//				Inimigo p = it.next();
//				p.load();}
//			catch(Exception e) {
//				break;
//			}
//				
//		}
	}
	/*============================================================================
	 * Metodo para spawnar os jogadores, invocando sua construtora e passando
	 * qual player é, src de sua imagem, e suas coordenadas de spawn.
	 * ===========================================================================
	 */
	public void inicializaJogadores() {
		player1 = new Jogador(1, "res\\mira.png", 100, 100);
		player1.load();
		player2 = new Jogador(2, "res\\mira2.png", 100, 100);
		player2.load();
	}
	/*============================================================================
	 * Metodo para spawnar os Inimigos, invocando sua construtora e passando
	 * qual inimigo é, src de sua imagem, e suas coordenadas de spawn.
	 * ===========================================================================
	 */
	public void inicializaInimigos(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno,int tempoBallonBoy) {
		timerPatoDourado = new java.util.Timer();
		timerPatoDourado.scheduleAtFixedRate(new SpawnerPatoDourado(),30,tempoPatoDourado);
		timerPatoFedido = new java.util.Timer();
		timerPatoFedido.scheduleAtFixedRate(new SpawnerPatoFedido(),30,tempoPatoFedido);
		timerPatoNormal = new java.util.Timer();
		timerPatoNormal.scheduleAtFixedRate(new SpawnerPatoNormal(),30,tempoPatoNormal);
		timerPatoPequeno = new java.util.Timer();
		timerPatoPequeno.scheduleAtFixedRate(new SpawnerPatoPequeno(),30,tempoPatoPequeno);
		timerBallonBoy = new java.util.Timer();
		timerBallonBoy.scheduleAtFixedRate(new SpawnerBallonBoy(), 30, tempoBallonBoy);
		timerExcluidor = new java.util.Timer();
		timerExcluidor.scheduleAtFixedRate(new ExcluiInimigos(), 500, tempoExcluiInimigo);
		
	}
	/*==================public void pain==========================================
	 * Metodo responsavel para fazer com que as imagens sejam "printadas" graficamente
	 * na tela do usuario.
	 * ===========================================================================
	 */
	public void paint(Graphics g) { 
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
	/*====================actionPerformed===========================
	 * Metodo responsavel para faze a atualização constante da fase.
	 * =============================================================
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		player1.update();
		player2.update();
		Iterator<Inimigo> it = getListaInimigos().iterator();
		while(it.hasNext()) {
			try {
			Inimigo p = it.next();
			p.update();}
			catch(Exception p) {
				break;
			}
		}
//		testando++;
//		if (testando==1000)
//		{
//			salvar();}
		repaint();
	}
	/*================class TecladoAdapter=======================
	 * Esta classe lida com a leitura das teclas pressionadas
	 * pelo usuario.
	 * ==========================================================
	 */
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
	public void salvar() {
		Iterator<Inimigo> it = getListaInimigos().iterator();
		int i=0;
		while(it.hasNext()) {
			
			i++;
			try {
			Inimigo p = it.next();
			InimigoDao pDAO = new InimigoDao();
			pDAO.inserir(p,i,"username");
			}
			catch(Exception p){
				break;}
			}
		
	}
	
	public static LinkedList<Inimigo> getListaInimigos() {
		return ListaInimigos;
	}
}
	
	
