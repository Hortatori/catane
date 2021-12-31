package catane;

import java.util.*;


public class Plateau2 {
Case[] [] plateauC ;
Sommet[] [] plateauS ;
LinkedList<Route> routes ;
String[][] routesHorizontales ;
String[][] routesVerticales ; 
LinkedList<Carte> pioche ;
LinkedList<Port> ports ;


public Plateau2() {
// version basique du constructeeur de plateau pour un 4 cases sur 4
Sommet[][] plateaus = new Sommet[7][7] ;
for (int i = 0 ; i<7 ; i++ ) {
	for (int j = 0 ; j<7 ; j++ ) {
		plateaus [i][j] = new Sommet(i-1, j-1) ;	
		//System.out.println(plateauS [i][j])
									}
							}
this.plateauS = plateaus ;

Case[][] plateauc = new Case[4][4] ;
for (int i = 1 ; i<5 ; i++ ) {
		for (int j = 1 ; j<5 ; j++ ) {
			plateauc [i-1][j-1] = new Case(plateauS[i][j], plateauS[i][j+1], plateauS[i+1][j], plateauS[i+1][j+1]) ;		
	}

}
this.plateauC = plateauc ;
this.setCaseType();
setPorts(4, 4) ;
setPositionPorts() ;

String[][] routesHorizontales = new String[7][7] ;


for (int i = 0 ; i<7 ; i++ ) {
	for (int j = 0 ; j<7 ; j++ ) {
		routesHorizontales [i][j] = "		" ;		
}
}

for (int i = 1 ; i<6 ; i++ ) {
		
		for (int j = 1 ; j<6 ; j++ ) {
			routesHorizontales [i][j] = "-------------" ;		
			//System.out.println(routesHorizontales[i][j]);
	}
}
this.routesHorizontales = routesHorizontales ;


//System.out.println("caribou");
//System.out.println(this.routesHorizontales[3][4]);

String[][] routesVerticales = new String[7][7] ;
for (int i = 0 ; i<7 ; i++ ) {
	for (int j = 0 ; j<7 ; j++ ) {
		routesVerticales [i][j] = " " ;		
}
}
for (int i = 1 ; i<6 ; i++ ) {
		for (int j = 1 ; j<6 ; j++ ) {
			routesVerticales [i][j] = "|" ;		
	}
}
this.routesVerticales = routesVerticales ;






}
public void setCaseType() {
	Paysage[]	tabp = new Paysage[16];

for (int j = 0  ; j<3 ; j++) {
	tabp[5*j] = Paysage.FORET ;
	tabp[5*j+1] = Paysage.MONTAGNE;
	tabp[5*j+2] = Paysage.COLLINE ;
	tabp[5*j+3] = Paysage.PRE ;
	tabp[5*j+4] = Paysage.CHAMP ;
	
}
tabp[15] = Paysage.DESERT ;

List<Paysage> listep = Arrays.asList(tabp);
Collections.shuffle(listep);
System.out.println(listep);
Integer[] tabn = new Integer[] { 2, 3, 4, 5, 6, 8, 9, 10, 11 , 12, 5, 6, 8 , 9, 10};
List<Integer> listen = Arrays.asList(tabn);
Collections.shuffle(listen);


int k = 0 ;
int l = 0 ;
for (int i = 0 ; i<4 ; i++ ) {
	for (int j = 0 ; j<4 ; j++ ) {
		
		this.plateauC[i][j].setType(listep.get(l));
		
		if (listep.get(l) != Paysage.DESERT) { 
		this.plateauC[i][j].setNumber(listen.get(k));
		k++;
		}
		l++;
		
	}
}
}

// on paramétrise la fonction pour pouvoir la généraliser plus facilement
public void setPorts(int largeur, int longueur) {
	int nb_ports = largeur + longueur ;
	LinkedList<Port> ports = new LinkedList<Port>() ;
	int reste = nb_ports / 5 ;// nombre de types de ressources
	
	for (int i = 0 ; i<reste ; i++ ) {
		
		
		for (Paysage pa : Paysage.values() ) {
		if (pa == Paysage.DESERT) { continue;}
		PortSpecial p  = new PortSpecial(pa) ;
		ports.add(p) ;}
		
	}
	
	for (int j = 5 * reste  ; j<nb_ports ; j++ ) {
		Port p = new Port() ;
		ports.add(p);
	}
	Collections.shuffle(ports);
	for (Port p : ports) { System.out.println(p) ; }
		
	this.ports = ports;
}

public void setPositionPorts() {
	int index = 0 ;
	for (int i = 0 ; i < 4 ; i+=2) {
		Port ptop = this.ports.get(index) ;
		Port pbottom = this.ports.get(index+1) ;
		ptop.setSommets(this.plateauS[1][i+1], this.plateauS[1][i+2]);
		pbottom.setSommets(this.plateauS[5][i+2], this.plateauS[5][i+3]);
		index +=2;
	}
	for (int h = 0 ; h < 4 ; h+=2) {
		Port pleft = this.ports.get(index) ;
		Port pright = this.ports.get(index+1) ;
		pleft.setSommets(this.plateauS[h+1][1], this.plateauS[h+2][1]);
		pright.setSommets(this.plateauS[5][h+2], this.plateauS[5][h+3]);
		index +=2;
	}
}

public void afficherPlateau() {
	for (int i = 0 ; i<5 ; i++ ) {
		
		String s = "";
		for (int j = 0 ; j<5 ; j++ ) {
			s += this.plateauS[i][j].toString()+"			";
						}
		System.out.println(s);
	}
	
	
for (int i = 0 ; i<4 ; i++ ) {
		String s = "	 ";
		for (int j = 0 ; j<4 ; j++ ) {
			s += this.plateauC[i][j].toString()+"			";
						}
		System.out.println(s);
	}
	
	
	
	
		}


public void afficherPlateau2() {

	
for (int i = 0 ; i<4 ; i++ ) {
		String sc = "	 ";
		String ss = "";
		for (int j = 0 ; j<4 ; j++ ) {
			sc += this.plateauC[i][j].toString()+"			";
			ss += this.plateauS[i][j].toString()+"			";
		}
		ss += this.plateauS[i][4] ;
						
		System.out.println(ss);
		System.out.println(sc);
	}
String ss = "";
for (int j = 0 ; j<5 ; j++ ) {
	ss += this.plateauS[4][j].toString()+"			";
		}
System.out.println(ss);





}



public void afficherPlateau3() {
	
// pour la gestion des ports, on considère qu'ils seront gérés sur une Array list indépendante et qu'ils seront répartis aléatoirement et ce en utilisant un incrément.
String init ="		";
String porttop = "~~~/¨¨¨¨¨¨¨¨¨\\~~" ;
String portgauche = "~~~~/¨¨¨¨¨¨¨¨¨¨¨" ;
String portdroit = "¨¨¨¨¨¨¨¨¨¨¨¨¨/~~~~	" ;	
String portbottom = "~~~\\__________/~";
String mer = "~~~~~~~~~~~~~~~~";

// Ce code est compliqué mais il pourra permettre plus facilement une extension du plateau
int index_port = 0 ;
LinkedList<Integer> porttab = new LinkedList<Integer>() ;
porttab.add(1);
porttab.add(3);



String top1 ="" ;
String top2 = "" ; 
String top3 = "" ;
String bottom1 ="" ;
String bottom2 = "" ; 
String bottom3 = top1 ;
for (int i = 0 ; i<6 ; i++ ) {
top1 += mer ;
if (porttab.contains(i)) {
top2 += porttop ;
top3 += this.ports.get(index_port).afficherPort();
index_port += 1 ;
bottom2 += mer ;
bottom1 += mer ;
}
else if  (porttab.contains(i-1)) {
	bottom2 += portbottom ;
	bottom1 += this.ports.get(index_port).afficherPort();
	index_port += 1 ;
	top2 += mer ;
	top3 += mer ;
	
}
else {
	top2 += mer ;
	top3 += mer ;
	bottom2 += mer ;
	bottom1 += mer ;
}
}	
	
	
	
System.out.println(top1);
System.out.println(top2);	
System.out.println(top3);

boolean alter = true ;
for (int i = 0 ; i<4 ; i++ ) {
	
	String sc ;
	String ss ;
	String sstandard ;
	if (alter) {
		sc = mer ;
		 ss = mer ;
		 sstandard =mer ; }
	else {
		 sc =  this.ports.get(index_port).afficherPort();
		index_port +=1;
		 ss = mer ;
		 sstandard = portgauche ; }
	
		
		for (int j = 0 ; j<4 ; j++ ) {
			String sint = "   " + this.plateauC[i][j].toString() ;
			while (sint.length() < 15 )  {
				sint += " " ;}
			
			sc += this.routesVerticales[i+1][j+1] + sint ;
			sstandard += this.routesVerticales[i+1][j+1]+"  		" ;
			ss += this.plateauS[i][j].toString() +  this.routesHorizontales[i+1][j+1] ;
			
			
			
		}
		sstandard += this.routesVerticales[i+1][4+1] ;
		ss += this.plateauS[i][4] ;
		sc += this.routesVerticales[i+1][3+1]	;	
		
		
		if (!alter) {
			sc += mer ;
			 ss += mer ;
			 sstandard +=mer ; }
		else {
			 sc +=  this.ports.get(index_port).afficherPort();
			index_port +=1;
			 ss += mer ;
			 sstandard += portgauche ; }
		
		
		
		
		
		
		
		
		
		
		System.out.println(ss);
		System.out.println(sstandard);
		System.out.println(sc);
		System.out.println(sstandard);
	
	alter = !alter ;
	}

String ss = mer ;
for (int j = 0 ; j<4 ; j++ ) {
	ss +=  this.plateauS[4][j].toString() +  this.routesHorizontales[4+1][j+1] ;
		}
	ss += this.plateauS[4][4]+mer ;
System.out.println(ss);

System.out.println(bottom1);
System.out.println(bottom2);	
System.out.println(top1);


}
























}
