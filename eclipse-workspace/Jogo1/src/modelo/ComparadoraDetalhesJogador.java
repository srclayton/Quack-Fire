package modelo;

import java.util.Comparator;

public class ComparadoraDetalhesJogador implements Comparator<DetalhesJogadorRanking> {
	public int compare(DetalhesJogadorRanking detJ1,DetalhesJogadorRanking detJ2) {
		return detJ1.compareTo(detJ2);
	}
}
