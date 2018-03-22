package com.dummy.myerp.business.impl.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;


public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();


    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        
        //Création de la référence
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String refYear= df.format(vEcritureComptable.getDate());
        vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+"-"+refYear+"/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitViolation() throws Exception {
    	 Assertions.assertThrows(FunctionalException.class, () -> {
    		 EcritureComptable vEcritureComptable;
	         vEcritureComptable = new EcritureComptable();
	         manager.checkEcritureComptableUnit(vEcritureComptable);
    	 });
        
    }

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
    	 Assertions.assertThrows(FunctionalException.class, () -> {
    		 EcritureComptable vEcritureComptable;
    	        vEcritureComptable = new EcritureComptable();
    	        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
    	        vEcritureComptable.setDate(new Date());
    	        vEcritureComptable.setLibelle("Libelle");
    	        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
    	                                                                                 null, new BigDecimal(123),
    	                                                                                 null));
    	        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
    	                                                                                 null, null,
    	                                                                                 new BigDecimal(1234)));
    	        manager.checkEcritureComptableUnit(vEcritureComptable);
    	 });
    }

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {
    	Assertions.assertThrows(FunctionalException.class, () -> {
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            manager.checkEcritureComptableUnit(vEcritureComptable);
    	});
    }
    
    @Test
    public void checkEcritureComptableUnitRG5YearError() throws Exception {
    	Assertions.assertThrows(FunctionalException.class, () ->{
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            
            //Création de la référence
            String refYear= "0000";
            vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+"-"+refYear+"/00001");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                     null, null,
                                                                                     new BigDecimal(123)));
            manager.checkEcritureComptableUnit(vEcritureComptable);
    	});
    }
    
    @Test
    public void checkEcritureComptableUnitRG5JournalCodeError() throws Exception {
    	Assertions.assertThrows(FunctionalException.class, () ->{
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            
            //Création de la référence
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String refYear= df.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference("BD"+"-"+refYear+"/00001");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                     null, null,
                                                                                     new BigDecimal(123)));
            manager.checkEcritureComptableUnit(vEcritureComptable);
    	});
    }
    
    @Test
    public void addReferenceUnit() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(-2);
        vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vEcritureComptable.setDate(pattern.parse("2016-12-30 00:00:00"));
        vEcritureComptable.setLibelle("TMA Appli Xxx");
        
        //Création de la référence
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String refYear= df.format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("VE"+"-"+refYear+"/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4457),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
        																		 null, null,
        																		 new BigDecimal(123)));
        manager.addReference(vEcritureComptable);
        assertEquals("VE-2016/00042", vEcritureComptable.getReference());
    }
    
    @Test
    public void checkEcritureComptableContextUnit() throws Exception {
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setId(-2);
            vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
            SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vEcritureComptable.setDate(pattern.parse("2016-12-30 00:00:00"));
            vEcritureComptable.setLibelle("TMA Appli Xxx");
            
            //Création de la référence
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String refYear= df.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference("VE"+"-"+refYear+"/00002");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
            																		 null, null,
            																		 new BigDecimal(123)));
            manager.checkEcritureComptableContext(vEcritureComptable);
    }
    
    @Test
    public void checkEcritureComptableContextUnitRG6NewEcritureException() {
    	Assertions.assertThrows(FunctionalException.class, () -> {
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
            SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vEcritureComptable.setDate(pattern.parse("2016-12-30 00:00:00"));
            vEcritureComptable.setLibelle("TMA Appli Xxx");
            
            //Création de la référence
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String refYear= df.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference("VE"+"-"+refYear+"/00002");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
            																		 null, null,
            																		 new BigDecimal(123)));
            manager.checkEcritureComptableContext(vEcritureComptable);
    	});
    }
    
    @Test
    public void checkEcritureComptableContextUnitRG6ExistingEcritureException() {
    	Assertions.assertThrows(FunctionalException.class, () -> {
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setId(1234);
            vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
            SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vEcritureComptable.setDate(pattern.parse("2016-12-28 00:00:00"));
            vEcritureComptable.setLibelle("TMA Appli Yyy");
            
            //Création de la référence
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String refYear= df.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference("VE"+"-"+refYear+"/00004");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
            																		 null, null,
            																		 new BigDecimal(123)));
            manager.checkEcritureComptableContext(vEcritureComptable);
    	});
    }
    
    @Test
    public void insertEcritureComptableUnit() {
    	try {
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
            
            manager.insertEcritureComptable(vEcritureComptable);
            assertNotNull(vEcritureComptable.getId());
    	}catch(Exception e) {
    		fail("Erreur lors de l'insertion de l'écriture comptable");
    	}
    }
    
    @Test
    public void insertEcritureComptableNotConformUnit() {
    	Assertions.assertThrows(FunctionalException.class, () -> {
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            
            //Création de la référence
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String refYear= df.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+"-"+refYear+"/001");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                                                                                     null, new BigDecimal(123),
                                                                                     null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                                                                                     null, null,
                                                                                     new BigDecimal(123)));
            
            manager.insertEcritureComptable(vEcritureComptable);
    	});
    }
    
    @Test
    public void updateEcritureComptableUnit() {
    	try {
			List<EcritureComptable> vListeEcritureComptable = getBusinessProxy().getComptabiliteManager().getListEcritureComptable();
			for(EcritureComptable vEcritureComptable : vListeEcritureComptable) {
				if(vEcritureComptable.getId()==-3) {
					vEcritureComptable.setLibelle("Hello world");
					getBusinessProxy().getComptabiliteManager().updateEcritureComptable(vEcritureComptable);
				}
			}
		}catch(Exception e) {
			fail("Erreur lors de la mise à jour de l'écriture comptable");
		}
    }
    
    @Test
    public void updateEcritureComptableNotConformUnit() {
    	Assertions.assertThrows(FunctionalException.class, () -> {
    		List<EcritureComptable> vListeEcritureComptable = getBusinessProxy().getComptabiliteManager().getListEcritureComptable();
			for(EcritureComptable vEcritureComptable : vListeEcritureComptable) {
				if(vEcritureComptable.getId()==-3) {
					vEcritureComptable.setLibelle("Hello world");
					vEcritureComptable.setReference("test");
					getBusinessProxy().getComptabiliteManager().updateEcritureComptable(vEcritureComptable);
				}
			}
    	});
    }
    
    @Test
    public void deleteEcritureComptableUnit() {
    	try {
			  getBusinessProxy().getComptabiliteManager().deleteEcritureComptable(-5);
		}catch(Exception e) {
			fail("Erreur lors de la mise à jour de l'écriture comptable");
		}
    }
    
    @Test
    public void TestQuiPasse() {
    	boolean test = true;
    	assertTrue(test);
    }
    
    @Test
    public void TestQuiNePassePas() {
    	boolean test = false;
    	assertTrue(test);
    }
}
