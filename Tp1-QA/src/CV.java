
public class CV {

	private String nom;
	private String pr�nom;
	private String formation;
	private int xp;
	private String[] comp;
	private String attente;
	
public CV (String nom, String pr�nom, String formation, int xp, String[] comp, String attente){
		
		this.nom = nom;
		this.pr�nom = pr�nom;
		this.formation = formation;
		this.xp = xp;
		System.arraycopy(comp, 0, this.comp, 0, comp.length);
		this.attente= attente;
		
	}

public void afficher(){
	String temp="";
	for(int i=0;i<this.comp.length;++i){
		temp+=this.comp[i] + ", ";
	}
	System.out.println("Nom: " + this.nom + "\nPr�nom: " + this.pr�nom + "\nFormation: " + this.formation + "\nAnn�es d'exp�riences: " + this.xp + "\nComp�tences: " + temp + "\nAttentes: " + this.attente );
}
	
}


