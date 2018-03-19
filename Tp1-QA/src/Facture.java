import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facture {

	private String[] texte;
	private Clients[] tabClients;
	private Plat[] tabPlats;
	private Commandes[] tabCommandes;

	private int j=0;
	private int k=0;
	private int l=0;
	private static  final double TPS = 1.05;
	private static final double TVQ = 1.1;
	private int compteurC=0;
	private int compteurP=0;
	private int compteurCommandes=0;
	private Clients clientTemp;
	private String[] textePlat;
	private String[] texteCommandes;
	private DecimalFormat df = new DecimalFormat( "#0.00" );
	private String erreur;
	
	public Facture(String nomFichier){


		try {
			texte = lectureFichier( nomFichier );
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			System.out.println( "Le fichier n'existe pas" );
		}

		// for(int i=0;i<texte.length;++i){
		// System.out.println(texte[i]);
		// }
		int i = 0;
		if ( texte[i].equalsIgnoreCase( "Clients:" ) ) {
			while ( !texte[i].equalsIgnoreCase( "Plats:" ) ) {
				++i;
				compteurC++;
			}

			while ( !texte[i].equalsIgnoreCase( "Commandes:" ) ) {
				++i;
				compteurP++;
			}
			while ( !texte[i].equalsIgnoreCase( "Fin" ) ) {
				++i;
				compteurCommandes++;
			}

		}

		tabClients = new Clients[compteurC - 1];
		tabPlats = new Plat[compteurP - 1];
		tabCommandes = new Commandes[compteurCommandes - 1];
	}

	public void lireFacture() {
		int i = 0;
		if ( texte[i].equalsIgnoreCase( "Clients:" ) ) {
			++i;

		while(!texte[i].equalsIgnoreCase("Plats:")){
			if(Vérifications.verifierClient(texte[i])){
				clientTemp =  new Clients(texte[i]);
				tabClients[j]= clientTemp;
			}else{
				erreur+="Le nom du client " + texte[i] + " n'est pas valide. \n";
			}
				++i;
				++j;
			}
			++i;

		while(!texte[i].equalsIgnoreCase("Commandes:")){
				textePlat = texte[i].split("\\s+");
				if(Vérifications.verifierPlat(textePlat[0]) && Vérifications.verifierPrix(textePlat[1])){
				Plat platTemp = new Plat(textePlat[0],Double.parseDouble(textePlat[1]));
				tabPlats[k]= platTemp;
				}else if(!Vérifications.verifierPlat(textePlat[0])){
				erreur+="Le nom du plat " + textePlat[0] + " n'est pas valide. \n";
				}else{
				erreur+="Le prix du plat " + textePlat[1] + " n'est pas valide. \n";
				}
				++i;
				++k;
			}
			++i;

		while(!texte[i].equalsIgnoreCase("Fin")){			
			texteCommandes = texte[i].split("\\s+");
			if(Vérifications.verifierClient(texteCommandes[0])&&Vérifications.verifierPlat(texteCommandes[1])&&Vérifications.verifierQuantite(texteCommandes[2])){
			Commandes commandesTemp = new Commandes(texteCommandes[0],texteCommandes[1],Double.parseDouble(texteCommandes[2]));
			tabCommandes[l]= commandesTemp;
			}else if(!Vérifications.verifierClient(texteCommandes[0])){
			erreur+="Le nom du client " + texteCommandes[0] + " n'est pas valide. \n";
			}else if(!Vérifications.verifierPlat(texteCommandes[1])){
			erreur+="Le nom du plat " + texteCommandes[1] + " n'est pas valide. \n";	
			}else{
			erreur+="La quantité " + texteCommandes[2] + " n'est pas valide. \n";	
			}
			++i;
			++l;
		}
		}

		}

	

	public void affichage() throws IOException {

		double qte = 0;
		double prix = 0;
		double total;
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat( "ddMMyy-HHmmss" );
		File fichier = new File( "Facture-du-" + dateFormat.format( date ) + ".txt" );
		String enregistrement = "Bienvenue chez Barette!" + System.getProperty( "line.separator" ) + "Factures:"
				+ System.getProperty( "line.separator" );
		enregistrer( enregistrement, fichier );

		System.out.println( "Bienvenue chez Barette!\nFactures:" );

		for ( int i = 0; i < tabCommandes.length; i++ ) {
			String temp = tabCommandes[i].getNomClient();
			String temp2 = tabCommandes[i].getNomRepas();

			
			if(!Vérifications.verifierClientExistant(tabClients, temp)){
				System.out.println("Le fichier ne respecte pas le format demandé!");
				enregistrement += "\nLa commande contient un client non existant.";
				System.exit(1);
			}
			if(!Vérifications.verifierPlatsExistant(tabPlats, temp2)){
				System.out.println("Le fichier ne respecte pas le format demandé!");
				enregistrement += "\nLa commande contient un plat non existant.";
				System.exit(1);
			}
		}
		for ( int i = 0; i < tabClients.length; i++ ) {

			String tempClient = tabClients[i].getNomClient();

			total = 0.00;

			for ( int y = 0; y < tabCommandes.length; y++ ) {
				qte = 0;
				if ( tabCommandes[y].getNomClient().equalsIgnoreCase( tempClient ) ) {

					qte = tabCommandes[y].getQuantité();

					String nomRepas = tabCommandes[y].getNomRepas();

					for ( int x = 0; x < tabPlats.length; x++ ) {

						if ( tabPlats[x].getNom().equalsIgnoreCase( nomRepas ) ) {
							prix = tabPlats[x].getPrix();

						}

					}

				}

				total += qte * prix;
				total = calculTPS( total );
				total = calculTVQ( total );

			}


			if ( total > 0 ) {
				enregistrement += "\n" + tempClient + " " + df.format( total ) + "$"
						+ System.getProperty( "line.separator" );
				enregistrer( enregistrement, fichier );
				System.out.println( tempClient + " " + df.format( total ) + "$" );
			}

		}

	}

	private String[] lectureFichier( String nomFichier ) throws IOException {

		FileReader fileReader;
		fileReader = new FileReader( nomFichier );

		BufferedReader bufferedReader = new BufferedReader( fileReader );
		List<String> lines = new ArrayList<String>();
		String line = null;

		while ( ( line = bufferedReader.readLine() ) != null )

			lines.add( line );

		bufferedReader.close();

		return lines.toArray( new String[lines.size()] );
	}

	
	
	private double calculTPS(double total){
		return total*TPS;
		
	}


	public static double calculTVQ( double total ) {
		return total * TVQ;

	}

	public static void enregistrer( String enregistrement, File fichier ) throws IOException {

		BufferedWriter writer = new BufferedWriter( new FileWriter( fichier ) );
		writer.write( enregistrement );
		writer.flush();
		writer.close();
	}

}
