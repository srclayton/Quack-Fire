package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoFedido;
import janela.Janela;
//*******************************************************************
//Recebemos nossa lista de inimigos, e criamos um novo inimigo
//passando para sua construtora, sua coordenada, x e y, e sua
//pontuação
//*******************************************************************
public class SpawnerPatoFedido extends TimerTask {
	@Override
	public void run() {
		LinkedList<Inimigo>lista=Fase.getListaInimigos();
		PatoFedido p = new PatoFedido(Janela.getLarguraJanela()+100,(int)(Math.random()*((Janela.getAlturaJanela()-200)+1)),15*Fase.getConstNegativa());
		p.load();
		lista.add(p);
	}
}
