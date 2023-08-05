package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AnnullareOperazioneAggiuntaPolinomio extends JFrame {
	
	protected static JFrame f;
	protected static JPanel p1, p2;
	protected static JButton b1, b2;
	protected static JLabel l;

	public AnnullareOperazioneAggiuntaPolinomio() {
	
		f = this;
		f.setTitle("ESC");
		f.setSize(600, 130);
		f.setLocation(450, 300);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		p1 = new JPanel();
		p2 = new JPanel();
		f.add(p1, BorderLayout.NORTH);
		f.add(p2, BorderLayout.SOUTH);
		
		b1 = new JButton();
		b2 = new JButton();
		
		b1.setText("NO");
		b1.setSize(300, 200);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == b1) {
					f.setVisible(false);
					AggiungiPolinomio.f.setVisible(true);
				}//if
			}//actionPerformed
		});
		
		b2.setText("SI");
		b2.setSize(300, 200);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == b2) {
					f.setVisible(false);
					AggiungiPolinomio.f.setVisible(false);
				}//if
			}//actionPerformed
		});
		
		l = new JLabel("Sei sicuro di voler annullare l'operazione?");
		p1.add(l);
		p2.add(b1, BorderLayout.WEST);
		p2.add(b2, BorderLayout.EAST);
		f.setVisible(true);
		
	}
	
}//AnnullareOperazione
