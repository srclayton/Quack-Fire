package player;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	protected int x, y; //coordenadas 
	protected int dx, dy; //coordenada
	protected Image img;
	protected int altura, largura;

	public Player(int x, int y) {
		this.x = x; //coordenada de spawn;
		this.y = y;//coordenada de spawn;
	}

	public void load() {
		ImageIcon src = new ImageIcon("res\\mira.png"); // SELECIONA A IMAGEM DO PLAYER
		src.setImage(src.getImage().getScaledInstance(50, 50, 100));
		img = src.getImage(); // SETA A IMG CM COM A IMG SELECIONADA
		altura = img.getHeight(null); // RECEBO O TAMANHO DA ALTURA 
		largura = img.getWidth(null); // RECEBO O TAMANHO DA LARGURA
;	}
	
	public void update() {
		x += dx; // MOVIMENTO  O PERSONAGEM DE ACORDO COM AS TECLAS
		y += dy;	
	}
	
	public void keyPressed(KeyEvent tecla) {
		int cod = tecla.getKeyCode();
		// MOVIMENTO 3 PIXEIS CASO A TECLA ENQUANTO A MESMA SEJA PRESSIONADA;
		if(cod == KeyEvent.VK_UP) {
			dy = -3;	
		}
		if(cod == KeyEvent.VK_DOWN) {
			dy = 3;
		}
		if(cod == KeyEvent.VK_LEFT) {
			dx = -3;
		}
		if(cod == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}
	// PARA DE MOVIMENTAR QUANDO ESTA SOLTA;
	public void KeyRelease(KeyEvent tecla) {
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_UP) {
			dy = 0;
		}
		if(cod == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if(cod == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if(cod == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}
}
