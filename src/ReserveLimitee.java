/**
 * Représente une réserve d'énergie limitée par une capacité maximale.
 * Utilisée par les personnages pour stocker leur énergie.
 * La capacité maximale est définie dans les paramètres du jeu.
 */
public class ReserveLimitee extends Reservoir {
    
    /**
     * Constructeur d'une réserve limitée.
     * Initialise la réserve avec l'énergie maximale définie dans les paramètres du jeu.
     */
    public ReserveLimitee() {
        super(ParametresJeu.getMaxEnergie()); // Pleine énergie au départ
    }

    /**
     * Puise une quantité d'énergie de la réserve.
     * La quantité puisée est limitée par l'énergie disponible.
     * 
     * @param quantite Quantité d'énergie à puiser
     * @return Quantité effectivement puisée
     */
    @Override
    public int puiser(int quantite) {
        // Limite la quantité puisée à ce qui est disponible
        int prelevement = Math.min(quantite, this.energie);
        // Réduit l'énergie de la réserve
        this.energie -= prelevement;
        // Retourne la quantité réellement prise
        return prelevement;
    }

    /**
     * Stocke une quantité d'énergie dans la réserve.
     * La quantité stockée est limitée par l'espace disponible jusqu'à la capacité maximale.
     * 
     * @param quantite Quantité d'énergie à stocker
     * @return Quantité effectivement stockée
     */
    @Override
    public int stocker(int quantite) {
        // Calcule l'espace disponible dans la réserve
        int espace = ParametresJeu.getMaxEnergie() - this.energie;
        // Limite la quantité ajoutée à l'espace disponible
        int ajoute = Math.min(quantite, espace);
        // Augmente l'énergie de la réserve
        this.energie += ajoute;
        // Retourne la quantité réellement stockée
        return ajoute;
    }
}