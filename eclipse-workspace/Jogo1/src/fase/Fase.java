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
	protected static final long serialVersionUID = 1L;
	protected Image fundo; // background da fase
	public int faseAtual;
	protected Jogador player1, player2;
	protected static LinkedList<Inimigo> ListaInimigos = new LinkedList<Inimigo>();
	protected long tempoExcluiInimigo=1000;
	protected Timer timerFase;// timer
	protected java.util.Timer timerPatoDourado;
	protected java.util.Timer timerPatoPequeno;
	protected java.util.Timer timerPatoFedido;
	protected java.util.Timer timerPatoNormal;
	protected java.util.Timer timerExcluidor;
	protected Graphics2D graficos;
	protected int tempoTotal=0;
	protected int tempoSegundosTotais=10;
	protected int taxaDeAtualizacao;

	/*===================================================================================
	 *Construtora da Fase, que atraves dela é feita a implementação da fase, 
	 *recebemos O tempo de spawn de cada inimigo, o src de background da fase e o numero 
	 *da fase em questão, 
	 *===================================================================================*/
	public Fase(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno, int tempoBallonBoy,String imgURL,int numeroFase, int taxaDeAtualizacao,boolean usarSave){
		setFocusable(true); // melhora desempenho;
		setDoubleBuffered(true);
		this.taxaDeAtualizacao = taxaDeAtualizacao;
		ImageIcon img = new ImageIcon(imgURL); // recebo o src da img;
		img.setImage(img.getImage().getScaledInstance(Janela.getLarguraJanela(), Janela.getAlturaJanela(), ABORT));
		this.fundo = img.getImage();
		addKeyListener(new TecladoAdapter()); // leitura das teclas
		this.faseAtual=numeroFase;
		timerFase=  new Timer(taxaDeAtualizacao, this); // 
		timerFase.start();
		tempoTotal=0;
		if(usarSave){
			construirSaveAntigo();
		}
		else {
			inicializaJogadores();
		}
		inicializaInimigos(tempoPatoDourado, tempoPatoFedido, tempoPatoNormal,tempoPatoPequeno);
		System.out.println("AAAAAAAAAAAAAAA");
	}
	public void construirSaveAntigo() {
		InimigoDao iDAO = new InimigoDao();
		iDAO.Construtora(faseAtual,"username");
		JogadorDAO jDAO = new JogadorDAO();
		player1 = jDAO.Construtora(faseAtual,"username", 1);
		player1.load();
		player2 = jDAO.Construtora(faseAtual,"username", 2);
		player2.load();
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
	public void inicializaInimigos(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno) {
		timerPatoDourado = new java.util.Timer();
		timerPatoDourado.scheduleAtFixedRate(new SpawnerPatoDourado(),30,tempoPatoDourado);
		timerPatoFedido = new java.util.Timer();
		timerPatoFedido.scheduleAtFixedRate(new SpawnerPatoFedido(),30,tempoPatoFedido);
		timerPatoNormal = new java.util.Timer();
		timerPatoNormal.scheduleAtFixedRate(new SpawnerPatoNormal(),30,tempoPatoNormal);
		timerPatoPequeno = new java.util.Timer();
		timerPatoPequeno.scheduleAtFixedRate(new SpawnerPatoPequeno(),30,tempoPatoPequeno);
		timerExcluidor = new java.util.Timer();
		timerExcluidor.scheduleAtFixedRate(new ExcluiInimigos(), 500, tempoExcluiInimigo);
		
	}
	/*==================public void pain==========================================
	 * Metodo responsavel para fazer com que as imagens sejam "printadas" graficamente
	 * na tela do usuario.
	 * ===========================================================================
	 */
	public void paint(Graphics g) { 
		this.requestFocus();
		graficos = (Graphics2D) g;
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
		tempoTotal++;
		if (tempoTotal>(10000-1800)/taxaDeAtualizacao)
		{	
			System.out.println("CCCCCCCCCC");
			salvar();
			encerra();
		}
		repaint();
	}
	
	public void encerra() {
		timerFase.stop();
		player1.deletImg();
		player2.deletImg();
		
		Iterator<Inimigo> iterador= getListaInimigos().iterator();
		while(iterador.hasNext()) {
			try {
			Inimigo in = iterador.next();
			in.deletImg();
			iterador.remove();
			}
			catch(Exception in){
				break;}
			}
		timerPatoDourado.cancel();
		timerPatoFedido.cancel();
		timerPatoPequeno.cancel();
		timerPatoNormal.cancel();
		timerExcluidor.cancel();
		this.removeAll();
		validate();
		repaint();
		//salvar();
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
			pDAO.inserir(p,i,"username",faseAtual);
			}
			catch(Exception p){
				break;}
			}
		JogadorDAO j = new JogadorDAO();		
		j.inserir(faseAtual,player1, player2, "username");
	}
	
	public static LinkedList<Inimigo> getListaInimigos() {
		return ListaInimigos;
	}
}
	
	
