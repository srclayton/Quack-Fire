package controle;

import java.io.File;

public class AchaSave {
	public static boolean  executar(int num,String username) { 
		try {
			File file=new File("saves/"+username+"JogadorDAO.json");
			return file.exists();}
		catch(Exception e) {
			return false;
		}
	}
}
