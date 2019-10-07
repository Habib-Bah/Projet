package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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
                                                                    vLibelle,
                                                                    vDebit, vCredit);
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


}
