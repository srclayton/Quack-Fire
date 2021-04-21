package janela;

import javax.swing.JFrame;
import fase.Fase;
public class Janela extends JFrame{
	
	private  int numOperacao = 3; // quantidade de opera��o da janela, maximizar, fechar e minimizar;
	private static int alturaJanela = 1024; // seto a altura e largura da janela;
	private static int larguraJanela = 728;
	
	public Janela() {
		add(new Fase()); // adiciona a fase para rolar;
		setTitle("Quack fire!"); // TITULO DA JANELA
		setDefaultCloseOperation(numOperacao); // OPERAÇÕES DA JANELA, MAXIMIZARM, MINIMIZAR E FECHA;
		setSize(alturaJanela, larguraJanela); // DEFINE O TAMANHO DA JANELA;
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
