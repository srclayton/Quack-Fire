package controle;

import java.util.Iterator;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
//*******************************************************************
//Atrav�s deste metodo, verificamos se nossos inimigos ainda
//est�o dentro da tela do usuario, caso o mesmo n�o exista mais na tela
//fazemos sua exclus�o.
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
