package player;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Jogador {
	protected int x, y; //coordenadas 
	protected int dx, dy; //coordenada
	protected Image img;
	protected int altura, largura;
	protected int teclas;
	protected String srcImg;
	public Jogador(int teclas, String srcImg, int x, int y) {
		this.teclas = teclas;
		this.srcImg = srcImg;
		this.x = x; 
		this.y = y;
	}
	
	public void load() {
		ImageIcon src = new ImageIcon(srcImg); // SELECIONA A IMAGEM DO PLAYER
		src.setImage(src.getImage().getScaledInstance(50, 50, 100));
		img = src.getImage(); // SETA A IMG CM COM A IMG SELECIONADA
		altura = img.getHeight(null); // RECEBO O TAMANHO DA ALTURA 
		largura = img.getWidth(null); // RECEBO O TAMANHO DA LARGURA
		}
	
	public void update() {
		x += dx; // MOVIMENTO  O PERSONAGEM DE ACORDO COM AS TECLAS
		y += dy;	
	}
	
	public void keyPressed(KeyEvent tecla) {
		if(teclas == 1) {
			int cod = tecla.getKeyCode();
			// MOVIMENTO 3 PIXEIS CASO A TECLA ENQUANTO A MESMA SEJA PRESSIONADA;
			if(cod == KeyEvent.VK_W) {
				dy = -3;	
			}
			if(cod == KeyEvent.VK_S) {
				dy = 3;
			}
			if(cod == KeyEvent.VK_A) {
				dx = -3;
			}
			if(cod == KeyEvent.VK_D) {
				dx = 3;
			}
		}else {
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
	}
	public void KeyRelease(KeyEvent tecla) {
		if(teclas == 1) {
			int cod = tecla.getKeyCode();
			
			if(cod == KeyEvent.VK_W) {
				dy = 0;
			}
			if(cod == KeyEvent.VK_S) {
				dy = 0;
			}
			if(cod == KeyEvent.VK_A) {
				dx = 0;
			}
			if(cod == KeyEvent.VK_D) {
				dx = 0;
			}
		}else {
			int cod = tecla.getKeyCode();
			// MOVIMENTO 3 PIXEIS CASO A TECLA ENQUANTO A MESMA SEJA PRESSIONADA;
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