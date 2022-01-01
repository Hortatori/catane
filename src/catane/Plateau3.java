package catane;

import java.util.*;

import catane.Paysage;

public class Plateau3 {
Case[] [] plateauC ;
Sommet[] [] plateauS ;
LinkedList<Route> routes ;
String[][] routesHorizontales ;
String[][] routesVerticales ; 
LinkedList<Carte> pioche ;
VuePlateau vp ;


public Plateau3() {
// version basique du constructeeur de plateau pour un 4 cases sur 4
Sommet[][] plateaus = new Sommet[5][5] ;
for (int i = 0 ; i<5 ; i++ ) {
	for (int j = 0 ; j<5 ; j++ ) {
		plateaus [i][j] = new Sommet(i, j) ;	
		//System.out.println(plateauS [i][j])
									}
							}
this.plateauS = plateaus ;

Case[][] plateauc = new Case[4][4] ;
for (int i = 0 ; i<4 ; i++ ) {
		for (int j = 0 ; j<4 ; j++ ) {
			plateauc [i][j] = new Case(plateauS[i][j], plateauS[i][j+1], plateauS[i+1][j], plateauS[i+1][j+1]) ;		
	}

}
this.plateauC = plateauc ;
this.setCaseType();

String[][] routesHorizontales = new String[5][5] ;
for (int i = 0 ; i<5 ; i++ ) {
		for (int j = 0 ; j<5 ; j++ ) {
			routesHorizontales [i][j] = "---------" ;		
			//System.out.println(routesHorizontales[i][j]);
	}
}
this.routesHorizontales = routesHorizontales ;


//System.out.println("caribou");
//System.out.println(this.routesHorizontales[3][4]);

String[][] routesVerticales = new String[5][5] ;
for (int i = 0 ; i<5 ; i++ ) {
		for (int j = 0 ; j<5 ; j++ ) {
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
	
	
for (int i = 0 ; i<4 ; i++ ) {
		String sc = "";
		String ss = "";
		String sstandard ="";
		
		for (int j = 0 ; j<4 ; j++ ) {
			sc += this.routesVerticales[i][j]+"  " + this.plateauC[i][j].toString()+"   ";
			sstandard += this.routesVerticales[i][j]+"  		" ;
			ss += this.plateauS[i][j].toString() +  this.routesHorizontales[i][j] ;
			
			
			
		}
		ss += this.plateauS[i][4] ;
		sc += this.routesVerticales[i][3]	;	
		
		System.out.println(ss);
		System.out.println(sstandard);
		System.out.println(sc);
		System.out.println(sstandard);
	}

String ss = "";
for (int j = 0 ; j<4 ; j++ ) {
	ss +=  this.plateauS[4][j].toString() +  this.routesHorizontales[4][j] ;
		}
System.out.println(ss);


}
























}
