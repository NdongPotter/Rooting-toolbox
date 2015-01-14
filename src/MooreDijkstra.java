import java.util.ArrayList;
import java.util.ListIterator;

/*On implémente ici l'algorithme de Moore Dijkstra
 *Cet algorithme est basé sur une hypothèse restrictive : la fonction de coût est positive.
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet.
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 */

public class MooreDijkstra extends Algo {

	//Attributs
	/*graphe sur lequel on va appliquer l'algorithme*/
	private Graphe graphe;
	/*sommet-source de l'algorithme*/
	private Sommet s;
	/*liste des meilleurs prédecesseurs pour chaque sommet du graphe, le prédecesseur d'indice i est le meilleur prédecesseur (pour venir
	 *du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de A dans le cours.*/
	private ArrayList<Sommet> pred;
	/*liste des coûts les plus faibles pour venir du sommet-source pour chaque sommet du graphe, le coût d'indice i est le coût minimal (pour venir
	 * du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de Pi dans le cours.*/
	private ArrayList<Integer> pi;
	/*liste des sommets à traiter. C'est l'équivalent de T dans le cours*/
	private ArrayList<Sommet> t;

	//Constructeur
	public MooreDijkstra(Graphe g , Sommet s){
		this.graphe=g;
		this.s=s;	
		this.t = new ArrayList<Sommet>();
		this.pred = new ArrayList<Sommet>();
		this.pi = new ArrayList<Integer>();

		System.out.println("*CONSTRUCTION"
				+"\n"+"Source : "+s.getNom()
				+"\n"+"Sommets du graphe : "+ g.afficheSommetsGraphe());
		
		/*L'étape d'initialisation de l'algo est réalisée lors de la construction de l'objet MooreDijkstra*/
		
		System.out.print("*INITIALISATION"+"\n");

		/*initialisation de la liste des sommets à traiter, il s'agit de tous les sommets sauf le sommet-source*/
		ListIterator<Sommet> iter1 = this.graphe.getSommets().listIterator();
		while(iter1.hasNext()){
			Sommet varS = iter1.next();
			if (!varS.equals(s)){
				t.add(varS);
			}
		}
		System.out.print("t = "+this.afficherT());
		
		/*initialisation des listes pred et pi : 
		 * Pour tous prédecesseurs, on indique un sommet temporaire x0 et un cout temporaire +infini
		 * Pour le sommet source, on indique E comme meilleur prédecesseur et 0 comme cout
		 * pour chaque successeur direct du sommet-source s, on renseigne s comme meilleur prédecesseur et pour le coût on prend la distance au sommet-source.
		 */
		Sommet E = new Sommet("E");
		Sommet x0 = new Sommet("x0");

		for (int i=0 ; i<this.graphe.getNbSommets(); i++){
			this.pred.add(x0);
			this.pi.add(Integer.MAX_VALUE);
		}
		
		this.pred.set(g.indexOf(s),E);
		this.pi.set(g.indexOf(s),0);
		
		ListIterator<Sommet> iter2 = this.s.getSuccesseurs().listIterator();
		while(iter2.hasNext()){
			int i1 = iter2.nextIndex();
			int i2 = g.indexOf(iter2.next());
			this.pred.set(i2,s);
			this.pi.set(i2,this.s.getCapacites().get(i1));
		}
		
		System.out.print("pred = "+this.afficherPred()
				+"pi = "+this.afficherPi()
				+"Prédecesseur par défaut : x0. Cout infini = 2147483647."
				+"\n");
	}
	
	//Méthodes d'affichage
	public String afficherT(){
		String liste = new String();
		ListIterator<Sommet> iter = this.t.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	public String afficherPred(){
		String liste = new String();
		ListIterator<Sommet> iter = this.pred.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	public String afficherPi(){
		return this.pi.toString()+"\n";
	}
	
	//METHODE D'AFFICHAGE d'UN RESULTAT selon sommet sortie
	
	public void affichageResultat(Sommet sortie){
		
		int index = this.graphe.indexOf(sortie);

		if (this.pi.get(index)!=Integer.MAX_VALUE){
		/*On récupère le coût associé à ce sommet de sortie*/
		int cout = this.pi.get(index);
		/*On remonte la liste pred pour déterminer le plus court-chemin du sommet-source au sommet de sortie*/
		ArrayList<Sommet> resultat = new ArrayList<Sommet>() ;
		Sommet varS = sortie ;
		while (! varS.equals(this.s)){
			/*la liste des sommets constituants le plus court chemin est complété en remontant un par un les meilleurs prédécesseurs depuis sMax
			 * La boucle s'arrête lorsqu'on retrouve le sommet-source.
			 */
			resultat.add(0, varS);
			int indexdansgraphe = this.graphe.indexOf(varS);
			Sommet meilleurpredecesseurVarS = this.pred.get(indexdansgraphe);
			varS = meilleurpredecesseurVarS;
		}
		resultat.add(0, this.s);//on ajoute le sommet-source en tête de la liste resultat.
		
		/*On affiche le résultat*/
		String liste = new String();
		ListIterator<Sommet> iter2 = resultat.listIterator();
		while(iter2.hasNext()){
			String var = iter2.next().getNom();
			if(iter2.hasNext()){
				var+=" -> ";
			}
			liste+=var;
		}
		
	System.out.println("Sortie passée en paramètre : " + sortie.getNom()
				+". Plus court-chemin (Moore-Dijkstra) est : "+liste
				+". Coût associé : "+cout);
			}
		else {System.out.print("Le sommet de sortie "+sortie.getNom()+" n'est pas atteignable depuis la source "+s.getNom());}
	}
	
	//Implémentation de l'algorithme
	
	void algo(Sommet sortie) {
		
		/*on implémente ici  les itérations de l'algorithme qui devra s'appliquer à un objet MooreDijkstra préalaablement construit en prenant
		*en argument le graphe d'intérêt g et le sommet-source.
		*L'initialisation de l'algorithme a déjà été réalisée dans le constructeur.
		*A terme il faudrait que l'algo retourne les listes pred et pi et que l'affichage du résultat soit externalisé dans d'autres méthodes
		*/
		
		/*L'algorithme tourne tant qu'il reste des sommets à traiter*/
		int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme
		
		System.out.print("\n"+"*ITERATION");

		while (this.t.size()>1){
			iteration++;
			
		/*Affichage des informations utiles*/
			System.out.print("\n"+"> Itération "+iteration
					+"\n"+"g = "+this.graphe.afficheSommetsGraphe()
					+"t = "+this.afficherT()
					+"pred = "+this.afficherPred()
					+"Liste des couts = "+this.afficherPi());
			
		/*On cherche dans la liste des sommets à traiter celui qui minimise Pi*/
			Sommet rechercheSommet = null; //initialisation de la variable qui contiendra le sommet de T qui minimise Pi
			int rechercheIndex = 0; //initialisation de la variable qui contiendra l'index du sommet dans le graphe
			int rechercheCout = Integer.MAX_VALUE ; //initialisation de la variable qui contiendra le cout depuis le sommet-source dans Pi
			ListIterator<Sommet> iter = this.t.listIterator();//itérateur sur la liste T des sommets à traiter

			while (iter.hasNext()){ //on parcourt tous les sommets de T
				Sommet sommetItere = iter.next();//sommet en cours d'étude
				int i = this.graphe.getSommets().indexOf(sommetItere);//index du sommet étudié dans le graphe
				if(this.pi.get(i)<=rechercheCout){
					rechercheSommet = sommetItere;//Si le cout depuis le sommet-source dans Pi est minimal, on stocke le sommet T qui minimise Pi
					rechercheIndex = i ;//on stocke également son index
					rechercheCout = this.pi.get(i);//et son cout
				}
			}
			System.out.print("Sommet qui minimise Pi : "+rechercheSommet.getNom()
					+". Index dans T : "+rechercheIndex
					+". Distance depuis la source : "+rechercheCout
					+"\n"+"Successeurs :");

			t.remove(rechercheSommet);//on supprime de la liste T des sommets à traiter le sommet qui minimise le cout depuis le sommet-source dans Pi
			
			for (int i=0 ; i < rechercheSommet.getSuccesseurs().size() ; i++){
				//on parcourt la liste des sucesseurs du sommet en train d'être traité afin  de vérifier qu'on a pas trouvé un nouveau plus-court chemin.
				Sommet varS = rechercheSommet.getSucc(i);
				System.out.print("- "+varS.getNom()+" est un successeur de "+rechercheSommet.getNom());
				ListIterator<Sommet> iter2 = this.t.listIterator();//on vérifie uniquement pour les sommets qui sont encore à traiter
				while(iter2.hasNext()){
					Sommet varT = iter2.next();
					if(varS.equals(varT)){
						System.out.println(". Il est dans t, on calcul le nouveau coût.");
						int nouvelleDistance = rechercheCout+rechercheSommet.getCapacites(i);//on calcul pour chaque successeur sa distance au sommet-source en passant par rechercheSommet
						if (nouvelleDistance<this.pi.get(this.graphe.indexOf(varS))){//si cette nouvelle distance est plus faible, on met à jour la liste des meilleurs prédecesseurs et la liste des couts
							System.out.print("Meilleur prédecesseur actuel de "+varS.getNom()
									+" : "+this.pred.get(this.graphe.indexOf(varS)).getNom()
									+". Coût associé : "+this.pi.get(this.graphe.indexOf(varS)));
							this.pred.set(this.graphe.indexOf(varS), rechercheSommet);
							this.pi.set(this.graphe.indexOf(varS),nouvelleDistance);
							System.out.print(". En passant par "+rechercheSommet.getNom()
									+" le cout devient "+nouvelleDistance+"\n");
						}		
						
					}
				}
				
				
			}
		}
		
		System.out.print("\n"+"*FIN ALGO"+"\n"
				+this.t.get(0).getNom()+" est seul dans t. C'est soit le plus éloigné du sommet-source, soit un sommet innateignable."
				+"\n"+"\n"+"*BILAN"
				+"\n"+"g = "+this.graphe.afficheSommetsGraphe()
				+"pred = "+this.afficherPred()
				+"pi = "+this.afficherPi());

		System.out.print("Sommets innateignables depuis "+this.s.getNom()+" : ");
		ListIterator<Integer> iter0 = this.pi.listIterator();
		while (iter0.hasNext()){ //on parcourt la liste des couts afin d'identifier les sommets innateignables
			int index = iter0.nextIndex();
			int varC = iter0.next();
			Sommet varS = this.graphe.getSommet(index);
			if (varC == Integer.MAX_VALUE){
				System.out.print(varS.getNom()+";");
			}
		}
			
		//RESULTATS
		
		System.out.println("\n"+"\n"+"*RESULTATS");

		Sommet sMax = null ; //variable qui contient le sommet le plus éloigné depuis la source - on affiche le chemin associé par défaut
		int cMax = 0 ; //cout associé à sMax
		ListIterator<Integer> iter1 = this.pi.listIterator();
		while (iter1.hasNext()){ //on parcourt la liste des couts afin d'identifier sMax et cMax
			int index = iter1.nextIndex();
			int varC = iter1.next();
			Sommet varS = this.graphe.getSommet(index);
			if (varC > cMax && varC < Integer.MAX_VALUE){
				cMax = varC ;
				sMax = varS ;
			}
		}
	
		System.out.println("Plus-court chemin jusqu'au sommet le plus éloigné de la source : ");
		this.affichageResultat(sMax);	
	
		System.out.println("Plus-court chemin jusqu'au sommet passé en paramètre de l'algo : ");
		this.affichageResultat(sortie);	

	
	}	
		
}
