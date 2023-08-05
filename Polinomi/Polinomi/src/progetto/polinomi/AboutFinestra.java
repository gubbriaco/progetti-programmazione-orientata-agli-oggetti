package progetto.polinomi;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AboutFinestra extends JFrame {
	
	private static JPanel p;
	private static JLabel l;
	
	public AboutFinestra() {

		this.setTitle("About");
		this.setSize(250, 100);
		this.setLocation(600, 350);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p = new JPanel();
		this.add(p, BorderLayout.CENTER);
		l = new JLabel("PolinomioGUI - progetto POO");
		p.add(l, BorderLayout.CENTER);
		
	}
	
}//AboutFinestra
