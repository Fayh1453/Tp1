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

	private static final int NOMBRE_TABLE = 25;
	private int j = 0;
	private int k = 0;
	private int l = 0;
	private static final double TPS = 0.05;
	private static final double TVQ = 0.1;
	public int compteurC = 0;
	public int compteurP = 0;
	public int compteurCommandes = 0;
	private Clients clientTemp;
	private String[] texteClient;
	private String[] textePlat;
	private String[] texteCommandes;
	private DecimalFormat df = new DecimalFormat( "#0.00" );
	private String erreur = "";
	private Vérifications verif = new Vérifications();
	private double[] prixTable = new double[NOMBRE_TABLE];
	private int compteurClientTable = 0;

	public Facture( String nomFichier ) {

		try {
			texte = lectureFichier( nomFichier );
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			System.out.println( "Le fichier n'existe pas" );
		}

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

		tabClients = new Clients[--compteurC];
		tabPlats = new Plat[--compteurP];
		tabCommandes = new Commandes[--compteurCommandes];
	}

	public void lireFacture() {
		int i = 0;

		if ( texte[i].equalsIgnoreCase( "Clients:" ) ) {
			++i;

			while ( !texte[i].equalsIgnoreCase( "Plats:" ) ) {
				texteClient = texte[i].split( "\\s+" );
				if ( verif.verifierClient( texteClient[0] ) && verif.verifierTable( texteClient[1] ) ) {
					clientTemp = new Clients( texteClient[0], Integer.parseInt( texteClient[1] ) );
					tabClients[j++] = clientTemp;
				} else {
					erreur += System.getProperty( "line.separator" ) + "Le nom du client " + texte[i]
							+ " n'est pas valide.";
					--compteurC;

				}
				++i;
			}
			++i;

			while ( !texte[i].equalsIgnoreCase( "Commandes:" ) ) {
				textePlat = texte[i].split( "\\s+" );
				if ( verif.verifierPlat( textePlat[0] ) && verif.verifierPrix( textePlat[1] ) ) {
					Plat platTemp = new Plat( textePlat[0], Double.parseDouble( textePlat[1] ) );
					tabPlats[k++] = platTemp;
				} else if ( !verif.verifierPlat( textePlat[0] ) ) {
					erreur += System.getProperty( "line.separator" ) + "Le nom du plat " + textePlat[0]
							+ " n'est pas valide.";
					--compteurP;
				} else {
					erreur += System.getProperty( "line.separator" ) + "Le prix du plat " + textePlat[1]
							+ " n'est pas valide.";
					--compteurP;
				}
				++i;
			}
			++i;

			while ( !texte[i].equalsIgnoreCase( "Fin" ) ) {
				texteCommandes = texte[i].split( "\\s+" );
				if ( verif.verifierClient( texteCommandes[0] ) && verif.verifierPlat( texteCommandes[1] )
						&& verif.verifierQuantite( texteCommandes[2] ) ) {
					Commandes commandesTemp = new Commandes( texteCommandes[0], texteCommandes[1],
							Double.parseDouble( texteCommandes[2] ) );
					tabCommandes[l++] = commandesTemp;
				} else if ( !verif.verifierClient( texteCommandes[0] ) ) {
					erreur += System.getProperty( "line.separator" ) + "Le nom du client " + texteCommandes[0]
							+ " n'est pas valide.";
					--compteurCommandes;
				} else if ( !verif.verifierPlat( texteCommandes[1] ) ) {
					erreur += System.getProperty( "line.separator" ) + "Le nom du plat " + texteCommandes[1]
							+ " n'est pas valide.";
					--compteurCommandes;
				} else {
					erreur += System.getProperty( "line.separator" ) + "La quantité " + texteCommandes[2]
							+ " n'est pas valide.";
					--compteurCommandes;
				}
				++i;
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

		for ( int i = 0; i < prixTable.length; ++i ) {
			prixTable[i] = 0;
		}

		for ( int i = 0; i < compteurCommandes; i++ ) {
			String temp = tabCommandes[i].getNomClient();
			String temp2 = tabCommandes[i].getNomRepas();

			if ( !verif.verifierClientExistant( tabClients, temp, compteurC ) ) {
				enregistrement += System.getProperty( "line.separator" )
						+ "La commande contient un client non existant.";
			}
			if ( !verif.verifierPlatsExistant( tabPlats, temp2, compteurP ) ) {
				enregistrement += System.getProperty( "line.separator" )
						+ "La commande contient un plat non existant.";
			}
		}
		System.out.println( erreur );

		for ( int i = 0; i < compteurC; i++ ) {

			String tempClient = tabClients[i].getNomClient();
			int tempTable = tabClients[i].getNumTable();

			for ( int y = 0; y < compteurCommandes; y++ ) {
				qte = 0;
				if ( tabCommandes[y].getNomClient().equalsIgnoreCase( tempClient ) ) {

					qte = tabCommandes[y].getQuantité();

					String nomRepas = tabCommandes[y].getNomRepas();

					for ( int x = 0; x < compteurP; x++ ) {
						if ( tabPlats[x].getNom().equalsIgnoreCase( nomRepas ) ) {
							total = 0.00;
							prix = tabPlats[x].getPrix();
							total += qte * prix;
							prixTable[tempTable] += total;

						}

					}

				}
			}

			/*
			 * if ( total > 0 ) { enregistrement += erreur + System.getProperty(
			 * "line.separator" ) + tempClient + " " + df.format( total ) + "$"
			 * + System.getProperty( "line.separator" ); enregistrer(
			 * enregistrement, fichier ); System.out.println( tempClient + " " +
			 * df.format( total ) + "$" ); }
			 */

		}

		for ( int i = 0; i < prixTable.length; ++i ) {
			compteurClientTable = 0;
			if ( prixTable[i] > 0 && prixTable[i] < 100 ) {
				for ( int j = 0; j < compteurC; ++j ) {
					if ( tabClients[j].getNumTable() == i ) {
						System.out.println( tabClients[j].getNomClient() );
						enregistrement += erreur + System.getProperty( "line.separator" )
								+ tabClients[j].getNomClient() + System.getProperty( "line.separator" );
						enregistrer( enregistrement, fichier );
						compteurClientTable++;
					}
				}
				if ( compteurClientTable >= 3 ) {
					prixTable[i] *= 1.15;
					prixTable[i] += calculTPS( prixTable[i] ) + calculTVQ( prixTable[i] );
					System.out.println( "Numéro de table " + i + ": " + df.format( prixTable[i] ) + "$" );
				} else {
					prixTable[i] += calculTPS( prixTable[i] ) + calculTVQ( prixTable[i] );
					System.out.println( "Numéro de table " + i + ": " + df.format( prixTable[i] ) + "$" );
				}
				enregistrement += erreur + System.getProperty( "line.separator" ) + "Numéro de table " + i + ": "
						+ df.format( prixTable[i] ) + "$" + System.getProperty( "line.separator" );
				enregistrer( enregistrement, fichier );
				System.out.println();
			} else if ( prixTable[i] > 100 ) {
				for ( int j = 0; j < compteurC; ++j ) {
					if ( tabClients[j].getNumTable() == i ) {
						System.out.println( tabClients[j].getNomClient() );
					}

				}
				prixTable[i] *= 1.15;
				prixTable[i] += calculTPS( prixTable[i] ) + calculTVQ( prixTable[i] );
				System.out.println( "Numéro de table " + i + ": " + df.format( prixTable[i] ) + "$" );

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

	private double calculTPS( double total ) {
		return total * TPS;

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
