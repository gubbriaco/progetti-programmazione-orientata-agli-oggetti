package progetto.espressione;

import java.util.StringTokenizer;

import poo.stack.Stack;
import poo.stack.StackConcatenato;

public class GetRisultato {
	
	public static int valutaEspressione() {
		StringTokenizer st = new StringTokenizer(ApplicationEspressione.CalcolaEspressione.tf.getText(), "^*/%+-()", true);
		return valutaEspressione(st);
	}//valutaEspressione
	private static int valutaEspressione(StringTokenizer st) {
		String operatoreCor = "";
		String operatore = "";
		Stack<Integer> operandi = new StackConcatenato<>();
		Stack<Character> operatori = new StackConcatenato<>();
		while(st.hasMoreTokens()) {
			operatoreCor = st.nextToken();
			if(operatoreCor.matches("\\(")){
				operandi.push(valutaEspressione(st));
			}//if
			else if(operatoreCor.matches("\\)")) {
				break;
			}//else if
			else if(!isOperatore(operatoreCor)) {
				operandi.push(Integer.parseInt(operatoreCor));
			}//else if
			else if(isOperatore(operatoreCor)) {
				if(operatori.isEmpty()) {
					operatori.push(operatoreCor.charAt(0));
				}//if
				else {
					operatore = String.valueOf(operatori.peek());
					if(priorita(operatoreCor,operatore)) {
						operatori.push(operatoreCor.charAt(0));
					}//if
					else {
						while(!operatori.isEmpty()) {
							if(priorita(operatoreCor, operatori.peek().toString())) {
								break;
							}//if
							calcola(operatore, operandi, operatori);
						}//while
						operatori.push(operatoreCor.charAt(0));
					}//else
				}//else
			}//else if
		}//while
		while(!operatori.isEmpty()) {
			calcola(operatore, operandi, operatori);
		}//while
		if(operandi.size() != 1) {
			throw new UnsupportedOperationException("ATTENZIONE. Espressione malformata!");
		}//if
		return operandi.pop();
	}//valutaEspressione
	
	private static void calcola(String operatore, Stack<Integer> operandi, Stack<Character> operatori) {
		int operando2 = operandi.pop();
		int operando1 = operandi.pop();
		operatore = String.valueOf(operatori.pop());
		switch(operatore) {
			case "+":	
				operandi.push(operando1 + operando2);
				break;
			case "-":	
				operandi.push(operando1 - operando2);
				break;
			case "*":	
				operandi.push(operando1 * operando2);
				break;
			case "/":	
				operandi.push(operando1 / operando2);
				break;
			case "%":	
				operandi.push(operando1 % operando2);
				break;
			default :	
				operandi.push((int)Math.pow((double)operando1, (double)operando2));
		}//switch
	}//calcola
	
	private static boolean isOperatore(String opCor) {
		String operatore = "[\\^\\*/%\\+\\-]";
		if(opCor.matches(operatore))
			return true;
		return false;
	}//isOperatore
	
	private static boolean priorita(String opCor, String operatore) {
		String r1 = "[\\^]";
		String r2 = "[\\*/%]";
		String r3 = "[\\+\\-]";
		if(opCor.matches(r1) && !operatore.matches(r1))
			return true;
		if(opCor.matches(r1) && operatore.matches(r2))
			return true;
		if(opCor.matches(r1) && operatore.matches(r3))
			return true;
		if(opCor.matches(r2) && operatore.matches(r3))
			return true;
		return false;
	}//priorita
	
}//GetRisultato