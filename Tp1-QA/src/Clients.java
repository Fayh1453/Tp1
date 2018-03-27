
public class Clients {

	private String nomClient;
	private int numTable;
	
	public Clients(){
		this.nomClient="";
		this.numTable=0;
	}
	
	public Clients(String nomClient, int numTable){
		this.nomClient=nomClient;
		this.numTable=numTable;
	}
	
	public String getNomClient(){
		return this.nomClient;
	}
	
	public int getNumTable(){
		return this.numTable;
	}

}
