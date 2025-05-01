import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
  public static final String BORD = "█";
  public static final String FOND = "░";
  
  private final Salle[][] grille;
  private Joueur joueur;
  private List<Adversaire> adversaires;
  private Collecteur collecteur;

  public int getNbLig() {
    return grille.length;
  }

  public int getNbCol() {
    return grille[0].length;
  }
  
  public Joueur getJoueur() {
    return joueur;
  }
  
  public List<Adversaire> getAdversaires() {
    return adversaires;
  }
  
  public Collecteur getCollecteur() {
    return collecteur;
  }

  public Salle getSalle(int lig, int col) {
    if (isValide(lig, col)) {
      return grille[lig][col];
    } else {
      return null;
    }
  }

  public void setSalle(Salle s, int lig, int col) {
    if (isValide(lig, col)) {
      grille[lig][col] = s;
    }
  }

  private boolean isValide(int lig, int col) {
    return lig >= 0 && lig < getNbLig() && col >= 0 && col < getNbCol();
  }

  public Salle getVoisine(SalleDedans s, Direction d) {
    int newLig = s.getLigne() + d.getdLig();
    int newCol = s.getColonne() + d.getdCol();
    return getSalle(newLig, newCol);
  }

  private void initContenu(int nbAdversaires, int nbBidons) {
    Random random = new Random();
    
    // Création du collecteur
    this.collecteur = new Collecteur();
    
    // Initialisation de toutes les salles comme vides
    for (int i = 0; i < getNbLig(); i++) {
      for (int j = 0; j < getNbCol(); j++) {
        if (i == 0 || i == getNbLig() - 1 || j == 0 || j == getNbCol() - 1) {
          // Bordures autour du plateau
          grille[i][j] = new SalleBordure(i, j, this);
        } else {
          // Salles vides à l'intérieur
          grille[i][j] = new SalleVide(i, j, this);
        }
      }
    }
    
    // Position du centre du plateau
    int centreI = getNbLig() / 2;
    int centreJ = getNbCol() / 2;
    
    // Création du joueur au centre avec une salle vide (non-SalleJoueur)
    SalleDedans salleCentrale = (SalleDedans) grille[centreI][centreJ];
    this.joueur = new Joueur(centreI, centreJ, this, collecteur);
    
    // Liste pour garder trace des positions utilisées
    List<int[]> positionsUtilisees = new ArrayList<>();
    positionsUtilisees.add(new int[]{centreI, centreJ});
    
    // Placement des bidons d'énergie
    this.adversaires = new ArrayList<>();
    int bidonsPlaces = 0;
    while (bidonsPlaces < nbBidons) {
      int i = 1 + random.nextInt(getNbLig() - 2);
      int j = 1 + random.nextInt(getNbCol() - 2);
      
      // Vérifier si la position est déjà utilisée
      boolean positionLibre = true;
      for (int[] pos : positionsUtilisees) {
        if (pos[0] == i && pos[1] == j) {
          positionLibre = false;
          break;
        }
      }
      
      if (positionLibre) {
        grille[i][j] = new SalleBidon(i, j, this);
        bidonsPlaces++;
        positionsUtilisees.add(new int[]{i, j});
      }
    }
    
    // Placement des adversaires
    int adversairesPlaces = 0;
    while (adversairesPlaces < nbAdversaires) {
      int i = 1 + random.nextInt(getNbLig() - 2);
      int j = 1 + random.nextInt(getNbCol() - 2);
      
      // Vérifier si la position est déjà utilisée
      boolean positionLibre = true;
      for (int[] pos : positionsUtilisees) {
        if (pos[0] == i && pos[1] == j) {
          positionLibre = false;
          break;
        }
      }
      
      if (positionLibre) {
        // Choisir aléatoirement un type d'adversaire
        int typeAdversaire = random.nextInt(3); // 0, 1 ou 2
        
        // Inertie aléatoire entre MIN et MAX
        int inertie = ParametresJeu.MIN_INERTIE_ADVERSAIRE + 
            random.nextInt(ParametresJeu.MAX_INERTIE_ADVERSAIRE - ParametresJeu.MIN_INERTIE_ADVERSAIRE + 1);
        
        Adversaire nouvelAdversaire;
        nouvelAdversaire = switch (typeAdversaire) {
              case 0 -> new AdversaireVelleitaire(inertie, joueur);
              case 1 -> new AdversaireDetermine(inertie, joueur);
              default -> new AdversaireIntelligent(inertie, joueur);
          };
        
        // Placer l'adversaire
        nouvelAdversaire.setSalle((SalleDedans)grille[i][j]);
        ((SalleDedans)grille[i][j]).setOccupant(nouvelAdversaire);
        adversaires.add(nouvelAdversaire);
        
        adversairesPlaces++;
        positionsUtilisees.add(new int[]{i, j});
      }
    }
  }

  public void afficherPlateau() {
    System.out.println();
    for (int i = 0; i < getNbLig(); i++) {
        for (int j = 0; j < getNbCol(); j++) {
            System.out.print(grille[i][j].toString() + " ");
        }
        System.out.println();
    }
  }
    
  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      
      // Bordure supérieure
      for (int j = 0; j < getNbCol(); j++) {
          sb.append(BORD).append(BORD);
      }
      sb.append("\n");
      
      // Contenu du plateau
      for (int i = 0; i < getNbLig(); i++) {
          // Bordure gauche
          sb.append(BORD);
          
          // Contenu de la ligne
          for (int j = 0; j < getNbCol(); j++) {
              sb.append(grille[i][j].toString());
          }
          
          // Bordure droite
          sb.append(BORD);
          sb.append("\n");
      }
      
      // Bordure inférieure
      for (int j = 0; j < getNbCol(); j++) {
          sb.append(BORD).append(BORD);
      }
      
      return sb.toString();
  }

  public Plateau(int nbLig, int nbCol, int nbAdversaires, int nbBidons) {
    this.grille = new Salle[nbLig][nbCol];
    initContenu(nbAdversaires, nbBidons);
  }
}