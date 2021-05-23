package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.*;
import janela.Janela;

public class SpawnerPatoDourado extends TimerTask {
	@Override
	public void run() {
		LinkedList<Inimigo>lista=Fase.getListaInimigos();
		PatoDourado p = new PatoDourado(Janela.getLarguraJanela()+100,(int)(Math.random()*((Janela.getAlturaJanela()-200)+1)),50*Fase.getConstPositiva());
		p.load();
		lista.add(p);
	}
}


