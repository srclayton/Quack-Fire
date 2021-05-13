package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoDourado;

public class SpawnerPatoDourado extends TimerTask {
	@Override
	public void run() {
		LinkedList<Inimigo>lista=Fase.getListaInimigos();
		PatoDourado p = new PatoDourado(1000,500,500);
		p.load();
		lista.add(p);
	}
}
