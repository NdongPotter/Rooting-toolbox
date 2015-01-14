public class Test {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//TEST 1
		System.out.println("_____________________________________ DEBUT TEST 1 _____________________________________");
		System.out.println("Exemple tiré du cours d'objet");

		/*Test 1 : Instanciation des sommets*/
		Sommet s1 = new Sommet("s1");
		Sommet s2 = new Sommet("s2");		
		Sommet s3 = new Sommet("s3");
		Sommet s4 = new Sommet("s4");
		Sommet s5 = new Sommet("s5");
		Sommet s6 = new Sommet("s6");

		/*Test 1 : Définition des successeurs et capacités*/
		s1.addSuccesseur(s2, 3, 3);
		s1.addSuccesseur(s3, 6, 6);
		s1.addSuccesseur(s5, 3, 3);
		s2.addSuccesseur(s3, 3, 3);
		s2.addSuccesseur(s4, 6, 6);
		s3.addSuccesseur(s4, 1, 1);
		s4.addSuccesseur(s5, 6, 6);
		s4.addSuccesseur(s6, 1, 1);
		s5.addSuccesseur(s3, 2, 2);
		s5.addSuccesseur(s6, 5, 5);
		
		/*Test 1 : Définition du graphe*/
		Graphe g1 = new Graphe(true);
		g1.addSommet(s1);
		g1.addSommet(s2);
		g1.addSommet(s3);
		g1.addSommet(s4);
		g1.addSommet(s5);
		g1.addSommet(s6);

		/*Test 1 : Utilisation de la classe FormatGraphe pour convertir le graphe g en format dot lisible par GraphViz*/
		FormatGraphe f1 = new FormatGraphe(g1);
		f1.ecrireGraphe("TEST_1.dot");
		//bash : "dot -Tjpg -o TEST_1.jpg TEST_1.dot "
		
		/*Test 1 : On applique l'algorithme de Moore-Dijkstra au graphe*/
		MooreDijkstra a1 = new MooreDijkstra(g1,s1);
		a1.algo(s5);

		System.out.println("_____________________________________ FIN TEST 1 _____________________________________");

//TEST 2
		System.out.println("_____________________________________ DEBUT TEST 2 _____________________________________");

		System.out.println("Exemple tiré de : https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra");
		
		/*Test 2 : Instanciation des sommets*/
		Sommet sA = new Sommet("sA");
		Sommet sB = new Sommet("sB");		
		Sommet sC = new Sommet("sC");
		Sommet sD = new Sommet("sD");
		Sommet sE = new Sommet("sE");
		Sommet sF = new Sommet("sF");
		Sommet sG = new Sommet("sG");
		Sommet sH = new Sommet("sH");
		Sommet sI = new Sommet("sI");
		Sommet sJ = new Sommet("sJ");


		/*Test 2 : Définition des successeurs et capacités*/
		sA.addSuccesseur(sB, 85, 85);
		sA.addSuccesseur(sC, 217, 217);
		sA.addSuccesseur(sE, 173, 173);
		sB.addSuccesseur(sF, 80, 80);
		sC.addSuccesseur(sG, 186, 186);
		sC.addSuccesseur(sH, 103, 103);
		sE.addSuccesseur(sJ, 502, 502);
		sF.addSuccesseur(sI, 250, 250);
		sH.addSuccesseur(sD, 183, 183);
		sH.addSuccesseur(sJ, 167, 167);
		sI.addSuccesseur(sJ, 84, 84);

		
		/*Test 2 : Définition du graphe*/
		Graphe g2 = new Graphe(true);
		g2.addSommet(sA);
		g2.addSommet(sB);
		g2.addSommet(sC);
		g2.addSommet(sD);
		g2.addSommet(sE);
		g2.addSommet(sF);
		g2.addSommet(sG);
		g2.addSommet(sH);
		g2.addSommet(sI);
		g2.addSommet(sJ);


		/*Test 2 : Utilisation de la classe FormatGraphe pour convertir le graphe g en format dot lisible par GraphViz*/
		FormatGraphe f2 = new FormatGraphe(g2);
		f2.ecrireGraphe("TEST_2.dot");
		//bash : "dot -Tjpg -o TEST_2.jpg TEST_2.dot "
		
		/*Test 2 : On applique l'algorithme de Moore-Dijkstra au graphe*/
		MooreDijkstra a2 = new MooreDijkstra(g2,sA);
		a2.algo(sJ);

		System.out.println("_____________________________________ FIN TEST 2 _____________________________________");

//TEST 3

		System.out.println("_____________________________________ DEBUT TEST 3 _____________________________________");

System.out.println("Exemple tiré de : http://icosaweb.ac-reunion.fr/Algorithmes/Graphes/Docs/AlgorithmeDijkstra.pdf");
		
		/*Test 3 : Instanciation des sommets*/
		Sommet si = new Sommet("si");
		Sommet sa = new Sommet("sa");		
		Sommet sb = new Sommet("sb");
		Sommet sc = new Sommet("sc");
		Sommet sd = new Sommet("sd");
		Sommet se = new Sommet("se");
		Sommet sg = new Sommet("sg");
		Sommet sh = new Sommet("sh");
		Sommet sk = new Sommet("sk");
		Sommet sl = new Sommet("sl");
		Sommet sm = new Sommet("sm");
		Sommet sn = new Sommet("sn");
		Sommet sp = new Sommet("sp");
		Sommet sf = new Sommet("sf");

		/*Test 3 : Définition des successeurs et capacités*/
		si.addSuccesseur(sa, 1, 1);
		si.addSuccesseur(sc, 6, 6);
		sa.addSuccesseur(sb, 1, 1);
		sa.addSuccesseur(sc, 5, 5);
		sa.addSuccesseur(sd, 4, 4);
		sb.addSuccesseur(sd, 2, 2);
		sc.addSuccesseur(sh, 3, 3);
		sd.addSuccesseur(sc, 4, 4);
		sd.addSuccesseur(se, 2, 2);
		se.addSuccesseur(sb, 1, 1);
		se.addSuccesseur(sl, 4, 4);
		sg.addSuccesseur(sc, 3, 3);
		sg.addSuccesseur(sm, 1, 1);
		sh.addSuccesseur(sd, 3, 3);
		sh.addSuccesseur(sg, 2, 2);
		sh.addSuccesseur(sm, 5, 5);
		sh.addSuccesseur(sn, 8, 8);
		sk.addSuccesseur(sd, 4, 4);
		sk.addSuccesseur(sn, 2, 2);
		sl.addSuccesseur(sk, 6, 6);
		sl.addSuccesseur(sp, 7, 7);
		sm.addSuccesseur(sf, 12, 12);
		sn.addSuccesseur(sf, 9, 9);
		sp.addSuccesseur(sn, 1, 1);
		sp.addSuccesseur(sf, 6, 6);

		/*Test 3 : Définition du graphe*/
		Graphe g3 = new Graphe(true);
		g3.addSommet(si);
		g3.addSommet(sa);
		g3.addSommet(sb);
		g3.addSommet(sc);
		g3.addSommet(sd);
		g3.addSommet(se);
		g3.addSommet(sg);
		g3.addSommet(sh);
		g3.addSommet(sk);
		g3.addSommet(sl);
		g3.addSommet(sm);
		g3.addSommet(sn);
		g3.addSommet(sp);
		g3.addSommet(sf);

		/*Test 2 : Utilisation de la classe FormatGraphe pour convertir le graphe g en format dot lisible par GraphViz*/
		FormatGraphe f3 = new FormatGraphe(g3);
		f3.ecrireGraphe("TEST_3.dot");
		//bash : "dot -Tjpg -o TEST_3.jpg TEST_3.dot "
		
		/*Test 2 : On applique l'algorithme de Moore-Dijkstra au graphe*/
		MooreDijkstra a3 = new MooreDijkstra(g3,si);
		a3.algo(sf);
		
		System.out.println("_____________________________________ FIN TEST 3 _____________________________________");
	}
}
		
