package inimigos;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import player.Player;

public class Inimigo extends Player{
	private static int qntd = 50;
	public Inimigo(int x, int y) {
		super(x, y);
		
	}
	@Override
	public void load() {
		ImageIcon src = new ImageIcon("res\\player1.png");
		img = src.getImage();
		altura = img.getHeight(null);
		largura = img.getWidth(null);
	}
	@Override
	public void keyPressed(KeyEvent tecla) {
		
	}
	@Override
	public void KeyRelease(KeyEvent tecla) {
		
	}
	@Override
	public void update() {
		x -= 7;
	}
	public static int getQntd() {
		return qntd;
	}
}