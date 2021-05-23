package fase;

import controle.ExcluiInimigos;
import controle.SpawnerPatoDourado;
import controle.SpawnerPatoFedido;
import controle.SpawnerPatoNormal;
import controle.SpawnerPatoPequeno;
import inimigos.BallonBoy;
import janela.Janela;

public class Fase3 extends Fase{
	public Fase3(boolean usarSave, String formato){
		super(10000,1000,1800,500,1000,"res\\City2.png",3,5,usarSave, formato,-40,7);
		
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
			BallonBoy bbb = new BallonBoy(Janela.getLarguraJanela()+350,Janela.getAlturaJanela()-250,-50*Fase.getConstNegativa());
			bbb.load();
			ListaInimigos.add(bbb);
		}
		timerExcluidor = new java.util.Timer();
		timerExcluidor.scheduleAtFixedRate(new ExcluiInimigos(), 500, tempoExcluiInimigo);}
		
}
