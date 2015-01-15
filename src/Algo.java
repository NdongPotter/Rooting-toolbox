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
			System.out.println("\n"+"*CONSTRUCTION CLASSE MERE");
			this.graphe=g;
			this.s=s;	
			this.t = new ArrayList<Sommet>();
			this.pred = new ArrayList<Sommet>();
			this.pi = new ArrayList<Integer>();
			/*La part commune aux initialisations des différents algo est réalisée ici*/
				/*initialisation de la liste des sommets à traiter, il s'agit de tous les sommets sauf le sommet-source*/
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
	
	//GETTER
		/*Recup sommet dans pred et t qui minimise pi*/
		public Sommet getMiniPi(){
			Sommet sMin = null; //initialisation de la variable qui contiendra le sommet de T qui minimise Pi
			int cMin = Integer.MAX_VALUE ; //initialisation de la variable qui contiendra le cout depuis le sommet-source dans Pi
			ListIterator<Sommet> iter = this.t.listIterator();//itérateur sur la liste T des sommets à traiter
			while (iter.hasNext()){ //on parcourt tous les sommets de T
				Sommet sommetItere = iter.next();//sommet en cours d'étude
				int i = this.graphe.getSommets().indexOf(sommetItere);//index du sommet étudié dans le graphe
				if(this.pi.get(i)<=cMin){
					sMin = sommetItere;//Si le cout depuis le sommet-source dans Pi est minimal, on stocke le sommet T qui minimise Pi
					cMin = this.pi.get(i);//et son cout
				}
			}
			return sMin;
		}
		/*Recup sommet dans pred qui maximise pi*/
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
		public void majPred(Sommet miniPi, int miniPiCout){
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
			
		}
		
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
		/*affiche infos relatives au sommet qui minimise Pi*/
			public void afficherMiniPi(Sommet s, int i, int c){
			System.out.println("Sommet qui minimise Pi : "+s.getNom()
					+". Index dans T : "+i
					+". Distance depuis la source : "+c);
			}
		/*affiche le + court-chemin de la source vers un sommet "sortie" passé en paramère*/
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

		/*affiche les sommets innateignables*/
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
		
	//METHODES
			abstract void algo(Sommet sortie);

}
