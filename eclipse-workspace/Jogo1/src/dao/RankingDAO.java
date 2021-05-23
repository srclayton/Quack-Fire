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
	public static void insereRankingDAOJSON(int pontuacaoP1,
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
			detalhesJogador1.put("Pontuacao", pontuacaoP1);
			detalhesJogador2.put("Pontuacao", pontuacaoP2);
			
			jogador1Documentado.put("Detalhes",detalhesJogador1);
			jogador2Documentado.put("Detalhes",detalhesJogador2);
	        try {
	        	BufferedWriter writer = new BufferedWriter(new FileWriter("ranking/RankingDAOFase"+numFase+".json", true));
	        	writer.append(jogador1Documentado.toString());
	        	writer.append(",\n");
	        	writer.append(jogador2Documentado.toString());
	        	writer.append(",\n");
	        	
	            writer.close();
	          } 
	        catch (IOException e) {
	            e.printStackTrace();
	          }
	        }
		catch(Exception e) {
			e.printStackTrace();
		}
		ordenaRankingDAOJSON(numFase);
	}
		
	
	
	public static void insereRankingDAOTXT(int pontuacaoP1,
			int pontuacaoP2,
			int numFase,
			String username1,
			String username2){
		String detalhesJogador1;
		String detalhesJogador2; 
		detalhesJogador1 = username1+";"+pontuacaoP1+"\n";
		detalhesJogador2 = username2+";"+pontuacaoP2+"\n";
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter("ranking/RankingDAOFase"+numFase+".txt", true));
            writer.append(detalhesJogador1);
            writer.append(detalhesJogador2);
            writer.close();
          } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        ordenaRankingDAOTXT(numFase);
	}
	

	public static ArrayList<DetalhesJogadorRanking> listaRankingDAOJSON(int numFase){
		
		ArrayList<DetalhesJogadorRanking> arrayDet = new ArrayList<DetalhesJogadorRanking>();
		try  
		{  
			File file=new File("ranking/RankingDAOFase"+numFase+".json");    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
			String line;  
		
			while((line=br.readLine())!=null)  
			{
				DetalhesJogadorRanking detalhesJ;
				JSONObject obj = new JSONObject(line);
				JSONObject jogadorJSON = (JSONObject) obj.get("Detalhes");
				detalhesJ = new DetalhesJogadorRanking((String)jogadorJSON.get("NomeDoJogador"),(int)jogadorJSON.get("Pontuacao"));
				arrayDet.add(detalhesJ);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		return arrayDet;
		
	}
	
	//retorna dados
	public static ArrayList<DetalhesJogadorRanking> listaRankingDAOTXT(int numFase){
		ArrayList<DetalhesJogadorRanking> arrayDet = new ArrayList<DetalhesJogadorRanking>();
		try  
		{  
			File file=new File("ranking/RankingDAOFase"+numFase+".txt");    //creates a new file instance  
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
	
	public static void ordenaRankingDAOTXT(int numFase){
		
		ArrayList<DetalhesJogadorRanking> arrayDet= listaRankingDAOTXT(numFase);
		ComparadoraDetalhesJogador comparadora = new ComparadoraDetalhesJogador();
		Collections.sort(arrayDet,comparadora);
		try {
			FileWriter file = new FileWriter("ranking/RankingDAOFase"+numFase+".txt");
			file.write("");
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();}
		Iterator<DetalhesJogadorRanking> it = arrayDet.iterator();
		while (it.hasNext()) {
			try {
				DetalhesJogadorRanking detJ = it.next();
				String detalhesJogador = detJ.getUsername()+";"+detJ.getPontuacao()+"\n";
			
	        	BufferedWriter writer = new BufferedWriter(new FileWriter("ranking/RankingDAOFase"+numFase+".txt", true));
	            writer.append(detalhesJogador);
	            writer.close();
	          } 
	        catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
          }}
	
	
	}
	public static void ordenaRankingDAOJSON(int numFase){
		
		ArrayList<DetalhesJogadorRanking> arrayDet= listaRankingDAOJSON(numFase);
		ComparadoraDetalhesJogador comparadora = new ComparadoraDetalhesJogador();
		Collections.sort(arrayDet,comparadora);
		System.out.println(arrayDet.get(0).getPontuacao()+" "+arrayDet.get(1).getPontuacao());
		Iterator<DetalhesJogadorRanking> it = arrayDet.iterator();
		try {
			FileWriter file = new FileWriter("ranking/RankingDAOFase"+numFase+".json");
			file.write("");
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		while (it.hasNext()) {
			try {
				DetalhesJogadorRanking detJ = it.next();
				
				JSONObject detalhesJogador = new JSONObject();
				JSONObject jogadorDocumentado = new JSONObject(); 
				detalhesJogador.put("NomeDoJogador", detJ.getUsername());
				detalhesJogador.put("Pontuacao", detJ.getPontuacao());
				jogadorDocumentado.put("Detalhes",detalhesJogador);
				
				BufferedWriter writer = new BufferedWriter(new FileWriter("ranking/RankingDAOFase"+numFase+".json", true));
				writer.append(jogadorDocumentado.toString());
	            writer.append(",\n");
				writer.close();
			} 
			catch (IOException e) {
				System.out.println("An error ocscurred.");
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
		
	}
}
