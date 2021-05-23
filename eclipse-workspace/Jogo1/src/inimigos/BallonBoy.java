package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import janela.Janela;

public class BallonBoy extends Inimigo{
	private boolean indoParaEsquerda = true;
	public BallonBoy(int x, int y, int pontos) {
		super(x, y,"res\\BallonBoy2.png",2,150,150,2,2,5,pontos);
	}
	
	/*===============public void update=============================
	 *utilizando o update o nosso inimigo BallonBoy se locomove
	 *na tela do usuario da esquerda para a direita,
	 *sempre dentro dos limites da tela.
	 * =============================================================
	 */
	@Override
	public void update() {
		Random dice = new Random();
		int impar_par = 0;
		impar_par = dice.nextInt();
		if(indoParaEsquerda == true) {
			x -= velocidade;
			if(x<=0) {
				indoParaEsquerda = false;
				setImg("res\\BallonBoy.png");
			}
				
		}else {
			x += velocidade;
			if(x>=Janela.getLarguraJanela()-largura) {
				indoParaEsquerda = true;
				setImg("res\\BallonBoy2.png");
			}		
		}
		if(impar_par % 2 == 0)
			y += dice.nextInt(movCima)+0;
		else
			y -= dice.nextInt(movBaixo)+0;			
	}
//	*******************************************
//	setImg é reponsavel de fazer a troca da img
//	do BallonBoy, para que quando ele se mova 
//	para a esquerda a imagem esteja para a esquerda
//	e quando ele retornar para a direita
//	a imagem também vire para a direita
//	********************************************
	@Override
	public void setImg(String srcImg) {
		ImageIcon src = new ImageIcon(srcImg);
		src.setImage(src.getImage().getScaledInstance( largura , altura, Image.SCALE_SMOOTH));
		img = src.getImage();
	}
}
