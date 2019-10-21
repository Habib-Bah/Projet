package com.dummy.myerp.business.impl.manager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
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
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
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
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
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
    }
    
    @Test
	public void testaddReference() {

		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.setReference("RF-2018/00002");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		manager.addReference(vEcritureComptable);
	}

	@Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitRG5() throws Exception {

		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("RT", "RETRAIT"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle1");
		//vEcritureComptable.setReference("RT-2017/00003");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));

		manager.checkEcritureComptableUnit(vEcritureComptable);
	}
	
	
	@Test
	public void testGetListeEcriture() throws IOException {
		
		List<EcritureComptable> listeEcriture = manager.getListEcritureComptable();
		
		int nombreEntite = 10;
		int tailleListe = listeEcriture.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetListeJournal() throws IOException {
		
		List<JournalComptable> listeJournal = manager.getListJournalComptable();
		
		int nombreEntite = 4;
		int tailleListe = listeJournal.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetCompteComptable() throws IOException {
		
		List<CompteComptable> listeCompteC = manager.getListCompteComptable();
		
		int nombreEntite = 7;
		int tailleListe = listeCompteC.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetSEComptable() throws IOException {
		
		List<SequenceEcritureComptable> listeSEC = manager.getListSequenceEcritureComptable();
		
		int nombreEntite = 4;
		int tailleListe = listeSEC.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}

	
	
	@Before
	public void before() {
		
		List <EcritureComptable> liste = manager.getListEcritureComptable();
		
		int t = liste.size();
	}
	
	
	/*
	
	@Test
	public void testInsert() throws IOException, FunctionalException {
			
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setJournal_code("TI");
		ecriture.setReference("TI-2019/00001");
		ecriture.setDate(new Date());
		ecriture.setLibelle("Test insert");
		
		manager.insertEcritureComptable(ecriture);
		
		List<EcritureComptable> listeEcriture = manager.getListEcritureComptable();
		boolean res = false;
		
		for(EcritureComptable ec : listeEcriture) {
			
			if(ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				res = true;
			}
		}
		assertEquals(true, res);
	}
	
	
	@After
	public void after() {
		
		List <EcritureComptable> liste = manager.getListEcritureComptable();
		
		for(EcritureComptable ec : liste) {
			if(ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				manager.deleteEcritureComptable(7);
			}
		}
	}
	*/
}
