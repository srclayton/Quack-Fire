package modelo;

import java.util.Comparator;
//**************************************
//metodo utilizado para a contabiliza��o 
//e compara��o dos jogadores para
//o ranking
//**************************************

public class ComparadoraDetalhesJogador implements Comparator<DetalhesJogadorRanking> {
	public int compare(DetalhesJogadorRanking detJ1,DetalhesJogadorRanking detJ2) {
		return detJ1.compareTo(detJ2);
	}
}
