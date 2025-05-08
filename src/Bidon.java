/**
 * Représente un bidon d'énergie placé sur le plateau.
 * Un bidon est une réserve limitée d'énergie où les personnages 
 * peuvent puiser, mais pas stocker d'énergie.
 */
public class Bidon extends ReserveLimitee
{
    /**
     * Constructeur par défaut du bidon.
     * Initialise le bidon avec l'énergie maximale définie dans les paramètres du jeu.
     */
    public Bidon() {
        super(); // Appel au constructeur de la classe parente
    }

    /**
     * Permet de puiser une quantité d'énergie du bidon.
     * 
     * @param quantite La quantité d'énergie que l'on souhaite puiser
     * @return La quantité réellement puisée (peut être inférieure si le bidon contient moins)
     */
    @Override
    public int puiser(int quantite) {
        // Limite la quantité puisée à ce qui est disponible
        int prelevement = Math.min(quantite, this.energie);
        // Réduit l'énergie du bidon
        this.energie -= prelevement;
        // Retourne la quantité réellement prise
        return prelevement;
    }

    /**
     * Cette méthode ne fait rien car on ne peut pas stocker d'énergie dans un bidon.
     * 
     * @param quantite La quantité qu'on tenterait de stocker
     * @return Toujours 0 car on ne peut pas stocker dans un bidon
     */
    @Override
    public int stocker(int quantite) {
        return 0; // Un bidon ne stocke pas d'énergie
    }

    /**
     * Représentation textuelle du bidon.
     * 
     * @return ☼ si le bidon contient de l'énergie, ○ sinon
     */
    @Override
    public String toString() {
        return energie > 0 ? "☼" : "○"; // Symbole plein si contient de l'énergie, vide sinon
    } 
}