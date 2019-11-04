package test_consumer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

public class ComptabiliteDAO {

	List<EcritureComptable> listeEcritureComptable = new ArrayList<>();
	List<JournalComptable> listeJournalComptable = new ArrayList<>();
	List<CompteComptable> listeCompteComptable = new ArrayList<>();
	List<SequenceEcritureComptable> listeSequenceEComptable = new ArrayList<>();

	Connection connection;
	Statement statement;
	ResultSet result;


	public List<EcritureComptable> getListeEcritureComptable() throws IOException {

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

	public void Inserer(EcritureComptable ecritureComptable) throws IOException {

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();

			String journal_code = ecritureComptable.getJournal_code();
			String reference = ecritureComptable.getReference();
			Date date = ecritureComptable.getDate();
			String libelle = ecritureComptable.getLibelle();

			String sql = "insert into MYERP.ecriture_comptable (journal_code, reference, date, libelle) values ( '"
					+ journal_code + "', '" + reference + "' ,'" + date + "', '" + libelle + "')";
			statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void supprimerEcritureComptable(int id) throws IOException{

		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();
			
			String sql = "delete from MYERP.ecriture_comptable where id = '" + id + "'";
			statement.executeQuery(sql);	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<JournalComptable> getListeJournalComptable() throws IOException {
		
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
	
public List<CompteComptable> getListeJCompteComptable() throws IOException {
		
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
	
	public List<SequenceEcritureComptable> getListeSEComptable() throws IOException {
		
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
	
	public void insertSequenceEC(SequenceEcritureComptable sequence) throws IOException{
		
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9032/db_myerp", "usr_myerp", "myerp");

			statement = connection.createStatement();

			String journal_code = sequence.getJournal_code();
			int annee = sequence.getAnnee();
			int derniere_valeur = sequence.getDerniereValeur();

			String sql = "insert into MYERP.sequence_ecriture_comptable (journal_code, annee, derniere_valeur) values ( '"
					+ journal_code + "', '" + annee + "', '" + derniere_valeur + "')";
			statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
