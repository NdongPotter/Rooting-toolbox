import java.util.ArrayList;
import java.util.ListIterator;

/*On implémente ici l'algorithme de Ford-Bellman
 *
 *La fonction coût peut être négative mais les circuits sont interdits. La vérification de l'absence de circuit est non-triviale et non-implémentée ici. 
 *L'absence de circuit implique que l'on trouvera toujours dans t un sommet dont tous les prédecesseurs ont déjà été traités : c'est à se sommet que l'on 
 *s'intéresse à chaque itération.
 *
 *A priori cet algorithme n'est pas adapté aux graphes routiers qui comportent des circuits. 
 *
 *L'implémentation actuelle de l'algo ne marche que si tous les sommets sont atteignables depuis le sommet-source. Comme le graphe est sans-circuit, cela implique que : 
 * - le sommet-source ne doit pas avoir de prédecesseur
 * 			Remarque : on pourrait tolérer que le sommet-source ait un ou plusieurs prédecesseurs.
 *			Dans ce cas on pourrait lister récursivement tous ses prédecesseurs, les prédecesseurs des ses prédecesseurs etc. et extraire un nouveau graphe d'étude débarassé de cette liste.
 * - tous les autres sommets ont au moins un prédecesseur
 * 			Remarque : on pourrait tolérer qu'il existe un autre sommet dans le graphe sans prédecesseur.
 * 			Dans ce cas on pourrait, si il existe un sommet à traiter qui n'a pas de prédecesseurs, le sortir de la liste des sommets à traiter, ne pas toucher Pi et Pred et faire
 * 			appel à la fonction afficherSommetsInnateignables. Enfin il faudrait l'ignorer de la liste des prédecesseurs des sommets encore à traiter pour éviter qu'un
 * 			plus-court chemin soit calculé à partir de lui.
 *
 *
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 *Pour deux plus courts-chemins de même longueur, on conservera celui qui a été étudié en dernier.
 */

public class FordBellman extends Algo {

	//ATTRIBUTS

	//CONSTRUCTEUR
	/*L'initialisation de l'algo est gérée dans le constructeur*/
		public FordBellman(Graphe g , Sommet s){
			super(g,s);
		}
	
	//ALGORITHME
		Chemins algo() {
			System.out.println("======ALGO FORD-BELLMAN======"+"\n");
			//INITIALISATION
				/*L'initialisation a été finalisée dans le constructeur*/
			//ITERATIONS
				System.out.print("\n"+"*ITERATIONS"+"\n");
				int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme
				/*L'algorithme tourne tant qu'il reste des sommets à traiter*/
				boolean erreur = false;
				while (this.t.size()>0 && !erreur){
					iteration++;
					/*Affichage des informations utiles*/
						System.out.print("\n"+"> Itération "+iteration+"\n");
						afficherAttributs();
					/*On cherche dans t un sommet dont tous les prédecesseurs sont absents de t*/
						Sommet recherche = null;
						/*On parcourt t avec un itérateur tant qu'on a pas trouvé*/
							ListIterator<Sommet> iterT = t.listIterator();
							Boolean aChercher = true;
							while (iterT.hasNext() && aChercher){						
								Sommet varT = iterT.next(); //sommet de t en cours d'étude
								ArrayList<Sommet> listPred = graphe.getPredecesseurs(varT); //on récupère les prédecesseurs de varT
								System.out.println("On s'intéresse au sommet "+varT.getNom()
										+" de t dont les prédecesseurs sont "+graphe.affichePredecesseurs(varT));
								ListIterator<Sommet> iterP = listPred.listIterator(); 
								boolean continuer = true ;
								while (iterP.hasNext() && continuer){ //on parcourt cette liste de prédecesseur tant qu'aucun n'a été trouvé dans t
									Sommet varP = iterP.next(); //prédecesseur en cours d'étude
									System.out.println("	On étudie le prédecesseur "+varP.getNom());
									ListIterator<Sommet> iterT2 = t.listIterator(); 
									while (iterT2.hasNext() && continuer){ //pour chaque prédecesseur, on parcourt t tant que le prédecesseur n'y a pas été trouvé
										Sommet varT2 = iterT2.next();
										if (! varP.equals(varT2)){
											aChercher = false ;
										}
										else{
											aChercher = true ;
											continuer = false ;
											System.out.println(varT.getNom()+" ne convient pas car son prédecesseur "+varP.getNom()+" est dans t");
										}
									}
								}
								if (!aChercher){
										recherche = varT;
										System.out.println("On va étudier "+recherche.getNom()
												+" qui est dans t et dont les prédecesseurs "+graphe.affichePredecesseurs(recherche)
												+" ont déjà été traités");
										int index = t.indexOf(recherche);
										t.remove(index);
									
									
									int indexRecherche = graphe.getSommets().indexOf(recherche);
									int coutRechercheActuel = this.pi.get(indexRecherche);
									System.out.println("Son index ds le graphe est "+indexRecherche
											+" et son cout dans Pi est "+coutRechercheActuel);
									
									Sommet meilleurPred = null;
									//on calcule le chemin depuis chaque predecesseur, quand c'est + court, on garde
									//on regarde chaqun de ses prédecesseurs
									ListIterator<Sommet> iter = this.graphe.getPredecesseurs(recherche).listIterator();
									while (iter.hasNext()){
										Sommet varS = iter.next();//on récupere le prédecesseur
										int i = this.graphe.getSommets().indexOf(varS);//son index dans le graphe
										int coutPred = this.pi.get(i);//son cout depuis l'origine
										System.out.println("On étudie son predecesseur : "+varS.getNom()
												+" d'index dans g : "+i
												+" et de cout dans Pi : "+coutPred);
										int i2 = varS.getSuccesseurs().indexOf(recherche);
										int coutArc = varS.getCapacites(i2);//cout de l'arc liant le pred et recherche
										System.out.println(recherche.getNom()
												+" est bien le successeur d'indice "+i2
												+" de "+varS.getNom()
												+". Le cout de l'arc associé est : "+coutArc);
										if (coutPred+coutArc < coutRechercheActuel){
											meilleurPred = this.graphe.getSommet(i);
											coutRechercheActuel = coutPred+coutArc;
										}
									}
									
									System.out.println("Le meilleur prédecesseur de "+recherche.getNom()
											+" est "+meilleurPred.getNom()
											+" le cout associé est "+coutRechercheActuel);
									
									this.pred.set(indexRecherche, meilleurPred);
									this.pi.set(indexRecherche, coutRechercheActuel);
								}
							}
							if (recherche == null){
								erreur = true;
							}
				}
				Chemins c = new Chemins(this.graphe, this.s);
				if (!erreur){
				//FIN
				System.out.print("\n"+"*FIN"+"\n");
				afficherParametres();
				afficherAttributs();
				//RESULTATS
				/*On récupère le résultat dans un objet Chemins*/
					c.setChemin(this.pred, this.pi);
				}
				else{
					System.out.println("STOP : l'algorithme a rencontré une erreur");
				}
				return c;

	}
}
