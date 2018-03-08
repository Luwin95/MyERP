package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CompteComptableTest {
	
	private static List<CompteComptable> comptes = new ArrayList<CompteComptable>();

	@BeforeAll
	public static void intialiserListeCompteComptable() {
		CompteComptable compte1 = new CompteComptable(1, "test1");
		CompteComptable compte2 = new CompteComptable(2, "test2");
		CompteComptable compte3 = new CompteComptable(3, "test3");
		CompteComptable compte4 = new CompteComptable(4, "test4");
		CompteComptable compte5 = new CompteComptable(5, "test5");
		comptes.add(compte1);
		comptes.add(compte2);
		comptes.add(compte3);
		comptes.add(compte4);
		comptes.add(compte5);
	}
	
	@Test
	public void getByNumero() {
		try {
			CompteComptable compte = CompteComptable.getByNumero(comptes, 1);
			assertNotNull(compte);
			assertTrue(compte.getNumero()==1);
			assertEquals("test1", compte.getLibelle());
		}catch(Exception e) {
			fail("Le compte comptable n'a pas été trouvé dans la liste");
		}
	}
}
