package inimigos;
import janela.Janela;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PatoDourado extends Inimigo {
	
	private int indoPraCima = 1;

	public PatoDourado(int x, int y, int pontos) {
		super(x, y,"res/GoldDuck.png",12,85,85,5,5,pontos);
	}

	@Override 
	public void update() {

		x -= velocidade;
		if(indoPraCima==1) {
				y -= movCima;
				if (y<=0)
					indoPraCima=0;
		}
		else {
				y += movBaixo;
				if (y>=Janela.getAlturaJanela()-altura-200)
					indoPraCima=1;}
	}
	
}
