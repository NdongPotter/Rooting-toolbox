public class Test{


	public static void main(String[] args) {
		

		
//CONSTRUCTION GRAPHES
	/*Graphe 1*/
			/*Instanciation des sommets*/
			Sommet s1 = new Sommet("s1");
			Sommet s2 = new Sommet("s2");		
			Sommet s3 = new Sommet("s3");
			Sommet s4 = new Sommet("s4");
			Sommet s5 = new Sommet("s5");
			Sommet s6 = new Sommet("s6");
			/*Définition des successeurs et capacités*/
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
			/*Définition du graphe*/
			Graphe g1 = new Graphe(true);
			g1.addSommet(s1);
			g1.addSommet(s2);
			g1.addSommet(s3);
			g1.addSommet(s4);
			g1.addSommet(s5);
			g1.addSommet(s6);
			
			
//TEST affichage
			
			//System.out.print(s1.afficherSuccesseurs());
			
	/*Graphe 2*/
			/*Instanciation des sommets*/
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
			/*Définition des successeurs et capacités*/
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
			/*Définition du graphe*/
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
	/*Graphe 3*/
			/*Instanciation des sommets*/
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
			/*Définition des successeurs et capacités*/
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
			/*Définition du graphe*/
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
	/*Graphe 4*/
			/*Instanciation des sommets*/
				//Sommet s10 = new Sommet("s10");
			Sommet s11 = new Sommet("s11");
			Sommet s12 = new Sommet("s12");		
			Sommet s13 = new Sommet("s13");
			Sommet s14 = new Sommet("s14");
			Sommet s15 = new Sommet("s15");
			Sommet s16 = new Sommet("s16");
			Sommet s17 = new Sommet("s17");
			/*Définition des successeurs et capacités*/
				//s10.addSuccesseur(s12, 3, 3);
			s11.addSuccesseur(s12, 3, 3);
			s11.addSuccesseur(s13, 2, 2);
			s12.addSuccesseur(s13, -2, -2);
			s12.addSuccesseur(s14, 2, 2);
			s12.addSuccesseur(s16, 2, 2);
			s13.addSuccesseur(s14, 5, 5);
			s13.addSuccesseur(s15, 2, 2);
			s14.addSuccesseur(s15, -1, -1);
			s14.addSuccesseur(s16, 3, 3);
				//s14.addSuccesseur(s13, 5, 5);
			s15.addSuccesseur(s16, 2, 2);
			s15.addSuccesseur(s17, 4, 4);
			s16.addSuccesseur(s17, 3, 3);
			/*Définition du graphe*/
			Graphe g4 = new Graphe(true);
				//g4.addSommet(s10);
			g4.addSommet(s11);
			g4.addSommet(s12);
			g4.addSommet(s13);
			g4.addSommet(s14);
			g4.addSommet(s15);
			g4.addSommet(s16);
			g4.addSommet(s17);
	/*Graphe 5*/
			/*Instanciation des sommets*/
			Sommet s20 = new Sommet("s20");
			Sommet s21 = new Sommet("s21");		
			Sommet s22 = new Sommet("s22");
			Sommet s23 = new Sommet("s23");
			Sommet s24 = new Sommet("s24");
			Sommet s25 = new Sommet("s25");
			Sommet s26 = new Sommet("s26");
			Sommet s27 = new Sommet("s27");
			Sommet s28 = new Sommet("s28");
			Sommet s29 = new Sommet("s29");

			/*Définition des successeurs et capacités*/
			s20.addSuccesseur(s21, 6, 6);
			s20.addSuccesseur(s22, 3, 3);
			s20.addSuccesseur(s23, 4, 4);
			s21.addSuccesseur(s24, 5, 5);
			s21.addSuccesseur(s25, -4, -4);
			s22.addSuccesseur(s21, 2, 2);
			s22.addSuccesseur(s23, 1, 1);
			s23.addSuccesseur(s26, 2, 2);
			s23.addSuccesseur(s28, -2, -2);
			s24.addSuccesseur(s25, -7, -7);
			s24.addSuccesseur(s27, -4, -4);
			s25.addSuccesseur(s26, 2, 2);
			s25.addSuccesseur(s27, 2, 2);
			s26.addSuccesseur(s28, -1, -1);
			s26.addSuccesseur(s29, -3, -3);
			s27.addSuccesseur(s29, 1, 1);
			s28.addSuccesseur(s29, -1, -1);
			/*Définition du graphe*/
			Graphe g5 = new Graphe(true);
			g5.addSommet(s20);
			g5.addSommet(s21);
			g5.addSommet(s22);
			g5.addSommet(s23);
			g5.addSommet(s24);
			g5.addSommet(s25);
			g5.addSommet(s28);	
			g5.addSommet(s26);	
			g5.addSommet(s27);	
			g5.addSommet(s29);
			
	/*Graphe 5*/
			/*Instanciation des sommets*/
			Sommet s31 = new Sommet("s31");		
			Sommet s32 = new Sommet("s32");
			Sommet s33 = new Sommet("s33");
			Sommet s34 = new Sommet("s34");
			Sommet s35 = new Sommet("s35");
			Sommet s36 = new Sommet("s36");

			/*Définition des successeurs et capacités*/
			//s31.addSuccesseur(s32, 3, 3);
			s31.addSuccesseur(s33, 6, 6);
			s31.addSuccesseur(s35, 3, 3);
			s32.addSuccesseur(s33, -3, -3);
			s32.addSuccesseur(s34, 6, 6);
			s33.addSuccesseur(s34, 1, 1);
			s34.addSuccesseur(s35, 6, 6);
			s34.addSuccesseur(s36, 1, 1);
			s35.addSuccesseur(s33, -2, -2);
			s35.addSuccesseur(s36, 5, 5);
			
			/*Définition du graphe*/
			Graphe g6 = new Graphe(true);
			g6.addSommet(s31);
			g6.addSommet(s32);
			g6.addSommet(s33);
			g6.addSommet(s34);
			g6.addSommet(s35);
			g6.addSommet(s36);	
			
//TESTS
	//TestMooreDijksrta
	/*Test 1*/
			System.out.println("_____________________________________ DEBUT TEST 1 _____________________________________");
				System.out.println("\n"+"Exemple tiré du cours d'objet sur l'algo homonyme");
				FormatGraphe f1 = new FormatGraphe(g1);
				f1.ecrireGraphe("TEST_1.dot");
				//bash : "dot -Tjpg -o TEST_1.jpg TEST_1.dot "
				MooreDijkstra a1 = new MooreDijkstra(g1,s1); // paramètres : graphe étudié & sommet-source
				Chemins c1 = a1.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c1.afficherChemins();
				c1.afficherResultat(s6);
				c1.afficherResultat(c1.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 1 _____________________________________");
	/*Test 2*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 2 _____________________________________");
				System.out.println("\n"+"Exemple tiré de : https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra");
				FormatGraphe f2 = new FormatGraphe(g2);
				f2.ecrireGraphe("TEST_2.dot");
				//bash : "dot -Tjpg -o TEST_2.jpg TEST_2.dot "
				MooreDijkstra a2 = new MooreDijkstra(g2,sA); // paramètres : graphe étudié & sommet-source
				Chemins c2 = a2.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c2.afficherChemins();
				c2.afficherResultat(sJ);
				c2.afficherResultat(c2.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 2 _____________________________________");

	/*Test 3*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 3 _____________________________________");
				System.out.println("\n"+"Exemple tiré de : http://icosaweb.ac-reunion.fr/Algorithmes/Graphes/Docs/AlgorithmeDijkstra.pdf");
				FormatGraphe f3 = new FormatGraphe(g3);
				f3.ecrireGraphe("TEST_3.dot");
				//bash : "dot -Tjpg -o TEST_3.jpg TEST_3.dot "
				MooreDijkstra a3 = new MooreDijkstra(g3,si); // paramètres : graphe étudié & sommet-source
				Chemins c3 = a3.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c3.afficherChemins();
				c3.afficherResultat(sf);
				c3.afficherResultat(c3.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 3 _____________________________________");
	
	//TestFordBellman
	/*Test 4*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 4 _____________________________________");
				System.out.println("\n"+"Exemple tiré du cours d'objet sur l'algo homonyme");
				FormatGraphe f4 = new FormatGraphe(g4);
				f4.ecrireGraphe("TEST_4.dot");
				//bash : "dot -Tjpg -o TEST_4.jpg TEST_4.dot "
				FordBellman fb1 = new FordBellman(g4,s11); // paramètres : graphe étudié & sommet-source
				Chemins c4 = fb1.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c4.afficherChemins();
				c4.afficherResultat(s16);
				c4.afficherResultat(c4.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 4 _____________________________________");
	/*Test 5*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 5 _____________________________________");
				System.out.println("\n"+"Exemple inventé");
				FormatGraphe f5 = new FormatGraphe(g5);
				f5.ecrireGraphe("TEST_5.dot");
				//bash : "dot -Tjpg -o TEST_5.jpg TEST_5.dot "
				FordBellman fb2 = new FordBellman(g5,s20); // paramètres : graphe étudié & sommet-source
				Chemins c5 = fb2.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c5.afficherChemins();
				c5.afficherResultat(s29);
				c5.afficherResultat(c5.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 5 _____________________________________");

	//TestBellman
	/*Test 6*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 6 _____________________________________");
				System.out.println("\n"+"Exemple tiré du cours d'objet sur l'algo homonyme");
				FormatGraphe f6 = new FormatGraphe(g6);
				f6.ecrireGraphe("TEST_6.dot");
				//bash : "dot -Tjpg -o TEST_6.jpg TEST_6.dot "
				Bellman b1 = new Bellman(g6,s31); // paramètres : graphe étudié & sommet-source
				Chemins c6 = b1.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c6.afficherChemins();
				c6.afficherResultat(s36);
				c6.afficherResultat(c6.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 6 _____________________________________");			
	/*Test 7*/
			System.out.println("\n"+"_____________________________________ DEBUT TEST 7 _____________________________________");
				System.out.println("\n"+"Exemple tiré du cours d'objet sur l'algo de FordBellman");
				Bellman b2 = new Bellman(g4,s11); // paramètres : graphe étudié & sommet-source
				Chemins c7 = b2.algo(); // paramètre : sommet de sortie (par défaut l'algo affiche le chemin vers le sommet le plus éloigné de la source)
				c7.afficherChemins();
				c7.afficherResultat(s15);
				c7.afficherResultat(c7.getMaxiPi());
			System.out.println("\n"+"_____________________________________ FIN TEST 6 _____________________________________");	
	}	
}
		
