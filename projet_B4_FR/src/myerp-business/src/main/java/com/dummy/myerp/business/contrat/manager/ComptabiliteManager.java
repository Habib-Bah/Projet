package com.dummy.myerp.business.contrat.manager;

import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;


/**
 * Interface du manager du package comptabilite.
 */
public interface ComptabiliteManager {

    /**
     * Renvoie la liste des comptes comptables.
     *
     * @return {@link List}
     */
    List<CompteComptable> getListCompteComptable();
    

    /**
     * Renvoie la liste des journaux comptables.
     *
     * @return {@link List}
     */
    List<JournalComptable> getListJournalComptable();
    
    /**
     * Renvoie la liste des ligne d'ecriture comptables.
     *
     * @return {@link List}
     */
    List<LigneEcritureComptable> getLigneEcritureComptable();


    /**
     * Renvoie la liste des écritures comptables.
     *
     * @return {@link List}
     */
    List<EcritureComptable> getListEcritureComptable();
    
    /**
     * Renvoie la liste des sequences comptables.
     *
     * @return {@link List}
     */
    List<SequenceEcritureComptable> getListSequenceEcritureComptable();
    

    /**
     * Ajoute une référence à l'écriture comptable.
     *
     * <strong>RG_Compta_5 : </strong>
     * La référence d'une écriture comptable est composée du code du journal dans lequel figure l'écriture
     * suivi de l'année et d'un numéro de séquence (propre à chaque journal) sur 5 chiffres incrémenté automatiquement
     * à chaque écriture. Le formatage de la référence est : XX-AAAA/#####.
     * <br>
     * Ex : Journal de banque (BQ), écriture au 31/12/2016
     * <pre>BQ-2016/00001</pre>
     *
     * <p><strong>Attention :</strong> l'écriture n'est pas enregistrée en persistance</p>
     * @param pEcritureComptable L'écriture comptable concernée
     */
    void addReference(EcritureComptable pEcritureComptable);

    /**
     * Vérifie que l'Ecriture comptable respecte les règles de gestion.
     *
     * @param pEcritureComptable -
     * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les règles de gestion
     */
    void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException;

    /**
     * Insert une nouvelle écriture comptable.
     *
     * @param pEcritureComptable -
     * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les règles de gestion
     */
   // void insertEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException;

    /**
     * insert une nouvelle sequence ecriture comptable.
     *
     * @param pEcritureComptable -
     * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les règles de gestion
     */
    void insertSEcritureComptable(SequenceEcritureComptable pEcritureComptable) throws FunctionalException;

    /**
     * insert une nouvelle sequence ecriture comptable.
     *
     * @param pEcritureComptable
     */
    
    //void insertCompteComptable(CompteComptable pCompteComptable) throws FunctionalException;

    /**
     * insert un nouveau  compte comptable.
     *
     * @param pEcritureComptable
     */
    
   // void insertJournalComptable(JournalComptable pjournalComptable) throws FunctionalException;

    /**
     * insert un nouveau  journal comptable.
     *
     * @param pjournalComptable
     */
    
   // void insertLigneEcritureC(LigneEcritureComptable ligne) throws FunctionalException;

    /**
     * insert un nouveau  journal comptable.
     *
     * @param pjournalComptable
     */
   
   // void deleteCompteComptable(int pNumero);

    /**
     * supprime un  compte comptable.
     *
     * @param pNumero numero de l'écriture
     */
    
 
    
    void deleteSEComptable(int derniereValeur);

    /**
     * supprime une sequence Ecriture comptable.
     *
     * @param pNumero numero de l'écriture
     */
    
   // void deleteJComptable(String libelle);

    /**
     * supprime une ecriture comptable.
     *
     * @param reference de l'ecriture
     */
    
  //  void deleteEcritureComptable(String reference);
    
    /**
     * supprime une ligne comptable.
     *
     * @param libelle de la ligne
     */
    
  //  void deleteLigneEcritureComptable(String libelle);
    
}
