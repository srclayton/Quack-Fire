package modelo;

public class DetalhesJogadorRanking implements Comparable<DetalhesJogadorRanking>{
	private String username;
	private int pontuacao;
	
	public DetalhesJogadorRanking(String u, int p){
		username = u;
		pontuacao = p;
	}
	public String toString() {
		return this.username;
	}
//	*******************************************
//	comparamos entre ambos os usuarios quem 
//	possui maior pontua��o, caso o player2
//	possua uma pontua��o maior
//	retornamos 1; caso o player2 possui pontua��o
//	inferior ao player1, retornamos -1
//	e caso possuam a mesma pontua��o retornamos 0
//	*************************************************
	@Override
	public int compareTo(DetalhesJogadorRanking detJ) {
		if (detJ.getPontuacao() > this.getPontuacao() ){
			return 1;
		}
		else if (detJ.getPontuacao() < this.getPontuacao()) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	public String getUsername() {
		return this.username;
	}
	public int getPontuacao() {
		return this.pontuacao;
	}
}
