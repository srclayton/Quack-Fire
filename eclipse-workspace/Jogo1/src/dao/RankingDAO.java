package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import player.Jogador;

public class RankingDAO {
	public void insereRankingDAOJSON(int pontuacaoP1,
			int pontuacaoP2,
			int numFase,
			String username1,
			String username2){
		
		JSONObject detalhesJogador1 = new JSONObject();
		JSONObject detalhesJogador2 = new JSONObject();
		JSONObject jogador1Documentado = new JSONObject(); 
		JSONObject jogador2Documentado = new JSONObject(); 
		detalhesJogador1.put("NomeDoJogador", username1);
		detalhesJogador2.put("NomeDoJogador", username2);
		detalhesJogador1.put("pontuacao", pontuacaoP1);
		detalhesJogador2.put("pontuacao", pontuacaoP2);
		
		jogador1Documentado.put("Detalhes",detalhesJogador1);
		jogador2Documentado.put("Detalhes",detalhesJogador2);
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/RankingDAOFase"+numFase+".json", true));
        	writer.append(jogador1Documentado.toString());
        	writer.append(",\n");
        	writer.append(jogador2Documentado.toString());
        	writer.append(",\n");
        	
            writer.close();
            System.out.println("Successfully wrote to the file.");
          } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}
		
	
	
	//retorna dados
	listaRankingDAOJSON(){
		
	}
	
	public void insereRankingDAOTXT(int pontuacaoP1,
			int pontuacaoP2,
			int numFase,
			String username1,
			String username2){
		String detalhesJogador1;
		String detalhesJogador2; 
		detalhesJogador1 = username1+";"+pontuacaoP1+"\n";
		detalhesJogador2 = username2+";"+pontuacaoP2+"\n";
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/RankingDAOFase"+numFase+".txt", true));
            writer.append(detalhesJogador1);
            writer.append(detalhesJogador2);
            writer.close();
          } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}
	
	//retorna dados
	listaRankingDAOTXT(){
		
	}
	
	ordenaRankingDAOJson(){
		
		ArrayList<algumacoisa> = listaRan
		int pontuacaoP2,
		int numFase,
		String username1,
		String username2){
	
	JSONObject detalhesJogador1 = new JSONObject();
	JSONObject detalhesJogador2 = new JSONObject();
	JSONObject jogador1Documentado = new JSONObject(); 
	JSONObject jogador2Documentado = new JSONObject(); 
	detalhesJogador1.put("NomeDoJogador", username1);
	detalhesJogador2.put("NomeDoJogador", username2);
	detalhesJogador1.put("pontuacao", pontuacaoP1);
	detalhesJogador2.put("pontuacao", pontuacaoP2);
	
	jogador1Documentado.put("Detalhes",detalhesJogador1);
	jogador2Documentado.put("Detalhes",detalhesJogador2);
    try {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/"+username+"JogadorDAO"+numFase+".txt", true));
      
    	try  
		{  
			File file=new File("saves/RankingFase"+numFase+".json");    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
			String line;  
			int i=0;
			while((line=br.readLine())!=null)  
			{  
				JSONObject infoJLinha = new JSONObject(line);
				JSONObject jogadorJSON = (JSONObject) infoJLinha.get("Detalhes");
				if(pontuacaoP1>(int) jogadorJSON.get("pontuacao")) {
					
			}  
			fr.close();  
			}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}
    	
    	
    	
    	
    	
        writer.close();
        System.out.println("Successfully wrote to the file.");
      } 
    catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
	}
	
	
	
}
