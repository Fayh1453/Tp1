import java.io.IOException;


public class Test {

	public static void main(String[] args) {
		
		System.out.println("Bienvenue chez Barette!");
		
		String[] competence = {"Java", "C#"};
		
		CV cv1 = new CV("Roy", "Luc", "Informatique", 10, competence, "Aucune" );
		
		cv1.afficher();
		
		CV cv2 = new CV("Vaillancourt", "Étienne", "Informatique", 2, competence,"Apprendre à utiliser GitHub" );
		
		cv2.afficher();
		
		Facture test = new Facture("Essai.txt");
		
		test.lireFacture();
		try {
			test.affichage();
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}




