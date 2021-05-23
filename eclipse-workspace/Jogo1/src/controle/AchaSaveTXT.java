package controle;

import java.io.File;

public class AchaSaveTXT {
	public static boolean  executar(int numFase,String username) { 
		try {
			File file=new File("saves/"+username+"JogadorDAO"+numFase+".txt");
		    //Ele retorna true ou false dependendo da existencia ou se está vazio
			return file.exists()&&file.length()>1;}
		catch(Exception e) {
			return false;
		}
	}
}
