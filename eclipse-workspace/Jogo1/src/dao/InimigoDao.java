package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.*;

import fase.Fase;
import inimigos.*;

public class InimigoDao{
	
	public void inserirJSON(Inimigo i,int index,String username,int numFase) {
		
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
        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/"+username+"inimigoDAO"+numFase+".json", true));
            writer.append(patoDocumentado.toString());
            writer.append(",\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
          } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
   }
	public void construtoraJSON(int numFase,String username) {
		LinkedList<Inimigo>listaInimigos=Fase.getListaInimigos();
		try  
		{  
			File file=new File("saves/"+username+"inimigoDAO"+numFase+".json");    //creates a new file instance  
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
	
	public void inserirTXT(Inimigo i,int index,String username,int numFase) {
		String detalhesInimigo;
        detalhesInimigo = index+";"+i.getX()+";"+i.getY()+";"+i.getPontos()+";"+i.getID()+"\n";
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/"+username+"inimigoDAO"+numFase+".txt", true));
            writer.append(detalhesInimigo);
            writer.close();
            System.out.println("Successfully wrote to the file.");
          } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}
	public void construtoraTXT(int numFase,String username) {
		LinkedList<Inimigo>listaInimigos=Fase.getListaInimigos();
		try  
		{  
			File file=new File("saves/"+username+"inimigoDAO"+numFase+".txt");    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			
			BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
			String line;  
			if(file.length()>1) 
				while((line=br.readLine())!=null)  
				{  
	
					Inimigo inimigo;
					try {
						
						String[] listaAtributos =   line.split(";",100);
	
						if (Integer.valueOf(listaAtributos[4])==1) {
							inimigo = new PatoDourado(Integer.valueOf(listaAtributos[1]),Integer.valueOf(listaAtributos[2]),Integer.valueOf(listaAtributos[3]));
							inimigo.load();
							listaInimigos.add(inimigo);
							
						}
						if (Integer.valueOf(listaAtributos[4])==2) {
							inimigo = new PatoFedido(Integer.valueOf(listaAtributos[1]),Integer.valueOf(listaAtributos[2]),Integer.valueOf(listaAtributos[3]));
							inimigo.load();
							listaInimigos.add(inimigo);
						}
						if (Integer.valueOf(listaAtributos[4])==3) {
							inimigo = new PatoNormal(Integer.valueOf(listaAtributos[1]),Integer.valueOf(listaAtributos[2]),Integer.valueOf(listaAtributos[3]));
							inimigo.load();
							listaInimigos.add(inimigo);
	
							System.out.println(listaAtributos[4]);
							
						}
						if (Integer.valueOf(listaAtributos[4])==4) {
							inimigo = new PatoPequeno(Integer.valueOf(listaAtributos[1]),Integer.valueOf(listaAtributos[2]),Integer.valueOf(listaAtributos[3]));
							inimigo.load();
							listaInimigos.add(inimigo);
						}
						if (Integer.valueOf(listaAtributos[4])==5) {
							inimigo = new BallonBoy(Integer.valueOf(listaAtributos[1]),Integer.valueOf(listaAtributos[2]),Integer.valueOf(listaAtributos[3]));
							inimigo.load();
							listaInimigos.add(inimigo);
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirSaveTXT(int numFase,String username) {
		try  
		{  
			FileWriter file=new FileWriter("saves/"+username+"inimigoDAO"+numFase+".txt"); 
			file.write("");
			file.close();
			File filee=new File("saves/"+username+"inimigoDAO"+numFase+".txt");
			System.out.println(filee.delete());
		}
		catch( Exception e) {
			
		}
}
		

	public void excluirSaveJSON(int numFase,String username) {
		try  
		{  
			FileWriter file=new FileWriter("saves/"+username+"inimigoDAO"+numFase+".json"); 
			file.write("");
			file.close();
			File filee=new File("saves/"+username+"inimigoDAO"+numFase+".json");
			filee.delete();
		}
		catch( Exception e) {
			
		}
}
}
