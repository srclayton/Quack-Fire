package janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controle.*;
import dao.RankingDAO;
import fase.*;
import modelo.*;

public class Janela extends JFrame implements ActionListener,MouseListener{
	
	private  int numOperacao = 3; // quantidade de opera��o da janela, maximizar, fechar e minimizar;
	private static int alturaJanela = 728; // seto a altura e largura da janela;
	private static int larguraJanela = 1024;
	private Timer tempoDeFase;
	
	private JButton bt = new JButton("Fase 1");
	private JButton bt2 = new JButton("Fase 2");
	private JButton bt3 = new JButton("Fase 3");
	private JButton botaoInsereUsername = new JButton("InsereUsername");
	private JButton btTxt;
	private JButton btJson;
	private JButton btRanking;
	private JButton btVoltaMenu;
	private JLabel jb = new JLabel("MENU");
	private JLabel labelU1;
	private JLabel ranking1;
	private JLabel ranking2;
	private JLabel ranking3;
    private JMenuItem salvarTxt;
    private JMenuItem salvarJson;
	private static JPanel FaseAtual;
	private static JPanel menu;
	private JPanel panelPedeUsuario;
	private JPanel pedeFormato;
	private JPanel panelRanking;
	private JTextField field1;
	private JTextField field2;
	private static String username1;
	private static String username2;
	private int numFase;
	private String formato;
//	********************************************************
//	cria��o da janelo do nosso jogo, � o primeiro contato 
//	que o usuario tem com o jogo, onde ja se inicializa
//	o menu de op��es.
//	********************************************************
	public Janela() {
		
		tempoDeFase = new Timer(65000,this);
		tempoDeFase.start();
		criaMenu();
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu salvar = new JMenu("Salvar");
        menuBar.add(salvar);
        salvarTxt = new JMenuItem("Salvar em .txt");
        salvarJson = new JMenuItem("Salvar em .jason");
        salvarTxt.addMouseListener(this);
        salvarJson.addMouseListener(this);
        salvar.add(salvarTxt);
        salvar.addSeparator();
        salvar.add(salvarJson);
        
		updateTela();
		
		
	}
	public void actionPerformed(ActionEvent e) {
		((Fase) FaseAtual).encerra();
		encerraFase();
		criaMenu();
	}

	public static int getAlturaJanela() {
		return alturaJanela;
	}

	public static int getLarguraJanela() {
		return larguraJanela;
	}
//	*******************************************
//	metodo para finalizar a fase e remove-la
//	da tela, e em seguida adicionar o menu
//	a mesma.
//	*******************************************
	public void encerraFase() {
		remove(FaseAtual);
		FaseAtual.removeAll();
		FaseAtual.validate();
		FaseAtual.repaint();
		FaseAtual= null;
		updateTela();
		
		
	}
	
//	*****************************************************
//	metodo que cria o Painel de menu, onde o usuario tem 
//	as escolhas de fase e exibir o ranking. 
//	*****************************************************
	public void criaMenu() {
		menu =  new JPanel();
		menu.setLayout(null);
		btRanking = new JButton("Ver Ranking");
		jb.setBounds(Janela.getLarguraJanela()/2 - jb.getText().length()-50,0,100,100);
		bt.setBounds(100,300,200,200);
		bt.addMouseListener(this);
		bt2.setBounds(400,300,200,200);
		bt2.addMouseListener(this);
		bt3.setBounds(700,300,200,200);
		bt3.addMouseListener(this);
		btRanking.setBounds(100,510,800,80);
		btRanking.addMouseListener(this);
		JLabel tabelaPontos = new JLabel();
		tabelaPontos.setBounds(240 ,100,700,200);
		ImageIcon icon = new ImageIcon("res\\tabelaDePontos.png");
		tabelaPontos.setIcon(icon);
		menu.add(tabelaPontos);
		menu.add(jb);
		menu.add(bt);
		menu.add(bt2);
		menu.add(bt3);
		menu.add(btRanking);
		add(menu);
		updateTela();
		
		tempoDeFase.stop();
		
	}
//	*******************************************************
//	de acordo com a escolha do usuario iniciamos uma nova
//	fase para o mesmo jogar, sempre verificando
//	se o mesmo possui algum formato de save
//	*******************************************************
	public void adicionaFase() {
		boolean usarSave = false;
		if (formato!=null) {
			usarSave = true;
		}
		if (numFase==1) {
			FaseAtual = new Fase1(usarSave, formato);
		}
		else if (numFase==2) {
			FaseAtual = new Fase2(usarSave, formato);
		}
		else if (numFase==3) {
			FaseAtual = new Fase3(usarSave, formato);
		}
		add(FaseAtual);
		updateTela();
		tempoDeFase.start();
		
		
	}
//	*****************************************
//	antes de iniciarmos uma fase, precisamos
//	primeiro remover o menu principal,
//	� atravs deste metodo que realizamos
//	a opera��o.
//	****************************************
	public void encerraMenu() {
		remove(menu);
		menu.removeAll();
		menu.validate();
		menu.repaint();
		menu = null;
		updateTela();
	}
	public void updateTela() {

		setTitle("Quack fire!"); // TITULO DA JANELA
		setDefaultCloseOperation(numOperacao); // OPERAÇÕES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
		setSize(larguraJanela,alturaJanela); // DEFINE O TAMANHO DA JANELA;
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
//		***************************************************
//		o mouse listener � de extrema importancia
//		pois � ele quem administra a a��o de cada bot�o.
//		Gerenciando qual fase o usuario escolheu jogar,
//		se o usuario resolveu verificar o ranking ou at�
//		mesmo salvar seu progresso em .txt e/ou .json.
//		***************************************************
		
		if(e.getSource() == bt) {
			bt.removeMouseListener(this);
			encerraMenu();
			numFase=1;
			pedeUsername();
			
			
		}
		if(e.getSource() == bt2) {
			bt2.removeMouseListener(this);
			encerraMenu();
			numFase=2;
			pedeUsername();
		}
		if(e.getSource() == bt3) {
			bt3.removeMouseListener(this);
			encerraMenu();
			numFase =3;
			pedeUsername();
			
		}
//		************************************************
//		antes de iniciar a fase para o jogador
//		n�s cadastramos os seus usernames.
//		posterior ao cadastramentos verificamos atraves
//		dos metodos AchaSave, se existe algum save 
//		do usuario, caso encontremos, solicitamos em uma
//		nova tela que o usuario escolha o formato desejado
//		para fazer o load. Caso contrario apenas iniciamos
//		uma nova fase
//		*************************************************
		if(e.getSource() == botaoInsereUsername) {
			botaoInsereUsername.removeMouseListener(this);
			username1 = field1.getText();
			username2 = field2.getText();
			excluiPanel(panelPedeUsuario);
			formato = null;
			boolean usarSaveJSON = AchaSaveJSON.executar(numFase,username1);
			boolean usarSaveTXT = AchaSaveTXT.executar(numFase,username1);
			if(usarSaveJSON||usarSaveTXT) {
				pedeFormato(usarSaveJSON,usarSaveTXT);
			}
			else {
				adicionaFase();
				}
		}
//		*******************************
//		Adicionamos a pagina de ranking
//		para visualiza��o
//		******************************
		if(e.getSource() == btRanking)
		{
			excluiPanel(menu);
			paginaRanking();
		}
//		******************************
//		bot�es de escolha de formato
//		de save, para o usuario
//		escolher qual ira dar load
//		******************************
		if(e.getSource() == btTxt) {
			formato = "TXT";
			excluiPanel(pedeFormato);
			adicionaFase();
		}
//		******************************
//		bot�es de escolha de formato
//		de save, para o usuario
//		escolher qual ira dar load
//		******************************
		if(e.getSource() == btJson) {
			formato = "JSON";
			excluiPanel(pedeFormato);
			adicionaFase();
		}
//		******************************
//		bot�o responsavel por apresentar
//		o menu novamente para o usuario
//		******************************
		if(e.getSource() == btVoltaMenu) {
			excluiPanel(panelRanking);
			criaMenu();
		}
		
	}
//	*********************************************************
//	Metodo responsavel por mostrar o ranking do jogo ao usuario
//	atraves das 3 diferentes listas, uma para cada fase.
//	mostramos os melhores 5 player, contendo seu username
//	e sua maior pontua��o, do maior para o menor.
//	e caso o ranking esteja vazio, apenas o informamos que
//	o mesmo n�o a nenhum player.
//	*********************************************************
	public void paginaRanking() {
		panelRanking = new JPanel();
		panelRanking.setLayout(null);
		ArrayList<DetalhesJogadorRanking> arrayDet1 = RankingDAO.listaRankingDAOJSON(1);
		ArrayList<DetalhesJogadorRanking> arrayDet2 = RankingDAO.listaRankingDAOJSON(2);
		ArrayList<DetalhesJogadorRanking> arrayDet3 = RankingDAO.listaRankingDAOJSON(3);
		for(int i=1;i<6;i++) {
			try {
				ranking1 = new JLabel();
				ranking1.setText(arrayDet1.get(i-1).getUsername()+"  :  "+arrayDet1.get(i-1).getPontuacao());}
			catch(Exception e) {
				ranking1.setText("RANKING VAZIO");
			}
			try {
				ranking2 = new JLabel();
				ranking2.setText(arrayDet2.get(i-1).getUsername()+"  :  "+arrayDet2.get(i-1).getPontuacao());}
			catch(Exception e) {
				ranking2.setText("RANKING VAZIO");}
			try {
				ranking3 = new JLabel();
				ranking3.setText(arrayDet3.get(i-1).getUsername()+"  :  "+arrayDet3.get(i-1).getPontuacao());}
			catch(Exception e) {
				ranking3.setText("RANKING VAZIO");
			}
			ranking1.setBounds(60, 60*i+100, 250, 58);
			ranking2.setBounds(400, 60*i+100, 250, 58);
			ranking3.setBounds(700, 60*i+100, 250, 58);
			panelRanking.add(ranking1);
			panelRanking.add(ranking2);
			panelRanking.add(ranking3);
		}
		ranking1 = new JLabel();
		ranking2 = new JLabel();
		ranking3 = new JLabel();
		ranking1.setText("Ranking Fase 1");
		ranking1.setBounds(60, 60, 250, 58);
		ranking2.setText("Ranking Fase 2");
		ranking2.setBounds(400, 60, 250, 58);
		ranking3.setText("Ranking Fase 3");
		ranking3.setBounds(700, 60, 250, 58);
		panelRanking.add(ranking1);
		panelRanking.add(ranking2);
		panelRanking.add(ranking3);
		
		btVoltaMenu = new JButton("Retornar Menu");
		btVoltaMenu.addMouseListener(this);
		btVoltaMenu.setBounds(500, 500, 150, 100);
		panelRanking.add(btVoltaMenu);
		
		add(panelRanking);
		
		updateTela();
	}
//	************************************************************
//	pedeFormato � um PANEL responsavel por solicitar qual save
//	o usuario gostaria de usar, se o mesmo possuir algum save
//	com as estens�es .txt e/ou .jason
//	************************************************************
	public void pedeFormato(boolean usarSaveJSON,boolean usarSaveTXT) {
		pedeFormato = new JPanel();
		pedeFormato.setLayout(null);
		if (usarSaveTXT) {
		btTxt = new JButton("txt");
		btTxt.setBounds(100,300,80,80);
		btTxt.addMouseListener(this);
		pedeFormato.add(btTxt);
		}
		if (usarSaveJSON) {
		btJson = new JButton("json");
		btJson.setBounds(400,300,80,80);
		btJson.addMouseListener(this);
		pedeFormato.add(btJson);
		}
		add(pedeFormato);
		updateTela();
	}
//	***************************************************
//	PANEL responsavel por fazer a coleta dos usernames 
//	dos jogadores presentes.
//	***************************************************
	public void pedeUsername() {
		
        
        //INICIALIZA JANELA
        panelPedeUsuario = new JPanel();
        panelPedeUsuario.setLayout(null);
        
        labelU1 = new JLabel();
        labelU1.setText("Sele��o nome dos Usu�rios");
        field1 = new JTextField(50);
        field2 = new JTextField(50);
        botaoInsereUsername.addMouseListener(this);
        
        //COLOCA OS OBJETOS NA JANELA
        panelPedeUsuario.add(botaoInsereUsername);
        panelPedeUsuario.add(labelU1);
        panelPedeUsuario.add(field1);
        panelPedeUsuario.add(field2);
        
        //COLOCA OS OBJETOS EM UMA ORDEM ADEQUADA
        labelU1.setBounds(375,100,250,50);
        botaoInsereUsername.setBounds(380,280,150,150);
        field1.setBounds(300,200,120,50);
        field2.setBounds(500, 200, 120, 50);
        add(panelPedeUsuario);
        updateTela();
	}
//	************************************
//	excluirPanel recebe um JPanel e faz
//	sua remo��o por completo para que 
//	possa ser adicionado um novo 
//	JPanel em seu lugar.
//	************************************
	public void excluiPanel(JPanel panel) {
		remove(panel);
		panel.removeAll();
		panel.validate();
		panel.repaint();
		panel = null;
		updateTela();
	}
	@Override
//	******************************************
//	os mousePressed ficam responsaveis
//	pelas a��es de saves encotradas no menuBar
//	******************************************
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == salvarTxt) {
			System.out.println("Salvar em TXT");
			Fase.salvar("TXT");
		}
		else
			if(e.getSource() == salvarJson) {
				System.out.println("Salvar em Json");
				Fase.salvar("JSON");
			}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	
}
	public static String getUsername1() {
		return username1;
	}
	public static String getUsername2() {
		return username2;
	}
	
}
