package com.dummy.myerp.model.bean.comptabilite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
@Entity
@Table(name="sequence_ecriture_comptable")
public class SequenceEcritureComptable implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ==================== Attributs ====================
    /** L'année */
    private Integer annee;
    /** La dernière valeur utilisée */
    private Integer derniereValeur;
    /** Journal code**/
    private String journal_code;

    // ==================== Constructeurs ====================
    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee -
     * @param pDerniereValeur -
     */
    public SequenceEcritureComptable(String journal_code,Integer pAnnee, Integer pDerniereValeur) {
        annee = pAnnee;
        derniereValeur = pDerniereValeur;
        this.journal_code = journal_code;
    }


    // ==================== Getters/Setters ====================
    public Integer getAnnee() {
        return annee;
    }
    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }
    public Integer getDerniereValeur() {
        return derniereValeur;
    }
    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }
    
    public String getJournal_code() {
		return journal_code;
	}

	public void setJournal_code(String journal_code) {
		this.journal_code = journal_code;
	}

	// ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
            .append("annee=").append(annee)
            .append(vSEP).append("derniereValeur=").append(derniereValeur)
            .append("}");
        return vStB.toString();
    }
    
  
    	
    
    
}
