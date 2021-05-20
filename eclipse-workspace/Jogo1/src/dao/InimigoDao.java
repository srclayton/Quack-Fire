package dao;

import java.io.*;
import java.util.LinkedList;

import org.json.*;

import fase.Fase;
import inimigos.*;

public class InimigoDao{
	
	public void inserir(Inimigo i,int index,String username) {
		
		JSONObject detalhesInimigo = new JSONObject();
		JSONObject patoDocumentado = new JSONObject(); 
        try {
        	
        	detalhesInimigo.put("id", i.getID());
        	detalhesInimigo.put("posX", i.getX());
        	detalhesInimigo.put("posY", i.getY());
        	detalhesInimigo.put("pontos", i.getPontos());			
			patoDocumentado.put("Inimigo"+index, detalhesInimigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(username+"inimigoDAO.json", true));
            writer.append(patoDocumentado.toString());
            writer.append(",\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
   }
	public void Construtora(String username) {
		LinkedList<Inimigo>listaInimigos=Fase.getListaInimigos();
		try  
		{  
		File file=new File(username+"inimigoDAO.json");    //creates a new file instance  
		FileReader fr=new FileReader(file);   //reads the file  
		BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
		String line;  
		
		int i =0;
		while((line=br.readLine())!=null)  
		{  i++;
			Inimigo inimigo;
			try {
				JSONObject obj = new JSONObject(line);
				JSONObject inimigoJSON = (JSONObject) obj.get("Inimigo"+i);
				if((int)inimigoJSON.get("id")==1){
					inimigo = new PatoDourado((int)inimigoJSON.get("posX"),(int)inimigoJSON.get("posY"),(int)inimigoJSON.get("pontos"));
					inimigo.load();
					listaInimigos.add(inimigo);
				}
				else if((int)inimigoJSON.get("id")==2){
					inimigo = new PatoFedido((int)inimigoJSON.get("posX"),(int)inimigoJSON.get("posY"),(int)inimigoJSON.get("pontos"));
					inimigo.load();
					listaInimigos.add(inimigo);
				}
				else if((int)inimigoJSON.get("id")==3){
					inimigo = new PatoNormal((int)inimigoJSON.get("posX"),(int)inimigoJSON.get("posY"),(int)inimigoJSON.get("pontos"));
					inimigo.load();
					listaInimigos.add(inimigo);
				}
				else if((int)inimigoJSON.get("id")==4){
					inimigo = new PatoPequeno((int)inimigoJSON.get("posX"),(int)inimigoJSON.get("posY"),(int)inimigoJSON.get("pontos"));
					inimigo.load();
					listaInimigos.add(inimigo);
				}
				else if((int)inimigoJSON.get("id")==5){
					inimigo = new BallonBoy((int)inimigoJSON.get("posX"),(int)inimigoJSON.get("posY"),(int)inimigoJSON.get("pontos"));
					inimigo.load();
					listaInimigos.add(inimigo);
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}       
		}  
		fr.close();  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		}  
	

	public void removerTodos() {
		
	}
}
