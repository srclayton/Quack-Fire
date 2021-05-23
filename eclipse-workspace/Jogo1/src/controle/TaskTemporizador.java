package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoDourado;
import janela.Janela;

public class TaskTemporizador extends TimerTask {
	@Override
	public void run() {
		Fase.diminuiSegundo();
	}
}
