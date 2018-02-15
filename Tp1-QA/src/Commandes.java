
public class Commandes {

	private String nomClient;
	private String nomRepas;
	private double quantit�;
	
	public Commandes(){
		this.nomClient="";
		this.nomRepas="";
		this.quantit�=0;
	}
	
	public Commandes(String nomClient, String nomRepas, double quantit�){
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
	
	public double getQuantit�(){
		return this.quantit�;
	}
}
