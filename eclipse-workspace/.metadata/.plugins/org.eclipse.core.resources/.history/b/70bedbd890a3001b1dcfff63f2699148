package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PatoFedido extends Pato {

	public PatoFedido(int x, int y) {
		super(x, y);
	}
	@Override
	public void load() {
		ImageIcon src = new ImageIcon("res\\StinkyDuck.png");
		src.setImage(src.getImage().getScaledInstance( 85 , 85, Image.SCALE_SMOOTH));
		img = src.getImage();
		altura = img.getHeight(null);
		largura = img.getWidth(null);
	}
	
}
