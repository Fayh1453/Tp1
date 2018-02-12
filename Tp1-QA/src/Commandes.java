
public class Commandes {

	private String nomClient;
	private String nomPlat;
	private int quantit�;
	
	public Commandes(){
		this.nomClient="";
		this.nomPlat="";
		this.quantit�=0;
	}
	
	public Commandes (String nomClient, String nomPlat, int quantit�){
		this.nomClient=nomClient;
		this.nomPlat=nomPlat;
		this.quantit�=quantit�;
	}
	
	public String getNomClient(){
		return this.nomClient;
	}
	
	public String getNomPlat(){
		return this.nomPlat;
	}
	
	public int getQuantit�(){
		return this.quantit�;
	}
}
