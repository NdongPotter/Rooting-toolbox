import java.util.ArrayList;
import java.util.ListIterator;


public class Chemins {
	/*
	 * On implémente ici la classe Chemins. Ses instances seront utilisée pour récupérer la liste pred (meilleurs prédécesseurs)
	 * et la liste pi (couts depuis l'origine) dans chaque algorithme.
	 * On parle de "Chemins" au pluriel car ses instances une fois traitées par les algorithmes permettent de trouver tous les plus courts-chemins
	 * depuis l'origine.
	 */
	
	/*Attributs*/
	/*le graphe d'étude*/
		protected Graphe graphe ;
	/*le sommet source d'étude*/
		protected Sommet source ;
	/*liste des meilleurs prédecesseurs pour chaque sommet du graphe, le prédecesseur d'indice i est le meilleur prédecesseur (pour venir
	 *du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de A dans le cours.*/
		protected ArrayList<Sommet> pred;
	/*liste des coûts les plus faibles pour venir du sommet-source pour chaque sommet du graphe, le coût d'indice i est le coût minimal (pour venir
	 * du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de Pi dans le cours.*/
		protected ArrayList<Integer> pi;

	/*Constructeur*/
		public Chemins(Graphe g, Sommet source){
			this.graphe = g ;
			this.source = source;
			this.pred = new ArrayList<Sommet>();
			this.pi = new ArrayList<Integer>();
		}
	
	/*Méthodes*/
		//SETTER
			public void setChemin (ArrayList<Sommet> pred, ArrayList<Integer> pi){
				this.pred = pred ;
				this.pi = pi ;
			}
			
		//GETTER
			
			/*Recup sommet dans pred qui maximise pi
			 *Est utilisé pour trouver le sommet le plus éloigné de la source */
				public Sommet getMaxiPi(){
					/*On cherche dans le sommet qui maximise Pi*/
					Sommet sMax = null ; //variable qui contient le sommet le plus éloigné depuis la source - on affiche le chemin associé par défaut
					int cMax = 0 ; //cout associé à sMax
					ListIterator<Integer> iterPi = this.pi.listIterator();
					while (iterPi.hasNext()){ //on parcourt la liste des couts afin d'identifier sMax et cMax
						int index = iterPi.nextIndex();
						int varC = iterPi.next();
						Sommet varS = this.graphe.getSommet(index);
						if (varC > cMax && varC < Integer.MAX_VALUE){
							cMax = varC ;
							sMax = varS ;
						}
					}
					return sMax;
				}
			
		//AFFICHAGE
		
			/*affiche les attributs du chemin*/
				public void afficherChemins(){
					System.out.println("\n"+"Les attributs de l'objet Chemins sont : "
							+"\n"+"g : "+ this.graphe.afficheSommetsGraphe()
							+"\n"+"s : "+ this.source.getNom());
					/*affiche liste pred*/
						String liste = new String();
						ListIterator<Sommet> iter = this.pred.listIterator();
						while(iter.hasNext()){
							String var = iter.next().getNom()+";";
							liste+=var;
						}
						System.out.print(liste+"\n");
					/*affiche liste pi*/
						System.out.println(this.pi.toString()+"\n");
				}

			/*affiche le + court-chemin de la source vers un sommet "sortie" passé en paramètre*/
				public void afficherResultat(Sommet sortie){
					int index = this.graphe.indexOf(sortie); //on récupère l'index du sommet de sortie
					if (this.pi.get(index)!=Integer.MAX_VALUE){
						int cout = this.pi.get(index); //On récupère le coût associé à ce sommet de sortie
						/*la liste des sommets constituants le plus court chemin est complété en remontant un par un les meilleurs prédécesseurs depuis sortie
						 * La boucle s'arrête lorsqu'on retrouve le sommet-source.
						 */
							ArrayList<Sommet> resultat = new ArrayList<Sommet>() ;
							Sommet varS = sortie ;
							while (! varS.equals(source)){
								resultat.add(0, varS);
								int indexdansgraphe = this.graphe.indexOf(varS);
								Sommet meilleurpredecesseurVarS = this.pred.get(indexdansgraphe);
								varS = meilleurpredecesseurVarS;
							}
						/*ajout du sommet-source en tête de la liste resultat*/
							resultat.add(0, source);
						/*affiche resultat*/
							String liste = new String();
							ListIterator<Sommet> iter2 = resultat.listIterator();
							while(iter2.hasNext()){
								String var = iter2.next().getNom();
								if(iter2.hasNext()){
									var+=" -> ";
								}
								liste+=var;
							}
							System.out.println("Plus court-chemin de "+source.getNom()
								+" à "+ sortie.getNom()
								+" : "+liste
								+". Coût : "+cout);
							}
					else {System.out.print("Le sommet de sortie "+sortie.getNom()+" n'est pas atteignable depuis la source "+source.getNom());}
				}
}
