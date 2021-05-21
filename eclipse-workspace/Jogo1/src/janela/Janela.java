package janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import fase.*;
import principal.Menu;

public class Janela extends JFrame implements ActionListener{
	
	private  int numOperacao = 3; // quantidade de opera��o da janela, maximizar, fechar e minimizar;
	private static int alturaJanela = 728; // seto a altura e largura da janela;
	private static int larguraJanela = 1024;
	private Timer tempoDeFase;
	private static JPanel FaseAtual;
	private Menu menu;
	public Janela() {
		
//		FaseAtual= (JPanel) add(new Fase1()); // adiciona a fase para rolar;
		//add(new Fase2()); // adiciona a fase para rolar;
		
		menu = (Menu)add(new Menu());
		setTitle("Quack fire!"); // TITULO DA JANELA
		setDefaultCloseOperation(numOperacao); // OPERAÇÕES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
		setSize(larguraJanela,alturaJanela); // DEFINE O TAMANHO DA JANELA;
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		tempoDeFase = new Timer(5000,this);
		tempoDeFase.start();
		
	}
	public void actionPerformed(ActionEvent e) {
	System.out.println("BBBBBBB");
    //remove(FaseAtual);
//	
//	FaseAtual.removeAll();
//	FaseAtual.validate();
//	FaseAtual.repaint();
//	FaseAtual= null;
	FaseAtual= (JPanel) add(new Fase1());
	setTitle("Quack fire!"); // TITULO DA JANELA
	setDefaultCloseOperation(numOperacao); // OPERAÇÕES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
	setSize(larguraJanela,alturaJanela); // DEFINE O TAMANHO DA JANELA;
	setLocationRelativeTo(null);
	setResizable(false);
	setVisible(true);
}

	public static int getAlturaJanela() {
		return alturaJanela;
	}

	public static int getLarguraJanela() {
		return larguraJanela;
	}
	public void encerraFase() {
		remove(FaseAtual);
		
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
