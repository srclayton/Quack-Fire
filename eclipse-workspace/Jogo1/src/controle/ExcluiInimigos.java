package controle;

import java.util.Iterator;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
//*******************************************************************
//Através deste metodo, verificamos se nossos inimigos ainda
//estão dentro da tela do usuario, caso o mesmo não exista mais na tela
//fazemos sua exclusão.
//*******************************************************************
public class ExcluiInimigos extends TimerTask{
	public void run() {
		Iterator<Inimigo> it= Fase.getListaInimigos().iterator();
		int passouDaTela=-100; 
		while(it.hasNext()) {
			Inimigo inim = it.next();
			if (inim.getX()<passouDaTela) {
				it.remove();
			}
		}
	}
}
