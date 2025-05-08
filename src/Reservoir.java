/**
 * Classe abstraite représentant une réserve d'énergie.
 * L'énergie est représentée par une quantité entière positive ou nulle.
 * Les sous-classes déterminent les règles de puisage et stockage de l'énergie.
 */
public abstract class Reservoir
{
    /** Quantité d'énergie actuellement contenue dans le réservoir */
    protected int energie;

    /**
     * Constructeur d'un réservoir avec une quantité d'énergie initiale.
     * 
     * @param energie Quantité d'énergie initiale
     */
    public Reservoir(int energie) {
        this.energie = energie;
    }
 
    /**
     * Récupère la quantité d'énergie contenue dans le réservoir.
     * 
     * @return Quantité d'énergie
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * Définit la quantité d'énergie contenue dans le réservoir.
     * La valeur est toujours maintenue positive ou nulle.
     * 
     * @param energie Nouvelle quantité d'énergie
     */
    public void setEnergie(int energie) {
        this.energie = Math.max(0, energie); // Assure que l'énergie n'est jamais négative
    }

    /**
     * Méthode abstraite pour puiser de l'énergie du réservoir.
     * Les règles de puisage sont définies par les sous-classes.
     * 
     * @param quantite Quantité d'énergie à puiser
     * @return Quantité effectivement puisée
     */
    public abstract int puiser(int quantite);
    
    /**
     * Méthode abstraite pour stocker de l'énergie dans le réservoir.
     * Les règles de stockage sont définies par les sous-classes.
     * 
     * @param quantite Quantité d'énergie à stocker
     * @return Quantité effectivement stockée
     */
    public abstract int stocker(int quantite);
}