package inimigos;


public class PatoFedido extends Inimigo {
	protected int id=2;
	public PatoFedido(int x, int y, int pontos) {
		super(x, y,"res\\StinkyDuck.png",1,85,85,3,3,2,pontos);
	}
}
