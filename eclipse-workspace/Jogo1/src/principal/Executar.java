package principal;
import janela.Janela;
import controle.AchaSaveJSON;
import dao.InimigoDao;
import dao.RankingDAO;
import inimigos.PatoDourado;
import modelo.*;
public class Executar {
	public static void main(String [] args) {
//		InimigoDao p = new InimigoDao();
//		p.inserir(new PatoDourado(15,20,30),1,"Royka");
//		p.inserir(new PatoDourado(30,40,50),2,"Royka");
		//new Janela(); // crio um objeto janela;
		RankingDAO rd = new RankingDAO();
		rd.ordenaRankingDAOTXT(1);
	}
}
