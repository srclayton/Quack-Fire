
package inimigos;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Inimigo {
	protected int x, y; //coordenadas 
	protected Image img;
	protected String imgUrl;
	protected int altura, largura;
	protected int velocidade;
	protected int movCima;
	protected int movBaixo;
	protected int pontos;
	/*======================public Inimigo====================
	 * Construtora de inimigos, recebe todos os dados 
	 * nescessarios para spawnar um inimigo, img, velocidade, 
	 * e suas coordenadas.
	 * ========================================================
	 */
	public Inimigo(int x, int y,String imgUrl,int vel,int altura, int largura,int yN,int yP,int pontos) {
		this.x=x;
		this.y=y;		
		this.imgUrl = imgUrl; 
		this.altura = altura;
		this.largura = largura;
		this.velocidade = vel;
		this.movBaixo = yN;
		this.movCima= yP;
		this.pontos = pontos;
	}
	/*
	 * ===============public void load()===========================
	 * Metodo responsavel para ler o caminho da imagem e setar a mesma.
	 * Alem de atribuir um tamanho especifico para o inimigo. 
	 * ============================================================
	 */
	public void load() {
		ImageIcon src = new ImageIcon(imgUrl);
		src.setImage(src.getImage().getScaledInstance( largura , altura, Image.SCALE_SMOOTH));
		img = src.getImage();
	}
	/*===============public void update=============================
	 * Atraves de um numero randomico é decidido se o inimigo ira se mover
	 * para cima ou para baixo além de andar da direita para esquerda
	 * na velocidade passada na construtora.
	 * =============================================================
	 */
	public void update() {
		Random dice = new Random();
		int impar_par = 0;
		impar_par = dice.nextInt();
		x -= velocidade;
		if(impar_par % 2 == 0)
			y += dice.nextInt(movCima)+0;
		else
			y -= dice.nextInt(movBaixo)+0;
		impar_par++;
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
	public  int getAltura() {
		return altura;
	}
	public int getPontos() {
		return pontos;
	}
	public abstract void setImg(String srcImg);



}
