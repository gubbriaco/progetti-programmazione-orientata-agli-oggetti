package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;
import poo.polinomi.PolinomioSet;


@SuppressWarnings("serial")
public class ScegliTipologia extends JFrame{
	
	private JPanel p1, p2, p3;
	private JCheckBox cb1, cb2;
	private JLabel l;
	private JButton b;
	private static JFrame f;
	
	protected static Polinomio polinomio;
	public static String p;
	

	class NessunaTipologiaSelezionata extends JFrame {
		JFrame n;
		JPanel p1, p2;
		JLabel l;
		JButton b;
		
		public NessunaTipologiaSelezionata() {
			n = this;
			this.setTitle("Errore");
			this.setLocation(535, 300);
			this.setSize(400, 100);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			p1 = new JPanel();
			p2 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			this.add(p2, BorderLayout.SOUTH);
			l = new JLabel("Nessuna tipologia selezionata!");
			p1.add(l, BorderLayout.CENTER);
			b = new JButton();
			b.setText("OK");
			p2.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						n.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
	}//NessunaTipologiaSelezionata
	
	public ScegliTipologia() {
		
		f = this;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame q = new NessunaTipologiaSelezionata();
				f.setVisible(false);
				q.setVisible(true);
			}//windowClosing
		});
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		this.setTitle("Check Box");
		this.setSize(600, 130);
		this.setLocation(450, 300);
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
		this.add(p3, BorderLayout.SOUTH);
		
		l = new JLabel();
		l.setText("Seleziona una delle seguenti tipologie");
		p1.add(l, BorderLayout.CENTER);
		
		b = new JButton();
		b.setText("Conferma");
		b.setSize(300, 200);
		p3.add(b, BorderLayout.CENTER);
		
		cb1 = new JCheckBox("PolinomioLL", false);
		p2.add(cb1, BorderLayout.WEST);
		cb2 = new JCheckBox("PolinomioSet", true);
		p2.add(cb2, BorderLayout.CENTER);
		
		
		
		cb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cb2.setSelected(false);
			}//actionPerformed
		});
		cb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cb1.setSelected(false);
			}//actionPerformed
			
		});
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == b) {
					if(confermaTipologia()) {
						if(cb1.isSelected()) {
							polinomio = new PolinomioLL();
							p = "PolinomioLL";
							avviaMenu();
							Application.Finestra.nuova.setEnabled(false);
						}//if
						if(cb2.isSelected()) {
							polinomio = new PolinomioSet();
							p = "PolinomioSet";
							avviaMenu();
							Application.Finestra.nuova.setEnabled(false);
						}//if
						f.setVisible(false);
					}//if	
				}//if
			}//actionPerformed
		});
	}
	
	private boolean confermaTipologia() {
		if( cb1.isSelected() || cb2.isSelected() )
			return true;
		return false;
	}//confermaTipologia
	
	private void avviaMenu() {
		Application.Finestra.apri.setEnabled(true);
		Application.Finestra.salva.setEnabled(true);
		Application.Finestra.salvaConNome.setEnabled(true);
		Application.Finestra.aggiungiPolinomio.setEnabled(true);
	}//avviaMenu
	
}//ScegliTipologia
