import static org.junit.Assert.*;

import org.junit.Test;

public class TestAffichageEtienne {

	@Test
	public void testTVQ() {
		double resultat = Facture.calculTVQ( 100 );
		assertEquals(110, resultat,2);
	}
	
	@Test
	public void testTPS() {
		double resultat = Facture.calculTPS(100.00);
		assertEquals(105,resultat, 2);
	}
	
	@Test
	public void testPlat(){
		Plat testPlat = new Plat("TEST");
		assertEquals("TEST",testPlat.getNom());
		
		
	}
	
}
