
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
		System.arraycopy(this.comp, 0, comp, 0, comp.length);
		this.attente= attente;
		
	}

public void afficher(CV cvTemp){
	String temp="";
	for(int i=0;i<cvTemp.comp.length;++i){
		temp+=this.comp[i] + ", ";
	}
	System.out.println("Nom: " + cvTemp.nom + "\nPr�nom: " + cvTemp.pr�nom + "\nFormation: " + cvTemp.formation + "\nAnn�es d'exp�riences: " + cvTemp.xp + "\nComp�tences: " + temp + "\nAttentes: " + cvTemp.attente );
}
	
}

	
	
	
