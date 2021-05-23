package controle;

import java.util.LinkedList;
import java.util.TimerTask;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoDourado;
import janela.Janela;
//*******************************************************************
//TaskTemporizador é responsavel por administrar a contagem de tempo
//da fase que é mostrada para o usuario na tela. 
//*******************************************************************
public class TaskTemporizador extends TimerTask {
	@Override
	public void run() {
		Fase.diminuiSegundo();
	}
}
