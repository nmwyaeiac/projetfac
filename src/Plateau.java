/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente le plateau du jeu défini par un tableau de salles.
 * 
 * @author jo
 */
public class Plateau {
  /*
   * Déclaration des attributs d'un plateau et accesseurs.
   */
  public static final String BORD = "█";
  public static final String FOND = "░";
  private Jeu jeu;
  private Salle[][] grille;

  /**
   * Restitue le nombre effectif de lignes occupables (sans compter les bords).
   * 
   * @return un nombre entier
   */
  public int getNbLig() {
    return grille.length;
  }

  /**
   * Restitue le nombre effectif de colonnes occupables (sans compter les bords).
   * 
   * @return un nombre entier
   */
  public int getNbCol() {
    return grille[0].length;
  }

  /**
   * Restitue la salle d'indices lig et col.
   * Si lig et col ne sont pas valide, la méthode restitue null
   * 
   * @param lig numéro de ligne
   * @param col idem lignes
   * @return la salle de numéro de ligne lig et de numéro de colonne col, et null
   *         si lig ou col n'est pas valide
   */
  public Salle getSalle(int lig, int col) {
    return (lig >= 0 && lig < getNbLig() && col >= 0 && col < getNbCol()) ? grille[lig][col] : null;
  }

  /**
   * Affecte une salle dans le plateau à la position lig-col si cette position est
   * valide
   * 
   * @param s   La salle à affecter
   * @param lig numéro de ligne valide
   * @param col numéro de colonne valide
   */
  public void setSalle(Salle s, int lig, int col) {
    /* Code à spécifier */
  }

  /**
   * Restitue true si et seulement si les numéros de ligne lig et de colonne col
   * sont valides
   * 
   * @param lig valide entre 0 et le nombre de lignes occupables +1 (la première
   *            (0) et la dernière (nbLig()+1) sont occupées par des salles de
   *            type Bordure)
   * @param col idem lig
   * @return un booléen qui vaut true en cas de validité des paramètres
   */
  private boolean isValide(int lig, int col) {
    return true; /* À remplacer par le code effectif */
  }

  /**
   * Restitue la salle immédiatement voisine d'une salle interne dans la direction
   * donnée.
   * Le numéro de ligne de la nouvelle salle est obtenu en faisant la somme du
   * numéro de ligne lig de la salle en paramètre et du dLig de la direction.
   * Idem pour les colonnes.
   * 
   * @param s Une salle interne au plateau (SalleDedans)
   * @param d Une direction quelconque, y compris en diagonales.
   * @return Une salle quelconque, y compris une salle de type Bordure
   */
  public Salle getVoisine(SalleDedans s, Direction d) {
    return null; /* À remplacer par le code effectif */
  }

  /**
   * Initialisation du contenu du plateau.
   * Placement de salles de type Bordure tout autour (aux ligne et colonne 0,
   * ainsi qu'à la ligne getNbLig()+1 et à la colonne getNbCol()+1).
   * Placement aléatoire de salles de type SalleBidon (sauf au centre)
   * Placement aléatoire d'adversaires (pas dans les salles SalleBidon ni au
   * centre)
   * Placement du joueur au centre.
   */
  private void initContenu() {
    for (int i = 0; i < getNbLig(); i++) {
      for (int j = 0; j < getNbCol(); j++) {
        if (i == 0 || i == getNbLig() - 1 || j == 0 || j == getNbCol() - 1) {
          grille[i][j] = new SalleBordure(i, j, this);// Salle de Bordure
        } else {
          grille[i][j] = new SalleVide(i, j, this);// Salle vide
        }
      }
    }
    grille[getNbLig() / 2][getNbCol() / 2] = new SalleJoueur(getNbLig() / 2, getNbCol() / 2, this);

  }

  /**
   * Restitue l'état du plateau (C'est cette chaîne qu'il faut afficher à chaque
   * tour).
   * On effectue une boucle sur les lignes et les colonnes du plateau en demandant
   * à concaténant les toString() des différentes salles — plus "\n" à la fin de
   * chaque ligne.
   * 
   * @return La chaîne de caractères qui représente le plateau
   */
  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < getNbLig(); i++) {
      for (int j = 0; j < getNbCol(); j++) {
        res.append(grille[i][j].toString()); // Ajoute la représentation de chaque salle
      }
      res.append("\n");
    }
    return res.toString();
  }

  /**
   * Création d'une instance de type Plateau.
   * Il faut au moins spécifier le nombre de lignes et de colonnes effectifs ainsi
   * que le jeu auquel appartient le plateau.
   * Il peut y avoir d'autres paramètres (proportion d'adversaires et de bidons
   * dans le plateau)
   */
  public Plateau(int nbLig, int nbCol, Jeu jeu) {
    this.grille = new Salle[nbLig][nbCol];
    this.jeu = jeu;
    initContenu();
  }
}
