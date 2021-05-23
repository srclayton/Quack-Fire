package controle;

import java.io.File;

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
