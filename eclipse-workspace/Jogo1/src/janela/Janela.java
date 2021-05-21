package janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import controle.AchaSave;
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
	private JPanel panelPedeUsuario;
	private JLabel labelU1;
	private JTextField field1;
	private JTextField field2;
	private static String username1;
	private static String username2;
	
	public Janela() {
		
		tempoDeFase = new Timer(10000,this);
		tempoDeFase.start();
		criaMenu();
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
	public void adicionaFase(int numFase,boolean usarSave) {
		encerraMenu();
		if (numFase==1) {
			FaseAtual = new Fase1(usarSave);
		}
		else if (numFase==2) {
			FaseAtual = new Fase2(usarSave);
		}
		else if (numFase==3) {
			FaseAtual = new Fase3(usarSave);
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
			pedeUsername();
			excluiPanel();
			boolean usarSave = AchaSave.executar(1,username1);
			adicionaFase(1,usarSave);
			
		}
		if(e.getSource() == bt2) {
			bt2.removeMouseListener(this);
			encerraMenu();
			pedeUsername();
			excluiPanel();
			boolean usarSave = AchaSave.executar(2,username1);
			adicionaFase(2,usarSave);
		}
		if(e.getSource() == bt3) {
			bt3.removeMouseListener(this);
			encerraMenu();
			pedeUsername();
			excluiPanel();
			boolean usarSave = AchaSave.executar(3,username1);
			adicionaFase(3,usarSave);
		}
		if(e.getSource() == botaoInsereUsername) {
			username1 = field1.getText();
			username2 = field2.getText();
		}
	}
	public void pedeUsername() {
		
		botaoInsereUsername = new JButton();
		
        labelU1 = new JLabel();
        labelU1.setText("Nome do Usuario");
        field1 = new JTextField(50);
        field2 = new JTextField(50);
        
        //INICIALIZA JANELA
        panelPedeUsuario = new JPanel();
        panelPedeUsuario.setLayout(null);
        
        //COLOCA OS OBJETOS NA JANELA
        panelPedeUsuario.add(botaoInsereUsername);
        panelPedeUsuario.add(labelU1);
        panelPedeUsuario.add(field1);
        panelPedeUsuario.add(field2);
        
        //COLOCA OS OBJETOS EM UMA ORDEM ADEQUADA
        labelU1.setBounds(200,650,200,50);
        botaoInsereUsername.setBounds(200,200,250,250);
        field1.setBounds(200,500,100,100);
        field2.setBounds(200, 700, 100, 100);
	}
	public void excluiPanel() {
		remove(panelPedeUsuario);
		panelPedeUsuario.removeAll();
		panelPedeUsuario.validate();
		panelPedeUsuario.repaint();
		panelPedeUsuario = null;
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
