
public class Vérifications {
	
	public static boolean verifierPlatsExistant(Plat tabPlats[],String temp){
		boolean verif=false;
		for (int w=0; w<tabPlats.length; ++w){
			if(tabPlats[w].getNom().equalsIgnoreCase(temp)){
				verif=true;
				break;
			}
		}
		return verif;
}
	
	public static boolean verifierClientExistant(Clients tabClients[], String temp){
		boolean verif=false;
		for (int w=0; w<tabClients.length; ++w){
			if(tabClients[w].getNomClient().equalsIgnoreCase(temp)){
				verif=true;
				break;
			}
		}
		
		return verif;
	}
	
	public static boolean verifierQuantite(String temp){
		
		boolean valide = false;
		
		try {
			
			Integer.parseInt( temp );
			valide = true;
		
		} catch (Exception e) {
			
			valide = false;
		}
		
		return valide;
	}
	
	public static boolean verifierPrix(String temp){
		
		boolean valide = false;
		
		try {
			
			Double.parseDouble( temp );
			valide = true;
		
		} catch (Exception e) {
			
			valide = false;
		}
		
		return valide;
	}
	
	public static boolean verifierPlat(String temp){
		
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
	
	public static boolean verifierClient(String temp){
		
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
