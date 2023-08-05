package progetto.polinomi;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import poo.polinomi.Polinomio;

@SuppressWarnings("serial")
public class PannelloPolinomi extends JPanel {
	
	protected static JPanel p;
	protected static JScrollPane scroll;
	protected static Box box;

	protected static List<JCheckBox> llCB = new ArrayList<>();
	protected static ArrayList<Polinomio> llP = new ArrayList<>();
	
	
	public PannelloPolinomi() {
		
		p = this;
		box = Box.createVerticalBox();
		p.add(box);
		scroll = new JScrollPane(box);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
}//PannelloPolinomi
