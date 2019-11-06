package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

public class EcritureComptableTest {

	private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
		BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
		BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
		String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
				.subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
		LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
				vLibelle, vDebit, vCredit);
		return vRetour;
	}
	
	@Test
	public void isEquilibree() {
		EcritureComptable vEcriture;
		vEcriture = new EcritureComptable();

		vEcriture.setLibelle("Equilibrée");
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
		Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

		vEcriture.getListLigneEcriture().clear();
		vEcriture.setLibelle("Non équilibrée");
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
		Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
	}

	@Test
	public void testgetTotalDebitEcritureCompta() {

		CompteComptable compteComptable = new CompteComptable(1, "compte 4");

		LigneEcritureComptable ligneEcriture = new LigneEcritureComptable();
		ligneEcriture.setCompteComptable(compteComptable);

		// Paramètre pour le teste
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);
		BigDecimal debit2 = new BigDecimal(20.27);
		BigDecimal credit2 = new BigDecimal(1000.00);
		LigneEcritureComptable ligneEC1 = new LigneEcritureComptable(compteComptable, compteComptable.getLibelle(),
				debit, credit);
		LigneEcritureComptable ligneEC2 = new LigneEcritureComptable(compteComptable, compteComptable.getLibelle(),
				debit2, credit2);

		// Methode A tester
		EcritureComptable ecritureCompta = new EcritureComptable();
		List<LigneEcritureComptable> liste = ecritureCompta.getListLigneEcriture();
		liste.add(ligneEC1);
		liste.add(ligneEC2);

		BigDecimal result = debit.add(debit2);
		BigDecimal res = ecritureCompta.getTotalDebit();

		assertEquals(result, res);
	}

	@Test
	public void testgetTotalCreditEcritureCompta() {

		CompteComptable compteComptable = new CompteComptable(2, "compte 5");

		LigneEcritureComptable ligneEcriture = new LigneEcritureComptable();
		ligneEcriture.setCompteComptable(compteComptable);

		// Paramètre pour le teste
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(36.10);
		BigDecimal debit2 = new BigDecimal(20.27);
		BigDecimal credit2 = new BigDecimal(70.10);
		LigneEcritureComptable ligneEC1 = new LigneEcritureComptable(compteComptable, compteComptable.getLibelle(),
				debit, credit);
		LigneEcritureComptable ligneEC2 = new LigneEcritureComptable(compteComptable, compteComptable.getLibelle(),
				debit2, credit2);

		// Methode A tester
		EcritureComptable ecritureCompta = new EcritureComptable();
		List<LigneEcritureComptable> liste = ecritureCompta.getListLigneEcriture();
		liste.add(ligneEC1);
		liste.add(ligneEC2);

		BigDecimal result = credit.add(credit2);
		BigDecimal res = ecritureCompta.getTotalCredit();

		assertEquals(result, res);
	}

	@Test
	public void testGetNumero() {

		CompteComptable compte = new CompteComptable(5, "test");
		int res = compte.getNumero();
		assertEquals(5, res);
	}

	@Test
	public void testSetNumero() {

		CompteComptable compte = new CompteComptable(5, "test");
		compte.setNumero(6);

		int res = compte.getNumero();
		assertEquals(6, res);
	}

	@Test
	public void testGetLibelle() {

		CompteComptable compte = new CompteComptable(5, "test");
		compte.setLibelle("Libelle setter");

		String res = compte.getLibelle();

		assertEquals("Libelle setter", res);
	}

	@Test
	public void testsetLibelle() {

		CompteComptable compte = new CompteComptable(5, "test");
		compte.setLibelle("test setter");
		String res = compte.getLibelle();
		assertEquals("test setter", res);
	}

	/* Sequence Ecriture Comptable */

	@Test
	public void testSequenceGetAnnee() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		int annee = sequence.getAnnee();
		assertEquals(2019, annee);
	}

	@Test
	public void testSequenceSetAnnee() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		sequence.setAnnee(2020);
		int annee = sequence.getAnnee();
		assertEquals(2020, annee);
	}

	@Test
	public void testSequenceGetDerniereV() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		int derniereV = sequence.getDerniereValeur();
		assertEquals(1, derniereV);
	}

	@Test
	public void testSequenceSetDerniereV() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		sequence.setDerniereValeur(2);
		int derniereV = sequence.getDerniereValeur();
		assertEquals(2, derniereV);
	}

	@Test
	public void testSequenceGetJournalC() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		String JournalC = sequence.getJournal_code();
		assertEquals("AC", JournalC);
	}

	@Test
	public void testSequenceSetJournalC() {

		SequenceEcritureComptable sequence = new SequenceEcritureComptable("AC", 2019, 1);
		sequence.setJournal_code("ACC");
		String JournalC = sequence.getJournal_code();
		assertEquals("ACC", JournalC);
	}

	/* Journal Comptable */

	@Test
	public void testJournalGetCode() {
		JournalComptable journal = new JournalComptable("TT", "Test");
		String code = journal.getCode();
		assertEquals("TT", code);
	}

	@Test
	public void testJournalSetCode() {
		JournalComptable journal = new JournalComptable("TT", "Test");
		journal.setCode("TIS");
		String code = journal.getCode();
		assertEquals("TIS", code);
	}

	@Test
	public void testJournalGetLibelle() {
		JournalComptable journal = new JournalComptable("TT", "Test");
		String code = journal.getLibelle();
		assertEquals("Test", code);
	}

	@Test
	public void testJournalSetLibelle() {
		JournalComptable journal = new JournalComptable("TT", "Test");
		journal.setLibelle("Test setter");
		String code = journal.getLibelle();
		assertEquals("Test setter", code);
	}

	/* Ligne Ecriture Comptable */

	@Test
	public void testLigneECGetLib() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		String libelle = ligne.getLibelle();
		assertEquals("Test", libelle);
	}

	@Test
	public void testLigneECSetLib() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		ligne.setLibelle("Test setter");
		String libelle = ligne.getLibelle();
		assertEquals("Test setter", libelle);
	}

	@Test
	public void testLigneECGetDebit() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		BigDecimal deb = ligne.getDebit();
		assertEquals(debit, deb);
	}

	@Test
	public void testLigneECSetDebit() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		BigDecimal debitSet = new BigDecimal(20.27);
		ligne.setDebit(debitSet);

		BigDecimal deb = ligne.getDebit();
		assertEquals(debitSet, deb);

	}

	@Test
	public void testLigneECGetCredit() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		BigDecimal deb = ligne.getCredit();
		assertEquals(credit, deb);
	}

	@Test
	public void testLigneECSetCredit() {
		CompteComptable compte = new CompteComptable(1, "test");
		BigDecimal debit = new BigDecimal(72.27);
		BigDecimal credit = new BigDecimal(9234.27);

		LigneEcritureComptable ligne = new LigneEcritureComptable(compte, "Test", debit, credit);

		BigDecimal creditSet = new BigDecimal(20.27);
		ligne.setCredit(creditSet);

		BigDecimal deb = ligne.getCredit();
		assertEquals(creditSet, deb);

	}

	/* Ecriture Comptable */

	@Test
	public void testEcritureCGetRefe() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		String reference = ecriture.getReference();
		assertEquals("RF-2019/00001", reference);
	}

	@Test
	public void testEcritureCSetRefe() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		ecriture.setReference("RF-2019/00002");
		String reference = ecriture.getReference();
		assertEquals("RF-2019/00002", reference);
	}

	@Test
	public void testEcritureCGetLibel() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		String libelle = ecriture.getLibelle();
		assertEquals("Test", libelle);
	}

	@Test
	public void testEcritureCSetLibel() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		ecriture.setLibelle("Test setter");
		String libelle = ecriture.getLibelle();
		assertEquals("Test setter", libelle);
	}

	@Test
	public void testEcritureCGetCode() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		String code = ecriture.getJournal_code();
		assertEquals("TT", code);
	}

	@Test
	public void testEcritureCSetCode() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");

		ecriture.setJournal_code("TTI");
		String code = ecriture.getJournal_code();
		assertEquals("TTI", code);
	}

	@Test
	public void testEcritureCGetSetId() {

		EcritureComptable ecriture = new EcritureComptable("TT", "RF-2019/00001", new Date(), "Test");
		ecriture.setId(1);
		int id = ecriture.getId();
		assertEquals(1, id);
	}

	@Test
	public void testListeEC() {

		List<EcritureComptable> liste = new ArrayList<>();

		EcritureComptable ecriture1 = new EcritureComptable("TT1", "RF-2019/00001", new Date(), "Test1");
		EcritureComptable ecriture2 = new EcritureComptable("TT2", "RF-2019/00002", new Date(), "Test2");

		liste.add(ecriture1);
		liste.add(ecriture2);
		int taille = liste.size();

		assertEquals(2, taille);
	}

	@Test
	public void testLibelleEcTaille() {

		List<EcritureComptable> liste = new ArrayList<>();

		EcritureComptable ecriture1 = new EcritureComptable("TT1", "RF-2019/00001", new Date(), "Test1");
		EcritureComptable ecriture2 = new EcritureComptable("TT2", "RF-2019/00002", new Date(), "Test2");

		liste.add(ecriture1);
		liste.add(ecriture2);
		boolean res = false;

		for (EcritureComptable c : liste) {
			if (c.getLibelle().length() < 1 || c.getLibelle().length() > 200) {
				res = true;
			}
		}

		assertEquals(false, res);
	}
	
	@Test
	public void testCCLibelleSize() {
		
		List<CompteComptable> liste = new ArrayList<>();
		
		CompteComptable c1 = new CompteComptable(1, "c1");
		CompteComptable c2 = new CompteComptable(2, "c2");
		CompteComptable c3 = new CompteComptable(3, "c3");
		liste.add(c1);
		liste.add(c2);
		liste.add(c3);
		boolean res = false;
		
		for (CompteComptable c : liste) {
			if (c.getLibelle().length() < 1 || c.getLibelle().length() > 150) {
				res = true;
			}
		}

		assertEquals(false, res);
	}
	
	
	Connection connection;
	Statement statement;
	ResultSet result;

	
	@Test
	public void testGetListeCompteC() {

		List<CompteComptable> listeCompteComptable = new ArrayList<>();
		
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from MYERP.compte_comptable");

			while (result.next()) {

				CompteComptable cp = new CompteComptable();

				int numero = result.getInt(1);
				String libelle = result.getString(2);

				cp.setNumero(numero);
				cp.setLibelle(libelle);

				listeCompteComptable.add(cp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<CompteComptable> listeCompteC = listeCompteComptable;

		int nombreEntite = 7;
		int tailleListe = listeCompteC.size();

		assertEquals(nombreEntite, tailleListe);
		
	}

	

	
	@Test
	public void testGetSEComptable() throws IOException {
		
		List<SequenceEcritureComptable> listeSequenceEComptable = new ArrayList<>();

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from MYERP.sequence_ecriture_comptable");

			while (result.next()) {

				SequenceEcritureComptable cp = new SequenceEcritureComptable();

				String journal_code = result.getString(1);
				int annee = result.getInt(2);
				int derniere_valeur = result.getInt(3);

				cp.setAnnee(annee);
				cp.setDerniereValeur(derniere_valeur);
				cp.setJournal_code(journal_code);

				listeSequenceEComptable.add(cp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<SequenceEcritureComptable> listeSEC = listeSequenceEComptable;

		int nombreEntite = 4;
		int tailleListe = listeSEC.size();

		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetListeJournal() throws IOException {

List<JournalComptable> listeJournalComptable = new ArrayList<>();
		
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from MYERP.journal_comptable");

			while (result.next()) {

				JournalComptable jc = new JournalComptable();

				String code = result.getString(1);
				String libelle = result.getString(2);

				jc.setCode(code);
				jc.setLibelle(libelle);

				listeJournalComptable.add(jc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<JournalComptable> listeJournal = listeJournalComptable;

		int nombreEntite = 4;
		int tailleListe = listeJournal.size();

		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetListeEcriture() throws IOException {
		
		List<EcritureComptable> listeEcritureComptable = new ArrayList<>();

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();
			result = statement.executeQuery("select * from MYERP.ecriture_comptable");

			while (result.next()) {

				EcritureComptable ec = new EcritureComptable();

				String journal_comptable = result.getString(2);
				String reference = result.getString(3);
				Date date = result.getDate(4);
				String libelle = result.getString(5);

				ec.setDate(date);
				ec.setJournal_code(journal_comptable);
				ec.setLibelle(libelle);
				ec.setReference(reference);

				listeEcritureComptable.add(ec);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<EcritureComptable> listeEcriture = listeEcritureComptable;

		int nombreEntite = 5;
		int tailleListe = listeEcriture.size();

		assertEquals(nombreEntite, tailleListe);
	}
}
