import static org.junit.Assert.*;

import org.junit.Test;

public class TestAffichageEtienne {

	@Test
	public void testTVQ() {
		double resultat = Facture.calculTPQ(100.00);
		assertEquals(110, resultat);
	}
	
	@Test
	public void testTPS() {
		double resultat = Facture.CalculTPS)(100.00);
		assertEquals(105,resultat);
	}
	
}
