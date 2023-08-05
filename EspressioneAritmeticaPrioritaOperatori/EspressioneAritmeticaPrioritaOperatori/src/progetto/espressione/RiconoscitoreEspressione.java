package progetto.espressione;

public class RiconoscitoreEspressione {
	private static String op = "[\\^\\*/%\\+\\-]?";
	private static String r1 = "[\\(]*[\\d]+[\\)]*";   
	private static String r2 = op + "[\\(]*" + "[\\d]+[\\)]*";
	private static String r3 = r1 + "(" + r2 + ")+";
	private static String regex = "(" + r1 + "|" + r3 + ")";
	
	public static boolean valutaEspressione(String s) {
		if(s.matches(regex))
			return true;
		return false;
	}//valutaEspressione
	
}//RiconoscitoreEspressione
