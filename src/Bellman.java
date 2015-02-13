import java.util.ArrayList;
import java.util.ListIterator;

/*On implémente ici l'algorithme de Bellman
 *Cet algorithme est basé sur une hypothèse restrictive : il ne peut y avoir de circuit absorbant
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet.
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 *Pour deux plus courts-chemins de même longueur, on conservera celui qui a été étudié en dernier.
 *
 *L'implémentation prend en compte le cas de sommets innateignables.
 */

public class Bellman extends Algo {
	
	//ATTRIBUTS	
	
	//CONSTRUCTEUR
		/*L'initialisation de l'algo est gérée dans le constructeur*/
			public Bellman(Graphe g, Sommet s) {
				super(g, s);
			}

	//ALGORITHME
		Chemins algo() {
			System.out.println("======ALGO BELLMAN======"
					+"\n"+"\n"+"*FIN INITIALISATION"+"\n");
			finInit();
			afficherAttributs();
			//ITERATIONS
				System.out.print("\n"+"*ITERATIONS"+"\n");
				int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme
				/*Le nombre d'itérations est égal à n-2 où n est le nombre de sommets du graphe*/
				while (iteration < this.graphe.getSommets().size()-1){
					iteration++;
					/*pi1 et pred1 permettront de stocker les valeurs de pred et pi de l'itération n pendant l'itération n+1*/
						ArrayList<Integer> pi1 = new ArrayList<Integer>(this.pi.size());
						ArrayList<Sommet> pred1 = new ArrayList<Sommet>(this.pred.size());
						for (int i=0; i < this.pi.size(); i++) {
								  pi1.add(pi.get(i));
								  pred1.add(pred.get(i));
								}
					/*Affichage des informations utiles*/
						System.out.print("\n"+"> Itération "+iteration+"\n");
						afficherAttributs();//attention ça affiche T
						afficherPredPi1(pred1,pi1);//on affiche les attributs de l'itération précédente
					/*On parcourt t (tout le graphe sauf la source) avec un itérateur*/
						ListIterator<Sommet> iterT = this.t.listIterator();
						while (iterT.hasNext()){
							Sommet varT = iterT.next(); //sommet de t en cours d'étude
							int varTi = this.graphe.getSommets().indexOf(varT); //son index dans le graphe
							int varTc = pi1.get(varTi); //son cout actuel depuis l'origine (dans le pi calculé à l'itération précédente)
							ArrayList<Sommet> listPred = graphe.getPredecesseurs(varT); //on récupère ses prédécesseurs
							/*variables de boucle*/
								int coutMin = Integer.MAX_VALUE; //variable qui contiendra le coutMin depuis l'ensemble des prédécesseurs, initialisée à +infini
								Sommet predMin = null; //variable qui contiendra le prédécesseurs correspondant à coutMin
							/*affichage*/
								System.out.println("\n"+"On s'intéresse au sommet "+varT.getNom()
									+" de t dont les prédecesseurs sont "+graphe.affichePredecesseurs(varT));
							/*On parcourt la liste de ses prédécesseurs avec un itérateur*/
								ListIterator<Sommet> iterP = listPred.listIterator(); 
								while (iterP.hasNext()){
										Sommet varP = iterP.next(); //prédécesseur en cours d'étude
										int varPindex = this.graphe.getSommets().indexOf(varP);//on récupère son index dans le graphe
										int varPcout = pi1.get(varPindex);//on récupère son cout depuis l'origine dans le Pi de l'itération précédente
									/*affichage*/
										System.out.println("*prédécesseurs "+varP.getNom()
												+" en cours d'étude "
												+"\n"+"    son index dans le graphe est "+varPindex
												+"\n"+"    son cout depuis l'origine est "+varPcout
												);
									/*Pour chaque prédécesseur, on vérifie que ce n'est pas la source et que son cout depuis l'origine n'est pas infini
									 *Si la condition est vérifiée alors on cherche son meilleur prédécesseur*/
										if (varP.equals(this.s)||!(varPcout < Integer.MAX_VALUE)){
											System.out.println("Soit ce prédécesseur est la source, soit son cout actuel est infini, on laisse et on passe au suivant");
										}
										else{
											System.out.println("On étudie le prédecesseur "+varP.getNom());
											int i2 = varP.getSuccesseurs().indexOf(varT);
											int coutArc = varP.getCapacites(i2);//cout de l'arc liant le pred et recherche
											System.out.println(varT.getNom()
													+" est bien le successeur d'indice "+i2
													+" de "+varP.getNom()
													+". Le cout de l'arc associé est : "+coutArc);	
											
											if (varPcout+coutArc < coutMin){ //identique que FordBellman
												coutMin = varPcout+coutArc;
												predMin = this.graphe.getSommet(varPindex);
											}
										}
									
									}//fin de la boucle sur les prédécesseurs
								/*Si la condition précédente a été vérifiée, alors on a trouvé un meilleur prédécesseur*/
									if (!(predMin==null)){
										/*affichage*/
											System.out.println("Après avoir étudié tous les prédécesseurs de "+varT.getNom()+", "
													+"il apparaît que le plus intéressant est "+predMin.getNom()
													+" et que le cout associé est "+coutMin);
										/*On vérifie si celui-ci est meilleur que celui trouvé à l'itération précédente*/
											if (coutMin < varTc){
												this.pi.set(varTi, coutMin);
												this.pred.set(varTi, predMin);
												System.out.println("On a trouvé un nouveau chemin plus rapide, les infos sont mises-à-jour dans pi et pred");
												afficherAttributs();//attention ça affiche T
												afficherPredPi1(pred1,pi1);//on affiche les attributs de l'itération précédente
											}
											else{
												System.out.println("Le meilleur chemin trouvé à cette itération est plus long ou égal au chemin déjà actuel");	
											}	
										
									}
								/*Sinon on passe à la suite*/
									else{
										System.out.println("Aucun prédécesseur ne fait l'affaire pour cette itération (soit la source est le seul précesseur, soit tous les prédecesseurs ont un coût encore infini depusi l'origine)");	
									}
						
						}//fin de la boucle sur sommets de t
				}//fin de l'algo
					
		//FIN
			System.out.print("\n"+"*FIN"+"\n");
			afficherParametres();
			afficherAttributs();
			afficherSommetsInnateignables();				
		//RESULTATS
			/*On récupère le résultat dans un objet Chemins*/
			Chemins c = new Chemins(this.graphe, this.s);
			c.setChemin(this.pred, this.pi);
			return c;

	}
				
	//AFFICHAGE
		/*permet d'afficher les valeurs des attributs pred et pi de l'itération précédente*/
		public void afficherPredPi1(ArrayList <Sommet> pred1, ArrayList <Integer> pi1){
			System.out.println("-Attributs Pred et Pi de l'itération précédente :"
					+"\n"+"	pred - 1  = "+afficherPred1(pred1)
					+"	pi -1  = "+afficherPi1(pi1));
		}
	
		/*affiche liste pred de l'itération précédente*/
		public String afficherPred1(ArrayList <Sommet> pred1){
			String liste = new String();
			ListIterator<Sommet> iter = pred1.listIterator();
			while(iter.hasNext()){
				String var = iter.next().getNom()+";";
				liste+=var;
			}
			return liste+"\n";
		}
		/*affiche liste pi de l'itération précédente*/
		public String afficherPi1(ArrayList <Integer> pi1){
			return pi1.toString()+"\n";
		}
	
}
