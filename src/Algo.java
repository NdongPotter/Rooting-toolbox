import java.util.ArrayList;
import java.util.ListIterator;


public abstract class Algo {
	
	//ATTRIBUTS
		/*graphe sur lequel on va appliquer l'algorithme*/
			protected Graphe graphe;
		/*sommet-source de l'algorithme*/
			protected Sommet s;
		/*liste des meilleurs prédecesseurs pour chaque sommet du graphe, le prédecesseur d'indice i est le meilleur prédecesseur (pour venir
		 *du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de A dans le cours.*/
			protected ArrayList<Sommet> pred;
		/*liste des coûts les plus faibles pour venir du sommet-source pour chaque sommet du graphe, le coût d'indice i est le coût minimal (pour venir
		 * du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de Pi dans le cours.*/
			protected ArrayList<Integer> pi;
		/*liste des sommets à traiter. C'est l'équivalent de T dans le cours*/
			protected ArrayList<Sommet> t;
	
	//CONSTRUCTEUR		
		public Algo(Graphe g , Sommet s){
			System.out.println("\n"+"*CONSTRUCTION CLASSE MERE et INITIALISATION ALGORITHMES");
			this.graphe=g;
			this.s=s;	
			this.t = new ArrayList<Sommet>();//inutile pour Bellman
			this.pred = new ArrayList<Sommet>();
			this.pi = new ArrayList<Integer>();
			/*La part commune aux initialisations des différents algo est réalisée ici*/
				/*initialisation de la liste des sommets à traiter, il s'agit de tous les sommets sauf le sommet-source
				 * inutile pour Bellman*/
					ListIterator<Sommet> iter1 = this.graphe.getSommets().listIterator();
					while(iter1.hasNext()){
						Sommet varS = iter1.next();
						if (!varS.equals(s)){
							t.add(varS);
						}
					}
				/*initialisation des listes pred et pi : 
				 * Pour tous prédecesseurs, on indique un sommet temporaire x0 et un cout temporaire +infini
				 * Pour le sommet source, on indique E comme meilleur prédecesseur et 0 comme cout
					 */
					Sommet E = new Sommet("E");
					Sommet x0 = new Sommet("x0");
					for (int i=0 ; i<this.graphe.getNbSommets(); i++){
						this.pred.add(x0);
						this.pi.add(Integer.MAX_VALUE);
					}
					this.pred.set(g.indexOf(s),E);
					this.pi.set(g.indexOf(s),0);			
			/*On affiche les attributs après construction*/
				afficherParametres();
				afficherAttributs();
		}
		
	//FIN INITIALISATION
		/*MooreDijkstra et Bellman comportent une étape supplémentaire dans l'initalisation*/
		/*Fin initialisation : s meilleur prédecesseur pour chacun de ses successeurs directs. Coût = distance au sommet-source.*/	
		public void finInit(){
			ListIterator<Sommet> iterS = this.s.getSuccesseurs().listIterator();
			while(iterS.hasNext()){							
				int i1 = iterS.nextIndex();
				int i2 = this.graphe.indexOf(iterS.next());
				this.pred.set(i2,s);
				this.pi.set(i2,this.s.getCapacites().get(i1));
			}
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
			
	//SETTER
		/*Si le chemin de miniPi à ses successeurs est plus court, mise-à-jour de pred et pi*/
		/*utilisé dans Moore et pas FB*/

		
	//AFFICHAGE
		/*affiche paramètres algo*/
			public void afficherParametres(){
				System.out.println("-Paramètres"
						+"\n"+"	s : "+s.getNom()
						+"\n"+"	g : "+ this.graphe.afficheSommetsGraphe());
			}
		/*affiche attributs*/
			public void afficherAttributs(){
				System.out.println("-Attributs"
						+"\n"+"	t = "+this.afficherT()
						+"	pred = "+this.afficherPred()
						+"	pi = "+this.afficherPi());
			}
		/*affiche liste t*/
			public String afficherT(){
				String liste = new String();
				ListIterator<Sommet> iter = this.t.listIterator();
				while(iter.hasNext()){
					String var = iter.next().getNom()+";";
					liste+=var;
				}
				return liste+"\n";
			}
		/*affiche liste pred*/
			public String afficherPred(){
				String liste = new String();
				ListIterator<Sommet> iter = this.pred.listIterator();
				while(iter.hasNext()){
					String var = iter.next().getNom()+";";
					liste+=var;
				}
				return liste+"\n";
			}
		/*affiche liste pi*/
			public String afficherPi(){
				return this.pi.toString()+"\n";
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
						while (! varS.equals(this.s)){
							resultat.add(0, varS);
							int indexdansgraphe = this.graphe.indexOf(varS);
							Sommet meilleurpredecesseurVarS = this.pred.get(indexdansgraphe);
							varS = meilleurpredecesseurVarS;
						}
					/*ajout du sommet-source en tête de la liste resultat*/
						resultat.add(0, this.s);
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
						System.out.println("Plus court-chemin de "+s.getNom()
							+" à "+ sortie.getNom()
							+" : "+liste
							+". Coût : "+cout);
						}
				else {System.out.print("Le sommet de sortie "+sortie.getNom()+" n'est pas atteignable depuis la source "+s.getNom());}
			}

		/*affiche les sommets innateignables
		 * cette méthode est utilisée dans MD et B mais pas FB (cf commentaire d'intro de la classe)*/
			public void afficherSommetsInnateignables(){
				System.out.print("Sommets innateignables depuis "+this.s.getNom()+" : ");
				ListIterator<Integer> iterPi1=this.pi.listIterator();
				while (iterPi1.hasNext()){ //on parcourt la liste des couts afin d'identifier les sommets innateignables
					int index = iterPi1.nextIndex();
					int varC = iterPi1.next();
					Sommet varS = this.graphe.getSommet(index);
					if (varC == Integer.MAX_VALUE){
						System.out.print(varS.getNom()+";");
					}
				}
			}
}
