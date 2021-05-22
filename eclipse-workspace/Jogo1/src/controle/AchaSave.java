package controle;

import java.io.File;

public class AchaSave {
	public static boolean  executar(int numFase,String username) { 
		try {
			File file=new File("saves/"+username+"JogadorDAO"+numFase+".json");
			return file.exists();}
		catch(Exception e) {
			return false;
		}
	}
}
