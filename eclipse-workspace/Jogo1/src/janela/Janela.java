package janela;

import javax.swing.JFrame;
import fase.Fase;
import fase.Fase1;
public class Janela extends JFrame{
	
	private  int numOperacao = 3; // quantidade de operação da janela, maximizar, fechar e minimizar;
	private static int alturaJanela = 728; // seto a altura e largura da janela;
	private static int larguraJanela = 1024;
	
	public Janela() {
		add(new Fase1()); // adiciona a fase para rolar;
		setTitle("Quack fire!"); // TITULO DA JANELA
		setDefaultCloseOperation(numOperacao); // OPERAÃ‡Ã•ES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
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
	
}
