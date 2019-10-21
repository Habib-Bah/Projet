package test_consumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;


public class DbUnit extends DBTestCase {

	public DbUnit(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://127.0.0.1:9032/db_myerp");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "usr_myerp");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "myerp");
	}

	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("app_user.xml"));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	
	
ComptabiliteDAO bib = new ComptabiliteDAO();

	
	
	/*
	@Test
	public void testInsert() throws IOException {
			
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setJournal_code("TI");
		ecriture.setReference("TI-2019/00001");
		ecriture.setDate(new Date());
		ecriture.setLibelle("Test insert");
		
		bib.Inserer(ecriture);
		
		List<EcritureComptable> listeEcriture = bib.getListeEcritureComptable();
		boolean res = false;
		
		for(EcritureComptable ec : listeEcriture) {
			
			if(ec.getReference().equalsIgnoreCase("TI-2019/00001")) {
				res = true;
			}
		}
		assertEquals(true, res);
	}
	*/
	
	@Test
	public void testGetListeEcriture() throws IOException {
		
		List<EcritureComptable> listeEcriture = bib.getListeEcritureComptable();
		
		int nombreEntite = 5;
		int tailleListe = listeEcriture.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	
	
	@Test
	public void testDelete(int id) throws IOException {
		
		id = 7;
		
		bib.supprimerEcritureComptable(id);
		
		List<EcritureComptable> listeEcriture = bib.getListeEcritureComptable();
		boolean res = true;
		
		for(EcritureComptable ec : listeEcriture) {
			
			if(ec.getId() != id) {
				res = false;
			}
		}
		assertEquals(false, res);
	}
	
	
	@Test
	public void testGetListeJournal() throws IOException {
		
		List<JournalComptable> listeJournal = bib.getListeJournalComptable();
		
		int nombreEntite = 4;
		int tailleListe = listeJournal.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetCompteComptable() throws IOException {
		
		List<CompteComptable> listeCompteC = bib.getListeJCompteComptable();
		
		int nombreEntite = 7;
		int tailleListe = listeCompteC.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}
	
	@Test
	public void testGetSEComptable() throws IOException {
		
		List<SequenceEcritureComptable> listeSEC = bib.getListeSEComptable();
		
		int nombreEntite = 4;
		int tailleListe = listeSEC.size();
		
		
		
		assertEquals(nombreEntite, tailleListe);
	}

}
