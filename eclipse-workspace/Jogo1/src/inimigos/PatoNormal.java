package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;


public class PatoNormal extends Inimigo{
	public PatoNormal(int x, int y,int pontos) {
		super(x, y,"res\\Duck.png",1,85,85,3,3,3,pontos);	
	}

	@Override
	public void setImg(String srcImg) {
		// TODO Auto-generated method stub
		
	}
	

}

