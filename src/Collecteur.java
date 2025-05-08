/**
 * Représente le collecteur d'énergie appartenant au joueur.
 * C'est un réservoir sans limite qui ne peut que stocker de l'énergie,
 * jamais en fournir. L'énergie stockée dans le collecteur est le score final.
 */
public class Collecteur extends Reservoir
{
    /**
     * Constructeur par défaut du collecteur.
     * Initialise le collecteur avec 0 d'énergie.
     */
    public Collecteur() {
        super(0); // Appel au constructeur de la classe parente avec 0 énergie
    }

    /**
     * Il est impossible de puiser de l'énergie dans le collecteur.
     * 
     * @param quantite La quantité que l'on tenterait de puiser
     * @return Toujours 0 car on ne peut pas puiser dans le collecteur
     */
    @Override
    public int puiser(int quantite) {
        return 0; // Impossible de puiser dans le collecteur
    }

    /**
     * Stocke de l'énergie dans le collecteur sans limite.
     * 
     * @param quantite La quantité d'énergie à stocker
     * @return La quantité effectivement stockée (toujours égale à quantite)
     */
    @Override
    public int stocker(int quantite) {
        this.energie += quantite; // Stockage sans limite
        return quantite; // Retourne la quantité stockée
    }

    /**
     * Représentation textuelle du collecteur.
     * 
     * @return Symbole représentant le collecteur (C)
     */
    @Override
    public String toString() {
        return "C"; // Symbole pour le collecteur
    }
}