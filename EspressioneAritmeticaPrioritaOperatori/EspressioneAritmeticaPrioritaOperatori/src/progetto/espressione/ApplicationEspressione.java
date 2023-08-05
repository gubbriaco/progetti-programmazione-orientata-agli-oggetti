package progetto.espressione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ApplicationEspressione {

	public static void main(String...strings) {
		JFrame f = new CalcolaEspressione();
		f.setVisible(true);
	}//main
	
	
	@SuppressWarnings("serial")
	protected static class CalcolaEspressione extends JFrame {
		
		protected static JFrame f;
		protected static JPanel p1, p2, p3;
		protected static JLabel l;
		protected static JTextField tf;
		protected static JButton b1, b2;
		
		public CalcolaEspressione() {
			f = this;
			this.setTitle("Calcola espressione aritmetica");
			this.setSize(350, 150);
			this.setLocation(575, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					JFrame esc = new Uscita();
					esc.setVisible(true);
				}//windowClosing
			});
			
			p1 = new JPanel();
			f.add(p1, BorderLayout.NORTH);
			l = new JLabel("Inserisci l'espressione aritmetica");
			l.setFont(new Font("Helvetica", Font.BOLD, 20));
			l.setForeground(Color.BLUE);
			p1.add(l, BorderLayout.CENTER);
			
			p2 = new JPanel();
			f.add(p2, BorderLayout.CENTER);
			tf = new JTextField("", 14);
			p2.add(tf, BorderLayout.CENTER);
			
			p3 = new JPanel();
			f.add(p3, BorderLayout.SOUTH);
			b1 = new JButton();
			b1.setText("annulla");
			p3.add(b1, BorderLayout.WEST);
			b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b1) {
						JFrame esc = new Uscita();
						esc.setVisible(true);
					}//if
				}//actionPerformed
			});
			b2 = new JButton();
			b2.setText("OK");
			p3.add(b2, BorderLayout.EAST);
			b2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b2) {
						if(RiconoscitoreEspressione.valutaEspressione(CalcolaEspressione.tf.getText())) {
							JFrame valuta = new ValutaEspressione();
							valuta.setVisible(true);
						}//if
						else {
							JFrame error = new ExprMalformata();
							error.setVisible(true);
						}//else
			 		}//if
				}//actionPerformed
			});
		}
	}//CalcolaEspressione
	
	@SuppressWarnings("serial")
	protected static class Uscita extends JFrame {
		
		protected static JFrame f;
		protected static JPanel p1, p2;
		protected static JLabel l;
		protected static JButton b1, b2;
		
		public Uscita() {
			f = this;
			this.setTitle("ESC");
			this.setSize(350, 150);
			this.setLocation(575, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					Uscita.f.setVisible(false);
				}//windowClosing
			});
			
			p1 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			l = new JLabel("Sei sicuro di voler annullare l'operazione?");
			l.setFont(new Font("Helvetica", Font.HANGING_BASELINE, 14));
			l.setForeground(Color.BLUE);
			p1.add(l, BorderLayout.CENTER);
			b1 = new JButton();
			b1.setText("NO");
			
			p2 = new JPanel();
			this.add(p2, BorderLayout.CENTER);
			p2.add(b1, BorderLayout.WEST);
			b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b1) {
						Uscita.f.setVisible(false);
						CalcolaEspressione.tf.setText("");
					}//if
				}//actionPerformed
			});
			b2 = new JButton();
			b2.setText("SI");
			p2.add(b2, BorderLayout.EAST);
			b2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b2) {
						System.exit(0);
					}//if
				}//actionPerformed
			});
		}
	}//Uscita
	
	@SuppressWarnings("serial")
	protected static class ValutaEspressione extends JFrame {
		protected static JFrame f;
		protected static JPanel p1, p2, p3;
		protected static JLabel l1, l2;
		protected static JButton ok;
		
		public ValutaEspressione() {
			f = this;
			this.setTitle("Valuta espressione aritmetica");
			this.setSize(350, 150);
			this.setLocation(575, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					ValutaEspressione.f.setVisible(false);
					CalcolaEspressione.tf.setText("");
					CalcolaEspressione.f.setVisible(true);
				}//windowClosing
			});
			
			p1 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			l1 = new JLabel("Il risultato di " + CalcolaEspressione.tf.getText() + " è:");
			l1.setFont(new Font("Helvetica", Font.BOLD, 14));
			l1.setForeground(Color.BLUE);
			p1.add(l1, BorderLayout.CENTER);
			
			p2 = new JPanel();
			this.add(p2, BorderLayout.CENTER);
			l2 = new JLabel(String.valueOf(GetRisultato.valutaEspressione()));
			l2.setFont(new Font("Helvetica", Font.BOLD, 14));
			l2.setForeground(Color.BLUE);
			p2.add(l2, BorderLayout.CENTER);
			
			p3 = new JPanel();
			this.add(p3, BorderLayout.SOUTH);
			ok = new JButton();
			ok.setText("OK");
			p3.add(ok, BorderLayout.CENTER);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == ok) {
						ValutaEspressione.f.setVisible(false);
						CalcolaEspressione.tf.setText("");
						CalcolaEspressione.f.setVisible(true);
					}//if
				}//actionPerformed
			});
		}
	}//ValutaEspressione
	
	@SuppressWarnings("serial")
	protected static class ExprMalformata extends JFrame {
		protected static JFrame f;
		protected static JPanel p1, p2;
		protected static JLabel l;
		protected static JButton ok;
		
		public ExprMalformata() {
			f = this;
			this.setTitle("ERROR");
			this.setSize(350, 150);
			this.setLocation(575, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					ExprMalformata.f.setVisible(false);
					CalcolaEspressione.tf.setText("");
				}//windowClosing
			});
			
			p1 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			l = new JLabel("ATTENZIONE! Espressione malformata!");
			l.setFont(new Font("Helvetica", Font.HANGING_BASELINE, 14));
			l.setForeground(Color.BLUE);
			p1.add(l, BorderLayout.CENTER);
			
			p2 = new JPanel();
			this.add(p2, BorderLayout.CENTER);
			ok = new JButton();
			ok.setText("OK");
			p2.add(ok, BorderLayout.CENTER);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == ok) {
						ExprMalformata.f.setVisible(false);
						ApplicationEspressione.CalcolaEspressione.tf.setText("");
					}//if
				}//actionPerformed
			});
		}
	}//ExprMalformata
	
}//ApplicationEspressione
