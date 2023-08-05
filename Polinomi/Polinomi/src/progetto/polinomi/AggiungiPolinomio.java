package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AggiungiPolinomio extends JFrame{
	
	protected static JFrame f;
	protected static JPanel p1, p2, p3;
	protected static JLabel l;
	protected static JTextField tf;
	protected static JButton b1, b2;
	
	public AggiungiPolinomio() {
		
		f = this;
		f.setTitle("Aggiungi polinomio");
		f.setSize(600, 130);
		f.setLocation(450,  300);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame ann = new AnnullareOperazioneAggiuntaPolinomio();
				ann.setVisible(true);
			}//windowCLosing
		});;
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		f.add(p1, BorderLayout.NORTH);
		l = new JLabel("Aggiungi un nuovo polinomio");
		p1.add(l, BorderLayout.CENTER);
		
		f.add(p2, BorderLayout.CENTER);
		tf = new JTextField("", 14);
		p2.add(tf, BorderLayout.CENTER);
		
		f.add(p3, BorderLayout.SOUTH);
		b1 = new JButton();
		b2 = new JButton();
		b1.setText("annulla");
		b1.setSize(300, 200);
		b2.setText("conferma");
		b2.setSize(300, 200);
		p3.add(b1, BorderLayout.WEST);
		p3.add(b2, BorderLayout.EAST);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == b1) {
					JFrame ua = new AnnullareOperazioneAggiuntaPolinomio();
					ua.setVisible(true);
				}//if
			}//actionPerformed
		});
		b2.addActionListener(new AggiuntaPolinomio());
	}
	
}//AggiungiPolinomio
