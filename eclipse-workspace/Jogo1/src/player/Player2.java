package player;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player2 extends Player {
	public Player2(int x, int y) {
		super(x, y);
	}

	@Override
	public void load() {
		ImageIcon src = new ImageIcon("res\\mira2.png");
		src.setImage(src.getImage().getScaledInstance(50, 50, 100));
		img = src.getImage();
		altura = img.getHeight(null);
		largura = img.getWidth(null);
	}
	
	@Override
	public void keyPressed(KeyEvent tecla) {
		int cod = tecla.getKeyCode();
		
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
	}
	@Override
	public void KeyRelease(KeyEvent tecla) {
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
	}
}
