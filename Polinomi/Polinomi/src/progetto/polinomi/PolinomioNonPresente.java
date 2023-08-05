package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PolinomioNonPresente extends JFrame{

	protected JFrame s;
	protected JPanel q1, q2;
	protected JLabel testo;
	protected JButton ok;
	
	public PolinomioNonPresente() {
		
		s=this;
		this.setTitle("Errore");
		this.setSize(400, 100);
		this.setLocation(535, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		q1 = new JPanel();
		q2 = new JPanel();
		this.add(q1, BorderLayout.NORTH);
		this.add(q2, BorderLayout.SOUTH);
		testo = new JLabel("Questo polinomio non è presente!");
		q1.add(testo, BorderLayout.CENTER);
		ok = new JButton();
		ok.setText("OK");
		q2.add(ok, BorderLayout.CENTER);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(false);
			}//actionPerformed
		});
		
	}
	
}//PolinomioNonPresente
