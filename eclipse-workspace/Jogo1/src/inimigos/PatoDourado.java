package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PatoDourado extends Pato {
	

	public PatoDourado(int x, int y) {
		super(x, y);
	}
	@Override
	public void load() {
		ImageIcon src = new ImageIcon("res\\GoldDuck.png");
		src.setImage(src.getImage().getScaledInstance( 85 , 85, Image.SCALE_SMOOTH));
		img = src.getImage();
		altura = img.getHeight(null);
		largura = img.getWidth(null);
	}
	@Override
	public void update() {
		Random dice = new Random();
		impar_par = dice.nextInt();
		x -= 15;
		if(impar_par % 2 == 0)
			y += dice.nextInt(3)+0;
		else
			y -= dice.nextInt(4)+0;
		impar_par++;
	}
	
	public static int getQntd() {
		return qntd ;
	}

}