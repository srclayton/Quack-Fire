package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import player.Jogador;

import modelo.*;

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
		try {
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
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	
	
	//retorna dados
//	listaRankingDAOJSON(){
//		
//	}
	
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
	public ArrayList<DetalhesJogadorRanking> listaRankingDAOTXT(int numFase){
		ArrayList<DetalhesJogadorRanking> arrayDet = new ArrayList<DetalhesJogadorRanking>();
		try  
		{  
			File file=new File("saves/RankingDAOFase"+numFase+".txt");    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
			String line;  
		
			while((line=br.readLine())!=null)  
			{
				DetalhesJogadorRanking detalhesJ;
				String[] listaAtributos =   line.split(";",5);
				detalhesJ = new DetalhesJogadorRanking(listaAtributos[0],Integer.valueOf(listaAtributos[1]));
				arrayDet.add(detalhesJ);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		return arrayDet;
	}
	
	public void ordenaRankingDAOTXT(int numFase){
		
		ArrayList<DetalhesJogadorRanking> arrayDet= listaRankingDAOTXT(numFase);
		ComparadoraDetalhesJogador comparadora = new ComparadoraDetalhesJogador();
    	
    	DetalhesJogadorRanking d1 = new DetalhesJogadorRanking("Roberto",500);
    	DetalhesJogadorRanking d2 = new DetalhesJogadorRanking("Roberto2",300);
    	DetalhesJogadorRanking d3 = new DetalhesJogadorRanking("Roberto3",800);
		arrayDet.add(d1);
		arrayDet.add(d2);
		arrayDet.add(d3);
    	
		Collections.sort(arrayDet,comparadora);
		System.out.println(arrayDet.get(0)+","+arrayDet.get(1)+" "+arrayDet.get(2));
//        writer.close();
//        System.out.println("Successfully wrote to the file.");
       
//    catch (IOException e) {
//        System.out.println("An error occurred.");
//        e.printStackTrace();
//      }
//	}
//	
	
	}
}
