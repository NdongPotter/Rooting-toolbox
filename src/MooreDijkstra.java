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
		Chemins algo() {
			System.out.println("======ALGO MOORE-DIJKSTRA======"
					+"\n"+"\n"+"*FIN INITIALISATION"+"\n");
			finInit();
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
								Sommet miniPi = null; //initialisation de la variable qui contiendra le sommet de T qui minimise Pi
								int cMin = Integer.MAX_VALUE ; //initialisation de la variable qui contiendra le cout depuis le sommet-source dans Pi
								ListIterator<Sommet> iter = this.t.listIterator();//itérateur sur la liste T des sommets à traiter
								while (iter.hasNext()){ //on parcourt tous les sommets de T
									Sommet sommetItere = iter.next();//sommet en cours d'étude
									int i = this.graphe.getSommets().indexOf(sommetItere);//index du sommet étudié dans le graphe
									if(this.pi.get(i)<cMin){
										miniPi = sommetItere;//Si le cout depuis le sommet-source dans Pi est minimal, on stocke le sommet T qui minimise Pi
										cMin = this.pi.get(i);//et son cout
									}
								}
								int miniPiIndex = this.graphe.getSommets().indexOf(miniPi); //on note son index ...
								int miniPiCout = this.pi.get(miniPiIndex); //... et le coût associé
							/*affichage*/
									System.out.println("Sommet qui minimise Pi : "+miniPi.getNom()
											+". Index dans T : "+miniPiIndex
											+". Distance depuis la source : "+miniPiCout);
							/*On l'enlève de T*/
								t.remove(miniPi);
							/*On calcul le coûts pour accéder à ses successeurs, mise-à-jour de pred et pi si le chemin est plus court */
									System.out.println("Successeur(s) : ");
									for (int i=0 ; i < miniPi.getSuccesseurs().size() ; i++){
										Sommet varS = miniPi.getSucc(i);
										System.out.print("- "+varS.getNom()+" est un successeur de "+miniPi.getNom()+" la capacité associée à l'arc est "+ miniPi.getCapacites(i));
										ListIterator<Sommet> iter2 = this.t.listIterator();//on vérifie uniquement pour les sommets qui sont encore à traiter
										while(iter2.hasNext()){
											Sommet varT = iter2.next();
											if(varS.equals(varT)){
												System.out.println(". Il est dans t, on calcul le nouveau coût.");
												int nouvelleDistance = miniPiCout+miniPi.getCapacites(i);//on calcul pour chaque successeur sa distance au sommet-source en passant par rechercheSommet
												if (nouvelleDistance<this.pi.get(this.graphe.indexOf(varS))){//si cette nouvelle distance est plus faible, on met à jour la liste des meilleurs prédecesseurs et la liste des couts
													System.out.print("Meilleur prédecesseur actuel de "+varS.getNom()
															+" : "+this.pred.get(this.graphe.indexOf(varS)).getNom()
															+". Coût associé : "+this.pi.get(this.graphe.indexOf(varS)));
													this.pred.set(this.graphe.indexOf(varS), miniPi);
													this.pi.set(this.graphe.indexOf(varS),nouvelleDistance);
													System.out.print(". En passant par "+miniPi.getNom()
															+" le cout devient "+nouvelleDistance+"\n");
												}		
												
											}
										}
									}	
						}//fin de l'algo
						
			//FIN
				System.out.print("\n"+"*FIN"+"\n");
				System.out.println(this.t.get(0).getNom()+" est seul dans t. C'est soit le plus éloigné du sommet-source, soit un sommet innateignable.");
				afficherSommetsInnateignables();				
			//RESULTATS
				/*On récupère le résultat dans un objet Chemins*/
					Chemins c = new Chemins(this.graphe, this.s);
					c.setChemin(this.pred, this.pi);
					return c;
		
		}	
		
		
		
}
