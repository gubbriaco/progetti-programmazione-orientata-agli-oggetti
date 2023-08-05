package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AggiuntaPolinomio implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == AggiungiPolinomio.b2) {
			String s = AggiungiPolinomio.tf.getText();
			if(IsPolinomio.riconosciPolinomio(s) && !polinomioNonPresente(s)) {
				PannelloPolinomi.llP.add(IsPolinomio.creaPolinomio(s));
				JCheckBox cb = new JCheckBox();
				Font f = new Font("Helvetica", Font.ITALIC, 55);
				cb.setText(s);
				cb.setFont(f);
				PannelloPolinomi.llCB.add(cb);
				PannelloPolinomi.box.add(cb);
				cb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == cb) {
							PannelloOperazioni.p.setVisible(true);
						}//if
					}//actionPerformed
				});
				for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
					if(PannelloPolinomi.llCB.get(i).isSelected())
						PannelloPolinomi.llCB.get(i).setSelected(false);
				}//for
				PannelloPolinomi.p.revalidate();
				AggiungiPolinomio.f.setVisible(false);
			}//if
			else if(polinomioNonPresente(s)) {
				JFrame t = new PolinomioGiaEsistente();
				t.setVisible(true);
			}//else if
			else if(!IsPolinomio.riconosciPolinomio(s)){
				JFrame q = new ErrorePattern();
				q.setVisible(true);
			}//else if
		}//if
	}//actionPerformed
	
	protected static boolean polinomioNonPresente(String s) {
		for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
			if(PannelloPolinomi.llCB.get(i).getText().equalsIgnoreCase(s))
				return true;
		}//for
		return false;
	}//polinomioNonPresente
	
	
	
	@SuppressWarnings("serial")
	protected static class PolinomioGiaEsistente extends JFrame {
		
		private JFrame f;
		private JPanel p1, p2;
		private JLabel l;
		private JButton b;
		
		public PolinomioGiaEsistente() {
			f = this;
			this.setTitle("Errore");
			this.setSize(400, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					f.setVisible(false);
					AggiungiPolinomio.tf.setText("");
				}//windowClosing
			});
			p1 = new JPanel();
			p2 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			this.add(p2, BorderLayout.CENTER);
			l = new JLabel("Attenzione! Polinomio già esistente!");
			p1.add(l, BorderLayout.CENTER);
			b = new JButton();
			b.setText("OK");
			p2.add(b, BorderLayout.CENTER);
			
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
						AggiungiPolinomio.tf.setText("");
					}//if
				}//actionPerformed
			});
		}
		
	}//PolinomioGiaPresente
	
	
	@SuppressWarnings("serial")
	protected static class ErrorePattern extends JFrame {
		
		private JFrame f;
		private JPanel p1, p2;
		private JLabel l;
		private JButton b;
		
		public ErrorePattern() {
			f = this;
			this.setTitle("Errore pattern");
			this.setSize(400, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					f.setVisible(false);
					AggiungiPolinomio.tf.setText("");
				}//windowClosing
			});
			p1 = new JPanel();
			p2 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			this.add(p2, BorderLayout.CENTER);
			l = new JLabel("Attenzione! Polinomio non valido!");
			p1.add(l, BorderLayout.CENTER);
			b = new JButton();
			b.setText("OK");
			p2.add(b, BorderLayout.CENTER);
			
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
						AggiungiPolinomio.tf.setText("");
					}//if
				}//actionPerformed
			});	
		}	
	}//ErrorePattern
}//AggiuntaPolinomio
