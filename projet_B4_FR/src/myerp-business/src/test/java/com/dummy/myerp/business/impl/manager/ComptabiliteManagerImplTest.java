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
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(123)));
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
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(1234)));
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	@Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitRG3() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
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
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));

		manager.checkEcritureComptableUnit(vEcritureComptable);
	}
	
	
	@Test(expected = FunctionalException.class)
	public void TestcheckEcritureComptable() throws Exception {

		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("RT", "RETRAIT"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle1");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));

		manager.checkEcritureComptable(vEcritureComptable);
	}
	

	@Test
	public void testGetListeEcriture() throws IOException {

		List<EcritureComptable> listeEcriture = manager.getListEcritureComptable();

		int nombreEntite = 5;
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

	/*
	@Test
	public void testGetCompteComptable() throws IOException {

		List<CompteComptable> listeCompteC = manager.getListCompteComptable();

		int nombreEntite = 7;
		int tailleListe = listeCompteC.size();

		assertEquals(nombreEntite, tailleListe);
	}
	
	
	
	@Test
	public void testGetLigneEC() {
		
		List<LigneEcritureComptable> listeLEC = manager.getLigneEcritureComptable();
		int nombreEntite = 13;
		int taille = listeLEC.size();
		assertEquals(nombreEntite, taille);
		
	}
*/
	int t = 0;

	@Test
	public void testApresInDe() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();
		int t1 = liste.size();

		assertEquals(t1, t);
	}

	@Test
	public void testListeECSize() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();
		int taille = liste.size();
		boolean res = false;

		int min = 2;

		if (taille >= min) {
			res = true;
		}

		//assertEquals(true, res);
	}

	@Test
	public void testLibelleNull() {

		List<CompteComptable> liste = manager.getListCompteComptable();
		boolean res = false;

		for (CompteComptable c : liste) {
			if (c.getLibelle().isEmpty()) {
				res = true;
			}
		}

		assertEquals(false, res);
	}

	@Test
	public void testLibelleCcSize() {

		List<CompteComptable> liste = manager.getListCompteComptable();
		boolean res = false;

		for (CompteComptable c : liste) {
			if (c.getLibelle().length() < 1 || c.getLibelle().length() > 150) {
				res = true;
			}
		}

		//assertEquals(false, res);
	}

	@Test
	public void testLibelleEcSize() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();
		boolean res = false;

		for (EcritureComptable c : liste) {
			if (c.getLibelle().length() < 1 || c.getLibelle().length() > 200) {
				res = true;
			}
		}

		//assertEquals(false, res);
	}

	@Test
	public void testcodeSize() {

		List<JournalComptable> liste = manager.getListJournalComptable();
		boolean res = false;

		for (JournalComptable j : liste) {
			if (j.getCode().length() < 1 || j.getCode().length() > 5) {
				res = true;
			}
		}

		assertEquals(false, res);
	}

	/*
	@Test
	public void testGetListeLEC() {

		List<LigneEcritureComptable> liste = manager.getLigneEcritureComptable();
		int nombreEntite = 13;
		int tailleListe = liste.size();

		assertEquals(nombreEntite, tailleListe);

	}
	
	@Test
	public void testInsertLEC() throws FunctionalException {
		
		LigneEcritureComptable ligne = new LigneEcritureComptable();
		ligne.setCompteComptable(new CompteComptable(30));
		ligne.setLibelle("Test");
		ligne.setCredit(new BigDecimal(30));
		ligne.setDebit(new BigDecimal(25));
		
		manager.insertLigneEcritureC(ligne);
		
	}

	@Test
	public void testInsertCompteC() throws FunctionalException {

		CompteComptable c = new CompteComptable();
		c.setLibelle("Test insertion");
		c.setNumero(415);

		manager.insertCompteComptable(c);

		List<CompteComptable> listeCC = manager.getListCompteComptable();
		boolean res = false;

		for (CompteComptable cc : listeCC) {

			if (cc.getNumero() == 415) {
				res = true;
			}
		}
		assertEquals(true, res);
	}

	

	@Test
	public void testInsert() throws IOException, FunctionalException {

		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setJournal_code("AC");
		ecriture.setJournal(new JournalComptable("AC", "Test insert"));
		ecriture.setReference("TI-2019/00001");
		ecriture.setDate(new Date());
		ecriture.setLibelle("Test insert");

		manager.insertEcritureComptable(ecriture);
		
		
		@Test
	public void testinsertJC() throws FunctionalException {

		JournalComptable j = new JournalComptable();
		j.setCode("TI");
		j.setLibelle("Test insert");
		manager.insertJournalComptable(j);

		List<JournalComptable> ljc = manager.getListJournalComptable();
		boolean res = false;

		for (JournalComptable jc : ljc) {
			if (jc.getCode().equalsIgnoreCase("TI")) {
				res = true;
			}
		}
		//assertEquals(true, res);
		
	}

		

	}
	*/
	
	@Before
	public void before() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();

		t = liste.size();

	}

	@Test
	public void testInsertSE() throws FunctionalException {

		SequenceEcritureComptable se = new SequenceEcritureComptable();
		se.setAnnee(2019);
		se.setDerniereValeur(1000);
		se.setJournal_code("AC");

		manager.insertSEcritureComptable(se);

		List<SequenceEcritureComptable> lse = manager.getListSequenceEcritureComptable();
		boolean res = false;

		for (SequenceEcritureComptable seq : lse) {
			if (se.getAnnee() == 2019) {
				res = true;
			}
		}
		//assertEquals(true, res);

	}

	
/*
	@Test
	public void testDeleteEcritureComptable() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();

		for (EcritureComptable ec : liste) {
			if (ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				manager.deleteEcritureComptable(ec.getReference());

			}
		}

	}

	@Test
	public void testDeleteCompteComptable() {

		List<CompteComptable> listeCC = manager.getListCompteComptable();

		for (CompteComptable cc : listeCC) {
			if (cc.getNumero() == 415) {
				manager.deleteCompteComptable(cc.getNumero());
			}
		}
		
		

	}
	
	@Test
	public void testDeleteJournalComptable() {

		List<JournalComptable> listeJC = manager.getListJournalComptable();

		for (JournalComptable jc : listeJC) {
			if (jc.getLibelle().equalsIgnoreCase("Test insert")) {
				manager.deleteJComptable(jc.getLibelle());
			}
		}

	}
	
	*/

	@Test
	public void testDeleteSequenceEcritureComptable() {

		List<SequenceEcritureComptable> listeSE = manager.getListSequenceEcritureComptable();

		for (SequenceEcritureComptable se : listeSE) {
			if (se.getDerniereValeur() == 1000) {
				manager.deleteSEComptable(se.getDerniereValeur());
			}
		}

	}
	
	


	@After
	public void after() {

		/*
		List<EcritureComptable> liste = manager.getListEcritureComptable();

		for (EcritureComptable ec : liste) {
			if (ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				manager.deleteEcritureComptable(ec.getReference());

			}
		}

		List<CompteComptable> listeCC = manager.getListCompteComptable();

		for (CompteComptable cc : listeCC) {
			if (cc.getNumero() == 415) {
				manager.deleteCompteComptable(cc.getNumero());
			}
		}
		
		List<JournalComptable> listeJC = manager.getListJournalComptable();

		for (JournalComptable jc : listeJC) {
			if (jc.getLibelle().equalsIgnoreCase("Test insert")) {
				manager.deleteJComptable(jc.getLibelle());
			}
		}
		
		List<LigneEcritureComptable> listeLE = manager.getLigneEcritureComptable();
		for(LigneEcritureComptable l : listeLE) {
			if((l.getLibelle() == null) || l.getLibelle().equals("") || l.getLibelle().equals(" ")) {
				int i = 1;
			}
			
			else {
				manager.deleteLigneEcritureComptable("Test");
			}
		}


*/
		List<SequenceEcritureComptable> listeSE = manager.getListSequenceEcritureComptable();

		for (SequenceEcritureComptable se : listeSE) {
			if (se.getDerniereValeur() == 1000) {
				manager.deleteSEComptable(se.getDerniereValeur());
			}
		}
		
	

		
	}

	@Test
	public void testComp() {

		List<CompteComptable> ljc = manager.getListCompteComptable();
		List<SequenceEcritureComptable> lseq = manager.getListSequenceEcritureComptable();

		int t1 = ljc.size();
		int t2 = lseq.size();
		boolean res = false;

		if (t1 > t2) {
			res = true;
		}
		assertEquals(true, res);
	}

	@Test
	public void testComp1() {

		List<EcritureComptable> ljc = manager.getListEcritureComptable();
		List<SequenceEcritureComptable> lseq = manager.getListSequenceEcritureComptable();

		int t1 = ljc.size();
		int t2 = lseq.size();
		boolean res = false;

		if (t1 > t2) {
			res = true;
		}
		assertEquals(true, res);
	}

	@Test
	public void testComp2() {

		List<LigneEcritureComptable> ljc = manager.getLigneEcritureComptable();
		List<EcritureComptable> lec = manager.getListEcritureComptable();

		int t1 = ljc.size();
		int t2 = lec.size();
		boolean res = false;

		if (t1 > t2) {
			res = true;
		}
		assertEquals(true, res);
	}

	@Test
	public void testComp3() {

		List<LigneEcritureComptable> ljc = manager.getLigneEcritureComptable();
		List<SequenceEcritureComptable> lseq = manager.getListSequenceEcritureComptable();

		int t1 = ljc.size();
		int t2 = lseq.size();
		boolean res = false;

		if (t1 > t2) {
			res = true;
		}
		assertEquals(true, res);
	}
	
	@Test
	public void testReffeIsEQ() {
		
		List<EcritureComptable> listeEC = manager.getListEcritureComptable();
		boolean res = false;
		
		for(EcritureComptable ec : listeEC) {
			if(ec.isEquilibree()) {
				res = true;
			}
		}
		//assertEquals(true, res);
	}
	
	@Test
	public void testReff() {
		
		List<EcritureComptable> listeEC = manager.getListEcritureComptable();
		boolean res = true;
		for(EcritureComptable ec : listeEC) {
			if(!ec.isEquilibree()) {
				res = false;
			}
		}
		
		assertEquals(true, res);
	}
	
	@Test
	public void testResfEmpty() {
		
		
		List<EcritureComptable> listeEcriture = manager.getListEcritureComptable();
		
		boolean res = false;
		for(EcritureComptable sequence : listeEcriture) {
			if(sequence.getReference().isEmpty()) {
				res = true;
			}
		}
		assertEquals(false, res);
	}

	@Test
	public void testSequenceDV() {
		
		List<SequenceEcritureComptable> Sequence = manager.getListSequenceEcritureComptable();
		boolean res = true;
		for(SequenceEcritureComptable jc : Sequence) {
			if(jc.getDerniereValeur() == 0) {
				res = false;
			}
		}
		assertEquals(true, res);
	}

	
	@Test(expected = FunctionalException.class)
	public void testEC() throws FunctionalException {
		
		List<EcritureComptable> listeEc = manager.getListEcritureComptable();
		for(EcritureComptable ec : listeEc) {
			manager.checkEcritureComptable(ec);
		}
	}
	
	/*
	public void testEC2() throws FunctionalException {
		
		List<EcritureComptable> listeEc = manager.getListEcritureComptable();
		for(EcritureComptable ec : listeEc) {
			manager.checkEcritureComptableContext(ec);
		}
	}
	
	*/
	
	@Test
	public void testEC3() throws FunctionalException {
		
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(123)));
		manager.checkEcritureComptableContext(vEcritureComptable);
	}
	
}
