package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JournalComptableTest {
	private static List<JournalComptable> vListeJournalComptable = new ArrayList<JournalComptable>();
	
	@BeforeAll
	public static void initialiserJournalComptable() {
		JournalComptable journal1= new JournalComptable("AA", "journal test 1");
		JournalComptable journal2= new JournalComptable("AB", "journal test 2");
		JournalComptable journal3= new JournalComptable("AC", "journal test 3");
		JournalComptable journal4= new JournalComptable("AD", "journal test 4");
		JournalComptable journal5= new JournalComptable("AE", "journal test 5");
		vListeJournalComptable.add(journal1);
		vListeJournalComptable.add(journal2);
		vListeJournalComptable.add(journal3);
		vListeJournalComptable.add(journal4);
		vListeJournalComptable.add(journal5);
	}
	
	@Test
	public void getByCode() {
		try {
			JournalComptable vJournal = JournalComptable.getByCode(vListeJournalComptable, "AA");
			assertNotNull(vJournal);
			assertTrue(vJournal.getCode().equals("AA"));
			assertEquals("journal test 1", vJournal.getLibelle());
		}catch(Exception e) {
			fail("Le compte comptable n'a pas été trouvé dans la liste");
		}
	}
	
	@Test
	public void getByCodeNotFound() {
		try {
			JournalComptable vJournal = JournalComptable.getByCode(vListeJournalComptable, "AF");
			assertNull(vJournal);
			
		}catch(Exception e) {
			fail("Le compte comptable a été trouvé dans la liste");
		}
	}
}
