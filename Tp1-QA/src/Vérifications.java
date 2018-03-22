
public class Vérifications {
	
	public boolean verifierPlatsExistant(Plat tabPlats[],String temp, int compteur){
		boolean verif=false;
		for (int w=0; w<compteur; ++w){
			if(tabPlats[w].getNom().equalsIgnoreCase(temp)){
				verif=true;
				break;
			}
		}
		return verif;
}
	
	public boolean verifierClientExistant(Clients tabClients[], String temp, int compteur){
		boolean verif=false;
		for (int w=0; w<compteur; ++w){
			if(tabClients[w].getNomClient().equalsIgnoreCase(temp)){
				verif=true;
				break;
			}
		}
		
		return verif;
	}
	
	public boolean verifierQuantite(String temp){
		
		boolean valide = false;
		
		try {
			
			Integer.parseInt( temp );
			valide = true;
		
		} catch (Exception e) {
			
			valide = false;
		}
		
		return valide;
	}
	
	public boolean verifierPrix(String temp){
		
		boolean valide = false;
		
		try {
			
			Double.parseDouble( temp );
			valide = true;
		
		} catch (Exception e) {
			
			valide = false;
		}
		
		return valide;
	}
	
	public boolean verifierPlat(String temp){
		
		char[] tab = temp.toCharArray();
		boolean valide = false;
		
		
		for (int i = 0 ; i < tab.length ; i++) {
			
			if ( Character.isLetter(tab[i]) || tab[i] == '_' ) {
				valide = true;
			} else {
				valide = false;
				break;
			}
		}
		
		return valide;
	}
	
	public boolean verifierClient(String temp){
		
		boolean valide = false;
		char[] tab = temp.toCharArray();
		
		for (int i = 0 ; i < tab.length ; i++) {
			
			if ( Character.isLetter(tab[i])) {
				valide = true;
			} else {
				valide = false;
				break;
			}
		}
		
		return valide;
	}
}
