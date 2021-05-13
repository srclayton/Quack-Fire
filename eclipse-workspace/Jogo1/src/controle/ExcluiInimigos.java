package controle;

import java.util.Iterator;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;

public class ExcluiInimigos extends TimerTask{
	public void run() {
		Iterator<Inimigo> it= Fase.getListaInimigos().iterator();
		int passouDaTela=200; 
		while(it.hasNext()) {
			Inimigo inim = it.next();
			if (inim.getX()<passouDaTela) {
				it.remove();
			}
		}
	}
}
