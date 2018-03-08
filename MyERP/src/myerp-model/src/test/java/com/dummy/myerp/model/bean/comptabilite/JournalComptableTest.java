package com.dummy.myerp.model.bean.comptabilite;

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
		
	}
	

}
