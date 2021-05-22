package controle;

import java.io.File;

public class AchaSaveTXT {
	public static boolean  executar(int numFase,String username) { 
		try {
			File file=new File("saves/"+username+"JogadorDAO"+numFase+".txt");
			return file.exists();}
		catch(Exception e) {
			return false;
		}
	}
}
