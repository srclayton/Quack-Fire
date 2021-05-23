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
	@Override
	public int compareTo(DetalhesJogadorRanking detJ) {
		if (detJ.getPontuacao() > this.getPontuacao() ){
			return -1;
		}
		else if (detJ.getPontuacao() < this.getPontuacao()) {
			return 1;
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
