package poo.math;

import javax.swing.*;

public class Application {

	static String b1;
	static String b2;
	enum VALORE{PRIMO, SECONDO};
	static String PATTERN_INTERO = "\\d+";
	
	@SuppressWarnings("unused")
	public static void main(String...strings) {
		SCEGLI:
		for(;;) {
			String so = JOptionPane.showInputDialog("Digita il tipo di operazione da svolgere.");
			if(JOptionPane.CANCEL_OPTION == -1) {
				int i = -1;
				do {
					i = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler annullare quest'operazione?");
					if(i == JOptionPane.NO_OPTION)
						continue SCEGLI;
					if(i == JOptionPane.YES_OPTION)
						System.exit(-1);
					if(i == JOptionPane.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Devi scegliere SI o NO!");
					if(i == JOptionPane.CLOSED_OPTION)
						System.exit(-1);
				}while(i != JOptionPane.YES_OPTION);
			}//if
			try {
				switch(so.toUpperCase()) {
					case "INCREMENTO","INCR": 
						incremento();
						continue SCEGLI;
					case "DECREMENTO","DECR": 
						decremento();
						continue SCEGLI;
					case "ADDIZIONE","ADD": 
						addizione();
						continue SCEGLI;
					case "SOTTRAZIONE","SUB": 
						sottrazione();
						continue SCEGLI;
					case "MOLTIPLICAZIONE","MUL": 
						moltiplicazione();
						continue SCEGLI;
					case "DIVISIONE","DIV": 
						divisione();
						continue SCEGLI;
					case "RESTO","REM": 
						resto();
						continue SCEGLI;
					case "POTENZA","POW": 
						potenza();
						continue SCEGLI;
					case "":
						JOptionPane.showMessageDialog(null, "Digitare un'operazione. Grazie.");
						continue SCEGLI;
					default: JOptionPane.showMessageDialog(null, "Nessuna operazione corrispondete. Riprova...");
				}//switch
			}catch(NullPointerException n) {
				int i = -1;
				do {
					i = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler annullare quest'operazione?");
					if(i == JOptionPane.NO_OPTION)
						continue SCEGLI;
					if(i == JOptionPane.YES_OPTION)
						System.exit(-1);
					if(i == JOptionPane.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Devi scegliere SI o NO!");
					if(i == JOptionPane.CLOSED_OPTION)
						System.exit(-1);
				}while(i != JOptionPane.YES_OPTION);
			}//catch
		}//for;
		
	}//main
	
	static void erroreNumero(VALORE valore) {
		JOptionPane.showMessageDialog(null, "Inserire un numero valido!");
		if(valore == VALORE.PRIMO)
			primoValore();
		else
			secondoValore();
	}//erroreNumero
	
	static void primoValore() {
			String input1 = JOptionPane.showInputDialog("Inserisci il valore del primo BigInt");
			if(input1.length() == 0) {
				int i = -1;
				do {
					i = JOptionPane.showConfirmDialog(null, "Inserire un numero valido!");
					if(i == JOptionPane.YES_OPTION)
						primoValore();
					if(i == JOptionPane.NO_OPTION)
						System.exit(-1);
					if(i == JOptionPane.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Devi scegliere SI o NO!");
					if(i == JOptionPane.CLOSED_OPTION)
						System.exit(-1);
				}while(i != JOptionPane.YES_OPTION);
			}//if
			else{
				if(input1.matches(PATTERN_INTERO))
					b1 = input1;
				else
					erroreNumero(VALORE.PRIMO);
			}//else
	}//acquisisciValori
	static void secondoValore() {
		String input2 = JOptionPane.showInputDialog("Inserisci il valore del secondo BigInt");
		if(input2.length() == 0) {
			int i = -1;
			do {
				i = JOptionPane.showConfirmDialog(null, "Inserire un numero valido!");
				if(i == JOptionPane.NO_OPTION)
					secondoValore();
				if(i == JOptionPane.YES_OPTION)
					System.exit(-1);
				if(i == JOptionPane.CANCEL_OPTION)
					JOptionPane.showMessageDialog(null, "Devi scegliere SI o NO!");
				if(i == JOptionPane.CLOSED_OPTION)
					System.exit(-1);
			}while(i != JOptionPane.YES_OPTION);
		}//if
		else{
			if(input2.matches(PATTERN_INTERO))
				b2 = input2;
			else
				erroreNumero(VALORE.SECONDO);
		}//else
	}//secondoValore
	
	static void incremento() {
		primoValore();
		BigInt x1 = new BigIntLL(b1);
		JOptionPane.showMessageDialog(null, x1.value()+" + 1 = "+x1.incr());
	}//incremento
	
	static void decremento() {
		primoValore();
		BigInt x1 = new BigIntLL(b1);
		JOptionPane.showMessageDialog(null, x1.value()+" - 1 = "+x1.decr());
	}//decremento
	
	static void addizione() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		BigInt x2 = new BigIntLL(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" + "+x2.value()+" = "+x1.add(x2));
	}//addizione
	
	static void sottrazione() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		BigInt x2 = new BigIntLL(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" - "+x2.value()+" = "+x1.sub(x2));
	}//sottrazione
	
	static void moltiplicazione() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		BigInt x2 = new BigIntLL(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" * "+x2.value()+" = "+x1.mul(x2));
	}//moltiplicazione
	
	static void divisione() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		BigInt x2 = new BigIntLL(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" / "+x2.value()+" = "+x1.div(x2));
	}//divisione
	
	static void resto() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		BigInt x2 = new BigIntLL(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" % "+x2.value()+" = "+x1.rem(x2));
	}//resto
	
	static void potenza() {
		primoValore();
		secondoValore();
		BigInt x1 = new BigIntLL(b1);
		int x2 = Integer.parseInt(b2);
		JOptionPane.showMessageDialog(null, x1.value()+" ^ "+x2+" = "+x1.pow(x2));
	}//potenza
	
}//Main
