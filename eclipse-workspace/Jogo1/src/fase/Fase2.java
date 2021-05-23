package fase;

import controle.ExcluiInimigos;
import controle.SpawnerPatoDourado;
import controle.SpawnerPatoFedido;
import controle.SpawnerPatoNormal;
import controle.SpawnerPatoPequeno;
import inimigos.BallonBoy;
import janela.Janela;

public class Fase2 extends Fase{

	public Fase2(boolean usarSave, String formato){
		super(5500,2500,1000,1200,1000,"res\\City1.png",2,5,usarSave, formato,-15,8);
		
	}
	@Override
	public void inicializaInimigos(int tempoPatoDourado,int tempoPatoFedido,int tempoPatoNormal,int tempoPatoPequeno) {
		timerPatoDourado = new java.util.Timer();
		timerPatoDourado.scheduleAtFixedRate(new SpawnerPatoDourado(),30,tempoPatoDourado);
		timerPatoFedido = new java.util.Timer();
		timerPatoFedido.scheduleAtFixedRate(new SpawnerPatoFedido(),30,tempoPatoFedido);
		timerPatoNormal = new java.util.Timer();
		timerPatoNormal.scheduleAtFixedRate(new SpawnerPatoNormal(),30,tempoPatoNormal);
		timerPatoPequeno = new java.util.Timer();
		timerPatoPequeno.scheduleAtFixedRate(new SpawnerPatoPequeno(),30,tempoPatoPequeno);
		if (!usarSave) {
			BallonBoy bb = new BallonBoy(Janela.getLarguraJanela()+150,Janela.getAlturaJanela()-250,-50*Fase.getConstNegativa());
			bb.load();
			ListaInimigos.add(bb);
		}
		timerExcluidor = new java.util.Timer();
		timerExcluidor.scheduleAtFixedRate(new ExcluiInimigos(), 500, tempoExcluiInimigo);}
}
