package com.dummy.myerp.consumer.impl.consumer;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;

public class ComptabiliteDaoImplTest extends ConsumerTestCase {
	
	@Test
	public void getListCompteComptable(){
		List<CompteComptable> comptes = getDaoProxy().getComptabiliteDao().getListCompteComptable();
		assertNotNull(comptes);
		assertEquals(7, comptes.size());
	}
	
	@Test
	public void getListJournalComptable() {
		List<JournalComptable> journaux = getDaoProxy().getComptabiliteDao().getListJournalComptable();
		assertNotNull(journaux);
		assertEquals(4, journaux.size());
	}
	
	@Test
	public void getListEcritureComptable() {
		List<EcritureComptable> ecritures = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
		assertNotNull(ecritures);
		assertEquals(5, ecritures.size());
	}
	
	@Test
	public void getEcritureComptable() {
		try{
			EcritureComptable ecriture = getDaoProxy().getComptabiliteDao().getEcritureComptable(-1);
			assertNotNull(ecriture);
			assertEquals("AC", ecriture.getJournal().getCode());
			assertEquals("AC-2016/00001", ecriture.getReference());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse("31-12-2016");
			assertEquals(date, ecriture.getDate());
			assertEquals("Cartouches d’imprimante", ecriture.getLibelle());
			assertNotNull(ecriture.getListLigneEcriture());
			assertTrue(ecriture.getListLigneEcriture().size()>2);
		}catch(Exception e) {
			fail("Ecriture non trouvée en base");
		}	
	}
	
	@Test
	public void getEcritureComptableNotFound() throws Exception{
		 Assertions.assertThrows(NotFoundException.class, () -> {
			 EcritureComptable ecriture = getDaoProxy().getComptabiliteDao().getEcritureComptable(1);
		 });
	}
	
	@Test
	public void getEcritureComptableByRef() {
		try {
			EcritureComptable ecriture = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("AC-2016/00001");
			assertNotNull(ecriture);
			assertEquals("AC", ecriture.getJournal().getCode());
			assertEquals("AC-2016/00001", ecriture.getReference());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse("31-12-2016");
			assertEquals(date, ecriture.getDate());
			assertEquals("Cartouches d’imprimante", ecriture.getLibelle());
			assertNotNull(ecriture.getListLigneEcriture());
			assertTrue(ecriture.getListLigneEcriture().size()>2);
		}catch(Exception e){
			fail("Ecriture comptable non trouvée en base pour la référence");
		}
	}
	
	@Test
	public void getEcritureComptableByRefNotFound() throws Exception{
		Assertions.assertThrows(NotFoundException.class, () -> {
			 EcritureComptable ecriture = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("AC-2016/10000");
		 });
	}
	
	@Test
	public void getLastSequenceOfYear() {
		try {
			SequenceEcritureComptable sequence = getDaoProxy().getComptabiliteDao().getLastSequenceOfYear(2016,"AC");
			assertNotNull(sequence);
			assertTrue(sequence.getAnnee()==2016);
			assertTrue(sequence.getDerniereValeur()==40);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Séquence non trouvée en base pour l'année sélectionnée");
		}
	}
	
	@Test
	public void getLastSequenceOfYearNullReturn() {
			SequenceEcritureComptable sequence = getDaoProxy().getComptabiliteDao().getLastSequenceOfYear(2017,"AC");
			assertNull(sequence);
			SequenceEcritureComptable sequence2 = getDaoProxy().getComptabiliteDao().getLastSequenceOfYear(2016,"A");
			assertNull(sequence2);
	}
	
	@Test
	public void insertEcritureComptable() {
		EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        
        //Création de la référence
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String refYear= df.format(vEcritureComptable.getDate());
        vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+"-"+refYear+"/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        getDaoProxy().getComptabiliteDao().insertEcritureComptable(vEcritureComptable);
        assertNotNull(vEcritureComptable.getId());
	}
	
	@Test
	public void insertSequenceEcritureComptable() {
		try {
			SequenceEcritureComptable sequence = new SequenceEcritureComptable();
			sequence.setAnnee(2016);
			sequence.setDerniereValeur(4);
			getDaoProxy().getComptabiliteDao().insertSequenceEcritureComptable(sequence, "AC");
		}catch(Exception e) {
			fail("Erreur lors de l'insertion de la séquence");
		}
	}
	
	@Test
	public void updateSequenceEcritureComptable() {
		try {
			SequenceEcritureComptable sequence = getDaoProxy().getComptabiliteDao().getLastSequenceOfYear(2016, "AC");
			sequence.setDerniereValeur(sequence.getDerniereValeur()+1);
			getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(sequence, "AC");
		}catch(Exception e) {
			fail("Erreur lors de la mise à jour de la séquence");
		}
		
	}
	
	@Test
	public void updateEcritureComptable() {
		try {
			EcritureComptable vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(-1);
			vEcritureComptable.setLibelle("Hello world");
			getDaoProxy().getComptabiliteDao().updateEcritureComptable(vEcritureComptable);
		}catch(Exception e) {
			fail("Erreur lors de la mise à jour de l'écriture comptable");
		}
	}
	
	@Test
	public void deleteEcritureComptable()
	{
		try {
			getDaoProxy().getComptabiliteDao().deleteEcritureComptable(-1);
		}catch(Exception e)
		{
			
			fail("Erreur lors de la suppression de l'écriture comptable");
		}
	}
}
