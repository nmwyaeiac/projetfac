/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Réserve d'énergie qui peut être vide.
 * Elle est représentée par une quantité d'énergie entière positive ou nulle.
 * @author jo
 */
public abstract class Reservoir
{
    /*
    * Déclaration des attributs d'un réservoir et accesseurs.
    */

    /**
     * Restitue la quantité d'énergie disponible
     * @return un nombre entier positif ou nul
     */
    public int getContenu()
    {
        return 0; /* À remplacer par le code effectif */
    }
    /**
     * Affecte une quantité d'énergie initiale
     * @param energie
     */
    private void setEnergie(int energie)
    {
        /* Code à spécifier */
    }
    
    /**
     * Restitue true si la réserve est vide
     * @return un booléen
     */
    public boolean estVide()
    {
        return false; /* À remplacer par le code effectif */
    }
    
    /**
     * Ajustement du montant positif que l'on veut ajouter à un réservoir d'énergie.
     * Le code dépend du type de réservoir
     * @param montant La quantité d'énergie à ajouter au réservoir
     * @return La part du montant qu'il est possible d'ajouter au réservoir
     */
    public abstract int ajustementAjout(int montant);

    /**
     * Ajustement du montant positif que l'on veut retirer au réservoir.
     * Le montant que l'on peut retirer ne peut pas dépasser la quantité disponible.
     * @param montant La quantité d'énergie à retirer au réservoir
     * @return La part du montant qu'il est possible de retirer au réservoir
     */
    public int ajustementRetrait(int montant)
    {
        return 0; /* À remplacer par le code effectif */
    }
    
    /**
     * Modifie la quantité d'énergie disponible d'un montant spécifié positif ou négatif
     * @param montant
     */
    public void modifEnergie(int montant)
    {
        /* Code à spécifier */
    }

    /**
     * Création d'un nouveau réservoir avec une énergie initiale
     * @param energie
     */
    public Reservoir(/*…*/)
    {
        /* Code à spécifier */
    }

    /**
     * Transfert d'un montant positig d'énergie d'un réservoir externe au réservoir courant
     * @param montant
     * @param autre
     */
    public void transfereDe(int montant, Reservoir autre)
    {
        /* Code à spécifier */
    }
}
