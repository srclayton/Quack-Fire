package controle;

import java.io.File;
//*******************************************************************
//		Ele verifica se existe um save do jogador em questão
//		caso encontre e o mesmo não esteja vazio retornamos true,
//		 e false se não o encontrar ou o arquivo for vazio.
//*******************************************************************
public class AchaSaveJSON {
	public static boolean  executar(int numFase,String username) { 
		try {
			File file=new File("saves/"+username+"JogadorDAO"+numFase+".json");
		    //Ele retorna true ou false dependendo da existencia ou se está vazio
			return file.exists()&&file.length()>1;}
		catch(Exception e) {
			return false;
		}
	}
}
