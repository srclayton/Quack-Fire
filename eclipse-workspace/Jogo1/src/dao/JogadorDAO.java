package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import fase.Fase;
import inimigos.Inimigo;
import inimigos.PatoDourado;
import inimigos.PatoFedido;
import inimigos.PatoNormal;
import inimigos.PatoPequeno;
import player.Jogador;

public class JogadorDAO {
		
		public void inserir(Jogador j1,Jogador j2,String username) {
			
			JSONObject detalhesJogador1 = new JSONObject();
			JSONObject jogador1Documentado = new JSONObject(); 
	        try {
	        	
	        	detalhesJogador1.put("posX", j1.getX());
	        	detalhesJogador1.put("posY", j1.getY());
	        	detalhesJogador1.put("pontuacao", j1.getPontuacao());
				
	        	jogador1Documentado.put("Jogador1", detalhesJogador1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        JSONObject detalhesJogador2 = new JSONObject();
			JSONObject jogador2Documentado = new JSONObject(); 
	        try {
	        	
	        	detalhesJogador2.put("posX", j2.getX());
	        	detalhesJogador2.put("posY", j2.getY());
	        	detalhesJogador2.put("pontuacao", j2.getPontuacao());
				
	        	jogador2Documentado.put("Jogador2", detalhesJogador2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try {
	        	BufferedWriter writer = new BufferedWriter(new FileWriter(username+"JogadorDAO.json", true));
	            writer.append(jogador1Documentado.toString());
	            writer.append(",\n");
	            writer.append(jogador2Documentado.toString());
	            writer.close();
	            System.out.println("Successfully wrote to the file.");
	          } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
	   }
		public Jogador Construtora(String username,int index) {
			Jogador j = null;
			try  
			{  
				File file=new File(username+"JogadorDAO.json");    //creates a new file instance  
				FileReader fr=new FileReader(file);   //reads the file  
				BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
				String line;  
				int i=0;
				while((line=br.readLine())!=null)  
				{  
					i++;
					if (i== index)
					try {
						JSONObject obj = new JSONObject(line);
						JSONObject jogadorJSON = (JSONObject) obj.get("Jogador"+index);
						j = new Jogador(index, "res\\mira"+index+".png", (int)jogadorJSON.get("posX"),(int) jogadorJSON.get("posY"));
						
					}
					catch (JSONException e) {
						e.printStackTrace();
					}       
				}  
				fr.close();  
				}  
			catch(IOException e)  
			{  
				e.printStackTrace();  
			}
			return j;
			}  
}
