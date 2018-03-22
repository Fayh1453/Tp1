
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFactureLuc {


	Vérifications verif = new Vérifications();

	@Test
	public void testVerifierQuantiteFaux() {
		boolean test = verif.verifierQuantite("a");
		Assert.assertFalse(test);
	}
	
	@Test
	public void testVerifierQuantiteVrai() {
		boolean test = verif.verifierQuantite("1");
		Assert.assertTrue(test);
	}
	
	@Test
	public void testVerifierPrixVrai() {
		boolean test = verif.verifierPrix("1");
		Assert.assertTrue(test);
	}
	
	@Test
	public void testVerifierPrixFaux() {
		boolean test = verif.verifierPrix("a");
		Assert.assertFalse(test);
	}
	
	@Test
	public void testVerifierPlatsVrai() {
		boolean test = verif.verifierPlat("Hamburger");
		Assert.assertTrue(test);
	}
	
	@Test
	public void testVerifierPlatsFaux() {
		boolean test = verif.verifierPlat("Hamburger1");
		Assert.assertFalse(test);
	}

}
