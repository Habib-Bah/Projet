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

		assertEquals(true, res);
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

		assertEquals(false, res);
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

		assertEquals(false, res);
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

	@Test
	public void testGetListeLEC() {

		List<LigneEcritureComptable> liste = manager.getLigneEcritureComptable();
		int nombreEntite = 13;
		int tailleListe = liste.size();

		assertEquals(nombreEntite, tailleListe);

	}

	@Test
	public void testInsertCompteC() throws FunctionalException {

		CompteComptable c = new CompteComptable();
		c.setLibelle("Test insertion");
		c.setNumero(415);

		manager.insertCompteComptable(c);
	}

	@Before
	public void before() {

		List<EcritureComptable> liste = manager.getListEcritureComptable();

		t = liste.size();

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

		List<EcritureComptable> listeEcriture = manager.getListEcritureComptable();
		boolean res = false;

		for (EcritureComptable ec : listeEcriture) {

			if (ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				res = true;
			}
		}
		assertEquals(true, res);
	}

	@Test
	public void testInsertSE() throws FunctionalException {
		
		SequenceEcritureComptable se = new SequenceEcritureComptable();
		se.setAnnee(2019);
		se.setDerniereValeur(1000);
		se.setJournal_code("AC");
		
		manager.insertSEcritureComptable(se);
	}
	
	@Test
	public void testinsertJC() throws FunctionalException {
		
		JournalComptable j = new JournalComptable();
		j.setCode("TI");
		j.setLibelle("Test insert");
		manager.insertJournalComptable(j);
	}
		
	
	@After
	public void after() {

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
		
		List<SequenceEcritureComptable> listeSE = manager.getListSequenceEcritureComptable();
		
		for(SequenceEcritureComptable se : listeSE) {
			if(se.getDerniereValeur() == 1000) {
				manager.deleteSEComptable(se.getDerniereValeur());
			}
		}
		
		
		List<JournalComptable> listeJC = manager.getListJournalComptable();
		
		for(JournalComptable jc : listeJC) {
			if(jc.getLibelle().equalsIgnoreCase("Test insert")) {
				manager.deleteJComptable(jc.getLibelle());
			}
		}

	}

}
