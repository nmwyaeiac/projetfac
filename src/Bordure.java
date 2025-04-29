/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Salles qui matérialisent le bord du plateau, auxquelles les personnages ne peuvent pas accéder.
 * Elle peuvent servir à gérer simplement les sorties de plateau — plutôt que de faire des tests systématiques.
 * Une tentative d'accès à ces salles est ignorée et peut éventuellement induire l'affichage d'un message d'erreur.
 * Si le plateau a nbCol colonnes et nbLig lignes effectives, numérotées de 1 à nbCol/nbLig, ces salles sont situées aux lignes/colonnes d'indice 0 et nbLig+1/nbCol+1
 * @author jo
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Salles qui matérialisent le bord du plateau, auxquelles les personnages ne peuvent pas accéder.
 * Elle peuvent servir à gérer simplement les sorties de plateau — plutôt que de faire des tests systématiques.
 * Une tentative d'accès à ces salles est ignorée et peut éventuellement induire l'affichage d'un message d'erreur.
 * Si le plateau a nbCol colonnes et nbLig lignes effectives, numérotées de 1 à nbCol/nbLig, ces salles sont situées aux lignes/colonnes d'indice 0 et nbLig+1/nbCol+1
 * @author jo
 */
public class Bordure extends Salle
{

    /**
     * Restitue l'aspect du bord du plateau. Par exemple, ██
     * @return
     */
    @Override
    public String toString()
    {
        return "░░";
    }
    
    public Bordure(int lig, int col, Plateau p)
    {
        super(lig, col, p);
    }

    @Override
    public void entre(Personnage p)
    {
        // Un personnage ne peut pas entrer dans une bordure, on affiche un message d'erreur
        System.out.println("Tentative d'accès à une bordure. Déplacement impossible.");
    }
}