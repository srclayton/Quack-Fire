package janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import controle.*;
import fase.*;

public class Janela extends JFrame implements ActionListener,MouseListener{
	
	private  int numOperacao = 3; // quantidade de operação da janela, maximizar, fechar e minimizar;
	private static int alturaJanela = 728; // seto a altura e largura da janela;
	private static int larguraJanela = 1024;
	private Timer tempoDeFase;
	private static JPanel FaseAtual;
	private static JPanel menu;
	private JLabel jb = new JLabel("MENU");
	private JButton bt = new JButton("Fase 1");
	private JButton bt2 = new JButton("Fase 2");
	private JButton bt3 = new JButton("Fase 3");
	private JButton botaoInsereUsername = new JButton("InsereUsername");
	private JButton btTxt;
	private JButton btJson;
	private JPanel panelPedeUsuario;
	private JPanel pedeFormato;
	private JLabel labelU1;
	private JTextField field1;
	private JTextField field2;
	private static String username1;
	private static String username2;
	private int numFase;
	private String formato;
	private JMenuItem cutAction;
	
	public Janela() {
		
		tempoDeFase = new Timer(10000,this);
		tempoDeFase.start();
		criaMenu();
		// Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();

        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);

        // Define e adiciona dois menus drop down na barra de menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Cria e adiciona um item simples para o menu
        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");
        cutAction = new JMenuItem("Cut");
        cutAction.addMouseListener(this);
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");

        // Cria e aiciona um CheckButton como um item de menu
        JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
        // Cria e aiciona um RadioButton como um item de menu
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem(
                "Radio Button1");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem(
                "Radio Button2");
        // Cria um ButtonGroup e adiciona os dois radio Button
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAction1);
        bg.add(radioAction2);
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(checkAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
		
		updateTela();
		
		
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("BBBBBBB");
		encerraFase();
		criaMenu();
	}

	public static int getAlturaJanela() {
		return alturaJanela;
	}

	public static int getLarguraJanela() {
		return larguraJanela;
	}
	public void encerraFase() {
		remove(FaseAtual);
		FaseAtual.removeAll();
		FaseAtual.validate();
		FaseAtual.repaint();
		FaseAtual= null;
		updateTela();
		
		
	}
	public void criaMenu() {
		menu =  new JPanel();
		menu.setLayout(null);
		jb.setBounds(Janela.getLarguraJanela()/2 - jb.getText().length()-50,100,100,100);
		bt.setBounds(100,300,200,200);
		bt.addMouseListener(this);
		bt2.setBounds(400,300,200,200);
		bt2.addMouseListener(this);
		bt3.setBounds(700,300,200,200);
		bt3.addMouseListener(this);
		menu.add(jb);
		menu.add(bt);
		menu.add(bt2);
		menu.add(bt3);
		add(menu);
		updateTela();
		
		tempoDeFase.stop();
		
	}
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
		setDefaultCloseOperation(numOperacao); // OPERAÃ‡Ã•ES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
		setSize(larguraJanela,alturaJanela); // DEFINE O TAMANHO DA JANELA;
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
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
		if(e.getSource() == btTxt) {
			formato = "TXT";
			excluiPanel(pedeFormato);
			adicionaFase();
		}else if(e.getSource() == btJson) {
			formato = "JSON";
			excluiPanel(pedeFormato);
			adicionaFase();
		}else if(e.getSource() == cutAction) {
			System.out.println("AAAAAABBBBB");
		}
		
	}
	
	public void pedeFormato(boolean usarSaveJSON,boolean usarSaveTXT) {
		pedeFormato = new JPanel();
		pedeFormato.setLayout(null);
		if (usarSaveTXT) {
		btTxt = new JButton("txt");
		btTxt.setBounds(100,300,200,200);
		btTxt.addMouseListener(this);
		pedeFormato.add(btTxt);
		}
		if (usarSaveJSON) {
		btJson = new JButton("json");
		btJson.setBounds(400,300,200,200);
		btJson.addMouseListener(this);
		pedeFormato.add(btJson);
		}
		add(pedeFormato);
		updateTela();
	}
	
	public void pedeUsername() {
		
        
        //INICIALIZA JANELA
        panelPedeUsuario = new JPanel();
        panelPedeUsuario.setLayout(null);
        
        labelU1 = new JLabel();
        labelU1.setText("Seleção nome dos Usuários");
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
	public void excluiPanel(JPanel panel) {
		remove(panel);
		panel.removeAll();
		panel.validate();
		panel.repaint();
		panel = null;
		updateTela();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
