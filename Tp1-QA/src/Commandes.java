
public class Commandes {

	private String nomClient;
	private String nomRepas;
	private int quantit�;
	
	public Commandes(){
		this.nomClient="";
		this.nomRepas="";
		this.quantit�=0;
	}
	
	public Commandes(String nomClient, String nomRepas, int quantit�){
		this.nomClient=nomClient;
		this.nomRepas=nomRepas;
		this.quantit�=quantit�;
	}
	
	public String getNomClient(){
		return this.nomClient;
	}
	
	public String getNomRepas(){
		return this.nomRepas;
	}
	
	public int getQuantit�(){
		return this.quantit�;
	}
}
