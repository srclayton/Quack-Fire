package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import inimigos.Inimigo;
import player.Jogador;

public class JogadorDAO {
//		*****************************************************
//		Assim como em inserir Inimigos, a logica para poder 
//		salvar o um jogador é a mesma, agrupamos suas informações
//		como suas coordenadas, pontuação e a fase em questão.
//		fazemos os mesmos processos para ambos os jogadores.
//		*****************************************************

		public void inserirJSON(int numFase,Jogador j1,Jogador j2,String username) {
			
			JSONObject detalhesJogador1 = new JSONObject();
			JSONObject jogador1Documentado = new JSONObject(); 
	        try {
	        	
	        	detalhesJogador1.put("posX", j1.getX());
	        	detalhesJogador1.put("posY", j1.getY());
	        	detalhesJogador1.put("pontuacao", j1.getPontuacao());
				
	        	jogador1Documentado.put("Jogador1", detalhesJogador1);
			} catch (Exception e) {
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
	        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/"+username+"JogadorDAO"+numFase+".json", true));
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
//		*****************************************************
//		Assim como em inserir Inimigos, a logica para poder 
//		salvar o um jogador é a mesma, agrupamos suas informações
//		como suas coordenadas, pontuação e a fase em questão.
//		fazemos os mesmos processos para ambos os jogadores.
//		*****************************************************
		public void inserirTXT(int numFase,Jogador j1,Jogador j2,String username) {
			String detalhesJogador1;
			String detalhesJogador2; 
			detalhesJogador1 = "1;"+j1.getX()+";"+j1.getY()+";"+j1.getPontuacao()+"\n";
			detalhesJogador2 = "2;"+j2.getX()+";"+j2.getY()+";"+j2.getPontuacao()+"\n";
	        try {
	        	BufferedWriter writer = new BufferedWriter(new FileWriter("saves/"+username+"JogadorDAO"+numFase+".txt", true));
	            writer.append(detalhesJogador1);
	            writer.append(detalhesJogador2);
	            writer.close();
	            System.out.println("Successfully wrote to the file.");
	          } 
	        catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
		}
//		*******************************************************
//		Fazemos a leitura de um possivel save, e a partir do mesmo
//		criamos o jogador, com sua coordenada armazenada e sua 
//		pontuação
//		*******************************************************
		public Jogador construtoraJSON(int numFase,String username,int index) {
			Jogador j = null;
			try  
			{  
				File file=new File("saves/"+username+"JogadorDAO"+numFase+".json");    //creates a new file instance  
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
						
						j.setPontuacao(jogadorJSON.getInt("pontuacao"));
						System.out.println(j.getPontuacao()+" "+jogadorJSON.getInt("pontuacao"));
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
//		*******************************************************
//		Fazemos a leitura de um possivel save, e a partir do mesmo
//		criamos o jogador, com sua coordenada armazenada e sua 
//		pontuação
//		*******************************************************
		public Jogador construtoraTXT(int numFase,String username,int index) {

			Jogador j = null;
			try  
			{  
				File file=new File("saves/"+username+"jogadorDAO"+numFase+".txt");    //creates a new file instance  
				FileReader fr=new FileReader(file);   //reads the file  
				BufferedReader br=new BufferedReader(fr);   //constructs a string buffer with no characters  
				String line;  
				if(file.length()>1) 
					while((line=br.readLine())!=null)  {
						try {
							String[] listaAtributos =   line.split(";",100);
							if(Integer.valueOf(listaAtributos[0])==index) {
								
								int x = Integer.valueOf(listaAtributos[1]);
								int y = Integer.valueOf(listaAtributos[2]);
								int pontuacao = Integer.valueOf(listaAtributos[3]);
								j = new Jogador(index,"res\\mira"+index+".png",x,y);
								j.load();
								j.setPontuacao(pontuacao);
								System.out.println("A PONTUACAO E:"+listaAtributos[3]);
								return j;
							}
						}
						catch(Exception e) {
							e.printStackTrace();  
						}
					}
				else{
					Jogador jo = new Jogador(index,"res\\mira"+index+".png",100,100);
					jo.load();
					return jo;
				}
				}
				catch(Exception e) {
					e.printStackTrace();  
				}
			return j;
		}
		
		public void excluirSaveJSON(int numFase,String username) {
			try  
			{  
				FileWriter file=new FileWriter("saves/"+username+"JogadorDAO"+numFase+".json"); 
				file.write("");
				file.close();
				File filee=new File("saves/"+username+"JogadorDAO"+numFase+".json"); 
				filee.delete();
			}
			catch (Exception e) {
				
			}
}
		public void excluirSaveTXT(int numFase,String username) {
			try  
			{  
				FileWriter file=new FileWriter("saves/"+username+"JogadorDAO"+numFase+".txt"); 
				file.write("");
				file.close();
				File filee=new File("saves/"+username+"JogadorDAO"+numFase+".txt");
				filee.delete();
			}
			catch (Exception e) {
				
			}
}
}
