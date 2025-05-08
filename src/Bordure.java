/**
 * Représente les bordures du plateau de jeu.
 * Ces salles spéciales matérialisent le bord du plateau et sont inaccessibles aux personnages.
 * Elles permettent de gérer simplement les limites du plateau sans avoir à faire
 * des tests systématiques pour vérifier si un déplacement est valide.
 */
public class Bordure extends Salle
{
    /**
     * Constructeur d'une bordure.
     * 
     * @param lig Numéro de ligne dans le plateau
     * @param col Numéro de colonne dans le plateau
     * @param p Référence au plateau contenant cette bordure
     */
    public Bordure(int lig, int col, Plateau p)
    {
        super(lig, col, p); // Appel au constructeur de la classe parente
    }

    /**
     * Représentation textuelle de la bordure.
     * 
     * @return Symbole représentant une bordure (░░)
     */
    @Override
    public String toString()
    {
        return "░░"; // Symbole pour une bordure
    }
    
    /**
     * Gère la tentative d'entrée d'un personnage dans une bordure.
     * L'entrée est toujours refusée avec un message d'erreur.
     * 
     * @param p Le personnage qui tente d'entrer dans la bordure
     */
    @Override
    public void entre(Personnage p)
    {
        // Un personnage ne peut pas entrer dans une bordure, on affiche un message d'erreur
        System.out.println("Tentative d'accès à une bordure. Déplacement impossible.");
    }
}