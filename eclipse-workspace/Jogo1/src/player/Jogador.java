package player;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;

import inimigos.BallonBoy;
import inimigos.Inimigo;
import fase.Fase;

public class Jogador {
	protected int x, y; // coordenadas
	protected int dx, dy; // coordenada
	protected Image img;
	protected int altura, largura;
	protected int teclas;
	protected String srcImg;
	protected int vel = 5;
	protected int pontuacao=0;
/*=======================public Jogador=====================
 * Construtora da classe Jogador, onde é atribuido as teclas
 * (W, A, S e D) ou (seta esquerda, seta cima, seta direita 
 * e seta baixo) de acordo com cada player.
 * O caminho da imagem de cada jogador, e suas coordenadas. 
 * ==========================================================
 */
	public Jogador(int teclas, String srcImg, int x, int y) {
		this.teclas = teclas;
		this.srcImg = srcImg;
		this.x = x;
		this.y = y;
	}
/*
 * ===============public void load()===========================
 * Metodo responsavel para ler o caminho da imagem e setar a mesma.
 * Alem de atribuir um tamanho especifico para o jogador. 
 * ============================================================
 */
	public void load() {
		ImageIcon src = new ImageIcon(srcImg); // SELECIONA A IMAGEM DO PLAYER
		src.setImage(src.getImage().getScaledInstance(50, 50, 100));
		img = src.getImage(); // SETA A IMG CM COM A IMG SELECIONADA
		altura = img.getHeight(null); // RECEBO O TAMANHO DA ALTURA
		largura = img.getWidth(null); // RECEBO O TAMANHO DA LARGURA
	}
/*
 * ====================public void update====================
 * Metodo responsavel pela movimentação.
 * Atribui a coordenada atual, com a coordenada recebida
 * de acordo com a tecla pressionada pelo jogador.
 * ==========================================================
 */
	public void update() {
		x += dx; 
		y += dy;
	}
/*
 * ====================public void keyPressed=================
 * Metodo que verifica qual tecla esta sendo pressionada
 * e apartir dai altera a coordenada que o jogador ira se 
 * movimentar, enquanto a tecla for pressionada, a coordenada
 * sera alterada constantimente.
 * A movimentação se desenvolve através da coordenada '+' ou '-'
 * velocidade do jogador.
 * ===========================================================
 */
	public void keyPressed(KeyEvent tecla) {
		if (teclas == 1) {
			int cod = tecla.getKeyCode(); //RECEBE A TECLA QUE ESTA SENDO PRESSIONADA
			if (cod == KeyEvent.VK_W) {
				dy = -vel;
			}
			if (cod == KeyEvent.VK_S) {
				dy = vel;
			}
			if (cod == KeyEvent.VK_A) {
				dx = -vel;
			}
			if (cod == KeyEvent.VK_D) {
				dx = vel;
			}
			if (cod == KeyEvent.VK_SPACE) {
				pontuacao+=atirar();
			}
				
		} else {
			int cod = tecla.getKeyCode();
			if (cod == KeyEvent.VK_UP) {
				dy = -vel;
			}
			if (cod == KeyEvent.VK_DOWN) {
				dy = vel;
			}
			if (cod == KeyEvent.VK_LEFT) {
				dx = -vel;
			}
			if (cod == KeyEvent.VK_RIGHT) {
				dx = vel;
			}
			if (cod == KeyEvent.VK_ENTER) {
				pontuacao+=atirar();
			}
		}
	}
/*====================public void KeyRelease===================
 * Similar o metodo KeyPressed, esse metodo identifica quando 
 * a tecla deixa de ser pressionada, tornando o valor da coordenada 
 * de movimentação em 0, ou seja fazendo com que o jogador
 * fique imovel.
 * ============================================================
 */
	public void KeyRelease(KeyEvent tecla) {
		if (teclas == 1) {
			int cod = tecla.getKeyCode();

			if (cod == KeyEvent.VK_W) {
				dy = 0;
			}
			if (cod == KeyEvent.VK_S) {
				dy = 0;
			}
			if (cod == KeyEvent.VK_A) {
				dx = 0;
			}
			if (cod == KeyEvent.VK_D) {
				dx = 0;
			}
		} else {
			int cod = tecla.getKeyCode();
			// MOVIMENTO 3 PIXEIS CASO A TECLA ENQUANTO A MESMA SEJA PRESSIONADA;
			if (cod == KeyEvent.VK_UP) {
				dy = 0;
			}
			if (cod == KeyEvent.VK_DOWN) {
				dy = 0;
			}
			if (cod == KeyEvent.VK_LEFT) {
				dx = 0;
			}
			if (cod == KeyEvent.VK_RIGHT) {
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
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int p) {
		pontuacao = p;
	}

	public Image getImg() {
		return img;
	}
	public int atirar() {
		int pont = 0;
		Iterator<Inimigo> it = Fase.getListaInimigos().iterator();
		while(it.hasNext()) {
			Inimigo p = it.next();
			if(x+15>p.getX()&&x+15<p.getX()+p.getAltura()&&y+15>p.getY()&&y+15<p.getAltura()+p.getY()) {
				if(p.getClass() != BallonBoy.class) {
					it.remove();
					pont+=p.getPontos();
				}else
					pont-=p.getPontos();	
				System.out.println(p.getClass());
		}
			
	}
		
		return pont;
	
}
}
