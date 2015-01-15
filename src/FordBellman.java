import java.util.ArrayList;
import java.util.ListIterator;

/*On implémente ici l'algorithme de Ford-Bellman
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet.
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 *La fonction coût peut être négative
 *On implémente ici l'algorithme du cours qui prend également comme hypothèse que l'on trouvera toujours dans t
 un sommet dont tous les prédecesseurs ont déjà été traités. C'est à se sommet que l'on s'intéresse à chaque itération
 */

public class FordBellman extends Algo {

	//ATTRIBUTS	
	
	//CONSTRUCTEUR
		public FordBellman(Graphe g , Sommet s){
			super(g,s);
		}
	
	//ALGORITHME
		void algo(Sommet sortie) {
			System.out.println("======ALGO FORD-BELLMAN======"+"\n");
			//INITIALISATION
			System.out.print("\n"+"*INITIALISATION"
					+"\n"+"On met à jour le cout associé aux sommets dont l'unique prédecesseur est la source"
					+"\n");
			
				//test
				/*ListIterator<Sommet> iterS = s.getSuccesseurs().listIterator();
				while (iterS.hasNext()){
					int i = iterS.nextIndex();
					Sommet varS = iterS.next();
					if(this.graphe.getPredecesseurs(varS).size() == 1){
						int index = this.graphe.indexOf(varS);
						this.pred.set(index, s);
						this.pi.set(index, this.s.getCapacites(i));
						
					}
				}*/
				
				afficherAttributs();
			//ITERATIONS
				System.out.print("\n"+"*ITERATIONS"+"\n");
				int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme
				/*L'algorithme tourne tant qu'il reste des sommets à traiter*/
				while (this.t.size()>0){
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
											+" sont absents de t");
									int index = t.indexOf(recherche);
									t.remove(index);
								}
								
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
				//FIN
				System.out.print("\n"+"*FIN"+"\n");
				afficherParametres();
				afficherAttributs();
				afficherSommetsInnateignables();				
			//RESULTATS
				System.out.println("\n"+"\n"+"*RESULTATS");
				afficherResultat(this.getMaxiPi());
				afficherResultat(sortie);
		
	}
}
