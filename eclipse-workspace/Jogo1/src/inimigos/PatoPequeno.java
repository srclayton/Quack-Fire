package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PatoPequeno extends Inimigo{
	
	public PatoPequeno(int x, int y,int pontos) {
		super(x, y,"res\\SmallDuck.png",10,50,50,3,3,pontos);
	}
}
