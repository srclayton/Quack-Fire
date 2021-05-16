package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.BallonBoy;
import janela.Janela;

public class SpawnerBallonBoy extends TimerTask {
	@Override
	public void run() {
		LinkedList<Inimigo>lista=Fase.getListaInimigos();
		BallonBoy p = new BallonBoy(Janela.getLarguraJanela()+150,Janela.getAlturaJanela()-250,500);
		p.load();
		lista.add(p);
	}
}
