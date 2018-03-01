package com.dummy.myerp.business.impl.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;


public class ComptabiliteManagerImplTest {

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
    public void addReference() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        
        //Création de la référence
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String refYear= df.format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC"+"-"+refYear+"/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.addReference(vEcritureComptable);
        assertEquals("AC-2018/00002", vEcritureComptable.getReference());
    }
}
