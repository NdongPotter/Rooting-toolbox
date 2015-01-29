import java.util.ListIterator;

/*On implémente ici l'algorithme de Moore Dijkstra
 *Cet algorithme est basé sur une hypothèse restrictive : la fonction de coût est positive.
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet.
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 *Pour deux plus courts-chemins de même longueur, on conservera celui qui a été étudié en dernier.
 */

public class MooreDijkstra extends Algo {

	//ATTRIBUTS	
	
	//CONSTRUCTEUR
	/*L'initialisation de l'algo est gérée dans le constructeur*/
		public MooreDijkstra(Graphe g , Sommet s){
			super(g,s);
		}
	
	//ALGORITHME
		void algo(Sommet sortie) {
			System.out.println("======ALGO MOORE-DIJKSTRA======"+"\n");
			//INITIALISATION
				System.out.print("\n"+"*FIN INITIALISATION"+"\n");
				/*Fin initialisation : s meilleur prédecesseur pour chacun de ses successeurs directs. Coût = distance au sommet-source.*/	
				ListIterator<Sommet> iterS = this.s.getSuccesseurs().listIterator();
				while(iterS.hasNext()){							
					int i1 = iterS.nextIndex();
					int i2 = this.graphe.indexOf(iterS.next());
					this.pred.set(i2,s);
					this.pi.set(i2,this.s.getCapacites().get(i1));
				}
				afficherAttributs();
			//ITERATIONS
				System.out.print("\n"+"*ITERATIONS"+"\n");
					/*L'algorithme tourne tant qu'il reste des sommets à traiter*/
						int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme
						while (this.t.size()>1){
							iteration++;
							/*Affichage des informations utiles*/
								System.out.print("\n"+"> Itération "+iteration+"\n");
								afficherAttributs();
							/*On cherche dans t le sommet qui minimise Pi*/
								Sommet miniPi = this.getMiniPi();
								int miniPiIndex = this.graphe.getSommets().indexOf(miniPi); //on note son index ...
								int miniPiCout = this.pi.get(miniPiIndex); //... et le coût associé
							/*On l'affiche*/
								afficherMiniPi(miniPi, miniPiIndex , miniPiCout);
							/*On l'enlève de T*/
								t.remove(miniPi);
							/*On calcul le coûts pour accéder à ses successeurs, mise-à-jour de pred et pi si le chemin est plus court */
								majPred(miniPi,miniPiCout);
						}
			//FIN
				System.out.print("\n"+"*FIN"+"\n");
				System.out.println(this.t.get(0).getNom()+" est seul dans t. C'est soit le plus éloigné du sommet-source, soit un sommet innateignable.");
				afficherParametres();
				afficherAttributs();
				afficherSommetsInnateignables();				
			//RESULTATS
				System.out.println("\n"+"\n"+"*RESULTATS");
				afficherResultat(this.getMaxiPi());
				afficherResultat(sortie);
		
		}	
		
		
		
}
