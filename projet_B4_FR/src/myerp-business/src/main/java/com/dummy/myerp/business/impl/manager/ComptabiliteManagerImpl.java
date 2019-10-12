package com.dummy.myerp.business.impl.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionStatus;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;


/**
 * Comptabilite manager implementation.
 */
public class ComptabiliteManagerImpl extends AbstractBusinessManager implements ComptabiliteManager {

	// ==================== Attributs ====================

	List<EcritureComptable> listeEcritureComptable = new ArrayList<>();
	List<JournalComptable> listeJournalComptable = new ArrayList<>();
	List<CompteComptable> listeCompteComptable = new ArrayList<>();
	List<SequenceEcritureComptable> listeSequenceEComptable = new ArrayList<>();

	Connection connection;
	Statement statement;
	ResultSet result;

	// ==================== Constructeurs ====================
	/**
	 * Instantiates a new Comptabilite manager.
	 */
	public ComptabiliteManagerImpl() {
	}

	// ==================== Getters/Setters ====================
	@Override
	public List<CompteComptable> getListCompteComptable() {

		
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

		return listeCompteComptable;
		
		
		

	}
	
	

	@Override
	public List<SequenceEcritureComptable> getListSequenceEcritureComptable() {

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

		return listeSequenceEComptable;

	}
	
	

	@Override
	public List<JournalComptable> getListJournalComptable() {

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
		return listeJournalComptable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EcritureComptable> getListEcritureComptable() {

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

		return listeEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 */
	// TODO à tester
	@Override
	public synchronized void addReference(EcritureComptable pEcritureComptable) {
		// TODO à implémenter
		// Bien se réferer à la JavaDoc de cette méthode !
		/*
		 * Le principe : 1. Remonter depuis la persitance la dernière valeur de la
		 * séquence du journal pour l'année de l'écriture (table
		 * sequence_ecriture_comptable) 2. * S'il n'y a aucun enregistrement pour le
		 * journal pour l'année concernée : Utiliser le numéro 1. Sinon : Utiliser la
		 * dernière valeur + 1 3. Mettre à jour la référence de l'écriture avec la
		 * référence calculée (RG_Compta_5) 4. Enregistrer (insert/update) la valeur de
		 * la séquence en persitance (table sequence_ecriture_comptable)
		 */

		
		/*
		int dernière_valeur = 0;

		try {
			List<SequenceEcritureComptable> listeSequence = getListSequenceEComptable();
			SequenceEcritureComptable sequence = new SequenceEcritureComptable();

			for (SequenceEcritureComptable se : listeSequence) {
				if (se.getJournal_code().equalsIgnoreCase(pEcritureComptable.getJournal().getCode())) {

					dernière_valeur = se.getDerniereValeur() + 1;
					String reference = se.getJournal_code() + "-" + se.getAnnee() + "/" + "00001";
					pEcritureComptable.setReference(reference);
					sequence.setJournal_code(se.getJournal_code());
					sequence.setAnnee(se.getAnnee());
					sequence.setDerniereValeur(dernière_valeur);

				}

				else {

					sequence.setJournal_code(pEcritureComptable.getJournal().getCode());
					sequence.setAnnee(pEcritureComptable.getDate().getYear());
					sequence.setDerniereValeur(1);
					String reference = sequence.getJournal_code() + "-" + se.getAnnee() + "/" + "00001";
					pEcritureComptable.setReference(reference);

				}

				// insertSEcritureComptable(sequence);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		*/
	}

	/**
	 * {@inheritDoc}
	 */
	// TODO à tester
	@Override
	public void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
		this.checkEcritureComptableUnit(pEcritureComptable);
		this.checkEcritureComptableContext(pEcritureComptable);
	}

	/**
	 * Vérifie que l'Ecriture comptable respecte les règles de gestion unitaires,
	 * c'est à dire indépendemment du contexte (unicité de la référence, exercie
	 * comptable non cloturé...)
	 *
	 * @param pEcritureComptable -
	 * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les
	 *                             règles de gestion
	 */
	// TODO tests à compléter
	protected void checkEcritureComptableUnit(EcritureComptable pEcritureComptable) throws FunctionalException {
		// ===== Vérification des contraintes unitaires sur les attributs de l'écriture
		Set<ConstraintViolation<EcritureComptable>> vViolations = getConstraintValidator().validate(pEcritureComptable);
		if (!vViolations.isEmpty()) {
			throw new FunctionalException("L'écriture comptable ne respecte pas les règles de gestion.",
					new ConstraintViolationException(
							"L'écriture comptable ne respecte pas les contraintes de validation", vViolations));
		}

		// ===== RG_Compta_2 : Pour qu'une écriture comptable soit valide, elle doit
		// être équilibrée
		if (!pEcritureComptable.isEquilibree()) {
			throw new FunctionalException("L'écriture comptable n'est pas équilibrée.");
		}

		// ===== RG_Compta_3 : une écriture comptable doit avoir au moins 2 lignes
		// d'écriture (1 au débit, 1 au crédit)
		int vNbrCredit = 0;
		int vNbrDebit = 0;
		for (LigneEcritureComptable vLigneEcritureComptable : pEcritureComptable.getListLigneEcriture()) {
			if (BigDecimal.ZERO
					.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getCredit(), BigDecimal.ZERO)) != 0) {
				vNbrCredit++;
			}
			if (BigDecimal.ZERO
					.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getDebit(), BigDecimal.ZERO)) != 0) {
				vNbrDebit++;
			}
		}
		// On test le nombre de lignes car si l'écriture à une seule ligne
		// avec un montant au débit et un montant au crédit ce n'est pas valable
		if (pEcritureComptable.getListLigneEcriture().size() < 2 || vNbrCredit < 1 || vNbrDebit < 1) {
			throw new FunctionalException(
					"L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
		}

		// TODO ===== RG_Compta_5 : Format et contenu de la référence
		// vérifier que l'année dans la référence correspond bien à la date de
		// l'écriture, idem pour le code journal...
		// Formatage de la date de l'ecriture
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = pEcritureComptable.getDate();
		String dated = df.format(today);

		// Recuperation de l'année d'ecriture
		String dateRes[] = dated.split("/");
		String Dateres = dateRes[2];

		// Recuperation de la date de reference
		String ref = pEcritureComptable.getReference();

		if (((ref == null) || ref.equals("") || ref.equals(" "))) {
			addReference(pEcritureComptable);
		}

		else {

			String[] reff = ref.split("/");
			String resReff1 = reff[0];
			String refDate[] = resReff1.split("-");
			String date1 = refDate[1];
			String jour = refDate[0];
			String jour1 = pEcritureComptable.getJournal().getCode();

			if (!date1.equalsIgnoreCase(Dateres)) {
				throw new FunctionalException("L'année dans la référence ne correspond pas à la date de l'écriture.");
			}
			if (!jour1.equalsIgnoreCase(jour)) {
				throw new FunctionalException("Le code journal ne correspond pas");
			}
		}

	}

	/**
	 * Vérifie que l'Ecriture comptable respecte les règles de gestion liées au
	 * contexte (unicité de la référence, année comptable non cloturé...)
	 *
	 * @param pEcritureComptable -
	 * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les
	 *                             règles de gestion
	 */
	protected void checkEcritureComptableContext(EcritureComptable pEcritureComptable) throws FunctionalException {
		// ===== RG_Compta_6 : La référence d'une écriture comptable doit être unique
		if (StringUtils.isNoneEmpty(pEcritureComptable.getReference())) {
			try {
				// Recherche d'une écriture ayant la même référence
				EcritureComptable vECRef = getDaoProxy().getComptabiliteDao()
						.getEcritureComptableByRef(pEcritureComptable.getReference());

				// Si l'écriture à vérifier est une nouvelle écriture (id == null),
				// ou si elle ne correspond pas à l'écriture trouvée (id != idECRef),
				// c'est qu'il y a déjà une autre écriture avec la même référence
				if (pEcritureComptable.getId() == null || !pEcritureComptable.getId().equals(vECRef.getId())) {
					throw new FunctionalException("Une autre écriture comptable existe déjà avec la même référence.");
				}
			} catch (NotFoundException vEx) {
				// Dans ce cas, c'est bon, ça veut dire qu'on n'a aucune autre écriture avec la
				// même référence.
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {

	/*	
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();

			String journal_code = pEcritureComptable.getJournal().getCode();
			String reference = pEcritureComptable.getReference();
			Date date = pEcritureComptable.getDate();
			String libelle = pEcritureComptable.getLibelle();

			String sql = "insert into MYERP.ecriture_comptable (journal_code, reference, date, libelle) values ( '"
					+ journal_code + "', '" + reference + "' ,'" + date + "', '" + libelle + "')";
			statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertSEcritureComptable(SequenceEcritureComptable pEcritureComptable) throws FunctionalException {

		/*
		 * TransactionStatus vTS = getTransactionManager().beginTransactionMyERP(); try
		 * {
		 * getDaoProxy().getComptabiliteDao().updateEcritureComptable(pEcritureComptable
		 * ); getTransactionManager().commitMyERP(vTS); vTS = null; } finally {
		 * getTransactionManager().rollbackMyERP(vTS); }
		 * 
		 */

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();

			String journal_code = pEcritureComptable.getJournal_code();
			int annee = pEcritureComptable.getAnnee();
			int derniere_valeur = pEcritureComptable.getDerniereValeur();

			String sql = "insert into MYERP.sequence_ecriture_comptable (journal_code, annee, derniere_valeur) values ( '"
					+ journal_code + "', '" + annee + "', '" + derniere_valeur + "')";
			statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEcritureComptable(Integer pId) {
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();

			String sql = "delete from MYERP.ecriture_comptable where id = '" + pId + "'";
			statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
