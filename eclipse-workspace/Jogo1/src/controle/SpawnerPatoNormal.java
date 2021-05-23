package controle;

import java.util.LinkedList;
import java.util.TimerTask;
import java.util.Random;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoNormal;
import janela.Janela;

public class SpawnerPatoNormal extends TimerTask {
	@Override
	public void run() {
		LinkedList<Inimigo>lista=Fase.getListaInimigos();
		PatoNormal p = new PatoNormal(Janela.getLarguraJanela()+100,(int)(Math.random()*((Janela.getAlturaJanela()-200)+1)),15*Fase.getConstPositiva());
		p.load();
		lista.add(p);
	}
}
