package progetto.polinomi;

import java.util.StringTokenizer;

import poo.polinomi.Monomio;
import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;

public class IsPolinomio {
	
	public static boolean riconosciPolinomio( String polinomio ) {
		String COEFF = "[\\d]*";  //COEFF
		String SIGNINIZIALE = "[\\-]?";    //SIGNINIZIALE
		String SIGN = "[\\+|\\-]";         //SIGN
		String GRADO = "[xX][\\^]?[\\d]*";  //GRADO
		String mINIZALE0 = SIGNINIZIALE + "[\\d]+"; //mINIZALE0 
		String mSUCCESSIVO0 = SIGN + "[\\d]+";//mSUCCESSIVO0
	
		String mINIZIALE = SIGNINIZIALE + COEFF + GRADO; //mINIZIALE
		String mSUCCESSIVO = SIGN + COEFF + GRADO; //mSUCCESSIVO
		
		String mINIZIALEf = "(" + mINIZALE0 + "|" + mINIZIALE + ")";//mINIZIALEf
		String mSUCCESSIVOf = "(" + mSUCCESSIVO0 + "|" + mSUCCESSIVO +")";//mSUCCESSIVOf
		
		String POLINOMIO = mINIZIALEf + "+" + mSUCCESSIVOf + "*";//POLINOMIO
		if( polinomio.matches(POLINOMIO) ) {
			return true;
		}//if
		else {
			return false;
		}//else
	}//riconosciPol
	
	public static Polinomio creaPolinomio( String polinomio ) {
		String SIGN = "[\\-]?";//SIGN
		String COEFFGRADO0 = SIGN + "[\\d]+";//COEFFGRADO0
		String COEFFGRADO1 = SIGN + "[\\d]+" + "[xX][\\^]?"; //COEFFGRADO1
		String COEFFGRADOX = SIGN + "[\\d]+" + "[xX][\\^]?[\\d]+"; //COEFFGRADOX
		String COEFF1GRADO1 = SIGN + "[xX]"; //COEFF1GRADO1
		String COEFF1GRADOX = SIGN + "[xX][\\^]?[\\d]+";//COEFF1GRADOX
		Polinomio p = new PolinomioLL();
		StringTokenizer st1 = new StringTokenizer(polinomio, "+-", true);
		while( st1.hasMoreTokens() ) {
			String monomio = st1.nextToken();
			if( monomio.equals("-") ) {
				monomio.concat( st1.nextToken() );
			}//if
			StringTokenizer st2 = new StringTokenizer(monomio, "xX^");
			
			if(monomio.matches(COEFFGRADO0)) {
				int coeff = Integer.parseInt(st2.nextToken());
				p.add(new Monomio(coeff, 0));
			}//if
			if(monomio.matches(COEFFGRADO1)) {
				int coeff = Integer.parseInt(st2.nextToken());
				p.add(new Monomio(coeff, 1));
			}//if
			if(monomio.matches(COEFFGRADOX)) {
				int coeff = Integer.parseInt(st2.nextToken());
				int grado = Integer.parseInt(st2.nextToken());
				p.add(new Monomio(coeff, grado));
			}//if
			if(monomio.matches(COEFF1GRADO1)) {  
				int coeff = 0;
				if(st2.nextToken().equals("+")) {
					coeff = 1;
				}//if
				coeff = -1;
				p.add(new Monomio(coeff, 1));
			}//if
			if(monomio.matches(COEFF1GRADOX)) {
				int coeff = 0;
				int grado = 0;
				if(st2.nextToken().equals("+")) {
					coeff = 1;
				}//if
				else {
					coeff = -1;
				}//else
				grado  = Integer.parseInt(st2.nextToken());
				p.add(new Monomio(coeff, grado));
			}//if
		}//while
		return p;
	}//creaPol

}//RiconoscitorePolinomio