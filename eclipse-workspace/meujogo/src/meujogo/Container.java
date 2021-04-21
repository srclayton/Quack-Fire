package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

public class Container extends JFrame{

	public Container() {
		add(new Fase());
		setTitle("Meu jogo");
		setSize(1336,722);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	public static void main (String []args) {
		new Container();
	}
}
